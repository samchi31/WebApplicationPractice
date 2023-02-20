<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/DataTables/datatables.min.css"/>

<pre>
	DataTableController, dataTableView.js 참고
	상품 row 들을 클릭하거나 검색 패널을 사용해 볼것.
</pre>
<div class="row mt-3">
	<table id="example" class="display" style="width:100%" 
		data-url='${pageContext.request.contextPath}/uiplugin/dataTable/prodSource'
		data-detail='${pageContext.request.contextPath}/prod'
		data-target='#detailArea'>
	    <thead>
	        <tr>
	            <th>상품명</th>
	            <th>상품분류</th>
	            <th>거래처명</th>
	            <th>소재지</th>
	            <th>구매가</th>
	            <th>판매가</th>
	            <th>마일리지</th>
	        </tr>
	    </thead>
	  
	    <tfoot>
	        <tr>
	            <th>상품명</th>
	            <th>상품분류</th>
	            <th>거래처명</th>
	            <th>소재지</th>
	            <th>구매가</th>
	            <th>판매가</th>
	            <th>마일리지</th>
	        </tr>
	    </tfoot>
	</table>
</div>
<div class="row" id="detailArea"></div>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/DataTables/datatables.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/packages/uiplugin/dataTableView.js"></script>