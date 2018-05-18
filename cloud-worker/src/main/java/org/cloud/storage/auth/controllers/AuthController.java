package org.cloud.storage.auth.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;

@Component
public class AuthController {

	private final AuthenticationController controller;
	private final String userInfoAudience;

	@Autowired
	public AuthController(AuthConfiguration config) {
		controller = AuthenticationController
				.newBuilder(config.getDomain(), config.getClientId(), config.getClientSecret()).build();
		userInfoAudience = String.format("https://%s/userinfo", config.getDomain());
	}

	public Tokens handle(HttpServletRequest request) throws IdentityVerificationException {
		return controller.handle(request);
	}

	public String buildAuthorizeUrl(HttpServletRequest request, String redirectUri) {
		return controller.buildAuthorizeUrl(request, redirectUri).withAudience(userInfoAudience).build();
	}

}
