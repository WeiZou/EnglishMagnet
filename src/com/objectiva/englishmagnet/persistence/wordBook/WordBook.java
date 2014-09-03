package com.objectiva.englishmagnet.persistence.wordBook;

/**
 * WordBook entity. @author MyEclipse Persistence Tools
 */

public class WordBook implements java.io.Serializable {

	private Integer wordid;
	private Integer userId;
	private String word;

	public WordBook() {
	}

	public WordBook(Integer userId, String word) {
		this.userId = userId;
		this.word = word;
	}

	public Integer getWordid() {
		return this.wordid;
	}

	public void setWordid(Integer wordid) {
		this.wordid = wordid;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWord() {

		return this.word;
	}

	public void setWord(String word) {
		this.word = word;

	}

}