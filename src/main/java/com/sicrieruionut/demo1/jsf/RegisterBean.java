package com.sicrieruionut.demo1.jsf;

import com.sicrieruionut.demo1.ejb.UserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("registerBean")
@RequestScoped
public class RegisterBean {
    private String username;
    private String password;
    private String message;

    @EJB
    private UserService userService;

    public void register() {
        try {
            userService.register(username, password);
            message = "Cont creat cu succes!";
        } catch (Exception e) {
            message = "Eroare: " + e.getMessage();
        }
    }

    // getters + setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}