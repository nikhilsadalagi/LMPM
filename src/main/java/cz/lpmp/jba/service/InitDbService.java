package cz.lpmp.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cz.lpmp.jba.entity.AppUser;
import cz.lpmp.jba.entity.Comments;
import cz.lpmp.jba.entity.Media;
import cz.lpmp.jba.entity.Post;
import cz.lpmp.jba.entity.Role;
import cz.lpmp.jba.repository.AppUserRepository;
import cz.lpmp.jba.repository.CommentsRepository;
import cz.lpmp.jba.repository.MediaRepository;
import cz.lpmp.jba.repository.PostRepository;
import cz.lpmp.jba.repository.RoleRepository;

@Transactional
@Service
public class InitDbService {
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void init(){

			if(roleRepository.findByName("ROLE_ADMIN")==null){
			Role rolestudent=new Role();
			rolestudent.setName("ROLE_STUDENT");
			roleRepository.save(rolestudent);
			
			Role roleteacher=new Role();
			roleteacher.setName("ROLE_TEACHER");
			roleRepository.save(roleteacher);
			
			Role roleadmin=new Role();
			roleadmin.setName("ROLE_ADMIN");
			roleRepository.save(roleadmin);
	
			AppUser useradmin=new AppUser();
			useradmin.setName("admin");
			useradmin.setUsn("admin");
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	
			useradmin.setPassword(encoder.encode("123"));
			useradmin.setJoindate(new Date());
			useradmin.setLastlogin(new Date());
			useradmin.setEmail("nikhil.sadalagi@gmail.com");
			List<Role> roles=new ArrayList<Role>();
			roles.add(rolestudent);
			roles.add(roleteacher);
			roles.add(roleadmin);
			useradmin.setRoles(roles);
			useradmin.setEnabled(true);
			appUserRepository.save(useradmin);
			
	
			Post post1=new Post();
			post1.setAppUser(useradmin);
			post1.setPosteddate(new Date());
			post1.setTitle("Computer Networks");
			post1.setContent("A computer network or data network is a telecommunications network that allows computers to exchange data.");
			post1.setCreatedate(new Date());
			postRepository.save(post1);
	
	
	
			Comments comment0= new Comments();
			comment0.setCommentdate(new Date());
			comment0.setContent("have a nice day");
			comment0.setAppUser(useradmin);
			comment0.setPost(post1);
			commentsRepository.save(comment0);
			
			Comments comment1= new Comments();
			comment1.setCommentdate(new Date());
			comment1.setContent("ok,this is understandable");
			comment1.setAppUser(useradmin);
			comment1.setPost(post1);
			commentsRepository.save(comment1);
			
			Comments comment2= new Comments();
			comment2.setCommentdate(new Date());
			comment2.setContent("good,it is very helpful");
			comment2.setAppUser(useradmin);
			comment2.setPost(post1);
			commentsRepository.save(comment2);
			
			
			
			AppUser teacher=new AppUser();
			teacher.setName("neelam");
			teacher.setUsn("neelam");
			BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
	
			teacher.setPassword(encode.encode("123"));
			teacher.setJoindate(new Date());
			teacher.setLastlogin(new Date());
			teacher.setEmail("neelam.bhawane@gmail.com");
			List<Role> rols=new ArrayList<Role>();
			rols.add(roleteacher);
			teacher.setRoles(rols);
			teacher.setEnabled(true);
			appUserRepository.save(teacher);
			
			
			
			Post post2=new Post();
			post2.setPosteddate(new Date());
			post2.setTitle("DBMS");
			post2.setContent("A database is an organized collection of data.");
			post2.setCreatedate(new Date());
			postRepository.save(post2);
			
			Media media1=new Media();
			media1.setPost(post1);
			media1.setPublisheddate(new Date());
			media1.setFileURI("file URI");
			media1.setTitle("media1 title");
			media1.setDescription("media1 description");
			mediaRepository.save(media1);
					
			Media media2=new Media();
			media2.setPost(post1);
			media2.setPublisheddate(new Date());
			media2.setFileURI("file URI");
			media2.setTitle("media2 title");
			media2.setDescription("media2 description");
			mediaRepository.save(media2);
			
			Media media3=new Media();
			media3.setPost(post2);
			media3.setPublisheddate(new Date());
			media3.setFileURI("file URI");
			media3.setTitle("media3 title");
			media3.setDescription("media3 description");
			mediaRepository.save(media3);
			
			AppUser student=new AppUser();
			student.setName("nikhil");
			student.setUsn("1PT12MCA11");
			//BCryptPasswordEncoder encod2=new BCryptPasswordEncoder();
			
			List<Post> subscribedPosts=new ArrayList<Post>();
			subscribedPosts.add(post2);
			subscribedPosts.add(post1);
			
			student.setPassword(encode.encode("123"));
			student.setJoindate(new Date());
			student.setLastlogin(new Date());
			student.setEmail("nikhil.sadalagi@gmail.com");
			List<Role> srols=new ArrayList<Role>();
			srols.add(rolestudent);
			student.setRoles(srols);
			//student.setSubscribedPosts(subscribedPosts);
			student.setEnabled(true);
			appUserRepository.save(student);
		}
	}
}
