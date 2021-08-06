package com.sbs.java.crud.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}

	public void add(Member member) {
		members.add(member);
		lastId = member.id;
	}

}