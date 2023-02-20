/**
 * https://github.com/mar10/fancytree/wiki
 */
function logEvent(event, data, msg){
	$.ui.fancytree.info(`Event('${event.type}', node=${data.node})${msg}`);
}
let fancyTreeConfig = {
	source: function(event, data){
		// this : FancyTree 인스턴스
		logEvent(event, data, `, this : ${this.toString()}`);
		
		let url = this.data.source; 
		return {
			url: url,
			cache: false
		};
	},
	init : function(event, data){
		// this : #treeArea div tag (HTMLDivElement) - FancyTree 컨테이너 엘리먼트  
		logEvent(event, data, `, this : ${this.toString()}`);
		
		let buyerArea = this.dataset.buyerArea;
		let prodArea = this.dataset.prodArea;
		
		// tree element 에 설정된 모든 data 속성들은 tree 인스턴스의 data 맵으로 복사됨.
		// tree 인스턴스의 데이터에 detainInfo 데이터를 추가함.
		data.tree.data.detailInfo = {
				$buyerTbl : $(buyerArea) // 거래처 정보 랜더링 엘리먼트
				, $prodTbl : $(prodArea) // 거래품목 랜더링 엘리먼트
				, makeProdTr : function(prod){
					// this : detailInfo
					let sampleTr = this.$prodTbl.find("tr.sample");
					let newTr = sampleTr.clone();
					newTr.removeClass("sample d-none");
					for(let prop in prod){
						newTr.find(`.${prop}`).html(prod[prop]);
					}
					this.$prodTbl.append(newTr);
					return newTr;
				}
		};
	},
	activate : function(event, data){
		logEvent(event, data, `, activation node type is ${data.node.type}, this : ${this.toString()}}`);
		
		let tree = data.tree;
		let detailInfo = tree.data.detailInfo;
		let $buyerTbl = detailInfo.$buyerTbl;
		let $prodTbl = detailInfo.$prodTbl;
		
		$buyerTbl.find("td").empty();
		$prodTbl.find("tr").remove(":not(.sample)");
		
		let buyer = null;
		let prodList = null;
		if(data.node.type==="BuyerVO"){
			buyer = data.node.data;
			prodList = data.node.children?.map(child=>child.data);
		}else{
			buyer = data.node.parent.data;
			prodList = [data.node.data];
		}
		
		for(let prop in buyer){
			$buyerTbl.find(`.${prop}`).html( buyer[prop] );
		}
		
		prodList?.map(detailInfo.makeProdTr.bind(detailInfo))
				
//		상세 정보 조회용 비동기 요청 필요시
//		let detailInfoUrl = data.tree.data.detail;
//		$.getJSON(detailInfoUrl, {what:buyer.buyerId}, function(resp){
//			console.log(resp);
//		});
	}
}

let $treeArea = $("#treeArea").fancytree(fancyTreeConfig);
console.log($treeArea);
