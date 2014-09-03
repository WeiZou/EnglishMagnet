package com.objectiva.englishmagnet.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.objectiva.englishmagnet.facade.SearchBackingBean;
import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.persistence.collectionInfro.Collection;
import com.objectiva.englishmagnet.persistence.collectionInfro.CollectionDAO;
import com.objectiva.englishmagnet.persistence.srtInfro.Srt;
import com.objectiva.englishmagnet.persistence.srtInfro.SrtDAO;

public class GetMovieInfo implements Serializable {
	int Sentence_length;
	int movie_num;
	String temp;
	SearchBackingBean Search = new SearchBackingBean();

	public List<String> find_list(int flag, String word) {
		movie_num = 0;
		temp = "";
		List<String> sene = new ArrayList<String>();
		List<String> senc = new ArrayList<String>();
		List<String> start_time = new ArrayList<String>();
		List<String> end_time = new ArrayList<String>();
		List<String> movieid = new ArrayList<String>();
		List<String> collect = new ArrayList<String>();
		SrtDAO srtdao = new SrtDAO();
		List<Srt> srtList = srtdao.findAll();

		if (word.equals("")) {
			word += " ";
			start_time.add("0");
			end_time.add("0");
		}
		for (int i = 0; i < srtList.size(); i++) {
			String Sentence = srtList.get(i).getSene();
			String Senc = srtList.get(i).getSenc();
			String Start = srtList.get(i).getId().getStart();
			String End = srtList.get(i).getEnd();
			String MovieId = "" + srtList.get(i).getId().getMovieId();

			String sen = Sentence;
			sen = sen.toLowerCase();
			word = word.toLowerCase();

			sen = sen.replace("!", " ").replace(".", " ").replace(",", " ")
					.replace("'", " ").replace(".", " ").replace("?", " ");
			String[] sen_word = sen.split(" ");
			for (int m = 0; m < sen_word.length; m++) {

				if (word.equals(sen_word[m])) {
					if (!temp.equals(MovieId)) {
						temp = MovieId;
						movie_num++;
					}

					sene.add(Sentence);
					senc.add(Senc);
					start_time.add(Start);
					end_time.add(End);
					movieid.add(MovieId);
					collect.add(collection_or_not(MovieId, Start));
					break;
				}
			}

		}
		temp = "";

		if (flag == 1)
			return sene;
		else if (flag == 2)
			return start_time;
		else if (flag == 3)
			return end_time;
		else if (flag == 4)
			return senc;
		else if (flag == 5)
			return movieid;
		else if (flag == 6)
			return collect;
		return null;
	}

	public String collection_or_not(String movieid, String start) {

		String sen_collection = "false";
		CollectionDAO dao = new CollectionDAO();
		List<Collection> list = dao.findAll();
		String uid = General.getUserId();
		if (uid == null)
			return "false";
		for (int i = 0; i < list.size(); i++) {
			String c_user_id = "" + list.get(i).getUserId();
			String c_movie_id = "" + list.get(i).getMoveId();
			String c_start = "" + list.get(i).getStart();
			if ((uid.equals(c_user_id)) && (movieid.equals(c_movie_id))
					&& (start.equals(c_start))) {
				sen_collection = "true";
				break;
			}
		}
		return sen_collection;
	}

	public boolean[] get_collect(String word) {
		List<String> collect_s = find_list(6, word);
		boolean[] collect = new boolean[collect_s.size()];
		for (int i = 0; i < collect_s.size(); i++) {
			if (collect_s.get(i).equals("true"))
				collect[i] = true;
			else if (collect_s.get(i).equals("false"))
				collect[i] = false;
		}

		return collect;

	}

