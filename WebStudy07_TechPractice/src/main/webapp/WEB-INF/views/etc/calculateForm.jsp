<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    

<h4>CalculateController, calculateForm.js참고</h4>

<form method="post" id="calForm" action="${pageContext.request.contextPath }/etc/calculate/operate" 
	  class="row g-3 mt-3" data-target="#resultArea">
	<div class="col-auto">
		<input type="number" name="leftOp" placeholder="좌측피연산자" class="form-control"/>
	</div>
	<div class="col-auto">
		<select name="operator" class="form-control">
			<%
				OperatorType[] operators = OperatorType.values();
				for(OperatorType tmp  : operators){
					%>
					<option value="<%=tmp.name()%>"><%=tmp.getSign() %></option>
					<%
				}
			%>
		</select>
	</div>
	<div class="col-auto">
		<input type="number" name="rightOp" placeholder="우측피연산자" class="form-control"/>
	</div>
	<div class="col-auto">
		<button type="submit" class="btn btn-primary">=</button>
	</div>
</form>

<div id="resultArea" class="bg-success fs-1 mt-3" style="--bs-bg-opacity: .5;"></div>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom.js"></script>
<script src="${pageContext.request.contextPath }/resources/packages/etc/calculateForm.js"></script>

















