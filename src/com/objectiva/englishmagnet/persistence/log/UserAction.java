package com.objectiva.englishmagnet.persistence.log;

import java.util.ArrayList;
import java.util.List;

import com.objectiva.englishmagnet.persistence.userInfro.*;

public class UserAction {
	private User user = new User();

	public UserAction() {
	}

	public boolean checkUserName(String userName) {
		boolean result;
		UserDAO ud = new UserDAO();
		List list = new ArrayList();
		list = ud.findByUser(userName);
		if (list.size() > 0) {
			this.user.setUser(userName);
			result = true;
		} else {
			this.user.setUser(userName);
			result = false;
		}
		return result;
	}

	public boolean checkUserPasswd(String passwd) {
		boolean result;
		UserDAO ud = new UserDAO();
		List list = new ArrayList();
		list = ud.findByUser(this.user.getUser());
		if (list.size() == 0) {
			result = false;
		} else {
			this.user = (User) list.get(0);
			if (this.user.getPassword().equals(passwd)) {
				result = true;
			} else
				result = false;

		}
		return result;

	}

	public void changePasswd(String passwd) {
		UserDAO ud = new UserDAO();
		this.user.setPassword(passwd);
		ud.save(this.user);
	}

	public void changeEmail(String email) {
		UserDAO ud = new UserDAO();
		this.user.setEmail(email);
		ud.save(this.user);
	}

	public void deleteUser() {
		UserDAO ud = new UserDAO();
		ud.delete(this.user);
	}

	public void saveUser() {
		UserDAO ud = new UserDAO();
		ud.save(this.user);
	}

	public int getUserId() {
		return this.user.getUserId();
	}

	public void regist(String userName, String passwd, String email) {
		UserDAO ud = new UserDAO();
		this.user.setUser(userName);
		// ud.save(this.user);
		this.user.setPassword(passwd);
		this.user.setEmail(email);
		ud.save(this.user);
	}

}
