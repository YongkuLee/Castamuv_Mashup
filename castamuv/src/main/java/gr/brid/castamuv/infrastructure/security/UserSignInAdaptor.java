package gr.brid.castamuv.infrastructure.security;

/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import gr.brid.castamuv.application.user.SignUpService;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserRepository;

import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Signs the user in by setting the currentUser property on the
 * {@link SecurityContext}. Remembers the sign-in after the current request
 * completes by storing the user's id in a cookie. This is cookie is read in
 * {@link UserInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
 * on subsequent requests.
 * 
 * @author Keith Donald
 * @see UserInterceptor
 */
@Component
public class UserSignInAdaptor implements SignInAdapter {

	@Inject
	private UserRepository userRepository;

	@Inject
	private SignUpService signUpService;

	public String signIn(String userId, Connection<?> connection,
			NativeWebRequest request) {
		UserProfile profile = connection.fetchUserProfile();
		User user = userRepository.findByEmail(profile.getEmail());
		if (user == null) {
			user = signUpService.signUp(profile.getEmail(), UUID.randomUUID()
					.toString(), connection.getImageUrl());
		}
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user.getId().getId(),
						user.getPassword(), user.getAuthorities()));
		return null;
	}
}
