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