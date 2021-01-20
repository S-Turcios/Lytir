package com.santos.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.santos.models.Comments;
import com.santos.models.Posts;
import com.santos.models.States;
import com.santos.models.User;
import com.santos.repos.CommentRepository;
import com.santos.services.CommentServices;
import com.santos.services.PostService;
import com.santos.services.StateServices;
import com.santos.services.UserService;
import com.santos.validator.UserValidator;
@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private StateServices stateService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentServices commentService;
	@Autowired
	private UserValidator userValidator;
  	@RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registerPage.jsp";
    }
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Validated @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	// if result has errors, return the registration page (don't worry about validations just now)
    	  userValidator.validate(user, result);
          if(result.hasErrors()) {
              return "registerPage.jsp";
          }
          User u = userService.registerUser(user);
          session.setAttribute("user", u.getId());
          return "redirect:/states"; 
    }
    @RequestMapping("/")
    public String login( HttpSession session, Model model) {
    	model.addAttribute("error", session.getAttribute("error"));
    	return "loginPage.jsp";
    }
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
    		if(userService.authenticateUser(email, password)){
    		 User user = userService.findByEmail(email);
    		 session.setAttribute("user", user.getId());
    		 return "redirect:/states";
    		}
    		session.setAttribute("error", "Email/Password is incorrect");
    		return "redirect:/";
        // else, add error messages and return the login page
    }
    @RequestMapping("/states")
    public String cosntinent(HttpSession session, Model model) {
    	Object s = stateService.allStates();
    	Long id = (Long) session.getAttribute("user");
    	User loggedUser = userService.findUserById(id);
    	if(loggedUser != null) {
    		model.addAttribute("user", loggedUser);
    		model.addAttribute("states", s);
    		return "states.jsp";
    	}else
    		return "redirect:/login";
    }
    @RequestMapping("/state/{id}")
    public String showState(@PathVariable("id")Long id, Model model, HttpSession session, @ModelAttribute("post") Posts post, @ModelAttribute("comment") Comments comment ) {
    	States s = stateService.findStatesById(id);
    	session.setAttribute("state", s);
    	List<Comments> allComments = commentService.findAll();
    	List<Posts> allPost = postService.findAllPost();
    	Long user = (Long) session.getAttribute("user");
    	User loggedUser = userService.findUserById(user);
    	model.addAttribute("state" , s);
    	model.addAttribute("user" , loggedUser);
    	model.addAttribute("allPost", allPost);
    	model.addAttribute("comments", allComments);
    	return "showState.jsp";
    }
    @RequestMapping(value="/create/post/{state_id}", method=RequestMethod.POST)
    public String createPost(@Validated @ModelAttribute("post") Posts post, BindingResult result,@PathVariable("state_id") Long s_id, HttpSession session, Model model) {
    	States s = stateService.findStatesById(s_id);
    	session.setAttribute("state", s);
    	Long user_id = (Long) session.getAttribute("user");
    	List<Posts> allPost = postService.findAllPost();
    	User loggedUser = userService.findUserById(user_id);
    	if(result.hasErrors()) {
    		model.addAttribute("allPost", allPost);
    		model.addAttribute("state", s);
    		model.addAttribute("user",loggedUser);
    		return "showState.jsp";
    	}else {
    		postService.createPost(post);
    		return "redirect:/state/"+s_id;
    }
    }
    @RequestMapping(value="/comment/{state_id}", method=RequestMethod.POST)
    public String createComment(@Validated @ModelAttribute("comment") Comments comment,@PathVariable("state_id")Long id, BindingResult result) {
    	if(result.hasErrors()) {
    		return "redirect:/state/"+id;
    	}else {
    		commentService.createComment(comment);
    		return "redirect:/state/"+id;    	}
    		
    }
    @RequestMapping(value="/delete/{id}/{state_id}", method=RequestMethod.DELETE)
    public String deletePost(@PathVariable("id")Long id, @PathVariable("state_id") Long s_id) {
    	postService.deletePost(id);
    	return "redirect:/state/" + s_id;
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/";
    }
}
