package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DemoApplicationTests {

	Calculator calculator = new Calculator();

	@Test
	void contextLoads() {
	}

	@Test
	void addNumbers() {
		// Given Input
		int one = 10;
		int second = 20;

		// When
		int result = calculator.add(one, second);

		// Then
		int expected = 30;
		assertThat(result).isEqualTo(expected);

	}

	class Calculator {
		int add(int a, int b) {
			return a+b;
		}
	}

}
