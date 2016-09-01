/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Informatica
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    private String username;
    private String password;

    /**
    * @return the username
    */
    public String getUsername() {
        return username;
    }

    /**
    * @param username the username to set
    */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
    * @return the password
    */
    public String getPassword() {
        return password;
    }

    /**
    * @param password the password to set
    */
    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (username.equals("madhav") && password.equals("password")) {
        return "success";
        } else
        return "failure";
    }
}
