package com.sgcib.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sgcib.domain.constant.AccountConstant;
import com.sgcib.domain.enums.OperationsType;

public class Account {

	private List<Operations> statement = new ArrayList<Operations>();
	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public void depo(int amount) {
		if (amount < 0)
			throw new RuntimeException(AccountConstant.NEGATIVE_AMOUNT);
		addOperationsToStatement(new Operations(LocalDate.now(), amount, OperationsType.depo));
		this.balance += amount;
	}

	public void draw(int amount) {
		if (amount < 0)
			throw new RuntimeException(AccountConstant.NEGATIVE_AMOUNT);
		if (balance < amount)
			throw new RuntimeException(AccountConstant.NEGATIVE_BALANCE);
		addOperationsToStatement(new Operations(LocalDate.now(), amount, OperationsType.draw));
		this.balance -= amount;
	}

	private void addOperationsToStatement(Operations operations) {
		statement.add(operations);
	}

	public int getBalance() {
		return balance;
	}

	public String printStatement() {
		StringBuilder stringToPrint = new StringBuilder(AccountConstant.HEADER);

		if (!this.statement.isEmpty()) {
			for (Operations operations : this.statement) {
				stringToPrint.append("\n" + operations.toString());
			}
		} else {
			stringToPrint.append("\nNo statement yet");
		}
		return stringToPrint.toString();
	}
}
