package kr.or.ddit.uiplugin.fullcalendar.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.uiplugin.dao.ProdDAO;
import kr.or.ddit.uiplugin.fullcalendar.FullCalendarEvent;
import kr.or.ddit.uiplugin.fullcalendar.ProdFullCalendarEvent;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/uiplugin/fullCalendar")
public class FullCalendarController {
	@Inject
	private ProdDAO prodDAO;
	
	@RequestMapping
	public String view() {
		return "uiplugin/fullCalendarView";
	}
	
	@RequestMapping(value="events", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<FullCalendarEvent<ProdVO>> json(
		@RequestParam @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start
		, @RequestParam @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end
		, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date
		, @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") long dateTime
	) {
		
		log.info("LocalDateTime start : {}", start);
		log.info("LocalDateTime end : {}", end);
		log.info("Date date : {}", date);
		log.info("long dateTime : {}", dateTime);
		
		Map<String, Object> randomSearch = new HashMap<>();
		randomSearch.put("start", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
		randomSearch.put("end", end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setRandomSearch(randomSearch);
		
		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
		return prodList.stream()
					.map(ProdFullCalendarEvent::new)
					.collect(Collectors.toList());
	}
}



























