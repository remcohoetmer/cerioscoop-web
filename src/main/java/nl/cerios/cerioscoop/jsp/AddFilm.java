package nl.cerios.cerioscoop.jsp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nl.cerios.cerioscoop.util.DateUtils;
import nl.cerios.cerioscoop.web.FilmShowingServlet;
import nl.cerios.cerioscoop.web.FilmShowingServletException;

//public class AddFilm {
//	private static DateUtils DU = new DateUtils();
//	private static MyFirstServlet MFS = new MyFirstServlet();
//	private static MotionpictureService MPS = new MotionpictureService();
//	
//	
//	if (request.getParameter("submitit").equals("submit")) {
//		String filmname = MFS.doPost(request.getParameter("filmname"));
//		int minutes = Integer.parseInt(request.getParameter("minutes"));
//		int type = Integer.parseInt(request.getParameter("type"));
//		String language = request.getParameter("language");		
//		
//		MPS.registerFilm(filmname, minutes, type, language);
//	}
//}
//}
