package com.asm.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


public abstract class AbstractEntityDAO<T> {
	private Class<T> entityClass;
	
	public AbstractEntityDAO(Class<T> cls) {
		this.entityClass = cls;
	}
	public void insert(T entity) throws Exception{
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(entity);
			trans.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			trans.rollback();
			throw ex;
		} finally {
			em.close();
		}
	}
	
	public void update(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(entity);
			trans.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public void delete(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			T entity = em.find(entityClass, id);
			if (entity !=null) {
				em.remove(entity);
			}else {
				throw new Exception("User cannot found");
			}
			trans.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();
		}finally {
			em.close();
		}
	}
	
	public T findById(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		T entity = em.find(entityClass, id);
		return entity;
	}
	
	public List<T> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}
	
	public List<T> query(String query, String ...args){
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery(query);
		for(String s : args) {
			q.setParameter("n", "%"+s+"%");
		}
		return q.getResultList();
	}
	
	public List<T> findAll(boolean all, int firstResult, int maxResult){
		EntityManager em = JpaUtils.getEntityManager();
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = em.createQuery(cq);
		if(!all) {
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResult);
		}
		return q.getResultList();	
		
	}

}
