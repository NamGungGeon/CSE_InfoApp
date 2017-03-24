<?
  //DB연결
  include "db_info.php";
  $name=$_POST[name];
  $contents=$_POST[contents];
  $pass=$_POST[pw];
  $title=$_POST[title];
  $email=$_POST[eamil];

  $sql="INSERT INTO board (name,contents,pass,title,email, wdate, view)
        VALUES ('$name','$contents','$pass','$title','$email', now(), '')";
  $result=mysql_query($sql, $conn) or die(mysql_error());
  //데이터베이스와의 연결 종료
  mysql_close($conn);
  //DB로 값 전달후 list.php로 이동
  echo("<script>location.replace('list.php');</script>");
?>
