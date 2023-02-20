package kr.or.ddit.realtime.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/realtime")
public class RealTimeController {
	
	@Inject
	private SessionRegistry registry;

	@RequestMapping("websocket")
	public String websocketView() {
		return "realtime/websocketView";
	}
	
	@RequestMapping("stomp")
	public String stompView() {
		return "realtime/stompView";
	}
	
	@RequestMapping("stomp/dm")
	public String stompDMView(Model model) {
		 registry.getAllSessions("c001", true);
		
		List<?> userList = registry.getAllPrincipals();
		List<SessionInformation> c001Sessions = userList.stream().map(p->(User)p)
												.filter(u->u.getUsername().equals("c001"))
												.findFirst()
												.map(c001->registry.getAllSessions(c001, true))
												.orElse(new ArrayList<>());
		model.addAttribute("userList", userList);
		model.addAttribute("c001Sessions", c001Sessions);
		return "realtime/stompDMView";
	}
	
}
