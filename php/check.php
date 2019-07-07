<?php

     echo "<h1> helloWorld PHP</h1>";

     echo "<br>";

     $str = "hello";               //变量声明
     echo $str;
     echo "<br>";

     $str1 = "hi ";
     $str2 = " hello";
     $str3 = $str1.$str;

     echo $str3;                  //字符串拼接
     echo "<br>";
      //php执行原理
      //浏览器不识别php代码，php代码必须在服务器中执行，双击打开PHP文件达不到效果


      $arr = array();
      $arr[0] = "张三";
      $arr[1] = "李四";
      $arr[2] = "王五";


      echo $arr[1];      //echo 不能输出复杂类型
      echo $arr[0];
      echo "<br>";

      //输出数组的三种方法
      print_r($arr);
      echo "<br>";
      var_dump($arr);
       echo "<br>";

       echo json_encode($arr,JSON_UNESCAPED_UNICODE);
       //需要  JSON_UNESCAPED_UNICODE，否则输出的不是汉字
       echo "<br>";


       //遍历
       for($i = 0;$i<count($arr);$i++){
           echo $arr[$i] . "<br>";

       }


       //get请求参数值的获取
       $username = $_GET["username"];
       $password = $_GET["password"];

       if($username == "admin" && $password == "123"){
           echo "Sucess";

       }else{
           echo "Falied";

       }

?>
























