package com.cg.demo.service;

import com.cg.demo.beans.Account;
import com.cg.demo.beans.Customer;
import com.cg.demo.exceptions.InsufficientBalanceException;
import com.cg.demo.exceptions.InvalidAccountException;
import com.cg.demo.exceptions.InvalidInitialAmountException;

public interface AccountService {

	public Account createAccount(Customer c, float amount) throws InvalidInitialAmountException, IllegalArgumentException;
	
	public double showBalance(int number) throws InvalidAccountException,IllegalArgumentException;
	
	public Account withdraw(int number, float amount) throws InvalidAccountException, InsufficientBalanceException, IllegalArgumentException;

	Account deposit(int number, float amount) throws InvalidAccountException, IllegalArgumentException;
	
}
