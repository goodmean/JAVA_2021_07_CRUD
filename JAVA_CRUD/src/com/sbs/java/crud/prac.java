package com.sbs.java.crud;

public class prac {
	public static void main(String[] args) {
		new App().start();
	}
}

class App {
	private  int a;

	App(){
		a = 10;
	}

	public void start() {
		makeTestData(); // a->5

		System.out.println(a);
	}

	private void makeTestData() {
		a = 5;
	}
} 