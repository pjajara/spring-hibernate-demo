/**
 * 
 */
package com.emc.xcp.controller;

/**
 * @author jajarp
 *
 */


import java.text.SimpleDateFormat;
import com.emc.xcp.utils.*;
import com.emc.xcp.domain.Contact;
import com.emc.xcp.dao.ContactDAO;
import com.emc.xcp.dao.impl.ContactDAOImpl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	@Autowired
	private ContactDAO contactDAO;

	@Autowired
	private Validator contactFormValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value="/searchContacts", method=RequestMethod.GET)
	public ModelAndView searchContacts(
			@RequestParam(required = false, defaultValue = "") String name) {
		ModelAndView mav = new ModelAndView("showContacts");
		List<Contact> contacts = contactDAO.searchContacts(name.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
		return mav;
	}

	@RequestMapping(value="/viewAllContacts", method = RequestMethod.GET)
	public ModelAndView getAllContacts() {
		ModelAndView mav = new ModelAndView("showContacts");
		List<Contact> contacts = contactDAO.getAllContacts();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
		return mav;
	}

	@RequestMapping(value = "/saveContact")
	public ModelAndView newuserForm() {
		ModelAndView mav = new ModelAndView("newContact");
		Contact contact = new Contact();
		mav.getModelMap().put("newContact", contact);
		return mav;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String create(@ModelAttribute("newContact") Contact contact,
			BindingResult result, SessionStatus status) {
		contactFormValidator.validate(contact, result);
		if (result.hasErrors()) {
			return "newContact";
		}
		contactDAO.save(contact);
		status.setComplete();
		return "redirect:viewAllContacts.do";
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("editContact");
		Contact contact = contactDAO.getById(id);
		mav.addObject("editContact", contact);
		return mav;
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.POST)
	public String update(@ModelAttribute("editContact") Contact contact,
			BindingResult result, SessionStatus status) {
		contactFormValidator.validate(contact, result);
		if (result.hasErrors()) {
			return "editContact";
		}
		contactDAO.update(contact);
		status.setComplete();
		return "redirect:viewAllContacts.do";
	}

	@RequestMapping(value= "deleteContact")
	public ModelAndView delete(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("redirect:viewAllContacts.do");
		contactDAO.delete(id);
		return mav;
	}
}
