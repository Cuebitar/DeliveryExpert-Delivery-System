package domain;

public abstract class Item {

	protected double weight;
	protected double distance;
	private double charge;

	public Item(double weight, double distance) {
		this.weight = weight;
		this.distance = distance;
		calculateCharge();
	}

	public double getWeight() {
		return weight;
	}

	public double getDistance() {
		return distance;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	protected abstract void calculateCharge();

	public abstract String itemType();

}
