<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.FileFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>model1구조에서model2구조로 imageStreamingFormServlet03에서 fowarding됨</title>
<script type="text/javascript" src='<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js'></script>
<style type="text/css">
	/* jpeg - red, png - green, gif - blue */
	.red{
		background-color: lavender;
	}
	.green{
		background-color: lightgreen;
	}
	.blue{
		background-color: lightyellow;
	}
</style>
</head>
<body>

	<form name="imgForm" action="<%=request.getContextPath()%>/imageStreaming.do">
		<select name="image">
		
		
		</select>
		
		<input type="text" name="sample" value ="asdf"/>
		<input type='submit' value='전송'/>
	</form>
	<div id="imgArea">
		<img src="<%=request.getContextPath()%>/imageStreaming.do?image=cute1.PNG"/>
	</div>
	
	<script type="text/javascript">
		
		const DIVTAG = $('#imgArea');
		const SELECTTAG = $('[name=image]').on('change',function(event){
			let option = $(this).find('option:selected');
			let mime = option.attr('class');
			let clzName = matchedClass(mime);
			$(this).removeClass(colors);
			$(this).addClass(clzName);
			
			let srcURL = document.imgForm.action;		
			let queryString = $(document.imgForm).serialize();
			let src = "%U?%P".replace("%U",srcURL).replace("%P",queryString); 	//serialize 쿼리스트링을 동적으로 생성
			console.log(srcURL)
			console.log(queryString)
			console.log(src)
<%-- 			var v_img = $('<img>',{src: '<%=request.getContextPath()%>/imageStreaming.do?image='+option.val()});	 --%>
			var v_img = $('<img>').attr('src',src);
			DIVTAG.html(v_img);
		});
		const changeCondition = {
				jpeg:"red",
				png: "green",
				gif: "blue"
		}
		const colors = [];
		$.each(changeCondition, function(prop, value){
// 			console.log(prop+", "+value);
			colors.push(value);
		});
		
		let matchedClass = function(mime){
			let clzName = "";
			for (let prop in changeCondition){
				let idx = mime.indexOf(prop);
				if(idx >= 0){
					clzName = changeCondition[prop];
					break;
				}
			}
			return clzName;
		}
		
		$.ajax({
			dataType : "json", 
			success : function(resp) {
				// resp : unmashaling 된 javascript 객체가 들어온다
// 				for(let i = 0; i < resp.length; i++){                
// 	                var option = $("<option>"+resp[i].name+"</option>");
// 	                $('select').append(option);
// 	            }
				let options = [];
				$.each(resp, function(index, file){
					let option = $('<option>').addClass(file.mime).html(file.name);
	                options.push(option);
				});
				SELECTTAG.append(options);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		
	</script>
</body>
</html>