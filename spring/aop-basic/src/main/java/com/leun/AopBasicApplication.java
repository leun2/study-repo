package com.leun;

import com.leun.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopBasicApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final TestService testService;

	public AopBasicApplication(TestService testService) {
		this.testService = testService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AopBasicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Value returned is {}", testService.calculateMax());
	}
}
