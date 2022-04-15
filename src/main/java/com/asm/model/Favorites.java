package com.asm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "favorites", uniqueConstraints= {
		@UniqueConstraint(columnNames= {
				"videoId", "userId"
		})
})
public class Favorites implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne @JoinColumn(name = "userId")
	Users user;
	@ManyToOne @JoinColumn(name ="videoId")
	Videos video;
	@Temporal(TemporalType.DATE)
	Date likeDate = new Date();
	
	public Favorites() {
		super();
	}
	public Favorites(Users user, Videos video, Date likeDate) {
		this.user = user;
		this.video = video;
		this.likeDate = likeDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Videos getVideo() {
		return video;
	}
	public void setVideo(Videos video) {
		this.video = video;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	

}
