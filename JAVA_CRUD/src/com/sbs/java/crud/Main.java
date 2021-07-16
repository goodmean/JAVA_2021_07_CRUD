package com.sbs.java.crud;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		Last
		
		while(true) {
			System.out.printf("명령어) ");
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
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			}
			
			else if(command.equals("article list")) {
				System.out.println("게시물이 없습니다");
			}
			else {
				System.out.printf("%s는(은) 존재하지 않는 명령어 입니다.\n", command);
			}
		}
		
		sc.close();
		System.out.println("==프로그램 끝==");

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
