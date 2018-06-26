package cz.lpmp.jba.entity;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import cz.lpmp.jba.annotation.UniqueUsername;


@Entity
public class AppUser {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String[] role1;
		
	@Size(min=3,message="Name must be atleast 3 characters!")
	@Column(unique=true)
	@UniqueUsername(message="Such username already exists!")
	private String name;
	
	@Size(min=3,message="USN must be atleast 3 characters!")
	@Column(unique=true)
	private String usn;

	private Date joindate;
	private Date lastlogin;
	@Size(min=1,message="Invalid Email address")
	@Email(message="Invalid Email address")
	private String email;
	
	@Size(min=5,message="Password must be atleast 5 characters!")
	private String password;
	private boolean enabled;
	
	@ManyToMany()
	@JoinTable
	private List<Role> roles;
	
	@OneToMany(mappedBy="appUser",cascade=CascadeType.REMOVE)
	private List<Post> posts;
	
	@OneToMany(mappedBy="appUser",cascade=CascadeType.REMOVE)
	private List<Comments> comments;
	
	private File image;
	
	//@ManyToMany()
	//@JoinTable
	//private List<Post> subscribedPosts;
	
	public String[] getRole1() {
		return role1;
	}
	public void setRole1(String[] role1) {
		this.role1 = role1;
	}
	/*public List<Post> getSubscribedPosts() {
		return subscribedPosts;
	}
	public void setSubscribedPosts(List<Post> subscribedPosts) {
		this.subscribedPosts = subscribedPosts;
	}*/
	
	
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getUsn() {
		return usn;
	}
	public void setUsn(String usn) {
		this.usn = usn;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
