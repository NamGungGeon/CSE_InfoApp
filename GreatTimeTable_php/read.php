<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <html xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Mobile read page</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" /> 
<script type="text/javascript">
</script>
<style>
<!--
td {font-size : 9pt;}
A:link {font : 9pt; color : black; text-decoration : none;
font-family : 굴림; font-size : 9pt;}
A:visited {text-decoration : none; color : black; font-size : 9pt;}
A:hover {text-decoration : underline; color : black; 
font-size : 9pt;}
th {font-size : 9pt;}
A:link {font : 9pt; color : black; text-decoration : none;
font-family : 굴림; font-size : 9pt;}
A:visited {text-decoration : none; color : black; font-size : 9pt;}
A:hover {text-decoration : underline; color : black; 
font-size : 9pt;}
.table_layout {width: 98%;}
table {width: 100%;}
}
-->
</style>
</head>

<body topmargin=0 leftmargin=0 text=#464646>
<center>
<BR>
<?
	include "db_info.php";

	$id = $_GET[id];
	$no = $_GET[no];
	$comment_id = $id;
	$result=mysql_query("SELECT * FROM board WHERE id=$id", $conn);
	$row=mysql_fetch_array($result);
	mysql_query('set names utf8');
	$result2 = mysql_query("SELECT * FROM comment where numb=$id ORDER BY ord", $conn);
	$result_count=mysql_query("SELECT count(*) FROM comment where numb=$id",$conn);
	$result_row=mysql_fetch_row($result_count);
	$total_row = $result_row[0];
	$number_of_comment = $total_row;
?>

<BR>
<table>
<tr>
	<td height=30 colspan=4 align=center>
		<font color=black size="2.3"><B><?=$row[title]?></B></font>
	</td>
</tr>
</table>
<div class="table_layout">
<table border=0 cellpadding=2 cellspacing=1 bgcolor=#777777 align=center>
<colgroup>  
<col width ="15%" />
<col width ="15%" />
<col width ="20%" />
<col width ="50%" />
</colgroup>
<thead>
<tr>
	<td width=50 height=20 align=center bgcolor=#EEEEEE>글쓴이</td>
	<td width=240 align=center bgcolor=white><?=$row[name]?></td>
	<td width=50 height=20 align=center bgcolor=#EEEEEE>이메일</td>
	<td width=240 align=center bgcolor=white><?=$row[email]?></td>
</tr>
<tr>
 <td width=50 height=20 align=center bgcolor=#EEEEEE>조회수</td>
	<BR>
	<td width=240 align=center bgcolor=white><?=$row[view]?></td>
	<BR>
	<td width=50 height=20 align=center bgcolor=#EEEEEE>
	날&nbsp;&nbsp;&nbsp;짜</td><td width=240
	bgcolor=white align=center><?=$row[wdate]?></td>
</tr>
</thread>
<tr>
	<td bgcolor=white colspan=4>
	<font color=black>
	<pre><?=$row[content]?></pre>
	</font>
	</td>
</tr>
<!-- 기타 버튼 들 -->
<tr>
	<td colspan=4 bgcolor=#FFFFFF>
	<table width=98%>
		<tr>
			<td align=center align=left height=20>
				<a href=mobile.php?no=<?=$no?>><font color=black>
				[목록보기]</font></a>
				<a href=mobile_write.php><font color=black>
				[글쓰기]</font></a>
				<a href=edit.php?id=<?=$id?>><font color=black>
				[수정]</font></a>
				<a href=predel.php?id=<?=$id?>>
				<font color=black>[삭제]</font></a>
			</td>
			<!-- 기타 버튼 끝 -->
			<!-- 이전 다음 표시 -->
			<td align=right>
