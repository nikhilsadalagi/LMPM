package cz.lpmp.jba.repository;

import java.util.List;




import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cz.lpmp.jba.entity.Media;
import cz.lpmp.jba.entity.Post;

public interface MediaRepository extends JpaRepository<Media, Integer>{
	List<Media> findByPost(Post post,Pageable pageable);
}
