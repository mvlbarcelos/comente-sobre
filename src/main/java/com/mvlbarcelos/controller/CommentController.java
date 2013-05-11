package com.mvlbarcelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvlbarcelos.dao.CommentDAO;
import com.mvlbarcelos.entity.Comment;


@Controller
public class CommentController {

	private CommentDAO commentDAO;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");

		return view;
	}

	@RequestMapping(value = "/{titleUrl}") 
	public ModelAndView createComment(@PathVariable("titleUrl") String titleUrl, @RequestParam(required = false) String title) {
		Comment comment = new Comment();
		comment.setTitle(title);
		comment.setTitleUrl(titleUrl);

		ModelAndView modelAndView = new ModelAndView("detail");
		modelAndView.addObject("comment", comment);

		return modelAndView;
	}

	@RequestMapping(value = "salvar/{titleUrl}")
	public ModelAndView save (@PathVariable("titleUrl") String titleUrl, String email, String comment, String title) {
		Comment c = new Comment();
		c.setTitleUrl(titleUrl);
		c.setEmail(email);
		c.setComment(comment);
		c.setTitle(title);
		commentDAO.save(c);

		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("comments", commentDAO.listCommentsByTitleUr(titleUrl));
		modelAndView.addObject("title", title);

		return modelAndView;
	}

	@Autowired
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
}