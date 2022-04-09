package delivery_System;

public class Receiver extends Person {

    public Receiver(String name, String telNo) {
        super(name, telNo);
    }

    public String toString() {
        return String.format("%s;%s", getName(), getTelNo());
    }
}
