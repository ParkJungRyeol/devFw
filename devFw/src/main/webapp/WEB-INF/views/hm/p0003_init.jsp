<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 검색창</title>
<script>
	
</script>
</head>
<body>
	<form method="post" action="${contextPath}/hm/p0003/searchList.do">
  고객번호 : <input type="text" name="cust_id"> <input type="submit"
   value="조회"> <input type="button" value="고객추가"
   onclick="location.href='practice1AddUpdate.jsp?command=add'">
 </form>
</body>
</html>