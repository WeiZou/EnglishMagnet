package com.objectiva.englishmagnet.movielist;

import java.util.List;

import com.objectiva.englishmagnet.persistence.collectionInfro.Collection;
import com.objectiva.englishmagnet.persistence.collectionInfro.CollectionDAO;
import com.objectiva.englishmagnet.persistence.movieInfro.Movieinfo;
import com.objectiva.englishmagnet.persistence.movieInfro.MovieinfoDAO;



public class test {
	public test()
	{
	Collection movieinfo=new Collection();
	CollectionDAO dao=new CollectionDAO();
	List<Collection> list=dao.findAll();
	Movieinfo movieinfo1=new Movieinfo();
	MovieinfoDAO dao1=new MovieinfoDAO();
	List<Movieinfo> list1=dao1.findAll();
	for(int i=0;i<list.size();i++)
	{
		System.out.println("UserId: "+list.get(i).getUserId()+"  MovieIdP:"+list.get(i).getMoveId()+" word:"+list.get(i).getWord()+" StartTime:"+list.get(i).getStart());
		
	}
	
	for(int i=0;i<list1.size();i++)
	{
		System.out.println("Age: "+list1.get(i).getAge()+"  Nation:"+list1.get(i).getRegion()+" Type:"+list1.get(i).getType()+" Actors:"+list1.get(i).getActors());
		
	}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		test t=new test();
		
	}

}