package com.asm.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.asm.model.Favorites;

public class FavoritesDAO extends AbstractEntityDAO<Favorites>{

	public FavoritesDAO() {
		super(Favorites.class);
	}
	
	public int findByUserAndVideo(String userId, String videoId) {
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery("select DISTINCT f from Favorites f where f.user.id like :userId and f.video.id like :videoId");
		q.setParameter("userId", userId);
		q.setParameter("videoId", videoId);
		return ((Favorites) q.getResultList().get(0)).getId();
	}
}
