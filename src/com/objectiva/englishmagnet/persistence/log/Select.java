package com.objectiva.englishmagnet.persistence.log;

import java.io.Serializable;
import java.util.List;
import com.objectiva.englishmagnet.persistence.userInfro.User;
import com.objectiva.englishmagnet.persistence.userInfro.UserDAO;
import com.objectiva.englishmagnet.general.*;

public class Select implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	int userId;

	public Select() {
		userName = "";
	}

	public static void ppush() {
		String userName = General.getUserName();
		String word = General.getWord();
		if (userName != null && !userName.equals("")) {
			UserDAO ud = new UserDAO();
			List list = ud.findByUser(userName);
			User user = (User) list.get(0);
			StringBuffer string = new StringBuffer();

			string.append(user.getselectHistory());
			if (word != null) {
				String ko = push(string.toString(), word);
				user.setselectHistory(ko);
				ud.save(user);

			}
		}
	}

	public static String push(String a, String word) {
		String t = word + "#" + a;
		if (t.endsWith("null"))
			t = t.substring(0, t.length() - 4);
		if (t.indexOf("@" + word + "#") > 0) {
			t = t.replaceAll("@" + word + "#", "");
			t = "@" + t;
		} else {
			String b = t = "@" + t;
			int i = 0;
			while (b.indexOf("#") > 0) {
				b = b.substring(b.indexOf("#") + 1);
				i++;
				if (i == 10) {
					t = t.replaceAll(b, "");
					break;
				}
			}

		}
		return t;
	}

	public int getUserId() {
		UserAction ua = new UserAction();
		boolean t = ua.checkUserName(userName);
		if (t) {
			ua.checkUserPasswd("");
			this.userId = ua.getUserId();
		}
		return this.userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getString1() {
		return getWord();
	}

	public void setString1(String[] string1) {
	}

	public String[] getWord() {
		this.userName = General.getUserName();
		String a = "";
		General.getWord();
		if (this.userName != null && !this.userName.equals("")) {
			General.setUserId(getUserId() + "");
			UserDAO ud = new UserDAO();
			List list = ud.findByUser(this.userName);
			User user = (User) list.get(0);

			StringBuffer string = new StringBuffer();
			string.append(user.getselectHistory());

			a = string.toString();
			a = a.replaceAll("@", "");
			if (a.endsWith("null"))
				a = a.substring(0, a.length() - 4);
			System.gc();
		}

		else {
			General.setUserId(null);
		}

		return a.split("#");

	}

	public void setWord(String word) {
	}

}
