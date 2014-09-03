package com.objectiva.englishmagnet.persistence.collectionInfro;

/**
 * Collection entity. @author MyEclipse Persistence Tools
 */

public class Collection implements java.io.Serializable {

	// Fields

	private Integer collectionId;
	private Integer userId;
	private Integer moveId;
	private String start;
	private String word;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** full constructor */
	public Collection(Integer userId, Integer moveId, String start, String word) {
		this.userId = userId;
		this.moveId = moveId;
		this.start = start;
		this.word = word;
	}

	// Property accessors

	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMoveId() {
		return this.moveId;
	}

	public void setMoveId(Integer moveId) {
		this.moveId = moveId;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}