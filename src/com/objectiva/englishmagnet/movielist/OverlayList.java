package com.objectiva.englishmagnet.movielist;

import java.util.Map;

import javax.faces.context.FacesContext;

import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.persistence.collectionOperate.CollectionSentence;
import com.objectiva.englishmagnet.persistence.collectionOperate.FindCollectionInfo;
import com.objectiva.englishmagnet.persistence.collectionOperate.ShowCollectionSentence;
import com.objectiva.englishmagnet.persistence.srtInfro.Srt;
import com.objectiva.englishmagnet.persistence.srtInfro.SrtDAO;
import com.objectiva.englishmagnet.persistence.srtInfro.SrtId;

public class OverlayList {
	 String senc[];
     String sene[] ;
     String[] start ;
     String[] end;
     String[] word;
     int[] movie_id ;
     static boolean[] like;
     static boolean[] dislike;
     static boolean show_overlay_sen[];
     int [] realindex;
     public void findOverlaySen(String[] start_time, int movieId, int start_pos, int size,boolean[] appear,String[] highlightsene,boolean outlike[])
     {
    	 int j=0;
    	 SrtDAO srtdao = new SrtDAO();
    	
    	 int length=0;
    	 for(int i=start_pos;i<size;i++)
    	 {
    		 if(appear[i]==true)
    			 length++;
    	 }
    	 if(length==0)length=1;
    	 start=new String[length];
    	 sene=new String[length];
    	 senc=new String[length];
    	 end=new String[length];
    	 movie_id=new int[length];
    	 like=new boolean[length];
    	 dislike=new boolean[length];
    	 show_overlay_sen=new boolean[length];
    	 word = new String[length];
    	 realindex=new int[length];
    	 for(int i=start_pos;i<start_pos+length;i++)
    	 {
    		 show_overlay_sen[j]=true;
    		 realindex[j]=i;
    		 start[j]=start_time[i];
    		 movie_id[j]=movieId;
    		 SrtId srtid =new SrtId();
			 srtid.setMovieId(movie_id[j]);
			 srtid.setStart(start[j]);
			 Srt srtList = srtdao.findById(srtid);
			 senc[j]=srtList.getSenc();
			 sene[j]=highlightsene[i];
			 end[j]=srtList.getEnd();
			 like[j]=outlike[i];
			 dislike[j]=!outlike[i];
			 word[j]=MovieList.word_temp;
    		 j++;
    	 }

     }
     
     
     
     public void findOverlaySenSimple(String[] start_time, int[] movieId,String[] highlightsene,String[] outword,boolean[] appear)
     {
    	
    	 SrtDAO srtdao = new SrtDAO();
    	
    	 int length=start_time.length;
    	
    	 start=new String[length];
    	 sene=new String[length];
    	 senc=new String[length];
    	 end=new String[length];
    	 movie_id=new int[length];
    	 like=new boolean[length];
    	 dislike=new boolean[length];
    	 word = new String[length];
    	 realindex=new int[length];
    	 show_overlay_sen= new boolean[length];
    	 for(int i=0;i<length;i++)
    	 {
    		 show_overlay_sen[i]=true;
    		 if(appear[i]==false)show_overlay_sen[i]=false;
    		 start[i]=start_time[i];
    		 movie_id[i]=movieId[i];
    		 SrtId srtid =new SrtId();
			 srtid.setMovieId(movie_id[i]);
			 srtid.setStart(start[i]);
			 Srt srtList = srtdao.findById(srtid);
			 senc[i]=srtList.getSenc();
			// sene[i]=srtList.getSene();
			 end[i]=srtList.getEnd();
			 like[i]=true;
			 dislike[i]=false;
			 word[i]=outword[i];
			 realindex[i]=i;
    		 
    	 }
    	 sene=highlightsene;
     }
     
		public void collectSen(int movie_id, int collect_index)
		{   String uid=General.getUserId();
		    if(uid!=null)
			{
		    	dislike[collect_index]=false;
				like[collect_index]=true;
				MovieList.dislike[realindex[collect_index]]=false;
				MovieList.like[realindex[collect_index]]=true;
				FindCollectionInfo.dislike[realindex[collect_index]]=false;
				FindCollectionInfo.like[realindex[collect_index]]=true;
		        FacesContext facesContext = FacesContext.getCurrentInstance(); 
		        
		        Map params = facesContext.getExternalContext().getRequestParameterMap();
		        	System.out.println(params);
		        	String myParam_type = (String) params.get("collect");
		        	//System.out.println(myParam_type);
		        	if (myParam_type != null && myParam_type.length() > 0) {
		        		System.out.println(myParam_type);
		        	}
		        	int index=Integer.parseInt(myParam_type);
		       
	
				CollectionSentence cs=new CollectionSentence();
				//System.out.println("movieid: "+movie_id+" start: "+start[index]+"word "+word[index]);
				cs.checkCollection(movie_id,start[index],word[index]);
			}
		}
		
		public void cancelCollectSen(int index) {
			
			dislike[index]=true;
			like[index]=false;
			try
			{
				MovieList.dislike[realindex[index]]=true;
			    MovieList.like[realindex[index]]=false;
			}
			catch(Exception e){System.out.println(" LALALA");} 
			if(FindCollectionInfo.dislike!=null&&FindCollectionInfo.like!=null)
			{
				FindCollectionInfo.dislike[index]=true;
				FindCollectionInfo.like[index]=false;
			}
			if(ShowCollectionSentence.show_sentence!=null)
				ShowCollectionSentence.show_sentence[index]=false;
			String uid = General.getUserId();
			int cancel_user_id = Integer.parseInt(uid);
			CollectionSentence cs = new CollectionSentence();
			String cancel_start = start[index];
			String word_temp = word[index];
			int cancel_movie_id = movie_id[index];
			cs.cancelCollection(cancel_user_id, cancel_movie_id, cancel_start,
					word_temp);
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



	public boolean[] getShow_overlay_sen() {
		return show_overlay_sen;
	}



	public void setShow_overlay_sen(boolean[] show_overlay_sen) {
		this.show_overlay_sen = show_overlay_sen;
	}
     

}
