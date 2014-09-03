package com.objectiva.englishmagnet.persistence.collectionOperate;

import java.util.ArrayList;
import java.util.List;
import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.persistence.collectionInfro.*;

public class CollectionSentence {

	public void saveCollection(int save_user_id, int save_move_id,
			String save_startTime, String save_word) {
		CollectionDAO cDAO = new CollectionDAO();
		Collection c = new Collection(save_user_id, save_move_id,
				save_startTime, save_word);
		cDAO.save(c);

		Wordbook wbWordbook = new Wordbook();
		wbWordbook.checkWord(save_user_id, save_word);

	}

	public void cancelCollection(int cancel_user_id, int cancel_move_id,
			String cancel_startTime, String cancel_word) {
		CollectionDAO cDAO = new CollectionDAO();
		Collection a = null;

		List<Collection> collection = cDAO.findByUserId(cancel_user_id);
		for (int i = 0; i < collection.size(); i++) {
			if ((collection.get(i).getMoveId() == cancel_move_id)
					&& (collection.get(i).getStart().equals(cancel_startTime))) {
				a = collection.get(i);
				cDAO.delete(a);
			}
		}

	}

	public void collectSen() {
		int userID = Integer.getInteger(General.USERID);
	}

	public String checkCollection(int movie_id, String start, String word) {

		String uid = General.getUserId();
		int user_id = Integer.parseInt(uid);
		try {
			CollectionDAO cdao = new CollectionDAO();
			Collection c = new Collection();

			@SuppressWarnings("rawtypes")
			List clist = new ArrayList();
			clist = cdao.findByMoveId(movie_id);

			if (clist.size() > 0) {

				for (int i = 0; i < clist.size(); i++) {

					c = (Collection) clist.get(i);
					Integer movieId = (Integer) movie_id;

					if ((c.getMoveId().equals(movieId))
							&& (c.getStart().matches(start))) {

					} else {
						if (i == clist.size() - 1) {
							saveCollection(user_id, movie_id, start, word);
						}
					}
				}
			} else {
				saveCollection(user_id, movie_id, start, word);
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return word;
	}

}
