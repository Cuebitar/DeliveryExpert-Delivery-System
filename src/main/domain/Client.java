package domain;

public class Client extends Person {

	public String id;

	public Client(String id, String name, String telNo) {
		super(name, telNo);
		this.id = id;
	}

	public Client() {
	}

	public String getId() {
		return id;
	}

	public String toString() {
		return String.format("%s;%s;%s", getId(), getName(), getTelNo());
	}
	
}
