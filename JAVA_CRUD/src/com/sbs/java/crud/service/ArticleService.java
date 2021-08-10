package com.sbs.java.crud.service;

import java.util.List;

import com.sbs.java.crud.container.Container;
import com.sbs.java.crud.dao.ArticleDao;
import com.sbs.java.crud.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticles(String searchKeyword) {

		return articleDao.getArticles(searchKeyword);
	}

	public int getArticleIndexById(int id) {
		return articleDao.getArticleIndexById(id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);

	}

}