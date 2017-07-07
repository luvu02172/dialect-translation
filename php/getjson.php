<?php  

$link=mysqli_connect("localhost","root","111111", "db" );  
if (!$link)  
{  
    echo "MySQL 접속 에러 : ";
    echo mysqli_connect_error();
    exit();  
}  

mysqli_set_charset($link,"utf8"); 

$search = $_GET['Data1'];

$sql="select voca, class, meaning, region from dialect1 where match(voca) against('+$search*' IN BOOLEAN MODE)";

$result=mysqli_query($link,$sql);
$data = array();   
if($result){  
    
    while($row=mysqli_fetch_array($result)){
        array_push($data, 
            array('voca'=>$row[0],
            'class'=>$row[1],
            'meaning'=>$row[2],
			'region'=>$row[3]
        ));
    }
header('Content-Type: application/json; charset=utf8');
$json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;

}  
else{  
    echo "SQL문 처리중 에러 발생 : "; 
    echo mysqli_error($link);
} 


 
mysqli_close($link);  
   
?>