/**
 * https://github.com/mar10/fancytree/wiki/TutorialLoadData
 */
function logEvent(event, data, msg){
	$.ui.fancytree.info(`Event('${event.type}', node=${data.node})\n${msg}`);
}
let $treeElement = $("#treeArea").fancytree({
	source: function(event, data){
		logEvent(event, data, "'this' : FancyTree 인스턴스");
		let url = this.data.source; 
		return {
			url: url,
			cache: false
		};
	}, 
	lazyLoad: function(event, data) {
		logEvent(event, data, "'this' : #treeArea div tag(HTMLDivElement)");
		let node = data.node;
		let treeElement = this;
		data.result = {
			url: treeElement.dataset.source
			, data : {
				path : node.getKeyPath()		
			}
		};
	}, 
	checkbox : true,
	selectMode : 3 // single selection(1), multi selection(2), hierarchical selection(3) 
});

$("#downBtn").on("click", function(){
	let mainTree = $.ui.fancytree.getTree($treeElement);
	let nodeArray = mainTree.getSelectedNodes();
	if(nodeArray.length==0) return false;
	
	let keyPaths = nodeArray.map(n=>n.getKeyPath());
	
	//==========POST 요청 헤더에 CSRF 공격 방어를 위한 토큰을 포함시킴, template.jsp 참고========
	
	let csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
	let csrfToken = $("meta[name='_csrf']").attr("content");
	let headers = {};
	headers[csrfHeaderName] = csrfToken;
	$.ajaxSetup({
		headers:headers
	});
	
	// csrf 토큰에 관한 설정이나 비동기 요청 처리 ING 표시는 대부분의 뷰에서 활용될 수 있는데, 레이아웃을 활용한 모듈화 방법을 생각해보면!!! 
	//==========================================================================
	
	let url = $(this).data('action');
		
	$.post({
		url : url
		, xhrFields : {
			responseType : "blob"
			, onprogress : function(e){
				$treeElement.data("progress").val((e.loaded/e.total) * 100);
			}
		}
		, data : JSON.stringify(keyPaths)
		, headers : {
			"Content-Type"  : "application/json;charset=UTF-8"
		}
		, beforeSend : function(){
			let prsBar = $("<progress max='100'>").addClass("col-6");
			$treeElement.prev(".prsArea").html(prsBar);
			$treeElement.data("progress", prsBar);
		}
	}).done((resp, status, jqXHR)=>{
		// this : #downBtn input tag(HTMLInputElement)
		let disposition = jqXHR.getResponseHeader("content-disposition");
		let filename =  disposition?.split("filename=")[1]
									?.split(";")[0]
									?.replaceAll('"', '');
		let url = window.URL.createObjectURL(resp);
		let hiddenA = document.createElement("a");
		hiddenA.download = filename??"download.zip";
		hiddenA.href = url;
		hiddenA.click();
		hiddenA.remove();
	}).always(()=>{
		$treeElement.data("progress").remove();
		$treeElement.removeData("progress");
	});
});