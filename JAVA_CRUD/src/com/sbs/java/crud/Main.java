package com.sbs.java.crud;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==���α׷� ����==");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		Last
		
		while(true) {
			System.out.printf("��ɾ�) ");
			String command = sc.nextLine().trim();
			
			if(command.length() == 0) {
				continue;
			}
			if(command.equals("systme exit")) {
				break;
			}
			if(command.equals("article wirte")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
			}
			
			else if(command.equals("article list")) {
				System.out.println("�Խù��� �����ϴ�");
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
