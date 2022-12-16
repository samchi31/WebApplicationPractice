<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<!-- jquery 가 있어야 사용가능한 경우 아래쪽에 -->
<script src="<%=request.getContextPath() %>/resources/js/custom.js"></script>
</head>
<body>
	<h4>Restful 기반의 메모 관리 (자원에 대한 식별성(url)+행위 정보(method))</h4>
	
	<form name="memoForm" action="${pageContext.request.contextPath}/memo" method="post">
		<input type="text" name="writer" placeholder="작성자" />
		<input type="date" name="date" placeholder="작성일" />
		<textarea name="content"></textarea>
		<input type="submit" value="등록" />		
	</form>
	
	<table class="table-bordered">
		<thead>
			<tr>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody id="listBody">
		
		</tbody>
	</table>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        ...
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
<script type="text/javascript">
	let listBody = $('#listBody');
	
	// $('[name="memoForm"]')
	let memoForm = $(document.memoForm).on('submit',function(event){
		//this == event.target
		//  	=/=$(this)		
		event.preventDefault();
		
// 		let data = $(this).serialize(); // parameter로 넘기기
		// json payload ~> custom.js 
// 		{
// 			writer:""
// 			, date:""
// 			, content:""
// 		}
		let data = $(this).serializeObject();	// parameter 말고 json으로 보내기 위한 객체		
		
		//let memoForm = this;	// dom 객체
		// parameter는 원래의 객체 구조를 알수 없다
		$.ajax({
			url : this.action,
			method : this.method,
			contentType : "application/json;charset=UTF=8",		// request content-type을 결정
			data : JSON.stringify(data),	// 마샬링 하기
			dataType : "json",		// 받아오는 resp 의 타입 (req의 accept 헤더, resp의 content-type헤더)
			success : function(resp) {
				makeListBody(resp.target);
				//memoForm.reset();	//dom 객체일때만
				memoForm[0].reset();	// jquery 객체일 경우
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		
		return false;
	});
	
	//ED'path't Driven Development), TDD(Test Driven Development)
	const myModal = $('#myModal').on('show.bs.modal',function(event){
		//this == event.target
		//console.log(event.relatedTarget);	// modal을 오픈할 때 사용한 클릭 대상, tr
		let memo = $(event.relatedTarget).data("memo");
		$(this).find(".modal-body").html(memo.content);	// 현재 떠있는 모달의 요소에 접근
		$(this).find(".modal-title").html(memo.code);		
	}).on('hidden.bs.modal',function(event){
		$(event.target).find(".modal-body").empty();
		$(event.target).find(".modal-title").empty();
	});
	
	let makeListBody = function(memoList){
		listBody.empty();
		let trs = [];
		if(makeListBody.length>0){
			$.each(memoList,function(idx,memo){
				//console.log(idx, value);
				//data-bs-toggle="modal" data-bs-target="#exampleModal"
				let tr = $('<tr>').append(
						$('<td>').html(memo.writer), $('<td>').html(this.date)
					).data("memo",memo)
					.attr({
						"data-bs-toggle":"modal",
						"data-bs-target":"#myModal"
					});
				trs.push(tr);
			});
		} else {
			$("<tr>").html(
					$("<td>").attr("colspan","2")
							 .html("작성된 메모 없음"));
			trs.push(tr);
		}
		listBody.append(trs);
	};
	
	$.ajax({
		url : "${pageContext.request.contextPath}/memo",
		method : "get",
		dataType : "json",
		success : function(resp) {
			makeListBody(resp.target);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});

</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>