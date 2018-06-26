package cz.lpmp.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.lpmp.jba.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer>{

	AppUser findByName(String name);

}
