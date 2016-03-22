package com.cg.demo.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cg.demo.beans.Account;

public class AccountRepoInMemoryImpl implements AccountRepo {

	private Map<Integer,Account> accounts = new HashMap<Integer,Account>();
	@Override
	public boolean save(Account a) {

		accounts.put(a.getNumber(),a);
		return true;
	}

	@Override
	public Account findByNumber(int number) {
		// TODO Auto-generated method stub
		return accounts.get(number);
	}
	
	@Override
	public Set<Entry<Integer, Account>> findAllAccounts(){
		return accounts.entrySet();
	}

}
