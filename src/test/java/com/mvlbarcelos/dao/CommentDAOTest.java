package com.mvlbarcelos.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.jstryker.database.DBUnitHelper;
import org.jstryker.database.JPAHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mvlbarcelos.entity.Comment;

public class CommentDAOTest {

	private CommentDAO commentDAO;

	@BeforeClass
	public static void beforeClass() {
		JStrykerHelper.init();
	}

	@Before
	public void setUp() throws Exception {
		new DBUnitHelper().cleanInsert("/comment/comments.xml");
		EntityManager em = JPAHelper.currentEntityManager();
		
		commentDAO = new CommentDAO();
		commentDAO.setEntityManager(em);
	}
	
	@Test
	public void shouldListComments() throws Exception {
		List<Comment> comments = commentDAO.listComments("metodos-ageis");
		
		assertThat(1, is(comments.size()));
	}
	
	@After
	public void tearDown() throws Exception {
		JPAHelper.close();
		new DBUnitHelper().deleteAll("/comment/comments.xml");
	}

}
