package com.mvlbarcelos.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.mvlbarcelos.dao.CommentDAO;
import com.mvlbarcelos.entity.Comment;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {

	private CommentController commentController;

	@Mock
	private CommentDAO commentDAO;

	@Before
	public void setup() throws Exception {
		commentController = new CommentController();
		commentController.setCommentDAO(commentDAO);
	}
	
	@Test
	public void shouldReturnIndexPage() throws Exception {
		ModelAndView modelAndView = commentController.index();
		
		assertThat(modelAndView.getViewName(), is("index"));
	}
	
	@Test
	public void shouldReturnComment() throws Exception {
		ModelAndView modelAndView = commentController.createComment("metodo-ageis", "método Ágeis");
		Comment comment = (Comment)modelAndView.getModelMap().get("comment");
		
		assertThat(modelAndView.getViewName(), is("detail"));
		assertThat(comment.getTitleUrl(), is("metodo-ageis"));
		assertThat(comment.getTitle(), is("método Ágeis"));
	}

	@Test
	public void shouldSaveCommentAndReturnComments() throws Exception {
		String titleUrl = "metodo-ageis";
		Comment c = new Comment();
		c.setComment("metodos ageis, qualidade de entrega");
		c.setEmail("teste@teste,com");
		c.setTitle("métodos Ágeis");

		List<Comment> comments = getComments();
		when(commentDAO.listCommentsByTitleUr(titleUrl)).thenReturn(comments );

		ModelAndView modelAndView = commentController.save(titleUrl, c);

		assertThat(modelAndView.getViewName(), is("list"));
		assertThat(comments, is(modelAndView.getModelMap().get("comments")));
		assertThat("métodos Ágeis", is(modelAndView.getModelMap().get("title")));
		verify(commentDAO).save(any(Comment.class));
		verify(commentDAO).listCommentsByTitleUr(titleUrl);
	}

	private List<Comment> getComments() {
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = new Comment();
		comment.setId(999L);

		return comments;
	}
}
