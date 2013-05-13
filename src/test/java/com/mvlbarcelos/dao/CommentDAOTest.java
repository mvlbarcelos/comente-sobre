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
	private EntityManager em;

	@BeforeClass
	public static void beforeClass() {
		JStrykerHelper.init();
	}

	@Before
	public void setUp() throws Exception {
		new DBUnitHelper().cleanInsert("/comment/comments.xml");
		em = JPAHelper.currentEntityManager();
		
		commentDAO = new CommentDAO();
		commentDAO.setEntityManager(em);
	}
	
	@Test
	public void shouldSaveComment() throws Exception {
		Comment comment = new Comment();
		comment.setComment("Comentario sobre metodos ageis");
		comment.setEmail("teste@email.com");
		comment.setTitle("Métodos Ágeis");
		comment.setTitleUrl("metodo-ageis");

		commentDAO.save(comment);
		Comment commentFound = em.find(Comment.class, comment.getId());

		assertThat(comment.getId(), is(commentFound.getId()));
		assertThat(comment.getComment(), is(commentFound.getComment()));
		assertThat(comment.getTitle(), is(commentFound.getTitle()));
		assertThat(comment.getTitleUrl(), is(commentFound.getTitleUrl()));
		assertThat(comment.getEmail(), is(commentFound.getEmail()));
	}

	@Test
	public void shouldListCommentsByTitleUrl() throws Exception {
		List<Comment> comments = commentDAO.listCommentsByTitleUr("metodos-ageis");
		
		assertThat(1, is(comments.size()));
	}
	
	@After
	public void tearDown() throws Exception {
		JPAHelper.close();
		new DBUnitHelper().deleteAll("/comment/comments.xml");
		new DBUnitHelper().deleteAll("/comment/commentsFound.xml");
	}

}
