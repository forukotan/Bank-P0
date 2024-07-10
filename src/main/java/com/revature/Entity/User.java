package com.revature.Entity;

import java.io.Serializable;
import java.util.Objects;
/*
this class is designed as a java bean: this is a design pattern that follows standard
naming and development practice
    - all fileds of the class implements Serializable, which allows it to be turned ino byte stress by java
    -all fiel of class are private and have public getter and settes

 */

public class User implements Serializable {
   private String username;
    private String password;

    public User(){}

    public User(String username,String password){
        this.username = username;
        this.username =password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
