package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import math.Statistics;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for math.Statistics.java which has following testing methods.
 * absTest : Test for absolute function.
 * averageTest : Test for average function.
 * stdvTest : Test for standard deviation.
 * sqrtTest : Test for square root.
 *
 * @author Kshitij Yerande
 * @since 29-July-2022
 */
public class SatisticsTest {
  
  static ArrayList<Double> elements;
  public static final double ACCEPTABLE_ERROR = 0.0000000001;
  
  @BeforeClass
  public static void setup() {
    elements = new ArrayList<Double>();
  }
  
  @Test
  public void absTest() {
    double num = -1;
    assertEquals(-1, Statistics.abs(num), 16);
    
    num = 0;
    assertEquals(0, Statistics.abs(num), ACCEPTABLE_ERROR);
    
    num = 1;
    assertEquals(1, Statistics.abs(num), ACCEPTABLE_ERROR);
  }
  
  @Test
  public void averageTest() {
    
    elements.add(10.5);
    elements.add(100.5);
    elements.add(104.5);
    
    assertEquals(71.8333333333, Statistics.average(elements), ACCEPTABLE_ERROR);
    
    elements.clear();
    elements.add(-10.5);
    elements.add(-100.5);
    elements.add(-104.5);
    
    assertEquals(-71.8333333333, Statistics.average(elements), ACCEPTABLE_ERROR);
  }
  
  @Test
  public void stdvTest() {
    
    //zero standard deviation
    elements.clear();
    elements.add(10.0);
    elements.add(10.0);
    
    assertEquals(0, Statistics.stdv(elements), ACCEPTABLE_ERROR);
    
    elements.clear();
    elements.add(10.0);
    elements.add(20.0);
    elements.add(30.0);
    
    assertEquals(8.16496580927726, Statistics.stdv(elements), ACCEPTABLE_ERROR);
      
  }
  
  @Test
  public void sqrtTest() {
  
    //square root of zero
    assertEquals(0.0, Statistics.sqrt(0.0), ACCEPTABLE_ERROR);
    
    //square root of 2
    assertEquals(1.41421356237, Statistics.sqrt(2.0), ACCEPTABLE_ERROR);
    
    //square root of 1
    assertEquals(1.0, Statistics.sqrt(1.0), ACCEPTABLE_ERROR);

  }

}
