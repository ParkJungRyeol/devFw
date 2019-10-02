<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="p0003VO" class="project.hm.p0003.vo.P0003VO" scope="request"></jsp:useBean>
<%
   request.setCharacterEncoding( "utf-8" );
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 정보 수정창</title>
<script>
	function frm_update(){
		var frmPro = document.frm;
		frmPro.method = "post";
		frmPro.action = "${contextPath}/hm/p0003/updateMember.do";
		frmPro.submit();
	}
</script>
</head>
<body>
	<form name="frm" method="post" encType="UTF-8">
		<table>
   <tr>
    <td>고객번호</td>
    <td> <input type='text' name='cust_id'
     <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_id}"</c:if>
     ></td>
   </tr>
   <tr>
    <td>고객이름</td>
    <td><input type='text' name='cust_name'
     <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_name}"</c:if>
     ></td>
   </tr>
   <tr>
    <td>고객주소</td>
    <td><input type='text' name='cust_address'
     <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_address}"</c:if>
     ></td>
   </tr>
   <tr>
    <td>고객주</td>
    <td><input type='text' name='cust_state'
     <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_state}"</c:if>
     ></td>
   </tr>
   <tr>
    <td>고객우편번호</td>
    <td><input type='text' name='cust_zip'
     <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_zip}"</c:if>
     ></td>
   </tr>
   <tr>
    <td>고객국가</td>
    <td><input type='text' name='cust_country'
    <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_country}"</c:if>
     ></td>
   </tr>
   <tr>
    <td>고객담당자</td>
    <td><input type='text' name='cust_contact'
     <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_contact}"</c:if>
     ></td>
   </tr>
   <tr>
    <td>고객메일주소</td>
    <td><input type='text' name='cust_email'
     <c:if test="${command=='modSearch'}"> placeholder="${p0003VO.cust_email}"</c:if>
     ></td>
   </tr>
  </table>
  
  <c:if test="${command=='modSearch'}">
  	<input type="submit" name='submit' value="수정" onclick = "frm_update()">
  	<input type='hidden' name='command' value='modUpdate' />
  </c:if>
	</form>
	
</body>
</html>