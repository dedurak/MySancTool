/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.modelobjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Deniz
 */
public class Member {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty userName;
    private StringProperty pwd;
    
    // constructor for overhead
    public Member(String userName, String pwd) {
        this.userName = new SimpleStringProperty(userName);
        this.pwd = new SimpleStringProperty(pwd);
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
    }
    
    // constructor for agent
    public Member(String firstName, String lastName, String shortCut) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.userName = new SimpleStringProperty(shortCut);
    }
    
    public String getFirstName() {
        return firstName.get();
    }
    
    public String getLastName() {
        return lastName.get();
    }
    
    public String getShortCut() {
        return userName.get();
    }
    
    public String getPwd() {
        return pwd.get();
    }
    
    public StringProperty firstNameProperty() {
        return firstName;
    }
    
    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    public StringProperty shortCutProperty() {
        return userName;
    }
    
    public StringProperty pwdProperty() {
        return pwd;
    }
    
    public void setFirstName(String newFirstName) {
        firstName.set(newFirstName);
    }
    
    public void setLastName(String newLastName) {
        lastName.set(newLastName);
    }
    
    public void setShortCut(String newShortCut) {
        userName.set(newShortCut);
    }
    
    public void setPwd (String newPwd) {
        pwd.set(newPwd);
    }
}
