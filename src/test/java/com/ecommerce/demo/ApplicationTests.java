package com.ecommerce.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	void applicationStarts() {
		assertDoesNotThrow(() -> {
			Application.main(new String[] { "--server.port=0" });
		});
	}

}
