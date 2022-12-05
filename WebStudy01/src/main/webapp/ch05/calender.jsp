<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
  <div>
  <button id="btn_prev">&lt;</button>
  <span></span>
  <button id="btn_next">&gt;</button>
  </div>
  <table border="1">
    <tr>
      <th>일</th>
      <th>월</th>
      <th>화</th>
      <th>수</th>
      <th>목</th>
      <th>금</th>
      <th>토</th>
    </tr>
   
  </table>


<script type="text/javascript">
const TABLE_CAL = $('table');
const SPAN_YEAR = $('span');

const BTN_PREV = $('btn_prev');
const BTN_NEXT = $('btn_next');

BTN_PREV

let date = new Date();
console.log(date);

function f_cal(){
	let prevLastDate = new Date(date.getFullYear(), date.getMonth(), 0);
	console.log(prevLastDate);
	
	let lastDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);
	console.log(lastDate);
	
	let startWeek = prevLastDate.getDay() + 1;	// 시작 요일
	console.log(startWeek);
	
	SPAN_YEAR.html(date.getFullYear());
	
	var prevCheck = true;
	var lastCheck = true;
	var cnt_date = prevLastDate.getDate() - prevLastDate.getDay();
	for(let i = 0;i<lastDate.getDate()/7+1;i++){
		var v_tr = $('<tr>');
		for(let j=0; j<7 ; j++){
			var v_td = $('<td>').html(cnt_date);
			
			if(cnt_date >= lastDate.getDate() && lastCheck && !prevCheck){
				cnt_date = 0;
				lastCheck = false;
			}
			if(cnt_date >= prevLastDate.getDate() && prevCheck){
				cnt_date = 0;
				prevCheck = false;
			}
			cnt_date++;
			v_tr.append(v_td);
		}
		
		TABLE_CAL.append(v_tr);
	}
};


</script>
</body>
</html>