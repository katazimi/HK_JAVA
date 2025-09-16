<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
<link rel="stylesheet" href="css/registForm.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function idChk() {
		const id = document.querySelectorAll("input[name=id]")[0].value;
		if (id=="") {
			alert("아이디를 입력하세요.");
		}else {
			window.open("userController.jsp?command=idChk&id="+id,"아이디 확인","width=300px,height=300px");
		}
	}
	
	//주소 API
	function addrIn() {
		var roadAddr;
		new daum.Postcode({
	        oncomplete: function(data) {
	        	document.querySelectorAll("input[name]")[5].value = data.zonecode;
	        	document.querySelectorAll("input[name]")[6].value = data.roadAddress;
	        	document.querySelectorAll("input[name]")[7].value = data.jibunAddress;
	        }
	    }).open();
	}
	
	onload=function(){
	    const inputs=document.querySelectorAll("input[name]");
	    const idInput = document.querySelectorAll("input[name=id]")[0];
	    
	    
	    // 아이디 입력 필드가 변경될 때마다 중복체크 상태 초기화
	    idInput.oninput = function() {
	        localStorage.setItem("idChk", "n");
	    }
	    
	    for (let i=2; i<inputs.length; i++) {
	        inputs[i].onfocus=function(){
	        	let isIdChk = localStorage.getItem("idChk");
	            if (isIdChk==null || isIdChk==='n') {
	                alert("아이디 중복체크를 먼저 확인하세요.");
	                inputs[1].focus(); // 아이디 필드로 포커스 이동 (inputs[0])
	            }
	        }
	    }
	}
	
	function isPW(formEle) {
		
		if(formEle.password.value!=formEle.password2.value) {
			alert("비밀번호를 확인하세요.");
			formEle.password.value="";
			formEle.password2.value="";
			formEle.password.focus();
		}
		
		localStorage.removeItem("idchk");
		return true;
	}
</script>
</head>
<body>
	<div id="registForm">
		<h1>회원가입</h1>
		<form action="userController.jsp" method="post" onsubmit="return isPW(this)">
			<input type="hidden" name="command" value="insertUser"/>
			<input type="text" name="id" required="required" placeholder="ID"/>
			<button type="button" onclick="idChk()">중복체크</button>
			<input type="text" name="name" required="required" placeholder="NAME"/>
			<input type="password" name="password" required="required" placeholder="PASSWORD"/>
			<input type="password" name="password2" required="required" placeholder="PASSWORD2"/>
			<input type="text" name="address" required="required" placeholder="우편번호"/>
			<button type="button" onclick="addrIn()">우편번호 찾기</button>
			<input type="text" name="address2" required="required" placeholder="도로명주소"/>
			<input type="text" name="address3" required="required" placeholder="지번주소"/>
			<input type="text" name="address4" required="required" placeholder="상세주소"/>
			<input type="email" name="email" required="required" placeholder="E-MAIL"/>
			<button type="submit">가입완료</button>
			<button type="button" onclick="location.href='index.jsp'">메인</button>
		</form>
	</div>
</body>
</html>