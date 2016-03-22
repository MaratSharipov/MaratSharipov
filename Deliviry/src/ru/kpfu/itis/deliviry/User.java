package ru.kpfu.itis.deliviry;

/**
 * Created by Lenovo on 21.03.2016.
 */
public class User {

    private String name;
    private String phone;
    private String address;
    private String card;


    public User(String address, String phone, String name, String card) {
        this.address = address;
        this.phone = phone;
        this.name = name;
        this.card = card;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", card='" + card + '\'';
    }
}
