package cz.lpmp.jba.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cz.lpmp.jba.entity.Comments;
import cz.lpmp.jba.entity.Post;

public interface CommentsRepository extends JpaRepository<Comments, Integer>{

	List<Comments> findByPost(Post post,Pageable pageable);

}
