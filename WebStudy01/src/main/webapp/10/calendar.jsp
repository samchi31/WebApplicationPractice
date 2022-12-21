<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(Locale.KOREA);
	String[] weekDays = dfs.getWeekdays();//
	pageContext.setAttribute("weekDays", weekDays);
	
	Calendar cal = Calendar.getInstance();
	int startWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1일~ 7토 
	pageContext.setAttribute("startWeek", startWeek);	// 시작하는 요일 숫자
	
	int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 현재 달 말일
	pageContext.setAttribute("lastDate", lastDate);
	
	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)-1, 1);
	int prevLastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 지난 달 말일
	pageContext.setAttribute("prevLastDate", prevLastDate);	
	
	pageContext.setAttribute("cal",cal);	// 되냐 되긴되네..
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="dayCount" value="1" />
<c:set var="offset" value="${startWeek }" />

<span><%=cal.get(Calendar.YEAR) %> ${car.get(1) }</span>
<table>
	<thead>
	<c:forEach var="idx" begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">
		<td>${weekDays[idx] }</td>
	</c:forEach>
	</thead>
	<tbody>
	<c:forEach begin="1" end="6">
		<tr>
		<c:forEach begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">
			<c:choose>
				<c:when test="${ (dayCount - offset) lt 1 }"><c:set var="date" value="${prevLastDate + (dayCount - offset) }" /></c:when>			
				<c:when test="${ (dayCount - offset) gt lastDate }"><c:set var="date" value="${dayCount - offset - lastDate }" /></c:when>			
				<c:otherwise><c:set var="date" value="${dayCount - offset }"/></c:otherwise>			
			</c:choose>
			<td>${date}</td>
			<c:set var="dayCount" value="${ dayCount + 1 }" />
		</c:forEach>
		</tr>
	</c:forEach>
	</tbody>
</table>

<script type="text/javascript">

</script>
</body>
</html>