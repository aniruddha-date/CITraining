package com.cg.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.cg.demo.beans.Account;
import com.cg.demo.beans.Customer;
import com.cg.demo.exceptions.InsufficientBalanceException;
import com.cg.demo.exceptions.InvalidAccountException;
import com.cg.demo.exceptions.InvalidInitialAmountException;
import com.cg.demo.repo.AccountRepo;
import com.cg.demo.util.AccountNumberGenerator;

public class AccountServiceImpl implements AccountService {

	private AccountRepo repo;

	public AccountServiceImpl(AccountRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Account createAccount(Customer c, float amount)
			throws InvalidInitialAmountException {
		if (c == null) {
			throw new IllegalArgumentException("Customer cannot be null");
		}

		if (c.getName() == null) {
			throw new IllegalArgumentException("Customer name cannot be null");
		}

		if (amount < 500) {
			throw new InvalidInitialAmountException();
		}
		Account a = new Account(AccountNumberGenerator.getNumber());
		a.setBalance(amount);
		a.setCustomer(c);
		if (repo.save(a)) {
			return a;
		}
		return null;
	}

	@Override
	public double showBalance(int number) throws InvalidAccountException {
		if (number <= 0) {
			throw new InvalidAccountException();
		}

		Account account = repo.findByNumber(number);
		if (account != null)
			return account.getBalance();
		else
			throw new InvalidAccountException();
	}

	// Account number should be valid
	// amount passed should not be negative
	// Minimum Rs. 500 balance should be maintained in the account
	// if account number and amount are valid then it should deduct the balance.

	@Override
	public Account withdraw(int number, float amount)
			throws InvalidAccountException, InsufficientBalanceException {
		if (number <= 0) {
			throw new InvalidAccountException();
		}

		if (amount <= 0) {
			throw new IllegalArgumentException(
					"withdrawal amount cannot be 0 or -ve");
		}

		Account account = repo.findByNumber(number);
		if (account != null) {
			double balance = account.getBalance();
			double diff = balance - amount;
			if (diff >= 500) {
				account.setBalance(diff);
				return account;
			} else
				throw new InsufficientBalanceException();

		} else
			throw new InvalidAccountException();
	}

	// Account should be valid
	// amount should not be greater than zero
	// After deposit balance should be increased.

	@Override
	public Account deposit(int number, float amount)
			throws InvalidAccountException {
		if (number <= 0) {
			throw new InvalidAccountException();
		}

		if (amount <= 0) {
			throw new IllegalArgumentException(
					"deposit amount cannot be 0 or -ve");
		}

		Account account = repo.findByNumber(number);
		if (account != null) {
			account.setBalance(account.getBalance() + amount);
			return account;

		} else
			throw new InvalidAccountException();
	}

	@Override
	public List<String> getAllAccountsDetails() {
		// TODO Auto-generated method stub
		List<String> allAccountsDetails = new ArrayList<>();
		for (Entry<Integer, Account> entry : repo.findAllAccounts()) {
			allAccountsDetails.add("account Number " + entry.getKey()
					+ ":	balance	" + entry.getValue().getBalance());
		}

		return allAccountsDetails;
	}

}
