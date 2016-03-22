package com.cg.demo.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cg.demo.beans.Account;
import com.cg.demo.exceptions.InvalidAccountException;

public class AccountRepoInMemoryImpl implements AccountRepo {

	private Map<Integer,Account> accounts = new HashMap<Integer,Account>();
	@Override
	public boolean save(Account a) {

		accounts.put(a.getNumber(),a);
		return true;
	}

	@Override
	public Account findByNumber(int number) throws InvalidAccountException {
		Set<Integer> allAccounts = accounts.keySet();
		Account accountFound = null;
		for (Integer nextAcc : allAccounts) {
			if (nextAcc == number) {
				accountFound = accounts.get(number);
			}
		}
		
		if (accountFound == null) {
			throw new InvalidAccountException();
		}
		
		return accountFound; 
	}
	
	@Override
	public Set<Entry<Integer, Account>> findAllAccounts(){
		return accounts.entrySet();
	}

}
