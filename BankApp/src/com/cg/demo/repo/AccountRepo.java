package com.cg.demo.repo;

import java.util.Set;
import java.util.Map.Entry;

import com.cg.demo.beans.Account;

public interface AccountRepo {

	boolean save(Account a);
	
	Account findByNumber(int number);
	public Set<Entry<Integer, Account>> findAllAccounts();
}
