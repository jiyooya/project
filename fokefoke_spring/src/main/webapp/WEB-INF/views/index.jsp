<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>FokeFoke</title>
<meta charset="UTF-8">
<meta name="description" content="FokeFoke">
<meta name="keywords" content="FokeFoke, salad, fresh, fast">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">


<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css?after"
	type="text/css">

<style>
.parents {
	position: relative;
}

.hero__buttons {
	width: 100%;
	height: 10%;
	position: absolute;
	top: 360px;
	z-index: 1;
}

.hero__dot-btn {
	width: 10px;
	height: 12px;
	border-radius: 10px;
	background: #469543;
	border: none;
}

.hero__dot-btn:hover {
	width: 10px;
	height: 12px;
	border-radius: 10px;
	background: #f06923;
	border: none;
}
/*  	.hero__dot-btn.active{
	 width: 50px;
  	height: 15px;
  	border-radius:10px;
  	background : red;
	} */
.bodybody {
	height: 600px;
	display: flex;
	align-items: center;
	padding-top: 110px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}
</style>

<!-- Favicons -->
<link href="${pageContext.request.contextPath}resources/img/favicon.png"
	rel="icon" />
<link href="${pageContext.request.contextPath}resources/img/favicon.png"
	rel="apple-touch-icon" />
</head>
<body>

	<jsp:include page="/WEB-INF/views/header.jsp" />

	<!-- Hero Section Begin -->
	<section class="hero">
		<div class="parents">
			<div class="hero__slider owl-carousel st">
			<a href="http://localhost:8080/notice/view?noticeId=7">
				<div class="hero__items set-bg" data-index="1"
					data-setbg="${pageContext.request.contextPath}/resources/img/hero/hero-1.png">
				</div>
				</a>
				<a href="http://localhost:8080/notice/view?noticeId=9">
				<div class="hero__items set-bg" data-index="2"
					data-setbg="${pageContext.request.contextPath}/resources/img/hero/hero-2.png">
				</div>
				</a>
				<a href="http://localhost:8080/notice/view?noticeId=8">
				<div class="hero__items set-bg" data-index="3"
					data-setbg="${pageContext.request.contextPath}/resources/img/hero/hero-3.png">
				</div>
				</a>
			</div>
			<div class="hero__buttons" style="text-align: center;">
				<button class="hero__dot-btn" data-index="1"></button>
				<button class="hero__dot-btn" data-index="2"></button>
				<button class="hero__dot-btn" data-index="3"></button>
			</div>
		</div>
	</section>

	<!-- Hero Section End -->

	<!-- Banner Section Begin -->
	<br>
	<br>

	<!-- Product Section Begin -->

	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
				<h2 style="font-weight: 700; font-size: 30px;">포케포케의 Menu</h2>
				<br>
				<br>
					<div class="product__list"></div>
				</div>
			</div>
		</div>
	</section>
	<div class="bodybody">
		<div class="container">
			<div class="row">

				<div class="col-lg-9">
					<div
						style="background: #fff; width: 870px; height: 300px; margin-bottom: 80px; border-radius: 30px 0 30px 0;">
						<div
							style="width: 300px; height: auto; display: inline-block; vertical-align: middle; padding-top: 80px;">
							<h4
								style="text-align: center; font-size: 30px; font-weight: 700; margin-top: 0px; margin-bottom: 5px; color: #f06923">What's
								New?</h4>
							<p style="text-align: center; color: #a1a1a1; margin-bottom: 0;">포케포케의
								다양한 소식을</p>
							<p
								style="text-align: center; color: #a1a1a1; margin-bottom: 50px;">빠르게
								전해드립니다.</p>
						</div>
						<div class="notice__list"
							style="width: 500px; height: auto; display: inline-block; vertical-align: middle; padding-top: 35px;"></div>
						<a href="/notice/list" class="more"
							style="font-size: 13px; color: #a1a1a1; margin-left: 310px;">더보기</a>
					</div>
				</div>
				<div class="col-lg-3">
					<div
						style="background: #469543; width: 270px; height: 300px; margin-bottom: 80px; margin-left: 30px; border-radius: 0 30px 0 30px; overflow: hidden;">
						<a href="http://localhost:8080/notice/view?noticeId=8"><img
							src="${pageContext.request.contextPath}/resources/img/maindown.jpg"
							style="width: 100%; height: auto;"></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/footer.jsp" />

	<!-- Js Plugins -->

	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.countdown.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<script>
		$(document).ready(function() {
			var $slider = $(".hero__slider");
			var $dots = $(".hero__dot-btn");
			var itemCount = $slider.find(".hero__items").length;

			$slider.owlCarousel({
				autoplay : true,
				autoplayTimeout : 5000,
				loop : true,
				margin : 0,
				items : 1,
				onInitialized : function() {
					$(".hero__items").each(function(index) {
						$(this).attr("data-index", index + 1);
					});
					/*  $(".hero__dot-btn[data-index='1']").addClass("active"); */
				},
			/* 		    onChanged: function(event) {
			 var index = event.item.index;
			 $dots.removeClass("active");
			 var activeIndex = (index % itemCount) + 1;
			 $(".hero__dot-btn[data-index='" + activeIndex + "']").addClass("active");
			 } */
			});

			$dots.click(function() {
				var index = $(this).data("index");
				$slider.trigger("to.owl.carousel", index - 1);
			});
		});
	</script>

	<script type="text/javascript">
		$(function() {
			$
					.get(
							"/menuList",
							function(data) {
								var productList = data.product;
								var html = "";
								for (var i = 0; i < productList.length; i++) {
									var product = productList[i];
									html += '<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix list">'
											+ '<div class="product__item__pic set-bg">'
											+ '<img src="' + product.productImage + '" onclick="window.location.href=\'/detail/view2?productName='+product.productName+'\';">'
											+ (product.productLabel == 'new' ? '<span class="new">New</span>'
													: (product.productLabel == 'sale' ? '<span class="sale">Sale</span>'
															: ''))
											+ '</div>'
											+ '<div class="product__item__text" >'
											+ '<h5>'
											+ product.productName
											+ '</h5>'
											+ '</div>'
											+ '<div class="product__item__text">'
											+ '<h6>'
											+ product.productEname
											+ '</h6>'
											+ '</div>'
											+ '<div class="product__item__text">'
											+ '<h4>'
											+ product.productCalorie
											+ 'kcal</h4>' + '</div>' + '</div>';
								}
								$('.product__list').html(html);
							});
		});

		$(function() {
			$
					.get(
							"/noticeList",
							function(data) {
								var noticeList = data.notice;
								var html = '<table class="table" style="width: 100%;">';
								for (var i = 0; i < noticeList.length && i < 3; i++) {
									var notice = noticeList[i];
									var noticeDate = new Date(notice.noticeDate);
									var formattedDate = noticeDate
											.getFullYear()
											+ '-'
											+ (noticeDate.getMonth() + 1)
													.toString()
													.padStart(2, '0')
											+ '-'
											+ noticeDate.getDate().toString()
													.padStart(2, '0');
									html += '<tr><td><a href="notice/view?noticeId='
											+ notice.noticeId
											+ '" style="color: #469543; font-size:16px; font-weight:700;">'
											+ notice.noticeTitle
											+ '</a></td><td style="color: #a1a1a1;">'
											+ formattedDate
											+ '</td></tr>';
								}
								html += '</table>'; // table 닫기
								$('.notice__list').html(html);
							});
		});
	</script>
</body>
</html>