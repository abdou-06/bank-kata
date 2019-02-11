package katasgcibtest;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sgcib.domain.Account;
import com.sgcib.domain.enums.OperationsType;
import com.sgcib.domain.service.Print;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementTest {

	private Account account;

	@Mock
	private Print print;
	private LocalDate date;

	@Before
	public void init() {
		account = new Account(0);
	}

	@Test
	public void should_print_header_and_no_statement_yet() {

		print.printer(account.printStatement());

		InOrder inOrder = Mockito.inOrder(print);
		inOrder.verify(print).printer("DATE | OPERATIONS TYPE | AMOUNT" + "\nNo statements yet");

	}

	@Test
	public void should_print_header_and_one_depo_operation() {
		Account account = new Account(0);
		account.depo(300);
		print.printer(account.printStatement());

		InOrder inOrder = Mockito.inOrder(print);
		inOrder.verify(print).printer(
				"DATE | OPERATIONS TYPE | AMOUNT" + "\n" +
					date.now() + " | " + OperationsType.DEPO + " | " + 300);

	}

	@Test
	public void should_print_header_depo_and_draw_operations() {
		account.depo(300);
		account.depo(300);
		account.draw(100);
		print.printer(account.printStatement());

		InOrder inOrder = Mockito.inOrder(print);
		inOrder.verify(print).printer(
				"DATE | OPERATIONS TYPE | AMOUNT" + "\n" + 
					date.now() + " | " + OperationsType.DEPO + " | " + 300+"\n" +
					date.now() + " | " + OperationsType.DEPO + " | " + 300+"\n" +
					date.now() + " | " + OperationsType.DRAW + " | " + 100);
	}
}