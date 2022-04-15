package domain;

public class Document extends Item {

	private static final double SHORT_DIST = 10;
	private static final double LONG_DIST = 30;

	public Document(double weight, double distance) {
		super(weight, distance);
	}

	@Override
	protected void calculateCharge() {
		if (weight <= 0 || weight >= 50001)
			throw new IllegalArgumentException("Invalid value for weight.");
		if (distance <= 0 || distance >= 81)
			throw new IllegalArgumentException("Invalid value for distance.");

		if (weight < 300) {
			setCharge(3);
		} else if (weight <= 1000) {
			if (distance < SHORT_DIST)
				setCharge(4);
			else if (distance <= LONG_DIST)
				setCharge(5);
			else
				setCharge(6);
		} else if (weight <= 3000) {
			if (distance < SHORT_DIST)
				setCharge(6);
			else if (distance <= LONG_DIST)
				setCharge(8);
			else
				setCharge(10);
		} else if (weight <= 5000) {
			if (distance < SHORT_DIST)
				setCharge(12);
			else if (distance <= LONG_DIST)
				setCharge(18);
			else
				setCharge(25);
		} else {
			if (distance < SHORT_DIST)
				setCharge(25);
			else if (distance <= LONG_DIST)
				setCharge(30);
			else
				setCharge(35);
		}
	}

	public String itemType() {
		return "Document";
	}

}
