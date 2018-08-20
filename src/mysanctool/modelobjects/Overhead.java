/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.modelobjects;

import javafx.beans.property.StringProperty;

/**
 *
 * @author Deniz
 */
public class Overhead extends Member {
    private QueryObjectForOverhead query;
    
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty userName;
    private StringProperty pwd;
    
    public Overhead(String userName, String pwd, String url) {
        super(userName,pwd);
        
        query = new QueryObjectForOverhead(userName, pwd);
        if(query.login(url)) {
            setFirstName(query.getLoggedInOHFirstName());
            setLastName(query.getLoggedInOHLastName());
        }
    }
    
    public boolean login(String url) {
        boolean loginChecker; // true if logged in, else false
        loginChecker = query.login(url);
        System.out.println("loginChecker: " + loginChecker);
        return loginChecker;
    }
    
    public QueryObjectForOverhead getQueryObject() {
        return query;
    }
}
