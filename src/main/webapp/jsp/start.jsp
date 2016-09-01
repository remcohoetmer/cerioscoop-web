<%@page import="nl.cerios.cerioscoop.domain.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Cerioscoop</title>
<!-- Index CSS ================================================== -->
<link rel="stylesheet" href="/cerioscoop-web/css/index.css">
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />

</head>
<body>
	<div class="body-navbar-wrapper">
		<div class="navbar">
			<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
		</div>
	</div>

	<div class="body-home-image-content">

		<!--w3schools code http://www.w3schools.com/howto/howto_js_slideshow.asp-->
		<div class="slideshow-container">
			<div class="mySlides fade">
				<div class="numbertext">1 / 7</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					<% Movie theLegendOfTarzan = (Movie) request.getAttribute("1"); %>
					src="<%=theLegendOfTarzan.getCover()%>" style="width: 100%">
				<div class="text"><%=theLegendOfTarzan.getTitle()%></div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">2 / 7</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					<% Movie tarzan = (Movie) request.getAttribute("3"); %>
					src="<%=tarzan.getCover()%>" style="width: 100%">
				<div class="text"><%=tarzan.getTitle()%></div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">3 / 7</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					<% Movie theLionKing = (Movie) request.getAttribute("6"); %>
					src="<%=theLionKing.getCover()%>" style="width: 100%">
				<div class="text"><%=theLionKing.getTitle()%></div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">4 / 7</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					<% Movie bloodDiamond = (Movie) request.getAttribute("5"); %>
					src="<%=bloodDiamond.getCover()%>" style="width: 100%">
				<div class="text"><%=bloodDiamond.getTitle()%></div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">5 / 7</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					<% Movie snatch = (Movie) request.getAttribute("7"); %>
					src="<%=snatch.getCover()%>" style="width: 100%">
				<div class="text"><%=snatch.getTitle()%></div>
			</div>
			<div class="mySlides fade">
				<div class="numbertext">6 / 5</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					<% Movie tarzanTheApeMan = (Movie) request.getAttribute("2"); %>
					src="<%=tarzanTheApeMan.getCover()%>" style="width: 100%">
				<div class="text"><%=tarzanTheApeMan.getTitle()%></div>
			</div>
			<div class="mySlides fade">
				<div class="numbertext">7 / 7</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					<% Movie weddingCrashers = (Movie) request.getAttribute("4"); %>
					src="<%=weddingCrashers.getCover()%>" style="width: 100%">
				<div class="text"><%=weddingCrashers.getTitle()%></div>
			</div>
			<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a class="next"
				onclick="plusSlides(1)">&#10095;</a>
		</div>
		<br>

		<div style="text-align: center">
			<span class="dot" onclick="currentSlide(1)"></span> <span class="dot"
				onclick="currentSlide(2)"></span> <span class="dot"
				onclick="currentSlide(3)"></span> <span class="dot"
				onclick="currentSlide(4)"></span> <span class="dot"
				onclick="currentSlide(5)"></span> <span class="dot"
				onclick="currentSlide(6)"></span> <span class="dot"
				onclick="currentSlide(7)"></span>
		</div>

		<!--This should be ImageSlider.js from the folder js-->
		<script>
			var slideIndex = 1;
			showSlides(slideIndex);

			function plusSlides(n) {
				showSlides(slideIndex += n);
			}

			function currentSlide(n) {
				showSlides(slideIndex = n);
			}

			function showSlides(n) {
				var i;
				var slides = document.getElementsByClassName("mySlides");
				var dots = document.getElementsByClassName("dot");
				if (n > slides.length) {
					slideIndex = 1
				}
				if (n < 1) {
					slideIndex = slides.length
				}
				for (i = 0; i < slides.length; i++) {
					slides[i].style.display = "none";
				}
				for (i = 0; i < dots.length; i++) {
					dots[i].className = dots[i].className
							.replace(" active", "");
				}
				slides[slideIndex - 1].style.display = "block";
				dots[slideIndex - 1].className += " active";
			}
		</script>
	</div>

	<div class="body-footer-wrapper">
		<div class="footer">
			<jsp:include page="/jsp/shared/footer.jsp" />
		</div>
	</div>
</body>
</html>