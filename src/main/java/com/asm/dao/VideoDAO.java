package com.asm.dao;

import com.asm.model.Videos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.asm.model.Users;
import com.asm.model.Videos;

public class VideoDAO extends AbstractEntityDAO<Videos>{
	
	public VideoDAO() {
		super(Videos.class);
	}
	
	public List<Videos> findAll(String n, int firstResult, int maxResult){
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery("select o from Videos o where (o.title like :n or o.id like :n)");
		q.setParameter("n", "%"+n+"%");
		q.setFirstResult(firstResult - 1);
		q.setMaxResults(maxResult);
		return q.getResultList();	
		
	}
	
	public List<Videos> findByTitle(String title){
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery("select v from Videos v where v.title like :title");
		q.setParameter("title", "%"+title+"%");
		return q.getResultList();
	}
	
	public List<Videos> favoriteVideo(String id, int firstResult, int maxResult){
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery("select f.video from Favorites f where f.user.id like :id");
		q.setParameter("id", id);
		q.setFirstResult(firstResult - 1);
		q.setMaxResults(maxResult);
		return q.getResultList();
	}
	
	public List<Object[]> mostLikeVideo(){
		EntityManager em = JpaUtils.getEntityManager();
		List<Object[]> list = em.createNamedStoredProcedureQuery("Videos.mostLikeVideo").getResultList();
		return list;
	}
	
	public List<Object[]> mostShareVideo(){
		EntityManager em = JpaUtils.getEntityManager();
		List<Object[]> list = em.createNamedStoredProcedureQuery("Videos.mostShareVideo").getResultList();
		return list;
	}
	
	public List<Object[]> mostViewVideo(){
		EntityManager em = JpaUtils.getEntityManager();
		List<Object[]> list = em.createNamedStoredProcedureQuery("Videos.mostViewVideo").getResultList();
		return list;
	}
	
	public static void main(String[] args) {
		VideoDAO video = new VideoDAO();
		List<Videos> videos = video.query(
				"select o from Videos o where o.title in (select t.title from Videos t where t.id like :n)",
				"1");
		System.out.println(videos.size());
		
	}
}
