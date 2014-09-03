package com.objectiva.englishmagnet.persistence.srtInfro;

/**
 * Srt entity. @author MyEclipse Persistence Tools
 */

public class Srt implements java.io.Serializable {

	// Fields

	private SrtId id;
	private String sene;
	private String senc;
	private String end;

	// Constructors

	/** default constructor */
	public Srt() {
	}

	/** full constructor */
	public Srt(SrtId id, String sene, String senc, String end) {
		this.id = id;
		this.sene = sene;
		this.senc = senc;
		this.end = end;
	}

	// Property accessors

	public SrtId getId() {
		return this.id;
	}

	public void setId(SrtId id) {
		this.id = id;
	}

	public String getSene() {
		return this.sene;
	}

	public void setSene(String sene) {
		this.sene = sene;
	}

	public String getSenc() {
		return this.senc;
	}

	public void setSenc(String senc) {
		this.senc = senc;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}