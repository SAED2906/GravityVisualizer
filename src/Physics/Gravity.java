package Physics;

import java.math.BigInteger;

public class Gravity {
	
	public static Forces getForceOfAttraction(int mx, int my, BigInteger ma, int x, int y) {
		BigInteger f = (ma.multiply(new BigInteger("66743")).divide(new BigInteger("100000000000000")));
		if ((calculateDistanceBetweenPointsWithHypot(mx, my, x, y)*calculateDistanceBetweenPointsWithHypot(mx, my, x, y)) == 0) {
			return new Forces((double) 0, 0);
		}
		f = f.divide(new BigInteger((calculateDistanceBetweenPointsWithHypot(mx, my, x, y)*calculateDistanceBetweenPointsWithHypot(mx, my, x, y)) + ""));
		int angle = (int) getAngle(mx, my, x, y);
		
		return new Forces((double) f.doubleValue(), angle);
	}
	
	public static int calculateDistanceBetweenPointsWithHypot(double x1, double y1, double x2, double y2) {
		        
		    double ac = Math.abs(y2 - y1);
		    double cb = Math.abs(x2 - x1);
  
		    return (int) Math.hypot(ac, cb);
		}
	
	public static float getAngle(int mx, int my, int x, int y) {
	    float angle = (float) Math.toDegrees(Math.atan2(my - y, mx - x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

}
