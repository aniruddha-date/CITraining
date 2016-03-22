package com.cg.demo.repo;

import java.util.Set;
import java.util.Map.Entry;

import com.cg.demo.beans.Account;
import com.cg.demo.exceptions.InvalidAccountException;

public interface AccountRepo {

	boolean save(Account a);
	
	Account findByNumber(int number) throws InvalidAccountException;
	public Set<Entry<Integer, Account>> findAllAccounts();

}
