<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/resources/js/fancytree/skin-win8/ui.fancytree.min.css" rel="stylesheet">
<div class="row mt-3">
	<h4> 거래처별 거래 품목 </h4>
	<div class="col-6" id="treeArea" 
		data-source="${pageContext.request.contextPath}/uiplugin/fancyTree"
		data-detail="${pageContext.request.contextPath}/uiplugin/fancyTree/detail"
		data-buyer-area="#buyerTbl"
		data-prod-area="#prodTbl"
	></div>
	<div class="col-6">
		<p>FancyTreeNode 인터페이스의 파생 구조와 Adapter Pattern 활용 방식 확인</p>
		<p>FancyTreeController, fancyTreeView.js 참고 </p>
		<p>좌측 트리 노드들을 클릭해서 activate 이벤트를 발생시켜 볼것.</p>
	</div>
</div>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>코드</th>
			<th>거래처명</th>
			<th>소재지</th>
			<th>담당자</th>
			<th>연락처</th>
			<th>이메일</th>
		</tr>
	</thead>
	<tbody id="buyerTbl">
		<tr>	
			<td class="buyerId"></td>
			<td class="buyerName"></td>
			<td class="buyerAdd1"></td>
			<td class="buyerCharger"></td>
			<td class="buyerComtel"></td>
			<td class="buyerMail"></td>
		</tr>
	</tbody>
</table>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품명</th>
			<th>구매가</th>
			<th>판매가</th>
		</tr>
	</thead>
	<tbody id="prodTbl">
		<tr class="sample d-none">
			<td class="prodId"></td>
			<td class="prodName"></td>
			<td class="prodCost"></td>
			<td class="prodPrice"></td>
		</tr>
	</tbody>
</table>
<script src="${pageContext.request.contextPath }/resources/js/fancytree/jquery.fancytree-all-deps.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/packages/uiplugin/fancyTreeView.js"></script>