package com.cg.demo.service;

import com.cg.demo.beans.Account;
import com.cg.demo.beans.Customer;
import com.cg.demo.exceptions.InsufficientBalanceException;
import com.cg.demo.exceptions.InvalidAccountException;
import com.cg.demo.exceptions.InvalidInitialAmountException;
import com.cg.demo.repo.AccountRepo;
import com.cg.demo.util.AccountNumberGenerator;

public class AccountServiceImpl implements AccountService {
	
	private AccountRepo repo;

	private final double minimumBalanceRequired = 500.00;
	public AccountServiceImpl(AccountRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Account createAccount(Customer c, float amount)
			throws InvalidInitialAmountException, IllegalArgumentException {
		if(c== null){
			throw new IllegalArgumentException("Customer cannot be null");
		}
		
		if(c.getName()== null){
			throw new IllegalArgumentException("Customer name cannot be null");
		}
		
		if(amount <500){
			throw new InvalidInitialAmountException();
		}

		Account a = new Account(AccountNumberGenerator.getNumber());
		a.setBalance(amount);
		a.setCustomer(c);
		if(repo.save(a)){
			return a;
		}
		return null;
	}

	@Override
	public double showBalance(int number) throws InvalidAccountException, IllegalArgumentException {
		if (number<=0) {
			throw new IllegalArgumentException("Invalid argument for Account number");
		}
		Account acctFound = repo.findByNumber(number);
		
		return acctFound.getBalance();
	}

	@Override
	public Account withdraw(int number, float amount)
			throws InvalidAccountException, InsufficientBalanceException, IllegalArgumentException {

		if (number<=0) {
			throw new IllegalArgumentException("Invalid argument for Account number");
		}
		if (amount <=0) {
			throw new IllegalArgumentException("Invalid amount attempted to be withdrawn");
		}
		
		Account acctFound = repo.findByNumber(number);

		if (acctFound.getBalance() < amount) {
			throw new InsufficientBalanceException();
			
		} else if (acctFound.getBalance() < (amount+minimumBalanceRequired)) {
			throw new InsufficientBalanceException();		
		}
		acctFound.setBalance(acctFound.getBalance()-amount);
		
		if (repo.save(acctFound)) {
			return acctFound; 
		}
		return null;
	}

	@Override
	public Account deposit(int number, float amount)
			throws InvalidAccountException, IllegalArgumentException {
		if (number<=0) {
			throw new IllegalArgumentException("Invalid argument for Account number");
		}
		if (amount <=0) {
			throw new IllegalArgumentException("Invalid amount attempted to be withdrawn");
		}
		
		Account acctFound = repo.findByNumber(number);
		acctFound.setBalance(acctFound.getBalance()+amount);
		
		if (repo.save(acctFound)) {
			return acctFound; 
		}
		return null;
	}

}
