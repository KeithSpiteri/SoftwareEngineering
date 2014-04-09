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
		assertEquals(25, calc.add(20, 5));
	}
	
	@Test
	public void testAddNegativeNumbers() {
		assertEquals(-27, calc.add(-20, -7));
	}

	@Test
	public void testSubtraction() {
		assertEquals(5, calc.sub(10, 5));
	}
	
	@Test
	public void testMultiplication() {
		assertEquals(25, calc.multiply(5, 5));
	}
	
	@Test
	public void testDivision() {
		assertEquals(6, calc.divide(30, 5));
	}

}
