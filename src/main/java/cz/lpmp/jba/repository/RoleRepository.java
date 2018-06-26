package cz.lpmp.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.lpmp.jba.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String rolename);


}
