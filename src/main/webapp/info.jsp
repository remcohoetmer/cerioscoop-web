<%@page import="java.io.IOException"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Technische informatie</title>
</head>
<body>
 <h1>Manifest file</h1>
<pre>
<%=readManifest()%>
</pre>
</body>
</html>

<%!
private String readManifest() throws IOException {
	try (InputStream input = getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF")) {
		return new BufferedReader(new InputStreamReader(input))
  					.lines()
  					.collect(Collectors.joining("\n"));
  	} catch (Exception e) {
  		return "Exception while reading manifest: "+e;
  	}
}
 %>