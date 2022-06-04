package Physics;

public class Forces {
	
	double Force = 0;
	int Angle = 0;
	
	public Forces(double f, int angle) {
		super();
		Force = f;
		Angle = angle;
	}

	public double getForce() {
		return Force;
	}

	public int getAngle() {
		return Angle;
	}
	
	

}
