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
public class Agent extends Member {
    // one time created the value is not changeable, delete the agent and create him new
    /*private final StringProperty teamLeaderOfAgent;
    
    private final QueryObjectForOverhead usedAgentObject;
    */
    public Agent(String firstName, String lastName, String shortCut) {
        super(firstName, lastName, shortCut);
    }
}
