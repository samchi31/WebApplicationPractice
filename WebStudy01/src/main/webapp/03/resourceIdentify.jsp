<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자원종류와 식별방법</title>
</head>
<body>
<pre>
	: 자원의 위치와 경로 표기 방법에 따라 분류
	
	1. File System Resource : d:/contents/images/cat2.jpg
	<%
		String realPath = "d:/contents/images/cat3.png";
		File fileSystemResource = new File(realPath);
	%>
	파일의 크기 : <%=fileSystemResource.length() %>
	2. Class path Resource : /kr/or/ddit/images/cat2.png (classpath 이후의 논리 경로)
	<%
		// String qualifiedName = "/kr/or/ddit/images/cat2.png";
		String qualifiedName = "../images/cat2.png";
		realPath = DescriptionServlet.class.getResource(qualifiedName).getFile();	// 물리 경로 찾기
		// 위와 같은 코드
		// classLoader는 상대경로 사용안함 classpath 포함이라 맨앞 / 안함
		realPath = DescriptionServlet.class.getClassLoader().getResource("kr/or/ddit/images/cat2.png").getFile();
		File classPathResource = new File(realPath);
	%>
	실제 경로 : <%=realPath %>
	파일의 크기 : <%=classPathResource.length() %>
	3. Web Resource : https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif
	http://localhost/WebStudy01/resources/js/jquery-3.6.1.min.js
	<%
		// String resourceURL = "https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif";
		String resourceURL = "http://localhost/WebStudy01/resources/js/jquery-3.6.1.min.js";
		URL url = new URL(resourceURL);
		// 주소창에 주소 입력 후 엔터 칠 경우 connection 수립
		URLConnection conn = url.openConnection();
		String resourcePath = url.getPath();
		int lastIndex = resourcePath.lastIndexOf("/");
		String fileName = resourcePath.substring(lastIndex+1);
		String folderPath = "d:/contents/images";
		File downloadFile = new File(folderPath,fileName);
		InputStream is = conn.getInputStream();
		Files.copy(is, Paths.get(downloadFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
	%>
	resourcePath : <%=resourcePath %>
	
	*** 웹자원에 대한 식별성 : URI
	URI(Uniform Resource Identifier) : 범용 자원 식별자 (아래는 종류)
	
		- URL(Uniform Resource Locator) : 범용 자원 위치
		- URN(Uniform Resource Name) : 범용 자원 이름 (같은이름일 경우 힘듬, 이름을 알고 있어야함)
		- URC(Uniform Resource Content,class,classfier) : 범용 자원 컨텐츠
	
	URL 구조
	protocol(scheme)://IP(DN):port/context/depth1...depthn/resourceName
		// : 루트를 표현하기 위한 구분자
		:port : 생략가능
	DomainName
	3 level ; www.naver.com 	// .com : GlobalTopLevelDomain (GTLD)
	4 level ; www.naver.co.kr	// .rk : NationalTopLevelDomain (NTLD)
	
	URL 표기 방식
	절대경로(**) : 최상위 루트부터 전체 경로 표현 - 생략 가능한 요소가 존재
		client side : /WebStudy01/resources/images/cat3.png
					: context path 부터 시작됨
		server side : /resources/images/cat3.png	// 서버 측에선 이미 context path 정보가 있어서 필요없다 
					: context path 이후의 경로 표기 // 서버에선 상대경로 사용안함
	상대경로 : 기준점(브라우저의 현재 주소)을 중심으로 한 경로
</pre>
<%
// 	InputStream is2 = application.getResourceAsStream("/resources/images/cat1.jpg");
	String realPath1 = application.getRealPath("/resources/images/cat1.jpg");
	String realPath2 = application.getRealPath(request.getContextPath()+"/resources/images/cat1.jpg");
	
	// 포워드는 서버 안에서 이동
	request.getRequestDispatcher("/WEB-INF/views/depth1/test.jsp").forward(request, response);
	// 리다이렉트는 헤더로 응답 후 새로운 요청
	response.sendRedirect(request.getContextPath()+"/member/memberForm.do");
%>
<!-- <img src="http://localhost/WebStudy01/resources/images/cat3.png" /> -->
<img src="<%=request.getContextPath() %>/resources/images/cat3.png" />
<img src="../resources/images/cat4.png" />
<img src="cat5.png" />
<%-- 서버사이드 방식으로 접근한 파일의 크기 : <%=is2.available() %> --%>
realPath1 : <%=realPath1 %>
realPath2 : <%=realPath2 %> 
</body>
</html>