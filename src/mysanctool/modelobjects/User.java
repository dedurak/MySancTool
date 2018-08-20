/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.modelobjects;

/**
 *
 * @author Deniz
 */
public class User {
    
    String userName;
    String firstName;
    String lastName;
    Integer userId;
    Integer roleId;
    String password;
    
    /**
     * constructor to create a new User. To define the RoleId there is another class called RoleChecker which checks the role
     * the userId is set after the insertion query to the user database.
     * */
    public User(String userName, 
            String firstName, 
            String lastName, 
            Integer roleId,
            Integer userId) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
    }
    
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public String getUserName() {
        return userName;
    }
    
    //*********************************************************
    public boolean setUserName(String thisIsTheNewUserName) {
        /* 
         * first of all check if the userName is assigned to another user. if 'yes' we return false. if 'no' return boolean and change the userName.
         **/
        
        return true;
    }
    //*********************************************************
    
    public String getFirstName() {
        return firstName;
    }
    
    public boolean setFirstName(String thisIsTheNewFirstName) {
        firstName = thisIsTheNewFirstName;
        return true;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public boolean setLastName(String thisIsTheNewLastName) {
        lastName = thisIsTheNewLastName;
        return true;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public boolean setRoleId(Integer nRoleId) {
        roleId = nRoleId;
        return true;
    }
    
    public Integer getUserId() {
        return userId;
    }
}
