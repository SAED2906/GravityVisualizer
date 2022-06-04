package Physics;

import java.math.BigInteger;

public class ObjectsInFreeSpace {
	
	static int x = 0; 
	static int y = 0; 
	BigInteger mass = new BigInteger("100000");

	public ObjectsInFreeSpace(int x, int y, BigInteger bigInteger) {
		super();
		this.x = x;
		this.y = y;
		this.mass = bigInteger;
	}
	
	public int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	

}
