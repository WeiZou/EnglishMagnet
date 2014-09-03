package com.objectiva.englishmagnet.persistence.collectionOperate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.persistence.collectionInfro.Collection;
import com.objectiva.englishmagnet.persistence.collectionInfro.CollectionDAO;
import com.objectiva.englishmagnet.persistence.wordBook.WordBook;
import com.objectiva.englishmagnet.persistence.wordBook.WordBookDAO;

public class Wordbook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String w[] = null;

	public void saveWord(int save_user_id, String save_word) {
		WordBookDAO cDAO = new WordBookDAO();
		WordBook c = new WordBook(save_user_id, save_word);
		cDAO.save(c);
	}

	public void loadWordList() {
		Collection wbBook = new Collection();
		CollectionDAO cDAO = new CollectionDAO();

		String uid = General.getUserId();
		int user_id = Integer.parseInt(uid);

		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		Set<String> set = new HashSet<String>();

		list = cDAO.findByUserId(user_id);
		if (list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				wbBook = (Collection) list.get(i);
				// if(!contains(w,wbBook.getWord()))
				// w[i] = wbBook.getWord();
				set.add(wbBook.getWord());

			}
			Iterator<String> it = set.iterator();
			w = new String[set.size()];
			for (int i = 0; it.hasNext(); i++) {
				w[i] = it.next();
			}
		} else {
			w = null;
		}
	}

	public static boolean contains(String[] stringArray, String source) {
		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);

		// 利用list的包含方法,进行判断
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}

	public void checkWord(int user_id, String word) {

		try {
			WordBookDAO wdao = new WordBookDAO();
			WordBook wb = new WordBook();

			@SuppressWarnings("rawtypes")
			List list = new ArrayList();
			list = wdao.findByWord(user_id);

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					wb = (WordBook) list.get(i);

					if (wb.getWord().matches(word)
							&& (wb.getUserId() == user_id)) {
					} else {
						saveWord(user_id, word);
					}
				}
			} else {
				saveWord(user_id, word);
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
	}

	public void setW(String[] w) {
		this.w = w;
	}

	public String[] getW() {
		try {
			loadWordList();
			return w;
		} catch (Exception e) {
			return null;
		}
	}
}
