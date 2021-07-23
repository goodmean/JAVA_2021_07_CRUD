package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.util.Util;

public class App {

	private List<Article> articles;

	public App() {
		articles = new ArrayList<>();
	}

	public void start() {
		System.out.println("== ���α׷� ���� ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("���ɾ�) ");
			String command = sc.nextLine();

			command = command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}
			if (command.equals("article write")) {
				int id = articles.size() + 1;
				String regDate = Util.getNowDateStr();
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body, 0);
				articles.add(article);

				System.out.printf("%d������ �����Ǿ����ϴ�.\n", id);

			} 
			else if (command.startsWith("article list")) {

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
						System.out.println("�˻������ �������� �ʽ��ϴ�.");
						continue;
					}
				}

				if (articles.size() == 0) {
					System.out.println("�Խñ��� �����ϴ�.");
					continue;
				}
				System.out.print("��ȣ | ��ȸ| ����\n");

				for (int i = forListArticles.size() - 1; i >= 0; i--) {
					Article article = forListArticles.get(i);

					System.out.printf("%4d|%4d|%s\n", article.id, article.hit, article.title);
				}
			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

				foundArticle.increaseHit();

				System.out.printf("��ȣ : %d\n", foundArticle.id);
				System.out.printf("��¥ : %s\n", foundArticle.regDate);
				System.out.printf("���� : %s\n", foundArticle.title);
				System.out.printf("���� : %s\n", foundArticle.body);
				System.out.printf("��ȸ�� : %d\n", foundArticle.hit);

			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d������ �����Ǿ����ϴ�.\n", id);

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = getArticleIndexbyId(id);

				if (foundIndex == -1) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

				articles.remove(foundIndex);
				System.out.printf("%d�� �Խù��� �����Ǿ����ϴ�.\n", id);
			}

			else {
				System.out.printf("%s(��)�� �������� �ʴ� ���ɾ� �Դϴ�.\n", command);
			}
		}

		sc.close();

		System.out.println("== ���α׷� �� ==");
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

//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//
//			if (article.id == id) {
//				return article;
//			}
//		}

		int index = getArticleIndexbyId(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	private void makeTestData() {
		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");

		articles.add(new Article(1, Util.getNowDateStr(), "����1", "����1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "����2", "����2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "����3", "����3", 33));

	}

}