package com.sicrieruionut.demo1.jsf;

import com.sicrieruionut.demo1.entity.UserAccount;
import com.sicrieruionut.demo1.ejb.UserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.context.FacesContext;


@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private UserAccount user;
    private String message;

    @EJB
    private UserService userService;

    public String login() {
        try {
            user = userService.login(username, password);
            if (user != null) {
                return "/index.xhtml?faces-redirect=true";
            } else {
                message = "Date invalide";
            }
        } catch (Exception e) {
            message = "Eroare: " + e.getMessage();
        }
        return null;
    }

    public String logout() {
        // invalidează sesiunea HTTP
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        // opțional: şterge referinţa locală
        user = null;
        // redirecţionează către pagina de login (sau home)
        return "/login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    // getters + setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserAccount getUser() { return user; }
    public void setUser(UserAccount user) { this.user = user; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
