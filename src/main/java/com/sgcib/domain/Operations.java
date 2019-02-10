package com.sgcib.domain;

import java.time.LocalDate;

import com.sgcib.domain.enums.OperationsType;

public class Operations {

	private LocalDate date;
	private int amount;
	private OperationsType operationsType;

	public Operations(LocalDate date, int amount, OperationsType operationsType) {
		this.date = date;
		this.amount = amount;
		this.operationsType = operationsType;
	}

	@Override
	public String toString() {
		StringBuilder stringToPrint = new StringBuilder();
		stringToPrint.append(date).append(" | ").append(operationsType).append(" | ").append(amount);
		return stringToPrint.toString();
	}
}
