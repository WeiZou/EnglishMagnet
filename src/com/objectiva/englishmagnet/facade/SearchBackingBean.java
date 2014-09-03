package com.objectiva.englishmagnet.facade;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.movielist.MovieList;
import com.objectiva.englishmagnet.persistence.log.Select;
import net.sf.json.JSONObject;

public class SearchBackingBean implements Serializable {

	private static final long serialVersionUID = -8719500002139004868L;

	public String input_word;

	public String word;
	private static String reword;
	private static String result[];
	public static boolean validate = false;
	private static String usPhonetic;
	private static String ukPhonetic;
	private String searchErr;
	private int length;

	public SearchBackingBean() {
		super();

	}

	public String invokeAPI() {
		word = input_word;
		if (!word.equals(null)) {
			validate = true;
		} else {
			validate = false;
		}

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

			reword = jsonObj.getString("query");
			if (reword.indexOf('\\') != -1)
				reword = reword.replace("\\", "");
			General.setWord(reword);

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
			searchErr = "查询不到结果";
			validate = false;
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
		MovieList.get(reword);
		Select.ppush();
		return null;
	}

	public String search_button(String word) {
		if (!word.equals(null)) {
			validate = true;
		} else {
			validate = false;
		}

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

			reword = jsonObj.getString("query");
			if (reword.indexOf('\\') != -1)
				reword = reword.replace("\\", "");
			General.setWord(reword);

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
			searchErr = "查询不到结果";
			validate = false;
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
		MovieList.get(reword);
		Select.ppush();
		return null;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setReword(String reword) {
		this.reword = reword;
	}

	public String getReword() {
		return reword;
	}

	public void setSearchErr(String searchErr) {
		this.searchErr = searchErr;
	}

	public String getSearchErr() {
		return searchErr;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setResult(String[] result) {
		this.result = result;
	}

	public String[] getResult() {
		return result;
	}

	public void setUsPhonetic(String usPhonetic) {
		this.usPhonetic = usPhonetic;
	}

	public String getUsPhonetic() {
		return usPhonetic;
	}

	public void setUkPhonetic(String ukPhonetic) {
		this.ukPhonetic = ukPhonetic;
	}

	public String getUkPhonetic() {
		return ukPhonetic;
	}

	public boolean getValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		validate = false;
	}

	public String getInput_word() {
		return input_word;
	}

	public void setInput_word(String input_word) {
		this.input_word = input_word;
	}
}
