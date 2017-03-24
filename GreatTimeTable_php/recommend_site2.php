<?php  
 
function unistr_to_xnstr($str){ 
    return preg_replace('/\\\u([a-z0-9]{4})/i', "&#x\\1;", $str); 
} 
 
$con=mysqli_connect("127.0.0.1","denkybrain","down_312","denkybrain");  
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}  
 
 
mysqli_set_charset($con,"utf8");  
 
 
$res = mysqli_query($con,"select * from recommend_site");  
   
$result = array();  
   
while($row = mysqli_fetch_array($res)){  
  array_push($result,  
    array('title'=>$row[0],'url'=>$row[1],'view'=>$row[2],
    'index' =>$row[3]  , 'updater' =>$row[4]  ,'context' =>$row[5]
    ));  
}  
   
 
$json = json_encode(array("result"=>$result));  
echo $json;
 
   
mysqli_close($con);  
   
?>