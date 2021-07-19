package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==���α׷� ����==");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			System.out.printf("��ɾ�) ");
			String command = sc.nextLine();
			
			command = command.trim();
			
			if(command.length() == 0) {
				continue;
			}
			if(command.equals("systme exit")) {
				break;
			}
			if(command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
			}
			
			else if(command.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("�Խù��� �����ϴ�");
					continue;
				}
				System.out.print("��ȣ | ����\n");
				
				for(int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					
					System.out.printf("%d | %s\n", article.id, article.title);
				}
				
			}
			
			else if(command.startsWith("article detail")) {
				String [] commandBits = command.split("");
				
				int id = Integer.parseInt(commandBits[2]);
				
				boolean foundArticle = false;
				
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = true;
						break;
					}
				}
				
				if(foundArticle == false) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
			
			}
			
			else {
				System.out.printf("%s��(��) �������� �ʴ� ��ɾ� �Դϴ�.\n", command);
			}
		}
		
		sc.close();
		System.out.println("==���α׷� ��==");

	}
}

class Article{
	int id;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}