<?
	// 현재 글보다 id 값이 큰 글 중 가장 작은 것을 가져온다. 삭제됬을때를 생각해서 이렇게 구현함
	// 즉 바로 이전 글 ORDER BY id ASC가 함축됨 즉 오름차순으로 정렬되있음
	$query=mysql_query("SELECT id FROM board WHERE id >$id LIMIT 1", 
	$conn);
	$prev_id=mysql_fetch_array($query);

	if ($prev_id[id]) // 이전 글이 있을 경우
	{
		echo "<a href=read.php?id=$prev_id[id]>
		<font color=white>[이전]</font></a>";
	}
	else
	{
		echo "[이전]";
	}

	//내림차순으로 정렬하고 작은 것 한개 가져옴
	$query=mysql_query("SELECT id FROM board WHERE id <$id 
	ORDER BY id DESC LIMIT 1", $conn);
	$next_id=mysql_fetch_array($query);

	if ($next_id[id])
	{
		echo "<a href=read.php?id=$next_id[id]>
		<font color=black>[다음]</font></a>";
	}
	else
	{
		echo "[다음]";
	}
?>
			</td>
		</tr>
	</table>
	</b></font>
	</td>
</tr>
</table>
</center>


<!-- comment include part-->

<!-- comment   insert                   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-->
<BR>

<form action="comment_insert.php" method="post">
	<input type="hidden" name="id" value="<? echo $id ?>">
	<CENTER>
	<div class="table_layout">
	<table cellpadding=2 cellspacing=1 bgcolor=#FFFFFF>
	<colgroup>
<col width ="15%" />
<col width ="15%" />
<col width ="20%" />
<col width ="50%" />
</colgroup>
<thead>
	  <tr>
	<td height=25 colspan=4 align=center bgcolor=#EEEEEE>
		<font color=black size="2.3">덧글 작성</font>
	</td>
</tr>


<!-- form -->
		<tr align="center">
				<th scope="row">아이디</th>
				<td><input width=35 type="text" name="name"></td>
			</tr>
			<tr align="center">
				<th scope="row">비밀번호</th>
				<td><input width=35 type="password" name="pass"></td>
			</tr>
			<tr align="center">
				<th scope="row">내용</th>
				<td><textarea width=55 name="content"></textarea></td>
			</tr>



	</table>
	</div>
	<div class="btnSet">
		<input type="submit" value="덧글 작성">
	</div>
	</thead>
	<?
if ($number_of_comment > 0) {
	?>

<!-- comment      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-->

<body topmargin=0 leftmargin=0 text=#FFFFFF>
<center>
<!-- 게시판 타이틀 -->
<BR>
<!-- 게시물 리스트를 보이기 위한 테이블 -->
<div class="table_layout">
<table cellpadding=2 cellspacing=1 bgcolor=#000000>
<colgroup>
	<col width="20%" />
	<col width="50%" />
	<col width="30%" />
	</colgroup>
<!-- 리스트 타이틀 부분 -->
<thread>
<tr height=20 bgcolor=#EEEEEE>
	<td align=center>
		<font color=black>글쓴이</font>
	</td>
	<td align=center>
		<font color=black>덧글</font>
	</td>
	<td align=center>
		<font color=black>날짜</font>
	</td>
</tr>
</thread>
<!-- 리스트 타이틀 끝 -->
<!-- 리스트 부분 시작 -->
<?
while($row=mysql_fetch_array($result2))
{
?>
<!-- 행 시작 -->
<tbody>
<tr>
	<!-- 제목 -->
	<td align=center height=20 bgcolor=white>
		<font color=black><?=$row[name]?></font>
	</td>
	<!-- 제목 끝 -->
	<!-- 이름 -->
	<td align=center height=20 bgcolor=white>
		<font color=black><?=$row[content]?></font>
	</td>
	<td align=center height=20 bgcolor=white>
		<font color=black><?=$row[wdate]?></font>
	</td>
	<!-- 이름 끝 -->
   
</tr>
</tbody>
<!-- 행 끝 -->
<?
}
} else {
?>
<BR>
<center><font color=grey size=2></a>덧글이 없습니다.</a></font></center>

<?
}
mysql_close($conn);
?>
</table>
</div>
<BR>

<?
  $result=@mysql_query("UPDATE board SET view=view+1 WHERE id=$id", $conn);
  @mysql_close($conn);
?>
</table>
</body>
</html>