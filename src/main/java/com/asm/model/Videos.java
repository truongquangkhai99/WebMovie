package com.asm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Videos.mostLikeVideo", 
			procedureName = "spMostLikeVideo"
	),
	@NamedStoredProcedureQuery(name = "Videos.mostShareVideo",
		procedureName = "spMostShareVideo"
	),
	@NamedStoredProcedureQuery(name = "Videos.mostViewVideo",
		procedureName = "spMostViewVideo"
	)
})

@Entity
@Table(name="videos")
public class Videos implements Serializable{

	@Id
	String id;
	@Column(name = "title")
	String title;
	@Column(name = "poster")
	String poster;
	@Column(name = "views")
	int views;
	@Column(name = "description")
	String description;
	@Column(name = "active")
	boolean active;
	@OneToMany(mappedBy = "video")
	List<Share> shares;
	
	@OneToMany(mappedBy = "video")
	List<Favorites> favorites;
	
	
	public List<Favorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorites> favorites) {
		this.favorites = favorites;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public Videos(String id, String title, String poster, int views, String description, boolean active) {
		this.id = id;
		this.title = title;
		this.poster = poster;
		this.views = views;
		this.description = description;
		this.active = active;
	}
	
	public Videos() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	

}
