<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<h4>WELCOME</h4>
<pre>
	1. Function/Procedure 실행 방법 + RESTful Controller 구현 예제 (**.etc.controller.ProceduralController)
	2. RESTful Controller 구현 예제 +  비동기 요청에 CSRF token 포함시키는 방법 (**.etc.controller.CalculateController)
	3. UI plugin : Adapter pattern 활용
		1) FancyTree(**.uiplugin.fancytree.controller.FancyTreeController)
		2) FullCalender(**.uiplugin.fullcalendar.controller.FullCalendarController)
		3) DataTable(**.uiplugin.datatable.controller.DataTableController)
		3) GridStack(Portlet)(**.uiplugin.gridstack.controller.GridStackController)
	4. 파일 다운로드 처리 방법 
		1) single file download(**.file.controller.FileDownloadController)
		2) multiple file download : commons-compress 활용 압축 다운로드(**.file.controller.FileDownloadController)
		3) FTP 서버와 Commons-Net 활용 FTP 클라이언트 기반의 파일 다운로드 구조 (**.file.controller.FTPFileController)
	5. 실시간 통신 (**.realtime.controller.RealTimeController, /WEB-INF/spring/appServlet/websocket-context.xml)
		1) WebSocket : kr.or.ddit.realtime.handler.RealtimeTextWebSocketHandler
		2) STOMP : kr.or.ddit.realtime.handler.StompMessageController
	6. 스케쥴링 기반의 일괄 처리 : task-context.xml 참고
	** 공통 (/src/main/webapp/resources/packages/**.js)
</pre>