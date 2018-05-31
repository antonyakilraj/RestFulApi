package com.jersey.service;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jersey.constants.Constants;
import com.jersey.dao.LdapDao;
import com.jersey.dto.UserDto;

@Path("/getUser")
public class LdapService {

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public UserDto getUser(@QueryParam("uid") String uid)
	{
		LdapDao dao=new LdapDao();	
		UserDto userDto=null;
		try {
			userDto=dao.findAccountByAccountName(Constants.ldapSearchBase, uid);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return userDto;
	}
}
