package com.objectiva.englishmagnet.persistence.userInfro;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String user;
	private String password;
	private String selectHistory;
	private String email;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String user, String password, String selectHistory, String email) {
		this.user = user;
		this.password = password;
		this.selectHistory = selectHistory;
		this.email = email;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getselectHistory() {
		return this.selectHistory;
	}

	public void setselectHistory(String selectHistory) {
		this.selectHistory = selectHistory;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}