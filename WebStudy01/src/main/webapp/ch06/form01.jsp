<%@page import="ch06.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Processing</title>
<script type="text/javascript" src='<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/ch06/ckeditor/ckeditor.js'></script>
</head>
<body>
<%
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

%>

<!--  
- input 태그 정리
	1. type
		- text : 한 줄 텍스트 입력
		- radio : 라디오 버튼 중 하나만 선택
		- checkbox : 다중 선택
		- password : 암호 입력
		- hidden : 숨기기
		- file : 파일 업로드
		- button : 버튼 모양
		- reset : 폼데이터 초기화
		- submit : 서버로 전송
	2. name : 입력 양식 식별 (유일함)
	3. value : 초기값 설정
	4. required : 필수
	5. placeholder : 힌트
	6. autofocus : 자동포커스
-->

	<h3>회원 가입</h3>
	<form action="<%=request.getContextPath() %>/ch06/form01_process.jsp" name="member" method="post">
		<p>
			아이디 : <input type="text" name="id" required placeholder="아이디를 입력" autofocus value=${memberVO.id }/>
			<input type="button" value="아이디중복검사" />
		</p>
		<p>비밀번호 : <input type="password" name="passwd" required value=${memberVO.passwd }/></p>
		<p>이름 : <input type="text" name="name" maxlength="7" size="7" required value=${memberVO.name }/></p>
		<p>
			우편번호 : <input type="text" name="postNum" required value=${memberVO.postNum }/>
			<button>우편번호 검색</button>
		</p>
		<p>
			연락처 : <input type="text" maxlength="4" size="4" name="phone1" required value=${memberVO.phone1 }/>
			- <input type="text" maxlength="4" size="4" name="phone2" required value=${memberVO.phone2 }/>
			- <input type="text" maxlength="4" size="4" name="phone3" required value=${memberVO.phone3 }/>
		</p>
		<p>
			성별 : <input type="radio" name="gender" value="남성" 
				<c:if test="value=${memberVO.gender=='남성' }">checked</c:if>
			/> 남성
			<input type="radio" name="gender" value="여성" 
				<c:if test="value=${memberVO.gender=='여성' }">checked</c:if>
			/> 여성
		</p>
		<p>
			취미 : 독서<input type="checkbox" name="hobby1" value="hobby1" 
				<c:if test="value=${memberVO.hobby1=='독서' }">checked</c:if> 
			/> 
			운동<input type="checkbox" name="hobby2" value="hobby2" 
				<c:if test="value=${memberVO.hobby2=='운동' }">checked</c:if>
			/>
			영화<input type="checkbox" name="hobby3" value="hobby3" 
				<c:if test="value=${memberVO.hobby3=='영화' }">checked</c:if>
			/>
		</p>
		<p>
			<!-- row 줄수 / col 열수 -->
			<textarea rows="3" cols="30" name="comment" placeholder="가입 인사를 입력해주세요">
			${memberVO.comment}
			</textarea>
		</p>
		<p>
			<!-- 폼 데이터에 내용이 채워져야지만 활성화 -->
			<!-- 비활성화 : disabled-->
			<input type="submit" value="가입하기" />
			<input type="reset" value="다시쓰기" />
		</p>
	</form>
	
<script>
const id = $('[name=id]');
const btn = $('[type=button]');
const btn_post = $('button');
const post = $('[name=postNum]');

btn.on('click',function(){
	if(id.val()=='a001' || id.val()=='b001' || id.val()=='c001'){
		alert("아이디 중복");
		id.val("");
		id.focus();
	} 
});

btn_post.on('click',function(){
	post.val('33321');
})

CKEDITOR.replace("comment");
</script>
</body>
</html>