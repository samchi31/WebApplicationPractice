/**
 * https://fullcalendar.io/docs/initialize-globals
 * https://fullcalendar.io/docs/event-source
 * 
 */
var calendarEl = document.getElementById('calendar');
var calendar = new FullCalendar.Calendar(calendarEl, {
	themeSystem: 'standard',
	headerToolbar: { 
		start : 'title',
		center : 'dayGridMonth,dayGridWeek',
		end : 'today,prev,next,prevYear,nextYear'
	},
	initialView: 'dayGridMonth',
	locale:"en",
	eventSources : [
		{
			url:calendarEl.dataset.source,
			dataType:"json",
			extraParams : {
				date : "2022-01-01"
			}
		}
	],
	initialDate : "2020-01-01"
});
calendar.render();