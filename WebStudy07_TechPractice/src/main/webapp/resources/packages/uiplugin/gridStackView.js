/**
 * https://github.com/gridstack/gridstack.js/tree/master/doc
 */
  fns_obj = {
	  makeBuyerUI : function(buyer){
		 return $("<tr>").append(
						$("<td>").html(buyer.buyerId)	 
						, $("<td>").html(buyer.buyerName)	 
						, $("<td>").html(buyer.buyerCharger)	 
						, $("<td>").html(buyer.buyerMail)	 
				  );
	  }
  	  , makeProdUI : function(prod){
			 return $("<tr>").append(
							$("<td>").html(prod.prodId)	 
							, $("<td>").html(prod.prodName)	 
							, $("<td>").html(prod.prodCost)	 
							, $("<td>").html(prod.prodPrice)	 
					  );
	  }
	  , dataLoad : function (params){
		  let $tbl = this;
		  let sourceURL = $tbl.data("source");
		  let makeTrFunc = fns_obj[$tbl.data("makeTr")];
		  $.getJSON(sourceURL, params)
	 		.done(function(json, status, jqXHR){
	 			  let $tbody = $tbl.find(".listBody");
	 			  let $tfoot = $tbl.find(".pagingArea");
	 			  $tbody.empty();
	 			  $tfoot.empty();
	 			  let trTags = [];
	 			  $.each(json.pagingVO.dataList, function(idx, single){
	 				 trTags.push(makeTrFunc(single)); 
	 			  });
	 			  $tbody.html(trTags);
	 			  $tfoot.html(json.pagingHTML);
	 		});
	  }
  };
  
  $(async function() {
	 let grid = await GridStack.init({
		  resizable : false
	  });
	  $("table[data-source]").each(function(idx, tbl){
		  fns_obj.dataLoad.call($(tbl));
	  }).on("click", "a.paging", function(event){
		  event.preventDefault();
		  let page = $(this).data("page");
		  if(!page) return false;
		  
		  let $tbl = $(this).parents("table:first");
		  fns_obj.dataLoad.call($tbl, {page:page} );
		  return false;
	  })
  });