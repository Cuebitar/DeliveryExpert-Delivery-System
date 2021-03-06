package delivery.app;

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
	
	public boolean equals(Staff s) {
		return id.equals(s.id)&& name.equals(s.name) && telNo.equals(s.telNo); 
	}
}
