package com.objectiva.englishmagnet.general;

import javax.faces.context.FacesContext;

public class General {

	public static String WORD = "word";
	public static String USERNAME = "username";
	public static String USERID = "userid";
	public static String WORDLIST_ITEM = "wordlistitem";

	public static void setWord(String word) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put(WORD, word);
	}

	public static String getWord() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return (String) fc.getExternalContext().getSessionMap().get(WORD);
	}

	public static void setUserName(String userName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put(USERNAME, userName);
	}

	public static String getUserName() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return (String) fc.getExternalContext().getSessionMap().get(USERNAME);
	}

	public static void setUserId(String id) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put(USERID, id);
	}

	public static String getUserId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return (String) fc.getExternalContext().getSessionMap().get(USERID);
	}

	public static void setWORDLIST_ITEM(String worditem) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put(WORDLIST_ITEM, worditem);
	}

	public static String getWORDLIST_ITEM() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return (String) fc.getExternalContext().getSessionMap()
				.get(WORDLIST_ITEM);
	}
}
