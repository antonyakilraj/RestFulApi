package com.jersey.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDto {
private String uid;
private String surname;
private String mail;
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}

public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}

}
