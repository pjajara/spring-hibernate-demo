/**
 * 
 */
package com.emc.xcp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.emc.xcp.service.UserService;

/**
 * @author jajarp
 *
 */
@Controller
public class UserController {
	
	protected final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private View jsonView;
	
	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("userId") String userId){
		User user = null;
		try {
			user = userService.getUserById(userId);
		} catch (Exception e) {
			logger.debug("Exception while serving /users/" +userId + "/" + " GET request: " + e.toString()  );
		}
		return new ModelAndView(jsonView,DATA_FIELD,user);
	}

	@RequestMapping(value="/users/", method=RequestMethod.GET)
	public ModelAndView getUsers(){
		List<User> users=null;
		try{
			users=userService.getAllUsers();
		}
		catch(Exception e){
			logger.debug("Exception while serving /users/ GET request: "+ e.toString());
		}
		return new ModelAndView(jsonView,DATA_FIELD,users);
	}
	
	@RequestMapping(value="/users/", method=RequestMethod.POST)
	public ModelAndView createUser(@RequestBody User user, HttpServletResponse httpServletResponse, WebRequest webRequest){
		User createdUser;
		String createdUserId=null;
		
		try{
			createdUserId = userService.saveUser(user);
			logger.info("POST /users/ executed, Created User ID: " + createdUserId);
		}
		catch(Exception e){
			logger.debug("Exception occured while serving POST /users/" + createdUserId + "/" + e.toString() );
		}
		createdUser=userService.getUserById(createdUserId);
		httpServletResponse.setStatus(HttpStatus.CREATED.value());
		httpServletResponse.setHeader("Location", webRequest.getContextPath()+"/users/"+user.getId());
		return new ModelAndView(jsonView, DATA_FIELD,createdUser);
		
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.PUT)
	public ModelAndView updateUser(@RequestBody User user, @PathVariable("userId") String userId, HttpServletResponse httpServletResponse){
		user.setId(userId);
		userService.updateUser(user);
		httpServletResponse.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView,DATA_FIELD,user);
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
	public ModelAndView deleteUser(@PathVariable("userId") String userId, HttpServletResponse httpServletResponse){
		userService.deleteUser(userId);
		httpServletResponse.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView, DATA_FIELD,null);
	}
	
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	
	public void setJsonView(View view){
		this.jsonView=view;
	}
	
}
