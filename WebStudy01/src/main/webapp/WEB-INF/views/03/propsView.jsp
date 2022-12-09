<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<!-- <img alt="" src="../../resources/images/cat3.png"> -->
<%-- <img alt="" src="<%=request.getContextPath() %>/resources/images/cat3.png"> --%>
<h4> properties 파일 뷰어 </h4>
<label>
	<input type="radio" name="dataType" value="json" checked/>JSON
</label>
<label>
	<input type="radio" name="dataType" value="xml"/>XML
</label>

<button type="button" class="loadData">LOAD DATA</button>
<button type="button" class="clearData">CLEAR DATA</button>
<table border='3'>
	<thead>
		<th>key</th>
		<th>value</th>
	</thead>
	<tbody id="listBody">
	
	</tbody>
</table>

<script type="text/javascript">
// const TABLE_BODY = $('table tbody');
let listBody = $('#listBody');
let dataTypes = $('[name=dataType]');
let makeTrTag = function(name, value){
	let tr = $('<tr>').append($('<td>').html(name), $('<td>').html(value));
	return tr;
};

let successes = {
	json :	function(resp){
		let trTags = [];
		$.each(resp, function(name, value){			
			trTags.push(makeTrTag(name,value));
		});
		listBody.empty();
		listBody.append(trTags);
	},
	
	xml :	function(domResp){
		let trTags = [];
		let root = $(domResp).find("Properties");
		root.children().each(function(idx, child){
			let name = child.tagName;
			let value = child.innerHTML;
			trTags.push(makeTrTag(name,value));
			
		});
		listBody.empty();
		listBody.append(trTags);
	}
};

let btn = $('.loadData').on('click', function(){
	let dataType = dataTypes.filter(':checked').val();
	$.ajax({
		dataType : dataType,
		success : successes[dataType],
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});	
});

let clearBtn = $('.clearData').on('click',function(){
	listBody.empty();
})


</script>
</body>
</html>