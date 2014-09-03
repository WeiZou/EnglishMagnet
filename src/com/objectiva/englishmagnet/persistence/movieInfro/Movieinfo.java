package com.objectiva.englishmagnet.persistence.movieInfro;

/**
 * Movieinfo entity. @author MyEclipse Persistence Tools
 */

public class Movieinfo implements java.io.Serializable {

	// Fields

	private Integer movieId;
	private String movieName;
	private String type;
	private String age;
	private String region;
	private String actors;
	private String other;

	// Constructors

	/** default constructor */
	public Movieinfo() {
	}

	/** full constructor */
	public Movieinfo(String movieName, String type, String age, String region,
			String actors, String other) {
		this.movieName = movieName;
		this.type = type;
		this.age = age;
		this.region = region;
		this.actors = actors;
		this.other = other;
	}

	// Property accessors

	public Integer getMovieId() {
		return this.movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return this.movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getActors() {
		return this.actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}