package nl.cerios.cerioscoop.jsp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nl.cerios.cerioscoop.util.DateUtils;
import nl.cerios.cerioscoop.web.MyFirstServlet;
import nl.cerios.cerioscoop.web.MyFirstServletException;

//public class AddMotionpicture {
//	private static DateUtils DU = new DateUtils();
//	private static MyFirstServlet MFS = new MyFirstServlet();
//	
//	Date date = null;
//	Date date2 = null;
//	DateFormat from = new SimpleDateFormat("MM/dd/yyyy"); 						// current format
//	DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);	// wanted format
//	java.sql.Date premdatum = null;
//	java.sql.Date laatvoor = null;
//	java.sql.Time premtijd = DU.getCurrentSqlTime();
//
//	if (request.getParameter("submitit").equals("submit")) {
//		String filmname = MFS.doPost(request.getParameter("filmnaam"));
//		int minutes = Integer.parseInt(request.getParameter("minuten"));
//		int type = Integer.parseInt(request.getParameter("type"));
//		String language = request.getParameter("taal");		
//		String premdat = request.getParameter("premdat");
//		String lavoor = request.getParameter("lavoor");
//		
//		try {
//			premdat = formatTo.format(from.parse(premdat));
//			date = formatTo.parse(premdat);
//			lavoor = formatTo.format(from.parse(lavoor));
//			date2 = formatTo.parse(lavoor); 
//			premdatum = new java.sql.Date(date.getTime());  //Maak methode in DateUtils!
//			laatvoor = new java.sql.Date(date2.getTime());	//Maak methode in DateUtils!
//		} catch (ParseException e) {
//			throw new MyFirstServletException("Something went wrong while parsing premiere datum.", e);
//			}
//		MPS.registerFilm(filmname, minutes, type, language, premdatum, premtijd, laatvoor);
//	}
//}
//}
