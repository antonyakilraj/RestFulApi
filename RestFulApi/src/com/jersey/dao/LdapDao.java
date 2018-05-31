package com.jersey.dao;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.jersey.dto.UserDto;
import com.jersey.util.Utilities;

public class LdapDao {

	public UserDto findAccountByAccountName(String ldapSearchBase,
			String accountName) throws NamingException {
		DirContext ctx = Utilities.getDirContext();
		UserDto userDto = new UserDto();
		String searchFilter = "(&(objectClass=person)(uid=" + accountName
				+ "))";

		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		NamingEnumeration results = null;
		try {
			results = ctx.search(ldapSearchBase, searchFilter, searchControls);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SearchResult searchResult = null;
		if (results != null) {
			if (results.hasMoreElements()) {
				searchResult = (SearchResult) results.nextElement();
				Attributes attributes = searchResult.getAttributes();

				userDto.setUid(attributes.get("uid").get(0).toString());
                userDto.setSurname(attributes.get("sn").get(0).toString()); 
                userDto.setMail(attributes.get("mail").get(0).toString());
				// make sure there is not another item available, there should
				// be only 1 match
				if (results.hasMoreElements()) {
					System.err
							.println("Matched multiple users for the accountName: "
									+ accountName);
					return null;
				}
			}
		}
		Utilities.close(ctx);
		return userDto;
	}
}
