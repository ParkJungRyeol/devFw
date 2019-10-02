<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function fn_process(p_command){
	var v_url = '';
	var vp_id = $("#p_cust_id").val();
	
	if(p_command == 'before'){
		p_command = 'search';
		var before_cust_id = $("#before_cust_id").val();
		$("#p_cust_id").val(before_cust_id);
		vp_id = $("#p_cust_id").val();
	}
	
	if(p_command == 'after'){
		p_command = 'search';
		var after_id = $("#after_cust_id").val();
		$("#p_cust_id").val(after_cust_id);
		vp_id = $("#p_cust_id").val();
	}
	
	//command 설정
	$("#command").val(p_command);
	
	var params = $("#frm").serialize();
	
	//추가시 값 초기화 후 종료
	if(p_command == 'add'){
		$('#frm')[0].reset();
		return;
	}
	
	//조회시 validation
	if(p_command == 'search' && vp_id == ''){
		alert("ID를 입력하세요");
		return;
	}
	
	//저장시 validation
	var v_id = $("#cust_id").val();
	if(p_command == 'save' && v_id == ''){
		alert("조회나 추가 후 저장을 선택해 주세요");
		return;
	}
	
	if(p_command == 'search'){
		v_url = "${contextPath}/hm/p0004/searchMember.do";
	}else if(p_command == 'save'){
		v_url = "${contextPath}/hm/p0004/updateMember.do";
	}
	
	$.ajax({
		type:"post",
		async:false,
		url:v_url,
		data: params,
		dataType:"json",
		success:function(data, textStatus){
			//var jsonInfo = JSON.parse(data);
			var jsonInfo = data;
			
			if(p_command == 'search'){
				if(jsonInfo.error_yn == 'Y'){
					alert(jsonInfo.error_text);
					$("#frm")[0].reset();
					return;
				}
				
				$('#cust_id').val(jsonInfo.cust_id);
				$('#before_cust_id').val(jsonInfo.before_cust_id);
				$('#after_cust_id').val(jsonInfo.after_cust_id);
				$('#idCopy').val(jsonInfo.cust_id);
				$('#cust_name').val(jsonInfo.cust_name);
				$('#cust_address').val(jsonInfo.cust_address);
				$('#cust_state').val(jsonInfo.cust_state);
				$('#cust_zip').val(jsonInfo.cust_zip);
				$('#cust_country').val(jsonInfo.cust_country);
				$('#cust_contact').val(jsonInfo.cust_contact);
				$('#cust_email').val(jsonInfo.cust_email);
			}else if(p_command == 'save'){
				if(jsonInfo.error_yn == 'Y'){
					alert(jsonInfo.error_text);
				}else{
					alert('저장되었습니다.');
				}
			}
		},
		error:function(request, textStatus, error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		complete:function(data,textStatus){
			
		}
	}); //end ajax
}
</script>
</head>
<body>
	<form name="frm" id="frm">
		<input type="text" name="p_cust_id" id="p_cust_id" />
		<input type="hidden" name="command" id="command"/>
		<input type="button" id="btn_search" value="조회" onClick="fn_process('search')" />
		<input type="button" id="btn_add" value="추가" onClick="fn_process('add')" />
		<input type="button" id="btn_save" value="저장" onClick="fn_process('save')" />
		<input type="button" id="btn_before" value="이전" onclick="fn_process('before')" />
		<input type="button" id="btn_after" value="이후" onclick="fn_process('after')" />
		<table>
			<tr>
				<td>고객ID</td>
				<td><input type="text" name="cust_id" id="cust_id"></td>
				<input type="hidden" name="idCopy" id="idCopy" />
				<input type="hidden" name="before_cust_id" id="before_cust_id" />
				<input type="hidden" name="after_cust_id" id="after_cust_id" />
			</tr>
			<tr>
				<td>고객이름</td>
				<td><input type="text" name="cust_name" id="cust_name"></td>
			</tr>
			<tr>
				<td>고객주소</td>
				<td><input type="text" name="cust_address" id="cust_address"></td>
			</tr>
			<tr>
				<td>고객주</td>
				<td><input type="text" name="cust_state" id="cust_state"></td>
			</tr>
			<tr>
				<td>고객우편번호</td>
				<td><input type="text" name="cust_zip" id="cust_zip"></td>
			</tr>
			<tr>
				<td>고객국가</td>
				<td><input type="text" name="cust_country" id="cust_country"></td>
			</tr>
			<tr>
				<td>고객담당자</td>
				<td><input type="text" name="cust_contact" id="cust_contact"></td>
			</tr>
			<tr>
				<td>고객메일주소</td>
				<td><input type="text" name="cust_email" id="cust_email"></td>
			</tr>
			<tr>
				<td>고객이름</td>
				<td><input type="text" name="cust_name" id="cust_name"></td>
			</tr>
		</table>
	</form>
</body>
</html>