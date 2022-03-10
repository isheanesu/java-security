package com.lessons.security;

import com.sun.security.auth.UserPrincipal;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class AppLoginModule implements LoginModule {
	
    
    private CallbackHandler callbackHandler;
    private UserDirectory userDirectory;
    private boolean isAuthenticated;
    private String username;
    private Principal userPrincipal;
    private Subject subject;
    
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.callbackHandler = callbackHandler;
		this.userDirectory = new UserDirectory();
		this.subject = subject;
	}

	@Override
	public boolean login() throws LoginException {
		// Define callback array to pass to handle method of AppCallbackHandler
		Callback[] callback_array = new Callback[2];
		callback_array[0] = new NameCallback("What is your unesername:");
		callback_array[1] = new PasswordCallback("Enter your password:", false);
		try {
			this.callbackHandler.handle(callback_array);
		}catch(IOException | UnsupportedCallbackException e) {
			e.printStackTrace();
		}

		this.username = ((NameCallback)callback_array[0]).getName();
		String password = new String(((PasswordCallback)callback_array[1]).getPassword());
		if(userDirectory.userInDirectory(new UserIdentity(this.username, password))) {
			this.isAuthenticated = true;
			System.out.println("Authentication successfull...");
			return true;
		}
		else {
			this.isAuthenticated = false;
			throw new FailedLoginException("Username or password incorrect.");
		}
	}

	@Override
	public boolean commit() throws LoginException {
		if(this.isAuthenticated) {
			this.userPrincipal = new UserPrincipal(this.username);
			subject.getPrincipals().add(userPrincipal);
		}
		return this.isAuthenticated;
	}

	@Override
	public boolean abort() throws LoginException {
		this.login();
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		return false;
	}

}
