package domain;

public class Staff extends Person {

	private String id;

	public Staff(String id, String name, String telNo) {
		super(name, telNo);
		this.id = id;
	}

	public Staff() {
		super();
	}

	public String getId() {
		return id;
	}

	public String toString() {
		return String.format("%s;%s;%s", getId(), getName(), getTelNo());
	}

}
