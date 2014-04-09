package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCalculator {

	Calculator calc;
	
	@Before
	public void setUp() throws Exception {
		calc = new Calculator();
	}

	@Test
	public void testAddPositiveNumbers() {
		assertEquals(20, calc.add(15, 5));
	}
	
	@Test
	public void testAddNegativeNumbers() {
		assertEquals(-27, calc.add(-20, -7));
	}
	
	@Test
	public void testDivision() {
		assertEquals(6, calc.divide(30, 5));
	}

}
