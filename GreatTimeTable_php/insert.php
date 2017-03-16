
<html>
<head>
	<title>지하상가 등록</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="assets/css/main.css" />
	<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->

	<script src="http://maps.google.com/maps/api/js?sensor=false" 
	type="text/javascript"></script>

</head>
<body class="subpage">
	<div id="page-wrapper">

		<!-- Header -->
		<div id="header-wrapper">
			<header id="header" class="container">
				<div class="row">
					<div class="12u">
						<!-- Logo -->
						<h1><a href="#" id="logo">UnderWhere:Korea</a></h1>
						<!-- Nav -->
						<nav id="nav">
							<a href="index.php">홈페이지</a>
							<a href="threecolumn.html">미구현</a>
							<a href="twocolumn1.html">미구현</a>
							<a href="twocolumn2.html">미구현</a>
							<a href="onecolumn.html">미구현</a>
						</nav>

					</div>
				</div>
			</header>
		</div>

		<!-- Content -->
		<div id="content-wrapper">
			<div id="content">
				<div class="container">
					<div class="row">
						<div class="12u">
							<!-- Main Content -->
							<section>
								<header>
								
								<?
								$loca = $_GET[loca];
								$loca_name = "";
								switch ($loca) {
									case 0:
										$loca_name = "서울";
										break;
									
									case 1:
										$loca_name = "인천";
										break;
									case 2:
										$loca_name = "부산";
										break;

									case 3:
										$loca_name = "대구";
										break;
									
									
									
									default:
										# code...
										break;
								}
								?>
								
									<h2 align="center"><? echo $loca_name ?> 지하상가 등록하기</h2>
									<!-- 
									<h3 align="center">sub title~~</h3>
									-->
								</header>	
									<body>
								<form action="insert_php.php?loca=<? echo $loca ?>" method="post">
    <input type="hidden" name="bno" value="<?php echo $bNo?>">
    <CENTER>
<table border=0 cellpadding=2 cellspacing=1 align=center>
    <colgroup>
<col width ="15%" />
<col width ="15%" />
<col width ="20%" />
<col width ="50%" />
</colgroup>
<thead>
   <tr>
        <td bgcolor=white>&nbsp;
        </td></tr>
<!-- form -->
 <tr align="center">
                <th scope="row"><label for="coId">제목</label></th>
                <td><input width=35 type="text" name="name"></td>
            </tr>
        <tr align="center">
                <th scope="row"><label for="coId">작성자</label></th>
                <td><input width=35 type="text" name="updater"></td>
            </tr>
            
             <tr align="center">
                <th scope="row"><label for="coId">Y좌표</label></th>
                <td><input width=35 type="text" name="Y" ></td>
            </tr>
            <tr align="center">
                <th scope="row"><label for="coId">X좌표</label></th>
                <td><input width=35 type="text" name="X" ></td>
            </tr>        
            <tr align="center">
                <th scope="row"><label for="coContent">내용</label></th>
                <td><textarea width=55 name="contents"></textarea></td>
            </tr>
    </table>
    <div class="btnSet">
    <BR>
    <BR><center>
        <input type="submit" value="게시글 작성">
        &nbsp;&nbsp;
                    <INPUT type=button value="목록으로" 
                    onclick="history.back(-1)">
&nbsp;&nbsp;
                    <INPUT type=button value="좌표 얻기" 
                    onclick="document.location.href = 'http://www.mapcoordinates.net/'">
                     <!--버튼이 클릭되었을때 발생하는 이벤트로 자바스크립트를 실행함. 이렇게 하면 바로 이전페이지로 감-->
    </center>
    </div>
    </thead>
									</body>
							<br>
							<br>
						</section>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<div id="footer-wrapper">
		<footer id="footer" class="container">
			<div class="row">
				<div class="8u 12u(mobile)">

						<!-- Links 
						<section>
							<h2>Links to Important Stuff</h2>
							<div>
								<div class="row">
									<div class="3u 12u(mobile)">
										<ul class="link-list last-child">
											<li><a href="#">Neque amet dapibus</a></li>
											<li><a href="#">Sed mattis quis rutrum</a></li>
											<li><a href="#">Accumsan suspendisse</a></li>
											<li><a href="#">Eu varius vitae magna</a></li>
										</ul>
									</div>
									<div class="3u 12u(mobile)">
										<ul class="link-list last-child">
											<li><a href="#">Neque amet dapibus</a></li>
											<li><a href="#">Sed mattis quis rutrum</a></li>
											<li><a href="#">Accumsan suspendisse</a></li>
											<li><a href="#">Eu varius vitae magna</a></li>
										</ul>
									</div>
									<div class="3u 12u(mobile)">
										<ul class="link-list last-child">
											<li><a href="#">Neque amet dapibus</a></li>
											<li><a href="#">Sed mattis quis rutrum</a></li>
											<li><a href="#">Accumsan suspendisse</a></li>
											<li><a href="#">Eu varius vitae magna</a></li>
										</ul>
									</div>
									<div class="3u 12u(mobile)">
										<ul class="link-list last-child">
											<li><a href="#">Neque amet dapibus</a></li>
											<li><a href="#">Sed mattis quis rutrum</a></li>
											<li><a href="#">Accumsan suspendisse</a></li>
											<li><a href="#">Eu varius vitae magna</a></li>
										</ul>
									</div>
								</div>
							</div>
						</section>

					</div>
					<div class="4u 12u(mobile)">

						<!-- Blurb -
						<section>
							<h2>An Informative Text Blurb</h2>
							<p>
								Duis neque nisi, dapibus sed mattis quis, rutrum accumsan sed. Suspendisse eu
								varius nibh. Suspendisse vitae magna eget odio amet mollis. Duis neque nisi,
								dapibus sed mattis quis, sed rutrum accumsan sed. Suspendisse eu varius nibh
								lorem ipsum amet dolor sit amet lorem ipsum consequat gravida justo mollis.
							</p>
						</section>

					</div>
				</div>
			</footer>
		</div>

		<!-- Copyright -->
	</div>
</div>

<p align="center">
	&copy; UnderWhere. All rights reserved. Made by Jung Widae
	<br>
	농협 302-0053-0040-81 정위대
</p>

</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/skel-viewport.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

</body>
</html>