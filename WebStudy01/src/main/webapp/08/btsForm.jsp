<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
	<select name="member">
		<option value>멤버선택</option>	
		<option value="B001">RM</option>	
	</select>
	
	
<script type="text/javascript">

	let memberSelect = $('[name=member]').on('change',function(){
		let option = $(this).find('option:selected');
		let url = "<%=request.getContextPath() %>/bts/" + option.val();
// 		console.log(url);
// 		location.href = url;
		$.ajax({
			url : url,
			dataType : "html",
			success : function(resp) {
				memberSelect.after(resp);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});	
	
	$.ajax({
		url : "<%=request.getContextPath() %>/bts",
		dataType : "json",
		success : function(resp) {
			memberSelect.empty();
			let options = [];
			$.each(resp.bts, function(code, values){
// 				console.log(id,content);
				let option = $('<option>').val(code).html(values[0]);
				options.push(option);
			});
			memberSelect.append(options);
			
			//memberSelect.trigger('change');
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
	
	

</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>