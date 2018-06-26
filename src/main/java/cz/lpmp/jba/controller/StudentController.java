package cz.lpmp.jba.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.lpmp.jba.service.PostService;
import cz.lpmp.jba.service.RoleService;
import cz.lpmp.jba.service.UserService;

@Controller
public class StudentController {
	@Autowired
	private UserService userService;  
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PostService postService;
	
	
	@RequestMapping("allsubject")
	public String allSubjects(Model model,Principal principal){
		model.addAttribute("posts",postService.findAllPosts());
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithPosts(name));
		return "allsub";
	}
}
