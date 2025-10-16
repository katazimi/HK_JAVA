<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
<!-- <link rel="stylesheet" href="css/registForm.css"> -->
<<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function idChk() {
		const id = document.querySelectorAll("input[name=id]")[0].value;
		if (id=="") {
			alert("아이디를 입력하세요.");
		}else {
			window.open("idChk.do?id="+id,"아이디 확인","width=300px,height=300px");
		}
	}
	
	//주소 API
	function addrIn() {
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	
	            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("sample6_extraAddress").value = extraAddr;
	            
	            } else {
	                document.getElementById("sample6_extraAddress").value = '';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('sample6_postcode').value = data.zonecode;
	            document.getElementById("sample6_address").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("sample6_detailAddress").focus();
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
	<div id="registForm" style="width:600px; margin: 100px auto;" class="form-control ml-5">
	<h1>회원가입</h1>
	<form action="insertUser.do" method="post" onsubmit="return isPW(this)">
		<div class="row mb-3">
			<div class="col-8">
				<input class="form-control" type="text" name="id"
					required="required" placeholder="ID" />
			</div>
			<div class="col-4">
				<button class="form-control" type="button" onclick="idChk()">중복체크</button>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-12">
				<input class="form-control" type="text" name="name"
					required="required" placeholder="NAME" />
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-12">
				<input class="form-control" type="password" name="password" required="required"
					placeholder="PASSWORD" />
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-12">
				<input class="form-control" type="password" name="password2" required="required"
					placeholder="PASSWORD확인" />
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-12">
				<!-- <input class="form-control" type="text" name="address" required="required" 
					placeholder="ADDRESS" /> -->
				<input type="text" id="sample6_postcode" name="address1" placeholder="우편번호">
				<input type="button" onclick="addrIn()" value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" name="address2" placeholder="주소"><br>
				<input type="text" id="sample6_detailAddress" name="address3" placeholder="상세주소">
				<input type="text" id="sample6_extraAddress" name="address4" placeholder="참고항목">
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-12">
				<input class="form-control" type="email" name="email" required="required"
					placeholder="EMAIL" />

			</div>
		</div>
		<div class="row mb-3">
			<div class="col-6">
				<button class="form-control" type="submit">가입완료</button>

			</div>
			<div class="col-6">
				<button class="form-control" type="button" onclick="location.href='index.jsp'">메인</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>