package com.leun;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.leun.auth.service.JwtService;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SecurityApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JwtService jwtService;

	@Test
	void authenticateTest() throws Exception {

		// Authentication 객체를 설정 (모킹)
		Authentication authentication = mock(Authentication.class);

		Collection<SimpleGrantedAuthority> authorities =
			List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

		when(authentication.getName()).thenReturn("admin");
		when(authentication.getAuthorities()).thenReturn((Collection) authorities);

		String token = jwtService.createToken(authentication);

		mockMvc.perform(post("/v1/authentication")
				.with(user("admin").password("1234").roles("ADMIN", "USER"))
				.with(csrf()))
			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.token").value(token))
			.andExpect(jsonPath("$.token").isNotEmpty());

	}

	@Test
	public void tokenResultTest() throws Exception {

		Authentication authentication = mock(Authentication.class);

		Collection<SimpleGrantedAuthority> authorities =
			List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

		when(authentication.getName()).thenReturn("admin");
		when(authentication.getAuthorities()).thenReturn((Collection) authorities);

		String token = jwtService.createToken(authentication);

		mockMvc.perform(get("/v1/token/test")
				.header("Authorization", "Bearer " + token)
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(content().string("success"));
	}
}
