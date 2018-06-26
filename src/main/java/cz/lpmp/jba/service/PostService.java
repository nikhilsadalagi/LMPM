package cz.lpmp.jba.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import cz.lpmp.jba.entity.AppUser;
import cz.lpmp.jba.entity.Comments;
import cz.lpmp.jba.entity.Media;
import cz.lpmp.jba.entity.Post;
import cz.lpmp.jba.repository.AppUserRepository;
import cz.lpmp.jba.repository.CommentsRepository;
import cz.lpmp.jba.repository.MediaRepository;
import cz.lpmp.jba.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	public void save(Post post,String name){
		AppUser user = appUserRepository.findByName(name);
		post.setAppUser(user);
		post.setPosteddate(new Date());
		post.setCreatedate(new Date());
		postRepository.save(post);
	}

	public void delete(int id) {
		postRepository.delete(id);
	}

	public Post findOne(int id) {
		return postRepository.findOne(id);
	}
	
	@PreAuthorize("#post.appUser.name==authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("post")Post post) {
		postRepository.delete(post	);
	}

	public Post findThePost(int id) {
		Post post=postRepository.findOne(id);
		return post;
	}

	public Post getPost(int id) {
		Post post=postRepository.findOne(id);
		return post;
	}

	public List<Post> findAllPosts() {
		List<Post> posts=postRepository.findAll();
		for (Post post : posts) {
			List<Media> medias = mediaRepository.findByPost(post ,new PageRequest(0, 20, Direction.DESC, "publisheddate"));
			post.setMedias(medias);
			List<Comments> comments = commentsRepository.findByPost(post,new PageRequest(0, 40, Direction.DESC, "commentdate"));
			post.setComments(comments);
		}
		return posts;
	}
	
	public List<Post> findSubscribedPosts(AppUser user) {
		List<Post> posts=postRepository.findByAppUser(user);
		for (Post post : posts) {
			List<Media> medias = mediaRepository.findByPost(post ,new PageRequest(0, 20, Direction.DESC, "publisheddate"));
			post.setMedias(medias);
			List<Comments> comments = commentsRepository.findByPost(post,new PageRequest(0, 40, Direction.DESC, "commentdate"));
			post.setComments(comments);
		}
		return posts;
	}


}
