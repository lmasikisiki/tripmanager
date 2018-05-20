package com.khwela.khwelacore;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class KhwelaCoreApplicationTests {

	@Test
	public void contextLoads() {

		String name = "Test";
		assertEquals("Test",name);
 	}



}
