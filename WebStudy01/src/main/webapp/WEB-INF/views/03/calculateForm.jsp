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
  2+2=4//sample
  </div>
  
<script type="text/javascript">
let dataTypes = $('[name=dataType]');

  let successes = {
		json :	function(resp){
			console.log(resp);
// 			let trTags = [];
// 			$.each(resp.target, function(name, value){			
// 				trTags.push(makeTrTag(name,value));
// 			});
// 			listBody.empty();
// 			listBody.append(trTags);
		},
		
		xml :	function(domResp){
			console.log(domResp);
// 			let trTags = [];
// 			let root = $(domResp).find("target");
// 			root.children().each(function(idx, child){
// 				let name = child.tagName;
// 				let value = child.innerHTML;
// 				trTags.push(makeTrTag(name,value));
				
// 			});
// 			listBody.empty();
// 			listBody.append(trTags);
		}
	};

  let form = $('form').on('submit',function(event){
	  event.preventDefault();
	  let dataType = dataTypes.filter(':checked').val();
// 	  console.log($(this).serialize());

	  $.ajax({
			method : this.method,
			data : $(this).serialize(),
			dataType : dataType,
			success : successes[dataType],
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
	  }); 
  });
  

</script>
</body>
</html>