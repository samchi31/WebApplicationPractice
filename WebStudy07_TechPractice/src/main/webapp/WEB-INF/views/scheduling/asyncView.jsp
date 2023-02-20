<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row mt-3">
<p>이 페이지는 로딩 지연없이 바로 랜더링 되었음.</p>
<p>이클립스의 콘솔을 보면 실행에 10초가 소요되는 작업이 실행중임.</p>
<p>너무 많은 작업이 실행중임을 알수 있음, 여타의 숫자를 출력하는 다른 작업들을 중단시키기 위해 아래 버튼을 눌러볼것.</p>
<p>로그인하기 전과 로그인한 후의 2개 이상의 브라우저를 이용해 동시 테스트할 것.</p>
<p>**.scheduling.controller.TaskSchedulingController, **.scheduling.jobs.**, task-context.xml, asyncView.js 참고</p>
<input type="button" class="btn btn-danger controlBtn" value="Spring Scheduler shutdown! Quartz Scheduler pauseAll!" 
			data-ws-url="${pageContext.request.contextPath }/realtime/full/stomp/system"
			data-role="PAUSE"
			data-command-url="${pageContext.request.contextPath }/scheduling/pause"
			/>
<input type="button" class="btn btn-primary controlBtn" value="Quartz Scheduler resumeAll!" 
			data-ws-url="${pageContext.request.contextPath }/realtime/full/stomp/system"
			data-role="RESUME"
			data-command-url="${pageContext.request.contextPath }/scheduling/resume"
			/>
<p id="msgArea"></p>
</div>
<script src="${pageContext.request.contextPath }/resources/js/stompjs/stomp.umd.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/packages/scheduling/asyncView.js"></script>