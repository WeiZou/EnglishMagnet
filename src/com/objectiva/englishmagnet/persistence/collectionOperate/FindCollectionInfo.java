package com.objectiva.englishmagnet.persistence.collectionOperate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.persistence.collectionInfro.Collection;
import com.objectiva.englishmagnet.persistence.collectionInfro.CollectionDAO;
import com.objectiva.englishmagnet.persistence.srtInfro.Srt;
import com.objectiva.englishmagnet.persistence.srtInfro.SrtDAO;

public class FindCollectionInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String word_temp;
	private static int[] mov ;
	private static String []wordd;


	private static String[] sente ;
	
	private static String[] sentc ;
	private static String[] startt ;
	private static String[] endt ;
	
	public static boolean[] like;
	public static boolean[] dislike;
	
	
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
	public int[] getMov() {
		return mov;
	}

	public void setMov(int[] mov) {
		this.mov = mov;
	}

	public String[] getSente() {
		return sente;
	}

	public void setSente(String[] sente) {
		this.sente = sente;
	}

	public String[] getSentc() {
		return sentc;
	}

	public void setSentc(String[] sentc) {
		this.sentc = sentc;
	}

	public String[] getStartt() {
	
		return startt;
	}

	public void setStartt(String[] startt) {
		this.startt = startt;
	}

	public String[] getEndt() {
		return endt;
	}

	public void setEndt(String[] endt) {
		this.endt = endt;
	}

	public String getWord_temp() {
		return word_temp;
	}

	public void setWord_temp(String word_temp) {
		this.word_temp = word_temp;
	}
	
	

	

	public String[] getWordd() {
		return wordd;
	}

	public void setWordd(String[] wordd) {
		this.wordd = wordd;
	}

	public int[] findMovieIdInt(int user_id, String word){
		int []movie_id = null;
		List<Integer> movie_list_temp= new ArrayList<Integer>();
		CollectionDAO cDAO = new CollectionDAO();
		Collection c = new Collection();
		List list = cDAO.findByUserId(user_id);
	
		if(list.size() > 0){
			
			for(int i = 0; i < list.size(); i++){
				c = (Collection) list.get(i);
				if( (c.getUserId() == user_id) && (c.getWord().matches(word)))
				{
					movie_list_temp.add(c.getMoveId());
				}
			}
		}
		movie_id = new int[movie_list_temp.size()];
		for(int n=0;n<movie_id.length;n++)
		{
			movie_id[n]=movie_list_temp.get(n);
		}
//		for(int m = 0; m < movie_id.length; m++){
//			System.out.println(movie_id[m]);
//		}
		return movie_id;
	}
	
	public String[] findStartTime(int user_id, String word){
		String []start = null;
		List<String> start_time_temp = new ArrayList<String>();
		CollectionDAO cDAO = new CollectionDAO();
		Collection c = new Collection();
		List list = cDAO.findByUserId(user_id);
	
		if(list.size() > 0){
			start = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				c = (Collection) list.get(i);
				if( (c.getUserId() == user_id) && (c.getWord().matches(word))){
					start_time_temp.add(c.getStart());
				}
			}
		}
		start = new String[start_time_temp.size()];
		for(int n = 0; n < start.length; n++){
			start[n] = start_time_temp.get(n);
		}
		return start;
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public FindCollectionInfo()
	{
		System.out.println("goooooooooooooooooooooFindCollection");
	}
	
	public void cancelCollectSen(int index)
	{
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Map params = facesContext.getExternalContext().getRequestParameterMap();
		String getword = (String) params.get("wordlist_word");
		if (getword != null && getword.length() > 0) {
			word_temp=getword;
		}
		System.out.println("cancel-------------------------"+index);
		
		String uid = General.getUserId();
		int user_id = Integer.parseInt(uid);
		
		CollectionSentence cs=new CollectionSentence();
		
		System.out.println("parameter------------"+user_id+"-----"+mov[index]+" ---------"+startt[index]+ "----------"+word_temp);
		cs.cancelCollection(user_id,mov[index], startt[index], word_temp);
		
		like[index]=false;
		dislike[index]=true;
	}
	public void collectSen()
	{
		   String uid=General.getUserId();
		    if(uid!=null)
			{
		        FacesContext facesContext = FacesContext.getCurrentInstance(); 
		      
		        Map params = facesContext.getExternalContext().getRequestParameterMap();
		       	System.out.println(params);
		        String param = (String) params.get("collect");
		        	
		        	if (param  != null && param.length() > 0) {
		        		System.out.println(param );
		        	}
		        	int index=Integer.parseInt(param);
		       
		       
				        	String temp_word = (String) params.get("wordlist_word");
				        	
				        	//System.out.println(myParam_type);
				        	if (temp_word != null && temp_word.length() > 0) {
				        		System.out.println(temp_word);
				        	}
		    
				
				CollectionSentence cs=new CollectionSentence();
				cs.checkCollection(mov[index],startt[index],word_temp);
				dislike[index]=false;
				like[index]=true;
			}
		    
		}
	
	public void findCollection( String word){
		String uid = General.getUserId();
		int user_id = Integer.parseInt(uid);
		
		List<Integer> movie_id_ = new ArrayList<Integer>();
		List<String> sene = new ArrayList<String>();
		List<String> senc = new ArrayList<String>();
		List<String> start_time = new ArrayList<String>();
		List<String> end_time = new ArrayList<String>();
		
		
		Srt srt = new Srt();
		SrtDAO srtdao = new SrtDAO();
		List<Srt> srtList = srtdao.findAll();
		String[] All_sene = new String[srtList.size()];
		like = new boolean[srtList.size()];
		Arrays.fill(like, true);
		dislike = new boolean[srtList.size()];
		Arrays.fill(dislike, false);
		int[] movie_id = findMovieIdInt(user_id, word);
		String[] start = findStartTime(user_id, word);
		
//		for(int n = 0; n < movie_id.length; n++)
//			System.out.println(movie_id[n] + " " + start[n]);
		int latest = 0;
		for(int j = 0; j < movie_id.length; j++){
			
			for (int i = 0; i < srtList.size(); i++) {
				
				String Sene = srtList.get(i).getSene();
				String Senc = srtList.get(i).getSenc();
				Integer Movie_id = srtList.get(i).getId().getMovieId();
				String Start = srtList.get(i).getId().getStart();
				String End = srtList.get(i).getEnd();
				
				Integer movieId = (Integer) movie_id[j]; 
				if(Movie_id.equals(movieId) && Start.equals(start[j])){
					sene.add(Sene);
					senc.add(Senc);
					start_time.add(Start);
					end_time.add(End);
					movie_id_.add(Movie_id);
				}
			}
			
			
			System.out.println(movie_id_.get(latest) + "  " + sene.get(latest) + "  " + senc.get(latest) +"  " 
					+ start_time.get(latest) + "  " + end_time.get(latest));
			
			latest++;
		
		}
		word_temp=word;
		mov = new int[movie_id_.size()];
		sente = new String[movie_id_.size()];
		sentc = new String[movie_id_.size()];
		startt = new String[movie_id_.size()];
		endt = new String[movie_id_.size()];
		wordd = new String[movie_id_.size()];
		
		for(int i = 0; i < movie_id_.size(); i++){
			mov[i] = movie_id_.get(i);
			sente[i] = sene.get(i);
			sentc[i] = senc.get(i);
			startt[i] = start_time.get(i);
			endt[i] = end_time.get(i);
			wordd[i] = word_temp;
		}
		sente=highlight_sene(word,sente);
	}
	
	public String[] highlight_sene(String word, String sene[]) {
		String[] Sentences = sene;
		String[] highlight_sene = new String[Sentences.length];
		
		for (int n = 0; n < Sentences.length; n++) {
			String sen = Sentences[n];
			word = word.toLowerCase();
			sen = sen.toLowerCase();
			sen = " " + sen + " ";
			sen = sen.replace("!", " ").replace(".", " ").replace(",", " ")
					.replace("'", " ").replace(".", " ").replace("?", " ");
			int word_start = sen.indexOf(" " + word + " ");
			int word_len = word.length();
			highlight_sene[n] =  Sentences[n].substring(0, word_start) //+ "</font>"
					+ "<font color = '#0099ff' >"
					+ Sentences[n].substring(word_start, word_start + word_len)
					+ "</font>" + Sentences[n].substring(word_start + word_len);

		}

		return highlight_sene;
	}
}
