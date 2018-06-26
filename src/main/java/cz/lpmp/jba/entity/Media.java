package cz.lpmp.jba.entity;

import java.io.File;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Media {
	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private File file;
	private String fileURI;
	private String description;
	
	@OneToMany(mappedBy="media")
	private List<Comments> comments;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
	private Date publisheddate;
	

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public Date getPublisheddate() {
		return publisheddate;
	}
	public void setPublisheddate(Date publisheddate) {
		this.publisheddate = publisheddate;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileURI() {
		return fileURI;
	}
	public void setFileURI(String fileURI) {
		this.fileURI = fileURI;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
