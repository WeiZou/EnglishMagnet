package com.objectiva.englishmagnet.persistence.srtInfro;

/**
 * SrtId entity. @author MyEclipse Persistence Tools
 */

public class SrtId implements java.io.Serializable {

	// Fields

	private String start;
	private Integer movieId;

	// Constructors

	/** default constructor */
	public SrtId() {
	}

	/** full constructor */
	public SrtId(String start, Integer movieId) {
		this.start = start;
		this.movieId = movieId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SrtId))
			return false;
		SrtId castOther = (SrtId) other;

		return ((this.getStart() == castOther.getStart()) || (this.getStart() != null
				&& castOther.getStart() != null && this.getStart().equals(
				castOther.getStart())))
				&& ((this.getMovieId() == castOther.getMovieId()) || (this
						.getMovieId() != null && castOther.getMovieId() != null && this
						.getMovieId().equals(castOther.getMovieId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStart() == null ? 0 : this.getStart().hashCode());
		result = 37 * result
				+ (getMovieId() == null ? 0 : this.getMovieId().hashCode());
		return result;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public Integer getMovieId() {
		return this.movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

}