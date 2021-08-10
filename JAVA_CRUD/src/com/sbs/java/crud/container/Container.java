package com.sbs.java.crud.container;

import com.sbs.java.crud.dao.ArticleDao;
import com.sbs.java.crud.dao.MemberDao;
import com.sbs.java.crud.service.ArticleService;

public class Container {
	
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;

	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
	}
}