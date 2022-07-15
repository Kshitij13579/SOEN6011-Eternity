package math;

import java.util.ArrayList;

public class Statistics {
	
	public static final double ACCEPTABLE_ERROR = 0.0000000001;
	
	public static double sqrt(Double n) {
		
		double x = n;
        double root;
     
        while (true)
        {
            root = ((x + (n / x)))/2;
            
            if (abs(root - x) < ACCEPTABLE_ERROR)
                break;
 
            x = root;
        }
     
        return root;
	}
	
	public static double stdv(ArrayList<Double> elements) {
		double stdv = 0;
		double sum = 0;
		double avg = average(elements);
		
		for(Double d:elements) {
			sum+= (d-avg) * (d-avg);
		}
		
		stdv = sqrt(sum/elements.size());
		
		return stdv;
	}
	
	public static double average(ArrayList<Double> elements) {
		
		double sum = 0;
		double avg = 0;
		
		for(Double d: elements) {
			sum+=d;
		}

		avg = sum/elements.size();
		return avg;
	}
	
	public static double abs(double n) {
		if(n < 0)
			return -1 * n;
		else
			return n;
	}
	
}
