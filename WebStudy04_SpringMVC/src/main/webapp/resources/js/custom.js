/**
 * $ = jQuery 객체
 * $.fn. : element를 selecting 한 경우 사용할 수 있는 함수
 * this : 현재 함수의 대상이 되는 element
 * tagName이라는 속성을 가져올 때는 prop 만 가능 (attr x)
 * this : jquery 객체 -> this[0] : dom 객체
 */
$.fn.serializeObject = function(){
	if(this.prop('tagName') != 'FORM'){
		throw Error('form 태그 외에는 사용 불가');
	}
	
	let fd = new FormData(this[0]);
	let nameSet = new Set();
	for(let key of fd.keys()){
		nameSet.add(key);
	}
	let data = {};
	for(let name of nameSet){
		let values = fd.getAll(name);
		if(values.length>1){
			data[name] = values;			
		} else {
			data[name] = values[0];
		}
	}
	return data;
}

/*
 * 폼태그의 모든 입력 데이터의 이름과 값을 콘솔에 로그로 출력할 수 있는 함수
 * ex) $('form').log().serializeObject();
 */

$.fn.log = function(){
	let data = this.serializeObject();
	// of 연산자 + 컬렉션,반복가능 | in 연산자 -> 속성 하나하나 접근가능
	for(let prop in data ){
		console.log(prop + " : " + data[prop]);
	}
	return this;
}

$.fn.sessionTimer = function(timeout, msgObj){
	if(!timeout)
		throw Error("세션 타임아웃 값이 없음");
	
	const SPEED = 100;
	const TIMEOUT = timeout;
	const timerArea = this;
	// event propagation : bubbling
	let msgArea = null;
	if(msgObj){
		msgArea = $(msgObj.msgAreaSelector).on("click", msgObj.btnSelector, function(event){		
			//this == controlBtn(msgArea의 descendent) javascript 객체
			//console.log(this.id + ", " + $(this).prop("id"));
			if(this.id=="YES"){
				jobClear();
				timerInit();
				$.ajax({
					method : "head"	//response의 body가 필요없다
				});
			} 
			msgArea.hide();
		}).hide();
	}
	let jobClear = function() {
		let timerJob = timerArea.data("timerJob");
		if(timerJob){
			clearInterval(timerJob);
		}
		
		let msgJob = msgArea.data("msgJob");
		if(msgJob){
			clearTimeout(msgJob);
		}
	}
	
	let timerInit = function(){
		if(msgObj){
			// 지연시간 설정
			let msgJob = setTimeout(() => {
				msgArea.show();
			}, (TIMEOUT-60) * SPEED);
			// 데이터 저장
			msgArea.data("msgJob", msgJob);
		}
		let timer = TIMEOUT;
		let timerJob = setInterval(() => {
			if(timer == 1){
				clearInterval(timerJob);
				location.reload();
			} else 
				timerArea.html(timeFormat(--timer));
		},SPEED);
		// 데이터 저장
		timerArea.data("timerJob",timerJob);
	}
	timerInit();
	
	let timeFormat = function(time){
		let min = Math.trunc(time / 60);	// 소수점 버림
		let sec = time % 60;
		return min + ":" + sec;
	};	
	
	return this;
}

//$.fn.sessionTimer = function(seconds){
//	let cnt = 1;
//	let area = this;
////	console.log(this);
//	let jobId = setInterval(function(){
////		console.log(cnt);
//		let data = seconds - cnt++;
//		area.html(data);
//
//		if(cnt>seconds){
//			clearInterval(jobId);
//			location.reload();
//		}
//		if(data < 60){
//			if(confirm("연장하시겠습니까?")){
//				clearInterval(jobId);
//				location.reload();
//			}
//		}
//	}, 1000);
//	return this;
//}