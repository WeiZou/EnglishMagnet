package com.objectiva.englishmagnet.persistence.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.objectiva.englishmagnet.persistence.srtInfro.*;
import com.objectiva.englishmagnet.persistence.movieInfro.*;

/**
 * Servlet implementation class FileuploadServlet
 */
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String fullPath;
	public int movies;

	MovieinfoDAO mDAO = new MovieinfoDAO();

	SrtDAO dao = new SrtDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileuploadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		copyFile(req, resp);
		String temStr = fullPath.replace("\\", "/");
		JsonConverter(temStr);
		resp.sendRedirect("uploadSuccess.jsf");
	}

	public String[] TimeConverter(String[] time) {
		String start = "";
		String end = "";
		String[] times = new String[2];
		for (int i = 0; i < 2; i++) {
			String[] t = time[i].split(":");
			int hour, minute, second;
			hour = Integer.parseInt(t[0]);
			minute = Integer.parseInt(t[1]);
			String sec = t[2].split(",")[0];
			String other = t[2].split(",")[1];
			second = hour * 3600 + minute * 60 + Integer.parseInt(sec);
			times[i] = "" + second + "." + other;
		}
		return times;
	}

	public String SentenceConverter(String stence) {
		if (stence.contains("\"")) {
			String[] temp = stence.split("\"");
			String t = "";
			for (int i = 0; i < temp.length; i++) {
				t += temp[i];
			}
			stence = t;
		}
		return stence;
	}

	public void JsonConverter(String path) {
		File dir = new File(path);
		String stence = "";
		String chinese = "";
		String[] time = new String[2];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(dir));
			String name = reader.readLine();
			Movieinfo info = new Movieinfo();
			info.setMovieName(name);
			info.setAge(reader.readLine());
			info.setType(reader.readLine());
			info.setRegion(reader.readLine());
			info.setActors(reader.readLine());
			info.setOther(reader.readLine());
			mDAO.save(info);
			movies = getMovieId(name);
			String s = reader.readLine();
			s = reader.readLine();
			while (true) {
				if (s.contains("-->")) {
					time = s.split(" --> ");
					time = TimeConverter(time);
					while ((s = reader.readLine()) != null
							&& !s.contains("-->")) {
						if (s.getBytes("utf-8").length == s.length()
								&& !s.matches("\\d+"))
							stence += s;
						else if (s.getBytes("utf-8").length != s.length())
							chinese += s;
					}
					if (s == null) {

						reader.close();
						break;
					}
					stence = SentenceConverter(stence);
					chinese = SentenceConverter(chinese);
					if (stence.length() != 0) {
						Srt srt = new Srt();
						SrtId id = new SrtId();
						id.setMovieId(movies);
						id.setStart(time[0]);
						srt.setId(id);
						srt.setSene(stence);
						srt.setSenc(chinese);
						srt.setEnd(time[1]);
						dao.save(srt);
					}
					stence = "";
					chinese = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getMovieId(String name) {
		List<Movieinfo> info = mDAO.findByMovieName(name);
		return info.get(0).getMovieId();
	}

	private void copyFile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();

		File tempFile = new File(this.getServletContext().getRealPath("/temp"));
		((DiskFileItemFactory) factory).setSizeThreshold(1024 * 1024);
		((DiskFileItemFactory) factory).setRepository(tempFile);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 50);
		upload.setFileSizeMax(1024 * 1024 * 12);

		List items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
		}

		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				String value = item.getString();
				req.setAttribute(fieldName, value);
			} else {
				// file type logic
				long d = item.getSize();
				if (d > upload.getSizeMax()) {
					try {
						resp.sendRedirect("/index");
					} catch (IOException e) {
					}
				}
				String fileName = item.getName();
				int index = fileName.lastIndexOf("\\");
				fileName = fileName.substring(index + 1);
				String basePath = this.getServletContext().getRealPath(
						"/uploadFile");
				File dir = new File(basePath);
				if (!dir.exists())
					dir.mkdir();
				File file = new File(basePath, fileName);
				fullPath = basePath + "/" + fileName;
				try {
					item.write(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
