package model;

/**
 * @author: Oliver Steiner
 * @version: 1, 24.02.2021
 */

public class Person {
    private String name;
    private String address;
    private String phoneNum;

    public Person(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNr() {
        return phoneNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" ");
            sb.append(address);
            sb.append(" ");
            sb.append(phoneNum);
            sb.append(" ");




        return sb.toString();
    }
}
