package org.cloud.storage.auth.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/logout")
	protected String logout(final HttpServletRequest req) {
		logger.debug("Performing logout");
		invalidateSession(req);
		return "redirect:" + req.getContextPath() + "/login";
	}

	private void invalidateSession(HttpServletRequest request) {
		if (request.getSession() != null) {
			request.getSession().invalidate();
		}
	}

}
