/**
 * https://datatables.net/manual/
 */
let $example = $('#example');
// $example !== exampleDT
let exampleDT = $example.DataTable({
	processing:true
	, ajax:function(){
		// this : $example table tag (HTMLTableElement)
		return this.data('url');
	}.call($example)
	, columns : [
		{data:'prodName'}
		, {data:'prodLgu'}
		, {data:'prodBuyer'}
		, {data:'buyer.buyerAdd1'}
		, {
			data:'prodCost'
			, render : DataTable.render.number(null, null, 0, '￦')
		}
		, {
			data:'prodPrice'
			, render : DataTable.render.number(null, null, 0, '￦')
		}
		, {data:'prodMileage'}

	]
	, select : 'single'
	, ordering : false
	, dom: 'flrtipP'
	, columnDefs: [{
        searchPanes: {
            hideCount: true
            , initCollapsed: true
            , orderable: false
        },
        targets: [1,2]
    }]
}).on("select", function(e, dt, type, indexes){
	// this : #example table tag (HTMLTableElement)
	let detailUrl = `${this.dataset.detail}/${dt.row(indexes).data().prodId}`;
	$(this.dataset.target).load(detailUrl);
}).on('deselect', function ( e, dt, type, indexes ) {
	// this : #example table tag (HTMLTableElement)
	$(this.dataset.target).empty();
});