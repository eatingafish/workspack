package com.example.buttongreenevent;

public class MessageEvent {
    public String name;
    public String password;

    public MessageEvent() {

    }

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
