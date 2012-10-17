/**
 * 
 */
package com.emc.xcp.controller;

import java.util.List;
import com.emc.xcp.utils.*;
import com.emc.xcp.domain.Customer;
import com.emc.xcp.dao.CustomerDao;
import com.emc.xcp.dao.impl.CustomerDaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jajarp
 *
 */

@Controller
public class CustomerController {

	@Autowired
	private CustomerDao customerDAO;
	
	@Autowired
	private CustomerFormValidator customerFormValidator;
	
	@RequestMapping("/searchCustomersFirstname")
	public ModelAndView searchCustomersByFirstName(@RequestParam(required=false, defaultValue="") String firstName ){
		ModelAndView mav = new ModelAndView("showCustomers");
		List<Customer> customers = customerDAO.searchCustomersByFirstName(firstName);
		mav.addObject("SEARCH_CUSTOMERS_RESULTS_KEY", customers);
		return mav;
	}
	
	@RequestMapping("/searchCustomersLastname")
	public ModelAndView searchCustomersByLastName(@RequestParam(required=false, defaultValue="") String lastName ){
		ModelAndView mav = new ModelAndView("showCustomers");
		List<Customer> customers = customerDAO.searchCustomerByLastName(lastName);
		mav.addObject("SEARCH_CUSTOMERS_RESULTS_KEY", customers);
		return mav;
	}
	
	@RequestMapping("/viewAllCustomers")
	public ModelAndView getAllCustomers(){
		ModelAndView mav = new ModelAndView("showCustomers");
		List<Customer> customers = customerDAO.getAllCustomers();
		mav.addObject("SEARCH_CUSTOMERS_RESULTS_KEY", customers);
		return mav;
	}

	@RequestMapping(value="/saveCustomer", method= RequestMethod.GET)
	public ModelAndView newCustomerRegistration(){
		ModelAndView mav = new ModelAndView("newCustomer");
		Customer customer = new Customer();
		mav.getModelMap().put("newCustomer", customer);
		return mav;
	}
	
	@RequestMapping(value="/saveCustomer", method=RequestMethod.POST)
	public String createCustomer(@ModelAttribute("newCustomer") Customer customer, BindingResult result, SessionStatus status){
		customerFormValidator.validate(customer,result);
		if(result.hasErrors()){
			return "newCustomer";
		}
		customerDAO.save(customer);
		status.setComplete();
		return "redirect:viewAllCustomers.do";
	}
	
	@RequestMapping(value="/updateCustomer", method=RequestMethod.GET)
	public ModelAndView editCustomer(@RequestParam("id") Integer id){
		ModelAndView mav = new ModelAndView("editCustomer");
		Customer customer = customerDAO.getById(id);
		mav.addObject("editCustomer", customer);
		return mav;
	}
	
	@RequestMapping(value="/updateCustomer", method=RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("editCustomer") Customer customer, BindingResult result, SessionStatus status){
		customerFormValidator.validate(customer, result);
		if(result.hasErrors()){
			return "editCustomer";
		}
		customerDAO.update(customer);
		status.setComplete();
		return "redirect:viewAllCustomers.do";
	}
	
	@RequestMapping(value="/deleteCustomer")
	public ModelAndView deleteCustomer(@RequestParam("id") Integer id){
		ModelAndView mav = new ModelAndView("redirect:viewAllCustomers.do");
		customerDAO.delete(id);
		return mav;
	}
	
}
