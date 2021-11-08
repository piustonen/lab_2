package com.company;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void some_float_calc() throws Exception {
        assertEquals(6.6, Calculator.Add_float("//[***][*][**]\\n1.1*2.2**3.3"), 0.001);
    }
    @Test
    public void some_float_calc_1() throws Exception {
        assertEquals(3.3, Calculator.Add_float("//[%]\\n1.1%2.2"), 0.001);

    }
    @Test
    public void some_float_calc_2() throws Exception {
        assertEquals(3.5, Calculator.Add_float("//[***]\\n1.3***2.2"), 0.001);

    }
    @Test
    public void some_float_calc_3() throws Exception {
        assertEquals(1002.2, Calculator.Add_float("//[+]\\2.2+1000"), 0.001);

    }
    @Test
    public void some_float_calc_4() throws Exception {
        assertEquals(3.5, Calculator.Add_float("//[=]\\n1.3=2.2"), 0.001);

    }
    @Test
    public void some_float_calc_5() throws Exception {
        assertEquals(3.5, Calculator.Add_float("//[=]\\n1.3=2.2"), 0.001);

    }
    @Test
    public void some_float_calc_6() throws Exception {
        assertEquals(4.9, Calculator.Add_float("//[:]\\n4.3:0.6"), 0.001);

    }
    @Test
    public void some_float_calc_7() throws Exception {
        assertEquals(4.9, Calculator.Add_float("//[:]\\n4.3:0.6:1001"), 0.001);

    }
    @Test
    public void some_float_calc_8() throws Exception {
        assertEquals(1004.9, Calculator.Add_float("//[+][***]\\n4.3***0.6+1000"), 0.001);

    }
    @Test
    public void some_float_calc_9() throws Exception {
        assertEquals(1004.9, Calculator.Add_float("//[***]\\n4.3***0.6***1000"), 0.001);

    }@Test
    public void some_float_calc_10() throws Exception {
        assertEquals(1004.7, Calculator.Add_float("//[*]\\n4.1*0.6*1000"), 0.001);

    }


}