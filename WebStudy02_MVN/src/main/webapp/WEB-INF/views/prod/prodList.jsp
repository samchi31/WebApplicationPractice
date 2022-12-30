<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>일련번호</th>
				<th>상품분류</th>
				<th>상품명</th>
				<th>거래처명</th>
				<th>구매가</th>
				<th>판매가</th>
				<th>상품구매자수</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="prodList" value="${pagingVO.dataList }" />
			<c:choose>
				<c:when test="${not empty prodList}">
					<c:forEach items="${prodList }" var="prod">
						<tr>
							<td>${prod.rnum }</td>
							<td>${prod.lprodNm }</td>					
							<td>${prod.prodName }</td>
							<td>${prod.buyer.buyerName }</td>
							<td>${prod.prodPrice }</td>
							<td>${prod.prodCost }</td>
							<td>${prod.cartCount }</td>					
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">조건에 맞는 품목이 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					${pagingVO.pagingHTML }
					<div id="searchUI">
						<select name="searchType">
							<option value>전체</option>
							<option value="lprodNm">분류명</option>
							<option value="buyerName">거래처명</option>
							<option value="prodName">상품명</option>
						</select>
						<input type="text" name="searchWord" />
						<input type="button" id="searchBtn" value="검색" />
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
	<h4>hidden form</h4>
	<form id="searchForm">
		<input type="text" name="page" />
		<input type="text" name="searchType" />
		<input type="text" name="searchWord" />
	</form>
	
<jsp:include page="/includee/postScript.jsp" />
<script type="text/javascript">
	
	$("[name=searchType]").val("${pagingVO.simpleCondition.searchType}");
	$("[name=searchWord]").val("${pagingVO.simpleCondition.searchWord}");
	
	let searchForm = $("#searchForm");
	let searchUI = $('#searchUI').on("click", "#searchBtn", function(){
		let inputs = searchUI.find(":input[name]");
		$.each(inputs, function(index, input){
			let name = this.name;	
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
	
	$("a.paging").on('click', function(event){
		event.preventDefault();
		
		let page = $(this).data('page');
		if(!page) return false;
		
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
		
		return false;
	});

</script>
</body>
</html>