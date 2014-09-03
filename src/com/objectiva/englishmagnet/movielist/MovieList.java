package com.objectiva.englishmagnet.movielist;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.objectiva.englishmagnet.facade.MenuBarBackingBean;
import com.objectiva.englishmagnet.facade.SearchBackingBean;
import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.persistence.collectionOperate.CollectionSentence;
import com.objectiva.englishmagnet.persistence.collectionOperate.WordlistBackingBean;
import com.objectiva.englishmagnet.utility.GetMovieInfo;
import com.objectiva.englishmagnet.persistence.collectionOperate.*;
public class MovieList implements Serializable {
	private static final long serialVersionUID = -8719500002139004868L;
    static GetMovieInfo t = new GetMovieInfo();
    static String test_ch[] = t.senc(" ");
    static String test_en[] = t.highlight_sene(" ");
    static String[] start = t.get_start(" ");
    static String[] end = t.get_end(" ");
    static String word_temp="";
    static int  movieinfo[][] = t.get_movieinfo(" ");
    String moviename[]={"","Paradiso","Rio2"};
    static int length=test_ch.length;
    static boolean[] appear ;
    static boolean[] type_appear;
	public static  boolean movie_show=false;
    static boolean[] like;
	static boolean[] dislike;
	public static  String userId;
	public static boolean yesorno;
	

		public void collectSen(int movie_id, int collect_index)
		{   String uid=General.getUserId();
		    if(uid!=null)
			{
		    	dislike[collect_index]=false;
				like[collect_index]=true;
		        FacesContext facesContext = FacesContext.getCurrentInstance(); 
		        
		        Map params = facesContext.getExternalContext().getRequestParameterMap();
		        	String myParam_type = (String) params.get("collect");
		        	if (myParam_type != null && myParam_type.length() > 0) {
		        	}
		        	int index=Integer.parseInt(myParam_type);
		       
	
				CollectionSentence cs=new CollectionSentence();
				cs.checkCollection(movie_id,start[index],word_temp);
			}
		    else{
		    userId = "";
		    }
		}
		
		public void cancelCollectSen(int movie_id, int collect_index,String cancel_start){
			dislike[collect_index]=true;
			like[collect_index]=false;
			String uid = General.getUserId();
			int cancel_user_id=Integer.parseInt(uid);
			CollectionSentence cs=new CollectionSentence();
			cs.cancelCollection(cancel_user_id,movie_id, cancel_start, word_temp);
		}
	
	public String change_appear(int start,int size) {
		int m = start;
		for (int k = start; k < size; k++)
			if (appear[k] == false) {
				m = k;
				break ;
			}
		
		if (size >= 3 && appear[m] == false) {
			for (int n = m; n < size && n < m + 5; n++) {
				appear[n] = true;
			}
		} 
//		else if (appear[size] == true) {
//			for (int n = start; n < size; n++) {
//				appear[n] = false;
//				if (n == start|| n == start+1)
//					appear[n] = true;
//			}
//		}
		return null;
	}

	public String less_appear(int start,int size) {
		for (int n = start; n < size; n++) {
			appear[n] = false;
			if (n == start || n == (start+1))
				appear[n] = true;
		}

		return null;
	}

	public static  String get(String word) {
		ShowCollectionSentence.show_collect=false;
		ShowCollectionSentence.anti_show_collect=true;
		word_temp=word;
		if(word==null||word.equals("")){

			movie_show=false;
			return null;
		}
		String uid = General.getUserId();
		userId = uid;
		length=t.senc(word).length;
		test_ch = t.senc(word);
		test_en = t.highlight_sene(word);
		start = t.get_start(word);
		end = t.get_end(word);
		appear = t.get_appear(word);
		movieinfo = t.get_movieinfo(word);
		
		
		for(int i=0;i<movieinfo.length;i++)
		{
			if(movieinfo[i][2]-movieinfo[i][1]<=2)
				yesorno = false;
			else
				yesorno = true;
		}
		
		
		
		like=t.get_collect(word);
		dislike=new boolean[like.length];
		for(int n=0;n<like.length;n++)
		{
			if(like[n]==false)
			    dislike[n]=true;
			else if (like[n]==true)
				dislike[n]=false;
		}
		if(length==0)movie_show=false;
		else{
			WordlistBackingBean.wordList_show=false;
			MenuBarBackingBean.type_show=true;
			movie_show=true;
			
		}

		return null;
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

	public String[] getTest_ch() {
		return test_ch;
	}

	public void setTest_ch(String[] test_ch) {
		this.test_ch = test_ch;
	}

	public GetMovieInfo getT() {
		return t;
	}

	public void setT(GetMovieInfo t) {
		this.t = t;
	}

	public String[] getTest_en() {
		return test_en;
	}

	public void setTest_en(String[] test_en) {
		this.test_en = test_en;
	}

	public boolean[] getAppear() {
		return appear;
	}

	public void setAppear(boolean[] appear) {
		this.appear = appear;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isMovie_show() {
		return movie_show;
	}

	public void setMovie_show(boolean movie_show) {
		this.movie_show = movie_show;
	}

	public  int[][] getMovieinfo() {
		return movieinfo;
	}

	public  void setMovieinfo(int[][] movieinfo) {
		this.movieinfo = movieinfo;
	}

	public String[] getMoviename() {
		return moviename;
	}

	public void setMoviename(String[] moviename) {
		this.moviename = moviename;
	}

	public boolean[] getLike() {
		return like;
	}

	public void setLike(boolean[] like) {
		this.like = like;
	}

	public boolean[] getDislike() {
		return dislike;
	}

	public void setDislike(boolean[] dislike) {
		this.dislike = dislike;
	}

	public String getWord_temp() {
		return word_temp;
	}

	public void setWord_temp(String word_temp) {
		this.word_temp =word_temp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean getYesorno() {
		return yesorno;
	}

	public void setYesorno(boolean yesorno) {
		this.yesorno = yesorno;
	}

	




}
