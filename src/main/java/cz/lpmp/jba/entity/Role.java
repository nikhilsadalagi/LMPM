package cz.lpmp.jba.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private List<AppUser> appUsers;
	
	public List<AppUser> getAppUsers() {
		return appUsers;
	}
	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
