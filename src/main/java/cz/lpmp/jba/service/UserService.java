package cz.lpmp.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

@Service
@Transactional
public class UserService {
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	public List<AppUser> findAll(){
		return appUserRepository.findAll();
		
	}

	public AppUser findOne(int id) {
		return appUserRepository.findOne(id);
	}

	@Transactional
	public AppUser findOneWithPosts(int id) {
		AppUser user=findOne(id);
		List<Post> posts= postRepository.findByAppUser(user);
		for (Post post : posts) {
			List<Media> medias = mediaRepository.findByPost(post ,new PageRequest(0, 10, Direction.DESC, "publisheddate"));
			post.setMedias(medias);
			List<Comments> comments = commentsRepository.findByPost(post,new PageRequest(0, 20, Direction.DESC, "commentdate"));
			post.setComments(comments);
		}
		user.setPosts(posts);
		return user;
	}

	public void save(AppUser user,String r) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles=new ArrayList<Role>();
		roles.add(roleRepository.findByName(r));
		user.setRoles(roles);
		
		appUserRepository.save(user);
	}
	
	public void saves(AppUser user,String rolez) {
		user.setEnabled(true);
		user.setJoindate(new Date());
		user.setLastlogin(new Date());
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles=new ArrayList<Role>();
		roles.add(roleRepository.findByName(rolez));
		user.setRoles(roles);
		
		appUserRepository.save(user);
	}

	public AppUser findOneWithPosts(String name) {
		AppUser user=appUserRepository.findByName(name);
		return findOneWithPosts(user.getId());
	}

	public void delete(int id) {
		appUserRepository.delete(id);
	}

	public AppUser findOne(String username) {
		return appUserRepository.findByName(username);
	}
	
}
