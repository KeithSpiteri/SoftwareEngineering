/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import jenkins.Calculator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christopher
 */
public class CalcTest {
    
    public CalcTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void TestCalc()
    {
        Calculator calc = new Calculator();
        assertEquals(6, calc.add(3, 1));
assertEquals(6, calc.add(5, 1));
        //assertEquals("Hello World!\n", calc.getMessage(1));
        //calc.divide(4,1);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /*@Test
    public void TestCalc()
    {
        Calculator calc = new Calculator();
        assertEquals("4/0 = ", -999, calc.divide(4,0));
        //calc.divide(4,1);
    }*/
}
