<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사칙연산계</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
  <input type="radio" name="dataType" value="json" checked />JSON
  <input type="radio" name="dataType" value="xml"/>XML
  
  <form method="post">
  	<input type="number" name="leftOp" placeholder="좌측피연산자" />
  	<select name="operator">
  		<option value="PLUS">+</option>
  		<option value="MINUS">-</option>
  		<option value="MULTIPLY">*</option>
  		<option value="DIVIDE">/</option>
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
  let successes = {
		json :	function(resp){
// 			console.log(resp);
			let resultStr = f_operator(parseInt(resp.leftOp),parseInt(resp.rightOp),resp.operator);
			resultArea.html(resultStr);
		},
		
		xml :	function(domResp){
// 			console.log($(domResp));
			
			let left = parseInt($(domResp).find("leftOp").text());
			let right = parseInt($(domResp).find("rightOp").text());
			let operator = parseInt($(domResp).find("operator").text());
			
			let resultStr = f_operator(left,right,operator);
			resultArea.html(resultStr);
		}
	};

  let form = $('form').on('submit',function(event){
	  event.preventDefault();
	  let dataType = dataTypes.filter(':checked').val();
// 	  console.log(dataType);
// 	  console.log($(this).serialize());
	  let data = {};
	  $.each($(this).serializeArray(),function(idx,item){
		  data[item.name] = item.value;
	  })
	  console.log(data);
	  $.ajax({
			method : this.method,
			data : data,
			dataType : dataType,
			success : successes[dataType],
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
	  }); 
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