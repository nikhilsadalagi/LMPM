package cz.lpmp.jba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.lpmp.jba.entity.Role;
import cz.lpmp.jba.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public void save(List<Role> roles) {
		roleRepository.save(roles);		
	}
	
}
