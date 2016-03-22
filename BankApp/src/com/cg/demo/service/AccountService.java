package com.cg.demo.service;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.cg.demo.beans.Account;
import com.cg.demo.beans.Customer;
import com.cg.demo.exceptions.InsufficientBalanceException;
import com.cg.demo.exceptions.InvalidAccountException;
import com.cg.demo.exceptions.InvalidInitialAmountException;

public interface AccountService {

	public Account createAccount(Customer c, float amount) throws InvalidInitialAmountException;
	
	public double showBalance(int number) throws InvalidAccountException;
	
	public Account withdraw(int number, float amount) throws InvalidAccountException, InsufficientBalanceException;

	Account deposit(int number, float amount) throws InvalidAccountException;
	
	public List<String> getAllAccountsDetails();
	
}
