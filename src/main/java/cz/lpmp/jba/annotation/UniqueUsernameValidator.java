package cz.lpmp.jba.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cz.lpmp.jba.annotation.UniqueUsername;
import cz.lpmp.jba.repository.AppUserRepository;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private AppUserRepository userRepository;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userRepository==null){
			return true;
		}else{
			return userRepository.findByName(username)==null;
		}
	}

	
}