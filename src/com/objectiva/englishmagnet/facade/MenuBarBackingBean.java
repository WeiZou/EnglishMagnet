package com.objectiva.englishmagnet.facade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.objectiva.englishmagnet.persistence.collectionOperate.ShowCollectionSentence;
import com.objectiva.englishmagnet.persistence.movieInfro.Movieinfo;
import com.objectiva.englishmagnet.persistence.movieInfro.MovieinfoDAO;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MenuBarBackingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// records the param value for the menu item which fired the event
	private String param;

	// our movie_mune
	private String param_type = null;
	private String param_nation = null;
	private String param_age = null;
	private String actor = "演员";

	public static boolean type_show = true;

	static MovieinfoDAO Select = new MovieinfoDAO();

	static int len = Select.findAll().size();
	static MenuBarBackingBean t = new MenuBarBackingBean();
	private static boolean[] typelist = new boolean[len];
	private static boolean[] nationlist = new boolean[len];
	private static boolean[] agelist = new boolean[len];
	private static boolean[] actorlist = new boolean[len];
	private static boolean[] all = t.get_all();

	public boolean[] get_all() {
		boolean[] get_All = new boolean[len];
		Arrays.fill(get_All, true);
		return get_All;
	}

	public void submitActor(ActionEvent e) {
		if (actor == null) {
			return;
		} else {
			getMovie();
		}
	}

	public void listener(ActionEvent e) {
		ShowCollectionSentence.show_collect = false;
		ShowCollectionSentence.anti_show_collect = true;
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Map params = facesContext.getExternalContext().getRequestParameterMap();
		String myParam_type = (String) params.get("myParam_type");
		if (myParam_type != null && myParam_type.length() > 0) {
			setParam_type(myParam_type);
		}

		String myParam_age = (String) params.get("myParam_age");
		if (myParam_age != null && myParam_age.length() > 0) {
			setParam_age(myParam_age);

		}

		String myParam_nation = (String) params.get("myParam_nation");
		if (myParam_nation != null && myParam_nation.length() > 0) {
			setParam_nations(myParam_nation);
		}

		getMovie();

	}

	public void getMovie() {

		Arrays.fill(all, false);

		// all[0]=false;all[1]=false;
		if (param_type != null) {
			Arrays.fill(typelist, false);
			List<Movieinfo> ll = Select.findByType(param_type);

			for (int i = 0; i < ll.size(); i++) {
				typelist[ll.get(i).getMovieId() - 1] = true;
			}
			if (param_type.equals("all"))
				Arrays.fill(typelist, true);
		} else {
			Arrays.fill(typelist, true);
		}

		if (param_nation != null) {
			Arrays.fill(nationlist, false);
			List<Movieinfo> ll = Select.findByRegion(param_nation);

			for (int i = 0; i < ll.size(); i++) {
				nationlist[ll.get(i).getMovieId() - 1] = true;
			}
			if (param_nation.equals("all"))
				Arrays.fill(nationlist, true);

		} else
			Arrays.fill(nationlist, true);//

		if (param_age != null) {
			Arrays.fill(agelist, false);
			List<Movieinfo> ll = Select.findByAge(param_age);

			for (int i = 0; i < ll.size(); i++) {
				agelist[ll.get(i).getMovieId() - 1] = true;
			}
			if (param_age.equals("all"))
				Arrays.fill(agelist, true);
		} else
			Arrays.fill(agelist, true);

		if (!actor.equals("演员")) {
			Arrays.fill(actorlist, false);
			List<Movieinfo> ll = Select.findByActors(actor);

			for (int i = 0; i < ll.size(); i++) {
				actorlist[ll.get(i).getMovieId() - 1] = true;
			}
			if (actor.equals(""))
				Arrays.fill(actorlist, true);
		} else
			Arrays.fill(actorlist, true);

		for (int i = 0; i < len; i++) {
			all[i] = typelist[i] && agelist[i] && nationlist[i] && actorlist[i];
		}

	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getParam_type() {

		return param_type;
	}

	public String getParam_nation() {
		return param_nation;
	}

	public String getParam_age() {
		return param_age;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	private void setParam_nations(String myParam) {

		this.param_nation = myParam;
	}

	private void setParam_age(String myParam) {

		this.param_age = myParam;
	}

	private void setParam_type(String myParam) {

		this.param_type = myParam;
	}

	public boolean isType_show() {
		return type_show;
	}

	public void setType_show(boolean type_show) {
		this.type_show = type_show;
	}

	public boolean[] getAll() {
		return all;
	}

	public void setAll(boolean[] all) {
		this.all = all;
	}

}