/**
 * cookieFunc.js
 */

	function setCookie(name,value,expires,domain,path,secure){
		
		let cookies="";
		
		//인코딩 처리3가지 : ASCII코드로 변환, 나머지 유니코드 변환 -> 이제 안씀
		//escape() : 주소전체를 인코딩할때 사용
		//			 일반 문자는 인코딩처리, 주소와 관련된 특수문자는 제외
		//			 예) https://www.hankyung.com/user?id=hk&name=han
		//encodeURIComponent(): 모든 문자/특수문자 등등을 인코딩 처리함
		//						파라미터에 대한 내용도 모두 처리 가능
		
		cookies+=name+"="+encodeURIComponent(value); //인코딩처리해서 저장
		
		const date = new Date();
		date.setDate(date.getDate()+expires);
		cookies+=";expires="+date.toUTCString(); //유효기간 설정(세계표준시)
	// 	cookies+=";max_age="+(1000*1*60*60*24)// ms단위로 설정
	
		if(domain){
			cookies+=";domain="+domain;
		}
		if(path){
			cookies+=";path="+path;
		}
		if(secure){
			cookies+=";secure="+secure;
		}
		
		document.cookie=cookies;//쿠키 저장하기
	}
	
	// cookie삭제기능: expires 값을 재설정
	function removeCookie(name) {
		const date=new Date();
		date.setDate(date.getDate()-1); //유효기간 지남
		document.cookie=name+"=;expires="+date.toUTCString();
	//		document.cookie=name+"=;max-age=0";
	}
		
	// getCookie 기능 구현
	function getCookie(cookieName){
		//cookie="user=hk;name=han;admin=kkk" -> 문자열 추출
		
		let cookieValue = document.cookie.split(";");
		console.log(cookieValue);
		let value = "";
		
		for (var i = 0; i < cookieValue.length; i++) {
			if(cookieValue[i].indexOf(cookieName)!= -1){
				value=cookieValue[i].split("=")[1];
				console.log("저장된 쿠키값: ",value);
				return decodeURIComponent(value);
			}
		}
	}