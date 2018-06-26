package cz.lpmp.jba.controller;

import java.security.Principal;
import java.util.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.lpmp.jba.entity.Comments;
import cz.lpmp.jba.repository.AppUserRepository;
import cz.lpmp.jba.repository.CommentsRepository;
import cz.lpmp.jba.repository.MediaRepository;
import cz.lpmp.jba.service.PostService;
import cz.lpmp.jba.service.UserService;

@Controller
public class CommentController {
	@Autowired
	private UserService userService;  
	
	@Autowired
	PostService postService;
	
	@Autowired
	MediaRepository mediaRepository;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	private CommentsRepository commentRepository;
	
	/*@RequestMapping(value="/comment",method=RequestMethod.GET)
	public void addComment(){
		
	}*/ 

	
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public String addComment(Model model,Principal principal,@RequestParam("post") int pid,@RequestParam("user") int uid,@RequestParam("comment") String coment){
		Comments comment=new Comments();
		comment.setAppUser(appUserRepository.getOne(uid));
		comment.setCommentdate(new Date());
		comment.setPost(postService.findOne(pid));
		comment.setContent(coment.toString());
		commentRepository.save(comment);
		
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithPosts(name));
		return "redirect:taccount.html";
		//return "redirect:/account.html";
		//return "redirect:users/"+uid+".html";
	}
	
	@RequestMapping(value="/commentsub",method=RequestMethod.POST)
	public String addCommentsub(Model model,Principal principal,@RequestParam("post") int pid,@RequestParam("user") int uid,@RequestParam("comment") String coment){
		Comments comment=new Comments();
		comment.setAppUser(appUserRepository.getOne(uid));
		comment.setCommentdate(new Date());
		comment.setPost(postService.findOne(pid));
		comment.setContent(coment.toString());
		commentRepository.save(comment);
		
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithPosts(name));
		return "redirect:allsubject.html";
		//return "redirect:/account.html";
		//return "redirect:users/"+uid+".html";
	}
}
