package com.lessons.security;

import java.security.PrivilegedAction;

public class BankDepositAction implements PrivilegedAction<String> {

	@Override
	public String run() {
		SecurityManager security_manager = System.getSecurityManager();
		if(security_manager != null) {
			security_manager.checkPermission(new ResourcePermission("secure-bank-deposit"));
		}
		return "Deposit operation completed";
	}

}
