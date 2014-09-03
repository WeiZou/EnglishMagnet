//package com.objectiva.englishmagnet.utility;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.faces.component.UIViewRoot;
//import javax.faces.component.html.HtmlInputText;
//import javax.faces.context.FacesContext;
//
//import com.objectiva.englishmagnet.facade.SearchBackingBean;
//import com.objectiva.englishmagnet.general.General;
//import com.objectiva.englishmagnet.movielist.GetElement;
//import com.objectiva.englishmagnet.persistence.collectionInfro.Collection;
//import com.objectiva.englishmagnet.persistence.collectionInfro.CollectionDAO;
//import com.objectiva.englishmagnet.persistence.srtInfro.Srt;
//import com.objectiva.englishmagnet.persistence.srtInfro.SrtDAO;
//
//public class GetMovieInfo implements Serializable{
//	int Sentence_length;
//    int movie_num;
//	String temp;
//	static public boolean[] s_collect_sen={} ;
//	static public String[] s_sene={} ;
//	static public String[] s_senc={} ;
//	static public String[] s_start_time={} ;
//	static public String[] s_end_time ={};
//	static public String[] s_movieid={} ;
//	List<Integer> index = new ArrayList<Integer>();
//	SearchBackingBean Search = new SearchBackingBean();
//    //String word=" ";
//
//
////
////	public String get_word() {
////		try
////		{
////		UIViewRoot viewRoot = null;
////		GetElement GT = new GetElement();
////		// System.out.println("~~~~~~~~~");
////		viewRoot = FacesContext.getCurrentInstance().getViewRoot();
////		HtmlInputText input = (HtmlInputText) GT.getElementById(viewRoot,"search_input");
////		String word = (String) input.getValue();
////		// System.out.println(" word:"+word);
////		 if(word=="")return " ";
////		  return word;
////		}
////		catch(Exception e)
////		{
////		    System.out.println("Exception");
////		    Sentence_length=0;
////			return " ";
////		}
////
////	}
//
//	public List<Integer> find_list(String word) {
//		movie_num=0;
//		temp="";
////		List<String> sene = new ArrayList<String>();
////		List<String> senc = new ArrayList<String>();
////		List<String> start_time = new ArrayList<String>();
////		List<String> end_time = new ArrayList<String>();
////		List<String> movieid = new ArrayList<String>();
//		List<Integer> index = new ArrayList<Integer>();
//		Srt srt = new Srt();
//		SrtDAO srtdao = new SrtDAO();
//		List<Srt> srtList = srtdao.findAll();
//		//String[] All_sene = new String[srtList.size()];
//		//String word = get_word().trim();
//		
//		//word = get_word().trim();
//		if (word.equals("")){
//			word += " ";
//			
//		}
//		// String word = "his";
//		// System.out.println("get:"+word);
//		for (int i = 0; i < srtList.size(); i++) {
//			// System.out.println(srtList.get(i).getId().getMovieId()+" "+srtList.get(i).getSene()+"  "+srtList.get(i).getSenc());
//			String Sentence = srtList.get(i).getSene();
////			String Senc = srtList.get(i).getSenc();
////			String Start = srtList.get(i).getId().getStart();
////			String End = srtList.get(i).getEnd();
//			String MovieId = ""+srtList.get(i).getId().getMovieId();
//
//			String sen = Sentence;
//			sen = sen.toLowerCase();
//			word = word.toLowerCase();
//
//			sen = sen.replace("!", " ").replace(".", " ").replace(",", " ")
//					.replace("'", " ").replace(".", " ").replace("?", " ");
//			String[] sen_word = sen.split(" ");
//			for (int m = 0; m < sen_word.length; m++) {
//				
//				// System.out.println(sen_word[m]);
//				if (word.equals(sen_word[m])) {
//					if(!temp.equals(MovieId))
//					{
//						//System.out.println("temp:"+temp+"MovieId:"+MovieId);
//						temp = MovieId;
//						movie_num++;
//					}
//
//					index.add(i);
////					sene.add(Sentence);
////					senc.add(Senc);
////					start_time.add(Start);
////					end_time.add(End);
////					movieid.add(MovieId);
//					
//					// System.out.println(Sentence);
//					break;
//				}
//			}
//
//		}
//		temp="";
//
////		if(flag==1)
////		    return sene;
////		else if(flag==2)
////			return start_time;
////		else if(flag==3)
////			return end_time;
////		else if(flag==4)
////			return senc;
////		else if(flag==5)
////			return movieid;
//		return index;
//	}
//
//	public void set_all(String word)
//	{
//		List<Integer> index = find_list(word);
//		Srt srt = new Srt();
//		SrtDAO srtdao = new SrtDAO();
//		List<Srt> srtList = srtdao.findAll();
//		s_collect_sen=new boolean[index.size()] ;
//	    s_sene=new String[index.size()] ;
//		s_senc=new String[index.size()] ;
//	    s_start_time=new String[index.size()] ;
//		s_end_time=new String[index.size()] ;
//		s_movieid=new String[index.size()] ;
//		for(int n=0;n<index.size();n++)
//		{
//			s_sene[n]=srtList.get(index.get(n)).getSene();
//			s_senc[n]=srtList.get(index.get(n)).getSenc();
//			s_start_time[n]=srtList.get(index.get(n)).getId().getStart();
//			s_end_time[n]=srtList.get(index.get(n)).getEnd();
//			s_movieid[n]=""+srtList.get(index.get(n)).getId().getMovieId();
//			s_collect_sen[n]=collection_or_not(s_movieid[n],s_start_time[n]);
//		}
//	}
//	public boolean collection_or_not(String movieid, String start)
//	{
//		boolean sen_collection = false;
//		Collection movieinfo=new Collection();
//		CollectionDAO dao=new CollectionDAO();
//		List<Collection> list=dao.findAll();
//		String uid = General.getUserId();
//	//	int user_id = Integer.parseInt(uid);
//		for(int i=0;i<list.size();i++)
//		{
//			//System.out.println("UserId: "+list.get(i).getUserId()+"  MovieIdP:"+list.get(i).getMoveId()+" word:"+list.get(i).getWord()+" StartTime:"+list.get(i).getStart());
//			String c_user_id = ""+list.get(i).getUserId();
//			String c_movie_id = ""+list.get(i).getMoveId();
//			String c_start = ""+list.get(i).getStart();
//			if((uid.equals(c_user_id))&&(movieid.equals(c_movie_id))&&(start.equals(c_start))) {
//				sen_collection = true;
//				break;
//			}
//		}
//		return sen_collection;
//	}
//
//	public int[][] get_movieinfo(String word)
//	{
//		try{
//		int movie_len=0;
//		int movie_start=0;
//		int senlist_len=0;
//		String[] id = s_movieid;
//		int [][] movieinfo =new int [movie_num][3];
//		for(int m=0;m<movieinfo.length;m++)//³õÊ¼»¯
//		{
//			for(int n=0;n<movieinfo[m].length;n++)
//			{
//				movieinfo[m][n]=-1;
//			}
//		}
//		if(id.length>0){
//			//temp=id.get(0);
//			temp=id[0];
//			
//		}
//		for (int i = 0; i < id.length; i++) {
//			
//			
//			if(!temp.equals(id[i]))
//			{
//				movieinfo[movie_len][0] = Integer.parseInt(temp);
//				temp = id[i];
//				movieinfo[movie_len][1] = movie_start;
//				movieinfo[movie_len][2] = movie_start+senlist_len-1;
//				//if(movie_len==movieinfo.length-2)
//				//movieinfo[movie_len+1][1]=movie_start+senlist_len;
//				movie_start=i;
//				senlist_len=0;
//				movie_len++;
//			}
//
//			//System.out.println("get:" + Sentences[i]);
//			senlist_len++;
//		}
//		movieinfo[movieinfo.length-1][0]=Integer.parseInt(temp);
//		movieinfo[movieinfo.length-1][1]=movie_start;
//	//	System.out.println("idNum:"+movie_num);
//        movieinfo[movieinfo.length-1][2]=movieinfo[movieinfo.length-1][1]+senlist_len-1;
//        
//       // System.out.println("movieinfoLength:"+movieinfo.length+"movieinfoFirst:"+movieinfo[0][2]+"movieinfoLast:"+movieinfo[movieinfo.length-1][2]);
////		for(int x=0;x<movieinfo.length;x++)
////		{
////			System.out.println("movieinfo"+x+":");
////			for(int y=0;y<movieinfo[x].length;y++)
////				System.out.println(movieinfo[x][y]+"---------");
////		}
//        return movieinfo;
//		}
//		catch (Exception e)
//		{
//			int [][] movieinfo ={{0,0,0}};
//			return  movieinfo;
//		}
//	}
//	public int[] get_movienum()
//	{
//		int movieNum[]=new int[movie_num];
//		for(int n=0;n<movieNum.length;n++)
//		{
//			movieNum[n]=0;
//		}
//		return movieNum;
//	}
//
//
//
//	
//
//   
//	public String[] highlight_sene(String word) {
//		String[] Sentences = s_sene;
//		String[] highlight_sene = new String[Sentences.length];
//		//String word = get_word().trim();
//		//word = get_word().trim();
//		if (word.equals(""))
//			word += " ";
//		// String word = "his";
//		for (int n = 0; n < Sentences.length; n++) {
//			String sen = Sentences[n];
//			sen = sen.toLowerCase();
//			sen = " " + sen + " ";
//			sen = sen.replace("!", " ").replace(".", " ").replace(",", " ")
//					.replace("'", " ").replace(".", " ").replace("?", " ");
//			int word_start = sen.indexOf(" " + word + " ");
//			int word_len = word.length();
//			highlight_sene[n] =  Sentences[n].substring(0, word_start) //+ "</font>"
//					+ "<font color = '#0099ff' >"
//					+ Sentences[n].substring(word_start, word_start + word_len)
//					+ "</font>" + Sentences[n].substring(word_start + word_len);
//
//		}
//
//		return highlight_sene;
//	}
//
//    public boolean []get_appear(String word)
//    {
//    	boolean []appear_or_not=new boolean[Sentence_length];
//    	System.out.println(Sentence_length);
//        int [][]movie_info=get_movieinfo(word);
//    	for(int n=0;n<Sentence_length;n++)
//    	{
//    		appear_or_not[n]=false;
//    		for(int m=0;m<movie_info.length;m++)
//    		{
//    			if(n==movie_info[m][1]||n==(movie_info[m][1]+1))appear_or_not[n]=true;
//    		}
//    		
//    		//System.out.println(n+":"+appear_or_not[n]);
//    	}
//    	//System.out.println(appear_or_not[Sentence_length-1]);
//    	return appear_or_not;
//    }
//	public static void main(String[] args) {
//
////		GetMovieInfo t = new GetMovieInfo();
////		// List<String> sene =t.find_sene();
////		String[] test = t.highlight_sene();
////		for (int n = 0; n < test.length; n++) {
////		//	System.out.println("get:" + test[n]);
////		}
////		List<String> start = t.find_list(2);
////		List<String> end = t.find_list(3);
////		for (int n = 0; n < test.length; n++) {
////			//System.out.println("start:" + start.get(n));
////			//System.out.println("end:" + end.get(n));
////		}
//	}
//
//	public int getSentence_length() {
//		return Sentence_length;
//	}
//
//	public void setSentence_length(int sentence_length) {
//		Sentence_length = sentence_length;
//	}
//
///*	public String getWord() {
//		return word;
//	}
//
//	public void setWord(String word) {
//		this.word = word;
//	}*/
//
//
//}
