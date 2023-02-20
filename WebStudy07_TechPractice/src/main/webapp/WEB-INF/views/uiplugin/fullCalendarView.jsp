<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<pre>
	FullCalendarEvent 인터페이스의 파생 구조와 Adapter Pattern 활용 방식 확인
	FullCalendarController, fullCalendarView.js 참고 
	달력의 월을 변경하거나 상품(Event)를 클릭해 볼것.
</pre>
    
 <div class="row mt-3 text-center">
 	<h4 class="mb-3">월별 입고 상품</h4>
	<div id="calendar" data-source="${pageContext.request.contextPath}/uiplugin/fullCalendar/events"></div>
</div>
<script src="${pageContext.request.contextPath }/resources/js/fullcalendar-6.1.0/dist/index.global.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/packages/uiplugin/fullCalendarView.js"></script>        