package com.sbs.java.crud.container;

import com.sbs.java.crud.dao.ArticleDao;
import com.sbs.java.crud.dao.MemberDao;

public class Container {
	public class articleDao {

	}

	public static ArticleDao articleDao;
	public static MemberDao memberDao;

	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}