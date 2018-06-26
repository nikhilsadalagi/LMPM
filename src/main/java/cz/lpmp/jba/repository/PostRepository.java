package cz.lpmp.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import cz.lpmp.jba.entity.AppUser;
import cz.lpmp.jba.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByAppUser(AppUser appUser);
    /*@Query("select appuser.name, role.name from appuser	join appuser_role on appuser.id = appuser_role.appusers_id join role on appuser_role.roles_id = role.id	where appuser.name = (:id)")
    public List<Post> findByIdAndFetchPostsEagerly(@Param("id") Integer id);*/
}
