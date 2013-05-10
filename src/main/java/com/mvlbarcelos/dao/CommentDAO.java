package com.mvlbarcelos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mvlbarcelos.entity.Comment;

@Repository
public class CommentDAO {
	
	private EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> listComments(String titleUrl) {
		Query query = entityManager.createQuery("from Comment c where c.titleUrl = :titleUrl").setParameter("titleUrl", titleUrl);
		
		return query.getResultList();
	}
}
