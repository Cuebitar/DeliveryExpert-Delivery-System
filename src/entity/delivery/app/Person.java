package delivery.app;

public class Person {

    private String name;
    private String telNo;

    public Person(String name, String telNo){
        this.name = name;
        this.telNo = telNo;
    }

    public Person(){}

    public String getName() {
        return name;
    }

    public String getTelNo() {
        return telNo;
    }

}
