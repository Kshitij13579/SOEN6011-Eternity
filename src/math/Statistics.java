package math;

import java.util.ArrayList;

/**
 * This class provides the implementations of
 * statistical functions to calculate standard deviation.
 *
 * @author Kshitij Yerande
 * @since 29-July-2022
 * @version 1.0
 *
 */
public class Statistics {
    
  public static final double ACCEPTABLE_ERROR = 0.0000000001;
  
  /**
   * This method calculates square root of number 
   * using Newton's Method.
   *
   * @param n - number of which square root is to be calculated.
   * @return - square root of number
   */
  public static double sqrt(Double n) {
        
    double x = n;
    double root;
    
    if ((n - 0) < ACCEPTABLE_ERROR) {
      return 0;
    }
     
    while (true) {
      root = ((x + (n / x))) / 2;
            
      if (abs(root - x) < ACCEPTABLE_ERROR) {
        break;
      }
 
      x = root;
    }
     
    return root;
  }
  
  /**
   * This method calculates standard deviation of population
   * of numbers. 
   *
   * @param elements - list of numbers
   * @return - standard deviation of numbers.
   */
  public static double stdv(ArrayList<Double> elements) {
  
    double stdv = 0;
    double sum = 0;
    double avg = average(elements);
        
    for (Double d : elements) {
      sum += (d - avg) * (d - avg);
    }
        
    stdv = sqrt(sum / elements.size());
    return stdv;
  }
  
  /**
   * This method computes average of numbers.
   *
   * @param elements - list of numbers
   * @return - average value of numbers.
   */
  public static double average(ArrayList<Double> elements) {
        
    double sum = 0;
    double avg = 0;
        
    for (Double d : elements) {
      sum += d;
    }

    avg = sum / elements.size();
    return avg;
  }
  
  /**
   * This method computes 
   * absolute value of number.
   *
   * @param n - number 
   * @return - absolute value of number
   */
  public static double abs(double n) {
    if (n < 0) {
      return -1 * n;
    } else {
      return n;
    }
  }
    
}
