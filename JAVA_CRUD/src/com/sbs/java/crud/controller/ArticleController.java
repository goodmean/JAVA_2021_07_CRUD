package com.sbs.java.crud.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.java.crud.container.Container;
import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.dto.Member;
import com.sbs.java.crud.service.ArticleService;
import com.sbs.java.crud.util.Util;

public class ArticleController extends Controller{
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private ArticleService articleService;

	public ArticleController(Scanner sc) {
		this.sc = sc;

		articleService = Container.articleService;
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
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			break;
		}
	}

	private void doWrite() {
		int id = Container.articleDao.getNewId();
		String regDate = Util.getNowDateStr();
		System.out.printf("���� : ");
		String title = sc.nextLine();
		System.out.printf("���� : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body, 0);
		Container.articleDao.add(article);

		System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);

	}

	private void showList() {

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> forPrintArticles = articleService.getForPrintArticles(searchKeyword);

		if (forPrintArticles.size() == 0) {
			System.out.println("�˻������ �������� �ʽ��ϴ�.");
			
			return;
		}

		System.out.print("��ȣ |      �ۼ��� | ��ȸ | ����\n");

		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);

			String writerName = null;

			List<Member> members = Container.memberDao.members;

			for (Member member : members) {
				if (article.memberId == member.id) {
					writerName = member.name;
					break;
				}
			}

			System.out.printf("%4d|%10s|%5d|%4s\n", article.id, writerName, article.hit, article.title);
		}

	}

	private void showDetail() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("��ȣ : %d\n", foundArticle.id);
		System.out.printf("��¥ : %s\n", foundArticle.regDate);
		System.out.printf("�ۼ��� : %s\n", foundArticle.memberId);
		System.out.printf("���� : %s\n", foundArticle.title);
		System.out.printf("���� : %s\n", foundArticle.body);
		System.out.printf("��ȸ�� : %d\n", foundArticle.hit);

	}

	private void doModify() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("������ �����ϴ�.");
			return;
		}

		System.out.printf("���� : ");
		String title = sc.nextLine();
		System.out.printf("���� : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d������ �����Ǿ����ϴ�.\n", id);

	}

	private void doDelete() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}
		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("������ �����ϴ�.");
			return;
		}
		articleService.remove(foundArticle);
		System.out.printf("%d�� �Խù��� �����Ǿ����ϴ�.\n", id);

	}

	public void makeTestData() {
		System.out.println("�׽�Ʈ�� ���� �Խù� �����͸� �����մϴ�.");

		Container.articleDao
		.add(new Article(Container.articleDao.getNewId(), Util.getNowDateStr(), 1, "����1", "����1", 11));
		Container.articleDao
		.add(new Article(Container.articleDao.getNewId(), Util.getNowDateStr(), 2, "����2", "����2", 22));
		Container.articleDao
		.add(new Article(Container.articleDao.getNewId(), Util.getNowDateStr(), 2, "����3", "����3", 33));
	}

}