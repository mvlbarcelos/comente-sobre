package com.mvlbarcelos.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.mvlbarcelos.entity.Comment;

public class CommentControllerTest {
	
	private CommentController commentController;
	
	@Before
	public void setup() throws Exception {
		commentController = new CommentController();
	}
	
	@Test
	public void shouldReturnIndexPage() throws Exception {
		ModelAndView modelAndView = commentController.index();
		
		assertThat(modelAndView.getViewName(), is("index"));
	}
	
	@Test
	public void shouldReturnComment() throws Exception {
		ModelAndView modelAndView = commentController.createComment("metodo-ageis", "metodo çgeis");
		Comment comment = (Comment)modelAndView.getModelMap().get("comment");
		
		assertThat(modelAndView.getViewName(), is("detail"));
		assertThat(comment.getTitleUrl(), is("metodo-ageis"));
		assertThat(comment.getTitle(), is("metodo çgeis"));
	}
}
