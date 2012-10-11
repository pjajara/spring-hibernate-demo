/**
 * 
 */
package com.emc.xcp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.emc.xcp.dao.UserDao;
import com.emc.xcp.domain.User;

/**
 * @author jajarp
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private View jsonView;
	
	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("userId") String userId){
		User user = null;
		try {
			user = userDao.getUserById(userId);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView(jsonView,DATA_FIELD,user);
	}

	@RequestMapping(value="/users/", method=RequestMethod.GET)
	public ModelAndView getUsers(){
		List<User> users=null;
		try{
			users=userDao.getAllUsers();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView(jsonView,DATA_FIELD,users);
	}
	
	@RequestMapping(value="/users/", method=RequestMethod.POST)
	public ModelAndView createUser(@RequestBody User user, HttpServletResponse httpServletResponse, WebRequest webRequest){
		User createdUser;
		String createdUserId=null;
		
		try{
			createdUserId = userDao.saveUser(user);
		}
		catch(Exception e){
			System.out.println(e);
		}
		createdUser=userDao.getUserById(createdUserId);
		httpServletResponse.setStatus(HttpStatus.CREATED.value());
		httpServletResponse.setHeader("Location", webRequest.getContextPath()+"/users/"+user.getId());
		return new ModelAndView(jsonView, DATA_FIELD,createdUser);
		
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.PUT)
	public ModelAndView updateUser(@RequestBody User user, @PathVariable("userId") String userId, HttpServletResponse httpServletResponse){
//		User userToUpdate = userDao.getUserById(userId);
		user.setId(userId);
		userDao.updateUser(user);
		httpServletResponse.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView,DATA_FIELD,user);
		//return null;
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
	public ModelAndView deleteUser(@PathVariable("userId") String userId, HttpServletResponse httpServletResponse){
		userDao.deleteUser(userId);
		httpServletResponse.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView, DATA_FIELD,null);
	}
	
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	public void setJsonView(View view){
		this.jsonView=view;
	}
	
}
