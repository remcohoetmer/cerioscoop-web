<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navigation</title>
	
	<!-- Navigation CSS
   	================================================== -->
	<link rel="stylesheet" href="/cerioscoop-web/css/navbar.css">
	
</head>
<body>

	<div class="navbar">
		<div class="navbar-buttons-wrapper">
			<div class="navbar-button">
				<a href="/cerioscoop-web/index.jsp"> <span
					class="navbar-button-text">CERIOSCOOP</span>
				</a>
			</div>
	
			<div class="navbar-button">
				<a href="#movies"> <span class="navbar-button-text">MOVIES</span>
				</a>
				<div class="navbar-dropdown-button-movie-content">
					<div class="navbar-dropdown-button-item">
						<a href="/cerioscoop-web/jsp/trailers.jsp"> <span
							class="navbar-button-text">TRAILERS</span>
						</a>
					</div>
					<div class="navbar-dropdown-button-item">
						<a href="/cerioscoop-web/NowShowingServlet"> <span
							class="navbar-button-text">NOW SHOWING</span>
						</a>
					</div>
					<div class="navbar-dropdown-button-item">
						<a href="#"> <span class="navbar-button-text">COMING
								SOON</span>
						</a>
					</div>
					<div class="navbar-dropdown-button-item">
						<a href="#"> <span class="navbar-button-text">CATEGORY</span>
						</a>
					</div>
				</div>
			</div>
			<div class="navbar-button">
				<a href="/cerioscoop-web/jsp/about.jsp"> <span
					class="navbar-button-text">ABOUT</span>
				</a>
			</div>
			<div class="navbar-button">
				<a href="/cerioscoop-web/jsp/team.jsp"> <span
					class="navbar-button-text">TEAM</span>
				</a>
			</div>
	
			<div class="navbar-button">
				<a href="#Login"> <span class="navbar-button-text">LOGIN</span>
				</a>
				<div class="login-menu">
					<jsp:include page="/jsp/login.jsp" />
				</div>
			</div>
		</div>
	</div>

</body>
</html>