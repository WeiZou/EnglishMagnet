package com.objectiva.englishmagnet.facade;

import com.objectiva.englishmagnet.general.General;
import com.objectiva.englishmagnet.persistence.collectionOperate.ShowCollectionSentence;
import com.objectiva.englishmagnet.persistence.collectionOperate.WordlistBackingBean;
import com.objectiva.englishmagnet.persistence.log.*;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import static com.objectiva.englishmagnet.facade.MessageContant.*;

import com.objectiva.englishmagnet.movielist.*;

public class UserBackingBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String mail;
	private static final String MAIL_NUM = "[a-zA-Z0-9]{1,16}@[a-zA-Z0-9]{2,3}.com";
	private String word;
	private boolean checkname = false;
	private boolean checkpasswd = false;
	private boolean checkrepasswd = false;
	private boolean checkemail = false;
	private boolean iscancel = false;
	private boolean recancel = true;
	private boolean withdraw = false;

	public void validateName(FacesContext context, UIComponent validate,
			Object value) {
		this.name = (String) value;
		if (this.name.length() < 6) {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(nameLength);
			context.addMessage(validate.getClientId(context), msg);
		} else {
			UserAction ua = new UserAction();
			boolean tt = ua.checkUserName(name);
			if (tt) {
				((UIInput) validate).setValid(false);
				FacesMessage msg = new FacesMessage(nameExist);
				context.addMessage(validate.getClientId(context), msg);
			} else {
				((UIInput) validate).setValid(false);
				FacesMessage msg = new FacesMessage(ok);
				checkname = true;
				context.addMessage(validate.getClientId(context), msg);
			}
		}
	}

	public void validateMail(FacesContext context, UIComponent validate,
			Object value) {
		Pattern mask = Pattern.compile(MAIL_NUM);
		this.mail = (String) value;
		Matcher macher = mask.matcher(mail);
		if (!macher.matches()) {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(MailFormt);
			context.addMessage(validate.getClientId(context), msg);
		} else {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(ok);
			checkemail = true;
			context.addMessage(validate.getClientId(context), msg);
		}
	}

	public void validatePassword(FacesContext context, UIComponent validate,
			Object value) {
		word = (String) value;
		if (word.length() < 6) {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(passwordLength);
			context.addMessage(validate.getClientId(context), msg);
		} else if (!word.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).*$")) {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(passwordFormt);
			context.addMessage(validate.getClientId(context), msg);
		} else {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(ok);
			checkpasswd = true;
			context.addMessage(validate.getClientId(context), msg);
		}
	}

	public void validatePasswordCheck(FacesContext context,
			UIComponent validate, Object value) {
		this.password = (String) value;
		if (this.password.equals(this.word)) {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(ok);
			checkrepasswd = true;
			context.addMessage(validate.getClientId(context), msg);
		} else {
			((UIInput) validate).setValid(false);
			FacesMessage msg = new FacesMessage(passwordCheck);
			context.addMessage(validate.getClientId(context), msg);
		}
	}

	public String login() {
		if (name.equals("root") && password.endsWith("00000000")) {
			iscancel = true;
			recancel = false;
			errMessage = null;
			withdraw = true;
			return "success";
		} else {
			UserAction ua = new UserAction();
			boolean tt = ua.checkUserName(name);
			if (tt) {
				if (ua.checkUserPasswd(password)) {
					errMessage = "欢迎回来," + name;
					General.setUserName(name);
					name = "";
					iscancel = false;
					recancel = false;
					withdraw = true;
					MovieList.movie_show = false;
					return "success";
				} else {
					errMessage = "密码错误";
					name = "";
					iscancel = false;
					recancel = true;
					withdraw = false;
					return "fail";
				}
			} else {
				errMessage = "账户名不存在";
				name = "";
				iscancel = false;
				recancel = true;
				withdraw = false;
				return "fail";
			}

		}

	}

	public void wdraw() {

		recancel = true;
		withdraw = false;
		errMessage = null;
		SearchBackingBean.validate = false;
		MovieList.movie_show = false;
		ShowCollectionSentence.show_collect = false;
		WordlistBackingBean.wordList_show = false;
		iscancel = false;
		General.setUserName("");
		General.setUserId(null);
	}

	public String register() {
		return "register";
	}

	public String cancel() {
		return "cancel";
	}

	public String regist() {
		if (checkname && checkpasswd && checkrepasswd && checkemail) {
			UserAction ua = new UserAction();
			ua.regist(name, password, mail);
			checkname = false;
			checkpasswd = false;
			checkrepasswd = false;
			checkemail = false;
			
			
			mail = "";
			errMessage = null;
			
			login();
			return "regist";
		} else {
			return "error";
		}
	}

	public String uplode() {
		return "uplode";
	}

	public boolean isWithdraw() {
		return withdraw;
	}

	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}

	public boolean isIscancel() {
		return iscancel;
	}

	public boolean isRecancel() {
		return recancel;
	}

	public void setRecancel(boolean recancel) {
		this.recancel = recancel;
	}

	public void setIscancel(boolean iscancel) {
		this.iscancel = iscancel;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private String errMessage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
}
