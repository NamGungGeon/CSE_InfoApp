<?
  //DB연결
  include "db_info.php";
  $id=$_POST[id];
  $password=$_POST[password];
  $univ=$_POST[univ];
  $major=$_POST[major];
  $nickname=$_POST[nickname];
  //변수 4개 전달받음
  $sql="INSERT INTO account (id, password, univ, major , nickname, signup_date)
        VALUES ('$id','$password','$univ','$major', '$nickname', now())";
  $result=mysql_query($sql, $conn) or die(mysql_error());
  mysql_close($conn);
?>
