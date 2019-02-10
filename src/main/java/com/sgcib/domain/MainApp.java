package com.sgcib.domain;

import com.sgcib.domain.service.Print;

public class MainApp {

	static Print printer = new Print();
	
	public static void main(String[] args) {

		Account account = new Account(0);
		account.depo(300);
		account.draw(100);
	printer.printer(account.printStatement());

		
	}

}
