package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.util.Util;

public class ArticleController extends Controller{
	private Scanner sc;
	private List<Article> articles;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;

		articles = new ArrayList<Article>();
	}
	
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			if (isLogined() == false) {
				System.out.println("로그인 후 이용해주세요.");
				break;
			}
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void doWrite() {
		int id = articles.size() + 1;
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body, 0);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);

	}

	private void showList() {

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> forListArticles = articles;

		if (searchKeyword.length() > 0) {
			forListArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forListArticles.add(article);
				}
			}

			if (forListArticles.size() == 0) {
				System.out.println("검색결과가 존재하지 않습니다.");
				return;
			}
		}

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
		}

		System.out.print("번호 | 작성자 | 조회| 제목\n");

		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);

			System.out.printf("%4d|%6d|%5d|%4s\n", article.id, article.memberId, article.hit, article.title);
		}

	}

	private void showDetail() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %s\n", foundArticle.memberId);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.hit);

	}

	private void doModify() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번글이 수정되었습니다.\n", id);

	}

	private void doDelete() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		int foundIndex = getArticleIndexbyId(id);

		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		articles.remove(foundIndex);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

	}

	private int getArticleIndexbyId(int id) {
		int i = 0;

		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}

		return -1;
	}

	private Article getArticleById(int id) {

		int index = getArticleIndexbyId(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNowDateStr(), 1, "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), 2, "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), 2, "제목3", "내용3", 33));
	}

}