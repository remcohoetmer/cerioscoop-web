<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>

<!-- Index CSS ================================================== -->
<link rel="stylesheet" href="/cerioscoop-web/css/index.css">

</head>
<body>
	<div class="body-navbar-wrapper">
		<div class="navbar">
			<jsp:include page="/jsp/navbar.jsp"></jsp:include>
		</div>
	</div>

	<div class="body-home-image-content">

		<!--w3schools code http://www.w3schools.com/howto/howto_js_slideshow.asp-->
		<div class="slideshow-container">
			<div class="mySlides fade">
				<div class="numbertext">1 / 5</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					src="images/slider/slide1.jpg" style="width: 100%">
				<div class="text">The Legend of Tarzan</div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">2 / 5</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					src="images/slider/slide2.jpg" style="width: 100%">
				<div class="text">Tarzan (1999)</div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">3 / 5</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					src="images/slider/slide3.jpg" style="width: 100%">
				<div class="text">The lion king</div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">4 / 5</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					src="images/slider/slide4.jpg" style="width: 100%">
				<div class="text">Blood Diamond</div>
			</div>

			<div class="mySlides fade">
				<div class="numbertext">5 / 5</div>
				<img
					onclick="document.location=this.id+'/cerioscoop-web/NowShowingServlet';return false;"
					src="images/slider/slide5.jpg" style="width: 100%">
				<div class="text">Zes mannen en een hond</div>
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
				onclick="currentSlide(5)"></span>
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
			<jsp:include page="/jsp/footer.jsp" />
		</div>
	</div>
</body>
</html>