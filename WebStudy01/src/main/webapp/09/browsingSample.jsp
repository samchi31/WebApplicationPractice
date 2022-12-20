<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/browsingSample.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript">
	$.fn.generateFileList = function(){
		let target = this.data("target");
		let ulTag = this;
		$.ajax({
			url : "<%=request.getContextPath() %>/browsing/getFileList",
			data : {
				target:target
			},
			dataType : "json",
			success : function(resp) {
// 				console.log(resp.files);
				ulTag.empty();
				let liTags = [];
				$.each(resp.files, function(idx, wrapper){
// 					console.log(idx,wrapper);					
					let li = $('<li>').addClass("list-group-item")
									.data("contentType",wrapper.contentType)
									.html(wrapper.name);
					liTags.push(li);
				});
				ulTag.append(liTags);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		return this;
	};
</script>
</head>
<body>
<div class="col">
	<h4>src : /resources/images</h4>
	<ul id="srcUL" class="list-group" data-target="/resources/images">
		<li data-content-type="image/jpeg" class="list-group-item">cat1.jpg</li>
		<li data-content-type="image/jpeg" class="list-group-item active">cat1.jpg</li>
	</ul>
</div>
<div class="col">
	<input type="radio" name="command" value="COPY" checked />COPY
	<input type="radio" name="command" value="MOVE" />MOVE
	<button id="controlBtn" class="btn btn-primary">실행</button>
</div>
<div class="col">
	<h4>dest : /destImgs</h4>
	<ul id="destUL" class="list-group" data-target="/destImgs">
	</ul>
</div>

</ul>

<script type="text/javascript">
	
	let srcUL = $('#srcUL').generateFileList().on('click', "li", function(){
		$(this).siblings("li").removeClass("active");
		$(this).addClass("active");
	});
	let destUL = $('#destUL').generateFileList();
	
	let radioCommand = $("[name=command]");
	$(document).on('click', "#controlBtn", function(){
// 		console.log(this);
		let filename = srcUL.find("li.active").text();
// 		console.log(filename);
		if(!filename) return false;
		let srcFile = srcUL.data("target")+"/"+filename;
		let data = {
				srcFile : srcFile
				, destFolder : destUL.data("target")
				, command : radioCommand.filter(":checked").val()
			};
		$.post("<%=request.getContextPath()%>/browsing/fileManager",data,function(resp){
// 			console.log(resp);
			if(resp.result){
				destUL.generateFileList();
				srcUL.generateFileList()
			}else
				alert("파일 관리 실패");
		}, "json");
		

	});
	

</script>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>