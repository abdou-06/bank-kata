package com.sgcib.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sgcib.domain.constant.AccountConstant;
import com.sgcib.domain.enums.OperationsType;

public class Account {

	private List<Operations> statements = new ArrayList<Operations>();
	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public void depo(int amount) {
		if (amount < 0)
			throw new RuntimeException(AccountConstant.NEGATIVE_AMOUNT);
		addOperationsToStatements(new Operations(LocalDate.now(), amount, OperationsType.DEPO));
		this.balance += amount;
	}

	public void draw(int amount) {
		if (amount < 0)
			throw new RuntimeException(AccountConstant.NEGATIVE_AMOUNT);
		if (balance < amount)
			throw new RuntimeException(AccountConstant.NEGATIVE_BALANCE);

		addOperationsToStatements(new Operations(LocalDate.now(), amount, OperationsType.DRAW));
		this.balance -= amount;
	}

	private void addOperationsToStatements(Operations operations) {
		statements.add(operations);
	}

	public int getBalance() {
		return balance;
	}

	public String printStatement() {
		StringBuilder stringToPrint = new StringBuilder(AccountConstant.HEADER);

		if (!this.statements.isEmpty()) {
			statements.forEach(statements -> stringToPrint.append("\n"+statements));
		} else {
			stringToPrint.append(AccountConstant.EMPTY_STATEMENTS);
		}
		return stringToPrint.toString();
	}
}
