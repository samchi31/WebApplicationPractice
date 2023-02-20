<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/resources/js/gridstack/dist/gridstack.min.css" rel="stylesheet"/>
<h4>각 grid item 의 페이징 구문이 별개로 동작하는 예제.</h4>
<pre>
	**.uiplugin.gridstack.GridStackController, gridStackView.js 참고
</pre>
<div class="grid-stack mt-3">
	<div class="grid-stack-item border" gs-w="5" gs-h="4">
		<div class="grid-stack-item-content">
			<table class="table table-bordered" 
				data-source="${pageContext.request.contextPath }/uiplugin/gridStack/prodList"
				data-make-tr="makeProdUI">
				<thead>
					<tr>
						<th>상품코드</th>
						<th>상품명</th>
						<th>구매가</th>
						<th>판매가</th>
					</tr>
				</thead>
				<tbody class="listBody">
					<tr>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="pagingArea">
						</td>
					</tr>			
				</tfoot>			
			</table>
		</div>
	</div>
	<div class="grid-stack-item border"  gs-w="5" gs-h="4">
		<div class="grid-stack-item-content">
			<table class="table table-bordered" 
				data-source="${pageContext.request.contextPath }/uiplugin/gridStack/buyerList" 
				data-make-tr="makeBuyerUI">
				<thead>
					<tr>
						<th>거래처코드</th>
						<th>거래처명</th>
						<th>담당자</th>
						<th>이메일</th>
					</tr>
				</thead>
				<tbody class="listBody">
					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="pagingArea">
						</td>
					</tr>			
				</tfoot>			
			</table>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath }/resources/js/gridstack/dist/gridstack-all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/packages/uiplugin/gridStackView.js"></script>