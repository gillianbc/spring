package org.gillianbc.messenger.model;

import java.util.Date;

public class Comment {
	private long id;
	private String commentText;
	private Date created;
	private String author;
	
	public Comment() {
		//No args constructor for JSON/XML conversion by JAX-RS
	}
	
	public Comment(long id, String commentText, String author) {
		this.id = id;
		this.commentText = commentText;
		this.author = author;
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
