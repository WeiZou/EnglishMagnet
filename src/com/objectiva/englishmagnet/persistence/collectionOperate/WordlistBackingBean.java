package com.objectiva.englishmagnet.persistence.collectionOperate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import javax.faces.context.FacesContext;
import com.objectiva.englishmagnet.facade.MenuBarBackingBean;
import com.objectiva.englishmagnet.movielist.MovieList;
import net.sf.json.JSONObject;

public class WordlistBackingBean implements Serializable {
	private static final long serialVersionUID = -8719500002139004868L;

	private String result[];
	private String word;
	private String usPhonetic;
	private String ukPhonetic;
	private int length;

	public static boolean wordList_show = false;

	public WordlistBackingBean() {
		super();
	}

	public boolean isWordList_show() {
		return wordList_show;
	}

	public void setWordList_show(boolean wordList_show) {
		WordlistBackingBean.wordList_show = wordList_show;
	}

	public void invokeAPI() {

		String url = "http://fanyi.youdao.com/openapi.do";

		String keyfrom = "duanyi2008";
		String key = "462413268";
		String doctype = "json";
		InputStream inStr = null;
		BufferedReader br = null;
		StringBuffer buf = null;
		String param = null;
		String urlName = null;
		URL realUrl = null;
		URLConnection conn = null;
		String tempVar = null;

		JSONObject jsonObj = null;
		String explaination = "";

		try {
			word = URLEncoder.encode(word, "utf-8");
			buf = new StringBuffer();
			buf.append("keyfrom=");
			buf.append(keyfrom);
			buf.append("&key=");
			buf.append(key);
			buf.append("&type=data&doctype=");
			buf.append(doctype);
			buf.append("&version=1.1&q=");
			buf.append(word);

			param = buf.toString();

			urlName = (url + "?" + param).toString();
			realUrl = new URL(urlName);
			conn = realUrl.openConnection();
			conn.connect();
			inStr = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(inStr, "UTF-8"));

			tempVar = br.readLine();

			while (tempVar != null) {
				explaination += tempVar;
				tempVar = br.readLine();
			}

			explaination = new String(explaination);
			jsonObj = JSONObject.fromObject(explaination);

			jsonObj.getString("query");

			usPhonetic = jsonObj.getJSONObject("basic")
					.getString("us-phonetic");
			ukPhonetic = jsonObj.getJSONObject("basic")
					.getString("uk-phonetic");

			length = jsonObj.getJSONObject("basic").getJSONArray("explains")
					.length();
			result = new String[length];
			for (int i = 0; i < length; i++) {
				result[i] = jsonObj.getJSONObject("basic")
						.getJSONArray("explains").getString(i);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				inStr.close();
				br.close();
				inStr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void back() {
		WordlistBackingBean.wordList_show = false;
		MenuBarBackingBean.type_show = true;
		MovieList.movie_show = true;
	}

	public void deliverWord() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Map params = facesContext.getExternalContext().getRequestParameterMap();

		String string_index = (String) params.get("word_list");
		if (string_index != null && string_index.length() > 0) {

			this.word = string_index;

		}
		invokeAPI();

		FindCollectionInfo fcti = new FindCollectionInfo();
		fcti.findCollection(word);

		MenuBarBackingBean.type_show = false;
		MovieList.movie_show = false;
		WordlistBackingBean.wordList_show = true;
		return ;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String[] getResult() {
		return result;
	}

	public String getUsPhonetic() {
		return usPhonetic;
	}

	public String getUkPhonetic() {
		return ukPhonetic;
	}
}
