package cz.lpmp.jba.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.lpmp.jba.entity.AppUser;
import cz.lpmp.jba.entity.Post;
import cz.lpmp.jba.entity.Role;
import cz.lpmp.jba.repository.AppUserRepository;
import cz.lpmp.jba.repository.RoleRepository;
import cz.lpmp.jba.service.PostService;
import cz.lpmp.jba.service.RoleService;
import cz.lpmp.jba.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;  
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PostService postService;
	

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	
	@ModelAttribute("user")
	public AppUser constructUser(){
		return new AppUser();
	}
	
	@ModelAttribute("post")
	public Post constructPost(){
		return new Post();
	}
	
	@RequestMapping("/users")
	public String users(Model model){
		model.addAttribute("users",userService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{id}")
	public String userdetail(Model model,@PathVariable int id){
		model.addAttribute("user",userService.findOneWithPosts(id));
		return "userdetail";
	}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "user-register";
	}
	
	@Transactional
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") AppUser user,BindingResult result,HttpServletRequest request){
		//int x=0;
		Role role=new Role();
		role.setName(request.getParameter("role1"));
		List<Role> rolez=new ArrayList<Role>();
		/*for (String rol : user.getRole1()) {
			role.setName(rol);
			rolez.add(role);
			x=1;
		}*//*
		if(x!=0){
			Role r3=new Role();
			r3.setName("ROLE_STUDENT");
			rolez.add(r3);
		}*/
		rolez.add(role);
		user.setRoles(rolez);
		if(result.hasErrors()){
			return "user-register";
		}
		
		userService.saves(user,role.getName());
		return "redirect:/login.html?success=true";
		//return "redirect:/register.html?success=true";
	}

	@RequestMapping("/account")
	public String account(Model model,Principal principal){
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithPosts(name));
		return "userdetail";
	}

	@RequestMapping("/taccount")
	public String taccount(Model model,Principal principal){
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithPosts(name));
		return "userdetail";
	}

	@RequestMapping(value="/account",method=RequestMethod.POST)
	public String doAddPost(Model model,@Valid @ModelAttribute("post") Post post,BindingResult result,Principal principal){
		if(result.hasErrors()){
			return account(model,principal);
		}
		String name = principal.getName();
		postService.save(post, name);
		return "redirect:/account.html";
	}
	
	@RequestMapping("/post/remove/{id}")
	public String removePost(@PathVariable int id){
		Post post=postService.findOne(id);
		postService.delete(post);
		return "redirect:/account.html";
	}
	
	@RequestMapping("/users/remove/{id}")
	public String removeUser(@PathVariable int id){
		userService.delete(id);
		return "redirect:/users.html";
	}
	
	@RequestMapping("/register/available")
	@ResponseBody
	public String available(@RequestParam String username){
		Boolean available = userService.findOne(username)==null;
		return available.toString();
	}
	@Transactional
	@RequestMapping("/updateMyDetails/{id}")
	public String updateMyDetails(@PathVariable int id,BindingResult result,HttpServletRequest request){
		AppUser user=new AppUser();
		user=userService.findOne(id);
		user.setId(id);
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setUsn(request.getParameter("usn"));
		appUserRepository.save(user);
		return "redirect:/logout";
	}
}
