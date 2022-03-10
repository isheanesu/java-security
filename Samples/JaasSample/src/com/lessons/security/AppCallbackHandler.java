package com.lessons.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class AppCallbackHandler implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		NameCallback name_callback = null;
		PasswordCallback password_callback = null;
		for(Callback c : callbacks) {
			if(c instanceof NameCallback) {
				name_callback = (NameCallback)c;
				System.out.println(name_callback.getPrompt());
				name_callback.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
			}
			else if(c instanceof PasswordCallback) {
				password_callback = (PasswordCallback)c;
				System.out.println(password_callback.getPrompt());
				password_callback.setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
			}
		}
	}

}
