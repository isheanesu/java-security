package com.lessons.security;

import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Program {

	@SuppressWarnings("removal")
	public static void main(String[] args) {
		System.setProperty("java.security.auth.login.config", "app.config");
		LoginContext login_context = null;
		try {
			//Plugin the login module from configuration file
			login_context = new LoginContext("AppConfig",new AppCallbackHandler());
		}catch(Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		
		//Attempt login
		while(true) {
			try {
				login_context.login();
				PrivilegedAction<String> bank_deposit_operation = new BankDepositAction();
				String response = Subject.doAsPrivileged(login_context.getSubject(), bank_deposit_operation, null);
				System.out.println(response);
				System.exit(0);
			}catch(LoginException ex) {
				ex.printStackTrace();
			}
		}
		

	}

}
