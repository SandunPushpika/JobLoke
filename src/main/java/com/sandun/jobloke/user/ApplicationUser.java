package com.sandun.jobloke.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "users")
public class ApplicationUser {

    @Id
    private String Username;

    private String Password;
    private String Name;
    private String Email;
    private String Phone;
    private LocalDate Bdate;
    private String Authority;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password, String name, String email, String phone, LocalDate bdate, String authority) {
        Username = username;
        Password = password;
        Name = name;
        Email = email;
        Phone = phone;
        Bdate = bdate;
        Authority = authority;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public LocalDate getBdate() {
        return Bdate;
    }

    public void setBdate(LocalDate bdate) {
        Bdate = bdate;
    }

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        Authority = authority;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Bdate=" + Bdate +
                ", Authority='" + Authority + '\'' +
                '}';
    }
}
