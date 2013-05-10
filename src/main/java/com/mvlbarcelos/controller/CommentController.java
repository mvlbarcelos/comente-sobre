package com.mvlbarcelos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvlbarcelos.entity.Comment;

@Controller
public class CommentController {

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
}