/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.modelobjects;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import net.ucanaccess.jdbc.*;

/**
 *
 * @author Deniz
 */

// contains all necessary attr. methods to login the user
public class QueryObjectForOverhead {
    
    String userName;
    String passWord;
    String firstName;
    String lastName;
    String queryFailedMessage;
    protected Agent[] agentsList;
    protected Agent[] overHeadList;
    public Connection connectionToAccdb;
    Statement statementForAccdb;
    ResultSet resultOfStatement;
    
    
    public QueryObjectForOverhead (String userName) {
        this.userName = userName;
    }
    
    public QueryObjectForOverhead (String userName, String password) {
        this.userName = userName;
        this.passWord = password;
    }
    
    public boolean login(String url) {
        createAccessConnection("jdbc:ucanaccess://"+url);
        createAccessStatement(connectionToAccdb);
        getResultOfQuery("SELECT userName, password FROM Users WHERE Users.userName='"+this.userName+"';");
        
        try {
        while (resultOfStatement.next()) {
            if (userName.equals(resultOfStatement.getString(1))) {
                if (passWord.equals(resultOfStatement.getString(2))) {
                    return true;
                }
                else return false;
                }
            else return false;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        
        return false;
    }
    
    public void createAccessConnection(String url) {
        try {
            connectionToAccdb = DriverManager.getConnection(url);
        }
        catch (SQLException exceptionToThrow) {
            exceptionToThrow.printStackTrace();
        }
    }
    
    public void createAccessStatement(Connection usedConnection) {
        try {
            statementForAccdb = usedConnection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    public void getResultOfQuery(String query){
        try {
            resultOfStatement = statementForAccdb.executeQuery(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    public String getLoggedInOHFirstName() {
        getResultOfQuery("SELECT firstName FROM Users WHERE userName='"+this.userName+"';");
        try {
            while(resultOfStatement.next()) {
                firstName = resultOfStatement.getString(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return firstName;
    }
    
    public String getLoggedInOHLastName() {
        getResultOfQuery("SELECT lastName FROM Users WHERE userName='"+this.userName+"';");
        try {
            while(resultOfStatement.next()) {
                lastName = resultOfStatement.getString(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return lastName;
    }
    
    public Agent[] getAgentsList() {
        getResultOfQuery("SELECT firstName, lastName, shortCutOfAgent FROM AgentsList");
        List<Agent> queriedList = new ArrayList<Agent>();
        Agent agent;
        try {
            while(resultOfStatement.next()) {
                agent = new Agent(resultOfStatement.getString(1), resultOfStatement.getString(2), resultOfStatement.getString(3));
                queriedList.add(agent);
            }
            agentsList = new Agent[queriedList.size()];
            queriedList.toArray(agentsList);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agentsList;
    }
    
    public Agent[] getOverHeadList() {
        getResultOfQuery("SELECT firstname, lastname, shortcutofoverhead FROM OverHeadList");
        List<Agent> ohList = new ArrayList<Agent>();
        try {
            while (resultOfStatement.next()) {
                Agent overHead = new Agent(resultOfStatement.getString(1), resultOfStatement.getString(2), resultOfStatement.getString(3));
                ohList.add(overHead);
            }
            overHeadList = new Agent[ohList.size()];
            ohList.toArray(overHeadList);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return overHeadList;
    }
    
    public boolean insertSanction(Sanction sancToInsert) {
        boolean checkQuerySucc;
        
        System.out.println("author: " + sancToInsert.getAuthor()+ "\n" + 
                "desc: " + sancToInsert.getDesc() + "\n" + 
                "reporter: " + sancToInsert.getReporter() + "\n" + 
                "agent: " + sancToInsert.getUserNameOfAgent() + "\n");
        
        String query = "INSERT INTO SanctionList(userNameOfAgent, DescriptionOfSanction, AuthorOfSanction) VALUES ('"
                + sancToInsert.getUserNameOfAgent()
                + "', '"
                + sancToInsert.getDesc()
                + "', '"
                + sancToInsert.getAuthor()
                + "');";
        
        if (sancToInsert.reporterOfSanction != null) {
            query = "INSERT INTO SanctionList(userNameOfAgent, DescriptionOfSanction, AuthorOfSanction, ReportedOf) VALUES ('"
                    + sancToInsert.getUserNameOfAgent()
                    + "', '"
                    + sancToInsert.getDesc()
                    + "', '"
                    + sancToInsert.getAuthor()
                    + "', '"
                    + sancToInsert.getReporter()
                    + "');";
        }
        
         try {
             checkQuerySucc = statementForAccdb.execute(query);
             return checkQuerySucc;
         } catch (SQLException exc) {
             exc.printStackTrace();
             queryFailedMessage = exc.getMessage();
             checkQuerySucc = true;
             return checkQuerySucc;
         }
    }
    
    public String getTeamLeaderName(String userName) {
        String query = "SELECT Teamleiter FROM AgentsList WHERE shortCutOfAgent = '" + userName + "';";
        String teamLeader;
        getResultOfQuery(query);
        try {
            if(resultOfStatement.next()) {
                teamLeader = resultOfStatement.getString(1);
            }
            else {
                teamLeader = "none";
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            teamLeader = "none";
        }
        return teamLeader;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public String getQueryFailedMsg () {
        return queryFailedMessage;
    }
}
