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
public class Sanction {
    StringProperty userNameOfAgent;
    StringProperty descOfSanction;
    StringProperty authorOfSanction;
    StringProperty reporterOfSanction;
    
    public Sanction () {
        this.userNameOfAgent = new SimpleStringProperty();
        this.descOfSanction = new SimpleStringProperty("");
        this.authorOfSanction = new SimpleStringProperty();
        this.reporterOfSanction = new SimpleStringProperty();
    }
    
    public Sanction (String userNameOfAgent, 
                     String descOfSanction, 
                     String authorOfSanction) {
        this.userNameOfAgent = new SimpleStringProperty(userNameOfAgent);
        this.descOfSanction = new SimpleStringProperty(descOfSanction);
        this.authorOfSanction = new SimpleStringProperty(authorOfSanction);
    }
    
    public Sanction (String userNameOfAgent, 
                     String descOfSanction, 
                     String authorOfSanction,
                     String reporterOfSanction) {
        this.userNameOfAgent = new SimpleStringProperty(userNameOfAgent);
        this.descOfSanction = new SimpleStringProperty(descOfSanction);
        this.authorOfSanction = new SimpleStringProperty(authorOfSanction);
        this.reporterOfSanction = new SimpleStringProperty(reporterOfSanction);
    }
    
    public String getUserNameOfAgent () {
        return userNameOfAgent.get();
    }
    
    public String getDesc () {
        return descOfSanction.get();
    }
    
    public String getAuthor () {
        return authorOfSanction.get();
    }
    
    public String getReporter () {
        return reporterOfSanction.get();
    }
    
    public StringProperty descProperty() {
        return descOfSanction;
    }
    
    public void setDesc(String setText) {
        this.descOfSanction.set(setText);
    }
    
    public void setAuthor(String setText) {
        this.authorOfSanction.set(setText);
    }
    
    public void setAgent(String setText) {
        this.userNameOfAgent.set(setText);
    }
    
    public void setReporter(String setText) {
        this.reporterOfSanction.set(setText);
    }
}
