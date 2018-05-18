package org.cloud.storage.auth.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.auth0.SessionUtils;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/portal/home")
	protected String home(final Map<String, Object> model, final HttpServletRequest req) {
		logger.info("Home page");
		String accessToken = (String) SessionUtils.get(req, "accessToken");
		String idToken = (String) SessionUtils.get(req, "idToken");
		if (accessToken != null) {
			model.put("userId", accessToken);
		} else if (idToken != null) {
			model.put("userId", idToken);
		}
		return "home";
	}

}
