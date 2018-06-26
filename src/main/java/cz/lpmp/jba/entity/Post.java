package cz.lpmp.jba.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;


@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer appuserid;
	@Size(min=1,message="Title must be atleast ten charateristics")
	private String title;
	@Size(min=1,message="Description must be atleast ten charateristics")
	private String content;
	private Date createdate;
	private Date posteddate;
	@ManyToOne
	@JoinColumn(name="user_id")
	private AppUser appUser;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.REMOVE)
	private List<Media> medias;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.REMOVE)
	private List<Comments> comments;
	
	/*@ManyToMany(mappedBy="subscribedPosts")
	private List<AppUser> subscribedAppUsers;
	
	public List<AppUser> getSubscribedAppUsers() {
		return subscribedAppUsers;
	}
	public void setSubscribedAppUsers(List<AppUser> subscribedAppUsers) {
		this.subscribedAppUsers = subscribedAppUsers;
	}*/
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public List<Media> getMedias() {
		return medias;
	}
	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAppuserid() {
		return appuserid;
	}
	public void setAppuserid(Integer appuserid) {
		this.appuserid = appuserid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getPosteddate() {
		return posteddate;
	}
	public void setPosteddate(Date posteddate) {
		this.posteddate = posteddate;
	}
	
}
