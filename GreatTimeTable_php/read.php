<?
  include "db_info.php";
  $id=$_GET['id'];
  //조회수 증가
  $view_increase='UPDATE view SET view=view+1 WHERE id='.$id;
  mysql_query($view_increase,$conn);

  $sql='SELECT title,name,wdate,view,contents FROM board WHERE id='.$id;
  $result=mysql_query($sql,$conn);
  $row=mysql_fetch_array($result);
?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>read.php</title>
  </head>
  <body>
    <table id="">
      <tr>
        <td id="borad_title">제목: <?echo $row[title]?></td>
      </tr>
      <tr><td>작성자: <?echo $row[name]?></td><tr/>
      <tr><td>작성일: <?echo $row[wdate]?></td></tr/>
      <tr><td>조회수: <?echo $row[view]?></td></tr>
      <tr>
        <td>내용: <?echo $row[contents]?></td>
      </tr>
    </table>
    <!--list로 가기-->
    <a href="list.php">list로 가기</a>
    <hr>
    <!--댓글insert-->
    <form action="#" method="post">
      <input type="text" name="reply">
      <input type="submit" value="입력">
    </form>
    <!--댓글view-->
  </body>
</html>
<!--DB접속 끊기-->
<? mysql_close($conn); ?>
