/**
 * 
 */
$(".downloadBtn").on("click", function(event){
	// this : a tag (HTMLAnchorElement)
	
	event.preventDefault();
	
	let role = this.dataset.role;
	
	if(role == 'single'){
		singleFileDownload.call(this); 
	}else if(role == 'multiple'){
		multipleFileDownload.call(this);
	}
	
	return false;
});

function singleFileDownload(){
	// this(click event target) : a tag (HTMLAnchorElement)
	
	let file = $(this.dataset.target)?.val();
	if(! file?.length ) return;
	
	let href = this.href;
	let hiddenA = document.createElement("a");
	hiddenA.href = `${href}?what=${file}`;
	hiddenA.click();
	hiddenA.remove();
}

function multipleFileDownload(){
	// this(click event target) : a tag (HTMLAnchorElement)
	
	let files = $(this.dataset.target)?.val();
	if(! files?.length ) return;
	
	//==========POST 요청 헤더에 CSRF 공격 방어를 위한 토큰을 포함시킴, template.jsp 참고========
	let csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
	let csrfToken = $("meta[name='_csrf']").attr("content");
	let headers = {};
	headers[csrfHeaderName] = csrfToken;
	$.ajaxSetup({
		headers:headers
	});
	//==========================================================================
	
	
	let href = this.href;
	
	$.post({ 
		url : href
		, xhrFields : {
			responseType : "blob"
		}
		, headers : {
			"Content-Type"  : "application/json;charset=UTF-8"
		}
		, data : JSON.stringify(files)
	}).done((resp, status, jqXHR)=>{
		// this(click event target) : a tag (HTMLAnchorElement)
		
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
	});
}
