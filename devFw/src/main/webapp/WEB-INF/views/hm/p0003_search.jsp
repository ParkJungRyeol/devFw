<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1 {
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>고객 정보 출력창</title>
</head>
<body>
<h1>고객 정보 출력</h1>
<%
   request.setCharacterEncoding( "utf-8" );
%>
<table border='1' width='800' align='center'>
   <tr align='center' bgcolor='pink'> 
     <td>고객번호</td>
   <td>고객이름</td>
   <td>고객주소</td>
   <td>고객 주</td>
   <td>고객<br>우편번호
   </td>
   <td>고객국가</td>
   <td>고객<br>담당자
   </td>
   <td>고객<br>메일주소
   </td>
   <td>수정</td>
  </tr>

  <c:forEach var="customers" items="${searchList}" >	
	 <c:url var="url"  value="searchMod.do"  >
	   <c:param  name="p_mod_id" value="${customers.cust_id}" />
	 </c:url>     

     <tr align=center>
       <td>${customers.cust_id}</td>
       <td>${customers.cust_name}</td>
       <td>${customers.cust_address}</td>
       <td>${customers.cust_state}</td>
       <td>${customers.cust_zip}</td>
       <td>${customers.cust_country}</td>
       <td>${customers.cust_contact}</td>
       <td>${customers.cust_email}</td>
       <td><a href='${url}'> 수정 </a></td>
     </tr>
  </c:forEach>
</table>
</body>
</html>
