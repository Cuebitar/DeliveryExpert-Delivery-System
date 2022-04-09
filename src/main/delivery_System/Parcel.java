package delivery_System;

public class Parcel extends Item {

    private static final double SHORT_DIST = 10;
    private static final double LONG_DIST = 30;

    public Parcel(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    protected void calculateCharge() {

        if (weight < 1001) {
            if (distance < SHORT_DIST)
                setCharge(5);
            else if (distance <= LONG_DIST)
                setCharge(8);
            else
                setCharge(10);
        } else if (weight <= 2000) {
            if (distance < SHORT_DIST)
                setCharge(15);
            else if (distance <= LONG_DIST)
                setCharge(18);
            else
                setCharge(25);
        } else if (weight <= 3000) {
            if (distance < SHORT_DIST)
                setCharge(23);
            else if (distance <= LONG_DIST)
                setCharge(28);
            else
                setCharge(35);
        } else if (weight <= 5000) {
            if (distance < SHORT_DIST)
                setCharge(35);
            else if (distance <= LONG_DIST)
                setCharge(40);
            else
                setCharge(50);
        } else {
            if (distance < SHORT_DIST)
                setCharge(45);
            else if (distance <= LONG_DIST)
                setCharge(50);
            else
                setCharge(60);
        }
    }

    public String itemType() {
        return "Parcel";
    }
}
