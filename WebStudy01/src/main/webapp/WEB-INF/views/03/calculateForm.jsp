<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사칙연산계</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom.js"></script>
</head>
<body>
  <input type="radio" name="dataType" value="json" />JSON
  <input type="radio" name="dataType" value="xml"/>XML
  
  <form method="post" id="calForm">
  	<input type="number" name="leftOp" placeholder="좌측피연산자" />
  	<select name="operator">
  		<%
  			OperatorType[] operators = OperatorType.values();
  			for(OperatorType tmp : operators){
  				%>
  				<option value="<%=tmp.name() %>"><%=tmp.getSign() %></option>
  				<%
  			}
  		%>
  	</select>
  	<input type="number" name="rightOp" placeholder="우측피연산자" />
  	<button type="submit">=</button>
  </form>
  <div id="resultArea">
<!--   2+2=4//sample -->
  </div>
  
<script type="text/javascript">
  let dataTypes = $('[name=dataType]');
  let resultArea = $('#resultArea');
  
  let form = $('#calForm').on('submit',function(event){
	  event.preventDefault();
	  
	  let dataType = dataTypes.filter(':checked').val();
	  if(dataType){
		  dataType='plain';
	  }
// 	  let data = $(this).serialize();
	  let data = $(this).serializeObject();
	  data.leftOp = parseInt( data.leftOp );
	  data.rightOp = parseInt( data.rightOp );
	  
	  // url이 없는 경우 client side 주소 그대로 감
	  $.ajax({
		    url :this.action,
			method : this.method,
			contentType : "application/json",
			data : JSON.stringify(data),
			dataType : dataType,
			success : function(resp){
				resultArea.html(resp.expression);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
	  }); 
	  return false; 	// preventDefault 시 필요없긴 함
  });
  
  function f_operator(num1,num2,opt){
	  let result;
	  let resultString;
	  let optStr;
	  if(opt == "PLUS"){
		  result = num1 + num2;
		  optStr = "+";
	  } else if(opt == "MINUS"){
		  result = num1 - num2;
		  optStr = "-";
	  } else if(opt == "MULTIPLY"){
		  result = num1 * num2;
		  optStr = "*";
	  } else if(opt == "DIVIDE"){
		  result = num1 / num2;
		  optStr = "/";
	  }
	  resultString = ""+num1+optStr+num2+"="+result;
	  return resultString;
  };

</script>
</body>
</html>