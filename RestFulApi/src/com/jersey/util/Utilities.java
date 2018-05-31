package com.jersey.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.jersey.constants.Constants;

public class Utilities {

	private static DirContext dirContext=null;
	
	public static DirContext getDirContext()
	{
		String  ldapUsername=Constants.ldapUsername;
		String ldapPassword=Constants.ldapPassword;
		String ldapAdServer=Constants.ldapAdServer;
		  Hashtable<String, Object> env = new Hashtable<String, Object>();
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	       
	        if(ldapUsername != null) {
	            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
	        }
	        if(ldapPassword != null) {
	            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
	        }
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, ldapAdServer);	
		try {
			dirContext=new InitialDirContext(env);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dirContext;
		
	}	
	public static void close(DirContext context)
	{
		if(context!=null)
		{
		try {
			context.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
