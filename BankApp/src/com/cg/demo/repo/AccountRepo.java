package com.cg.demo.repo;

import com.cg.demo.beans.Account;
import com.cg.demo.exceptions.InvalidAccountException;

public interface AccountRepo {

	boolean save(Account a);
	
	Account findByNumber(int number) throws InvalidAccountException;
}
