package cz.lpmp.jba.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.lpmp.jba.entity.AppUser;
import cz.lpmp.jba.service.PostService;
import cz.lpmp.jba.service.RoleService;
import cz.lpmp.jba.service.UserService;

@Controller
public class SubscribedController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/subscribed")
	public String subscribed(Model model,Principal principal){
		String name =principal.getName();
		AppUser user=userService.findOne(name);
		//user.setSubscribedPosts(postService.findSubscribedPosts(user));
		model.addAttribute("posts",postService.findSubscribedPosts(user));
		model.addAttribute("user",userService.findOneWithPosts(name));
		return "subscribed";
	}
}
