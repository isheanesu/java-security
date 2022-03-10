package com.lessons.security;

import java.security.BasicPermission;

public class ResourcePermission extends BasicPermission {

		public ResourcePermission(String name) {
			super(name);
		}
	
}
