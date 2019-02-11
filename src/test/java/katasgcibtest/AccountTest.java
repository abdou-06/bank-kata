package katasgcibtest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sgcib.domain.Account;

public class AccountTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void should_add_ammount_once_to_my_account() {
		Account account = new Account(0);
		account.depo(100);

		Assert.assertEquals(100, account.getBalance());
	}

	@Test
	public void should_add_ammount_twice_to_my_account() {
		Account account = new Account(0);
		account.depo(100);
		account.depo(100);
		Assert.assertEquals(200, account.getBalance());
	}

	@Test
	public void should_throw_exception_with_message_insufficient_funds() {
		Account account = new Account(0);
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("insufficient funds");
		account.draw(100);

	}

	@Test
	public void should_throw_exception_with_message_negative_amount_not_allowed_in_depo_method() {
		Account account = new Account(0);
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Amount should be positive");
		account.depo(-100);

	}
	
	@Test
	public void should_draw_ammount_from_my_account() {
		Account account = new Account(500);
		account.draw(100);
		Assert.assertEquals(400, account.getBalance());
	}

	@Test
	public void should_draw_ammount_twice_from_my_account() {
		Account account = new Account(500);
		account.draw(100);
		account.draw(300);
		Assert.assertEquals(100, account.getBalance());
	}

	@Test
	public void should_throw_exception_with_message_negative_amount_not_allowed_in_draw_method() {
		Account account = new Account(500);
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Amount should be positive");
		account.draw(-100);
	}
}