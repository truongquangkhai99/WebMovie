package com.asm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.asm.model.Users;

public class UserDAO extends AbstractEntityDAO<Users>{
	
	public UserDAO() {
		super(Users.class);
	}
	
	public Users checkAccount(Users entity) {
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery("select u from Users u where u.id like :id and u.password like :password");
		q.setParameter("id", entity.getId());
		q.setParameter("password", entity.getPassword());
		List list = q.getResultList();
		try {
			Users user = (Users)list.get(0);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Users getPassword(String email) {
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery("select u from Users u where u.email like :email");
		q.setParameter("email", email);
		List<Users> list = q.getResultList();
		try {
			Users user = list.get(0);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
}
