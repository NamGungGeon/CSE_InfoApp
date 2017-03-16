<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<?
//데이터 베이스 연결하기
include "db_info.php";

# LIST 설정
# 1. 한 페이지에 보여질 게시물의 수
$page_size=10;

# 2. 페이지 나누기에 표시될 페이지의 수
// $no는 페이지가 시작되는 첫 글의 순번이다.
$page_list_size = 10;
$no = $_GET[no];
if (!$no || $no <0) $no=0;

// 데이터베이스에서 페이지의 첫번째 글($no)부터 
// $page_size 만큼의 글을 가져온다.
mysql_query('set names utf8');
$query = "SELECT * FROM board ORDER BY index DESC LIMIT $no, $page_size";
$result = mysql_query($query, $conn);
// 총 게시물 수 를 구한다.
$result_count=mysql_query("SELECT count(*) FROM board",$conn);
$result_row=mysql_fetch_row($result_count);
$total_row = $result_row[0];
echo "$result_row";
//결과의 첫번째 열이 count(*) 의 결과다.
//mysql_fetch_row 쓰면 $result_row[0] 처럼 숫자를 써서 접근을 해야한다. 

# 총 페이지 계산
# ceil는 올림이다. 
if ($total_row <= 0) $total_row = 0;
$total_page = ceil($total_row / $page_size);//1개면

# 현재 페이지 계산
# no 변수는 0부터 시작해서 +1을 해줌.
$current_page = ceil(($no+1)/$page_size);
?>

<!-- 여기까지 php ###############################################################################-->

<html xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml"> 
<head> 

<title>Mobile list page</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" /> 
<script type="text/javascript"> 
<!-- 
// 주소창 자동 닫힘
window.addEventListener("load", function(){ 
setTimeout(loaded, 100); 

}, false); 

function loaded(){ 
window.scrollTo(0, 1); 
} 
//--> 
</script> 
</head> 

<head>
<style>
<!--
td {font-size : 11pt;}
A:link {font : 11pt;color : black;text-decoration : none; fontfamily
: 굴림;font-size : 12pt;}
A:visited {text-decoration : none; color : black; font-size : 10pt;}
A:hover {text-decoration : underline; color : black; font-size : 10pt;}
-->
.table_layout {width:98%;}
table {width:100%;}
</style>
</head>
<body topmargin=0 leftmargin=0 text=#464646>
<center>
<BR>
<!-- 게시판 타이틀 -->
<BR>
<img src="logo.png" width="140" height ="140">
<BR>
<BR><BR>
<!-- 게시물 리스트를 보이기 위한 테이블 -->
<div class="table_layout">
<table cellpadding=2 cellspacing=1 bgcolor=#000000>
<colgroup>
    <col width="75%" />
    <col width="25%" />
    </colgroup>
<!-- 리스트 타이틀 부분 -->
<thread>
<tr height=20 bgcolor=#CCFFC9>
    <td align=center>
        <font color=black>제 목</font>
    </td>
    <td align=center>
        <font color=black>글쓴이</font>
    </td>

</tr>
</thread>
<!-- 리스트 타이틀 끝 -->
<!-- 리스트 부분 시작 -->
<?
while($row=mysql_fetch_array($result))
{

?>
<!-- 행 시작 -->
<tbody>
<tr>
    <!-- 제목 -->
    <td align=center height=20 bgcolor=white>&nbsp;
        <a href="mobile_read.php?id=<?=$row[id]?>&no=<?=$no?>">
        <?=strip_tags($row[title], '<b><i>');?></a>
    </td>
    <!-- 제목 끝 -->
    <!-- 이름 -->
    <td align=center height=20 bgcolor=white>
        <font color=grey>
        <a href="mailto:<?=$row[email]?>"><?=$row[name]?></a>
        </font>
    </td>
    <!-- 이름 끝 -->
   
</tr>
</tbody>
<!-- 행 끝 -->
<?
} // end While
//데이터베이스와의 연결을 끝는다.
mysql_close($conn);
?>
</table>
<!-- 게시물 리스트를 보이기 위한 테이블 끝-->
<!-- 페이지를 표시하기 위한 테이블 -->
<BR>
<table border=0>
<tr>
    <td width=600 height=20 align=center rowspan=4>
    <font color=gray>
    &nbsp;
<?
$start_page = floor(($current_page - 1) / $page_list_size) * $page_list_size + 1;
# floor 함수는 소수점 이하는 버림

# 페이지 리스트의 마지막 페이지가 몇 번째 페이지인지 구하는 부분이다.
$end_page = $start_page + $page_list_size - 1;

if ($total_page <$end_page) $end_page = $total_page;

if ($start_page >= $page_list_size) {
    # 이전 페이지 리스트값은 첫 번째 페이지에서 한 페이지 감소하면 된다.
    # $page_size 를 곱해주는 이유는 글번호로 표시하기 위해서이다.

    $prev_list = ($start_page - 2)*$page_size;
    echo "<a href='$PHP_SELF?no=$prev_list'>◀</a> ";
}

# 페이지 리스트를 출력

for ($i=$start_page;$i <= $end_page;$i++) {
    $page= ($i-1) * $page_size;// 페이지값을 no 값으로 변환.
    if ($no!=$page){ //현재 페이지가 아닐 경우만 링크를 표시
        echo "<a href='$PHP_SELF?no=$page'>";
    }
    
    echo " $i "; //페이지를 표시
    
    if ($no!=$page){ //현재 페이지가 아닐 경우만 링크를 표시하기 위해서
        echo "</a>";
    }
}

# 다음 페이지 리스트가 필요할때는 총 페이지가 마지막 리스트보다 클 때이다.
# 리스트를 다 뿌리고도 더 뿌려줄 페이지가 남았을때 다음 버튼이 필요할 것이다.
if($total_page >$end_page)
{
    $next_list = $end_page * $page_size;
    echo "<a href=$PHP_SELF?no=$next_list>▶</a><p>";
}
?>
    </font>
    </td>
</tr>


</table>


    <form method=post action='list.php'>
    <tr>
        <td width=100% colspan=5 align=center>
            <input type=hidden name=page value=<? echo "$page" ; ?>>


            <select name=src_name>
            <option value=name>이름</option>
            <option value=subject selected>제목</option>
            </select>
            <input type=text name=src_value size=20>
            <input type=submit value=검색>
        </td>
    </tr>
    </form>


<BR>
<a href=mobile_write.php>글쓰기</a>
<BR>
<body>
<BR>
<FONT size="2.1pt" COLOR="#A0A0A0">Jung Wi Dae</FONT><BR><BR>
</body>
</center>


</body>
</html>