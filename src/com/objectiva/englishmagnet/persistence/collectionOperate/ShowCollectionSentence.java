package com.objectiva.englishmagnet.persistence.collectionOperate;

import java.util.List;
import javax.faces.event.ActionEvent;
import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.movielist.MovieList;
import com.objectiva.englishmagnet.persistence.collectionInfro.Collection;
import com.objectiva.englishmagnet.persistence.collectionInfro.CollectionDAO;
import com.objectiva.englishmagnet.persistence.srtInfro.Srt;
import com.objectiva.englishmagnet.persistence.srtInfro.SrtDAO;
import com.objectiva.englishmagnet.persistence.srtInfro.SrtId;

public class ShowCollectionSentence {
	String senc[];
	String sene[];
	String[] start;
	String[] end;
	int[] movie_id;
	String[] word;
	public static boolean[] show_sentence;
	public static boolean show_collect = false;
	public static boolean anti_show_collect = true;

	public void find_info(ActionEvent e) {
		CollectionDAO dao = new CollectionDAO();
		SrtDAO srtdao = new SrtDAO();
		String uid = General.getUserId();
		int user_id = Integer.parseInt(uid);
		List<Collection> collectionList = dao.findByUserId(user_id);
		senc = new String[collectionList.size()];
		sene = new String[collectionList.size()];
		start = new String[collectionList.size()];
		end = new String[collectionList.size()];
		movie_id = new int[collectionList.size()];
		show_sentence = new boolean[collectionList.size()];
		word = new String[collectionList.size()];

		for (int i = 0; i < collectionList.size(); i++) {
			movie_id[i] = collectionList.get(i).getMoveId();
			start[i] = collectionList.get(i).getStart();
			word[i] = collectionList.get(i).getWord();
			SrtId srtid = new SrtId();
			srtid.setMovieId(movie_id[i]);
			srtid.setStart(start[i]);
			Srt srtList = srtdao.findById(srtid);
			senc[i] = srtList.getSenc();
			sene[i] = srtList.getSene();
			end[i] = srtList.getEnd();
			show_sentence[i] = true;

		}
		sene = highlight_sene(word, sene);
		show_collect = true;
		anti_show_collect = false;
		MovieList.movie_show = true;

	}

	public void cancelCollectSen(int index) {

		String uid = General.getUserId();
		int cancel_user_id = Integer.parseInt(uid);
		CollectionSentence cs = new CollectionSentence();
		show_sentence[index] = false;
		String cancel_start = start[index];
		String word_temp = word[index];
		int cancel_movie_id = movie_id[index];
		cs.cancelCollection(cancel_user_id, cancel_movie_id, cancel_start,
				word_temp);
	}

	public String[] highlight_sene(String[] wordList, String[] sene) {
		String[] Sentences = sene;
		String[] highlight_sene = new String[Sentences.length];
		String word;
		for (int n = 0; n < Sentences.length; n++) {
			word = wordList[n];
			String sen = Sentences[n];
			sen = sen.toLowerCase();
			word = word.toLowerCase();
			sen = " " + sen + " ";
			sen = sen.replace("!", " ").replace(".", " ").replace(",", " ")
					.replace("'", " ").replace(".", " ").replace("?", " ");
			int word_start = sen.indexOf(" " + word + " ");
			if (word_start == -1) {
				word_start = 0;
			}
			int word_len = word.length();
			highlight_sene[n] = Sentences[n].substring(0, word_start) // +
																		// "</font>"
					+ "<font color = '#0099ff' >"
					+ Sentences[n].substring(word_start, word_start + word_len)
					+ "</font>" + Sentences[n].substring(word_start + word_len);

		}

		return highlight_sene;
	}

	public String[] getSenc() {
		return senc;
	}

	public void setSenc(String[] senc) {
		this.senc = senc;
	}

	public String[] getSene() {
		return sene;
	}

	public void setSene(String[] sene) {
		this.sene = sene;
	}

	public String[] getStart() {
		return start;
	}

	public void setStart(String[] start) {
		this.start = start;
	}

	public String[] getEnd() {
		return end;
	}

	public void setEnd(String[] end) {
		this.end = end;
	}

	public int[] getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int[] movie_id) {
		this.movie_id = movie_id;
	}

	public String[] getWord() {
		return word;
	}

	public void setWord(String[] word) {
		this.word = word;
	}

	public boolean isShow_collect() {
		return show_collect;
	}

	public void setShow_collect(boolean show_collect) {
		this.show_collect = show_collect;
	}

	public boolean isAnti_show_collect() {
		return anti_show_collect;
	}

	public void setAnti_show_collect(boolean anti_show_collect) {
		this.anti_show_collect = anti_show_collect;
	}

	public boolean[] getShow_sentence() {
		return show_sentence;
	}

	public void setShow_sentence(boolean[] show_sentence) {
		this.show_sentence = show_sentence;
	}

}