	public int[][] get_movieinfo(String word) {
		try {
			int movie_len = 0;
			int movie_start = 0;
			int senlist_len = 0;
			List<String> id = find_list(5, word);
			int[][] movieinfo = new int[movie_num][3];
			for (int m = 0; m < movieinfo.length; m++)// ³õÊ¼»¯
			{
				for (int n = 0; n < movieinfo[m].length; n++) {
					movieinfo[m][n] = -1;
				}
			}
			if (id.size() > 0) {
				temp = id.get(0);

			}
			for (int i = 0; i < id.size(); i++) {

				if (!temp.equals(id.get(i))) {
					movieinfo[movie_len][0] = Integer.parseInt(temp);
					temp = id.get(i);
					movieinfo[movie_len][1] = movie_start;
					movieinfo[movie_len][2] = movie_start + senlist_len - 1;
					movie_start = i;
					senlist_len = 0;
					movie_len++;
				}

				senlist_len++;
			}
			movieinfo[movieinfo.length - 1][0] = Integer.parseInt(temp);
			movieinfo[movieinfo.length - 1][1] = movie_start;
			movieinfo[movieinfo.length - 1][2] = movieinfo[movieinfo.length - 1][1]
					+ senlist_len - 1;

			return movieinfo;
		} catch (Exception e) {
			int[][] movieinfo = { { 0, 0, 0 } };
			return movieinfo;
		}
	}

	public int[] get_movienum() {
		int movieNum[] = new int[movie_num];
		for (int n = 0; n < movieNum.length; n++) {
			movieNum[n] = 0;
		}
		return movieNum;
	}

	public String[] get_start(String word) {

		List<String> sene = find_list(2, word);
		String[] Sentences = new String[sene.size()];
		for (int i = 0; i < sene.size(); i++) {
			Sentences[i] = sene.get(i);
		}

		return Sentences;
	}

	public String[] get_end(String word) {

		List<String> sene = find_list(3, word);
		String[] Sentences = new String[sene.size()];
		for (int i = 0; i < sene.size(); i++) {
			Sentences[i] = sene.get(i);
		}

		return Sentences;
	}

	public String[] sene(String word) {

		List<String> sene = find_list(1, word);
		String[] Sentences = new String[sene.size()];
		for (int i = 0; i < sene.size(); i++) {
			Sentences[i] = sene.get(i);
		}
		Sentence_length = Sentences.length;
		return Sentences;
	}

	public String[] highlight_sene(String word) {
		String[] Sentences = sene(word);
		String[] highlight_sene = new String[Sentences.length];
		if (word.equals(""))
			word += " ";
		for (int n = 0; n < Sentences.length; n++) {
			String sen = Sentences[n];
			sen = sen.toLowerCase();
			word = word.toLowerCase();
			sen = " " + sen + " ";
			sen = sen.replace("!", " ").replace(".", " ").replace(",", " ")
					.replace("'", " ").replace(".", " ").replace("?", " ");
			int word_start = sen.indexOf(" " + word + " ");
			int word_len = word.length();
			highlight_sene[n] = Sentences[n].substring(0, word_start) // +
																		// "</font>"
					+ "<font color = '#0099ff' >"
					+ Sentences[n].substring(word_start, word_start + word_len)
					+ "</font>" + Sentences[n].substring(word_start + word_len);

		}

		return highlight_sene;
	}

	public String[] senc(String word) {

		List<String> sene = find_list(4, word);
		String[] Sentences = new String[sene.size()];
		for (int i = 0; i < sene.size(); i++) {
			Sentences[i] = sene.get(i);
		}

		return Sentences;
	}

	public boolean[] get_appear(String word) {
		boolean[] appear_or_not = new boolean[Sentence_length];
		int[][] movie_info = get_movieinfo(word);
		for (int n = 0; n < Sentence_length; n++) {
			appear_or_not[n] = false;
			for (int m = 0; m < movie_info.length; m++) {
				if (n == movie_info[m][1] || n == (movie_info[m][1] + 1))
					appear_or_not[n] = true;
			}

		}
		return appear_or_not;
	}

	public int getSentence_length() {
		return Sentence_length;
	}

	public void setSentence_length(int sentence_length) {
		Sentence_length = sentence_length;
	}

}
