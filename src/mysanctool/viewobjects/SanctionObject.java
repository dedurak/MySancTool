/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.viewobjects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mysanctool.modelobjects.Agent;
import mysanctool.modelobjects.QueryObjectForOverhead;

/**
 *
 * @author Deniz
 */
public class SanctionObject {
    
    
    Agent sanctAgent = new Agent("", "", "");
    Agent reporterOverhead = new Agent("", "", "");
    QueryObjectForOverhead queriesForLoggInUser;
    
    public SanctionObject(Stage mainStage, QueryObjectForOverhead queriesForLoggInUser) {
        this.queriesForLoggInUser = queriesForLoggInUser;
    }
    
    public void openAgentsList(Stage mainStage, Agent agent) {
        final Stage stageAfterSearchAgentButtonIsClicked = new Stage();
        
        stageAfterSearchAgentButtonIsClicked
                .initModality(Modality.APPLICATION_MODAL);
        stageAfterSearchAgentButtonIsClicked
                .initOwner(mainStage);
        stageAfterSearchAgentButtonIsClicked
                .getIcons().add(mainStage.getIcons().get(0));
        stageAfterSearchAgentButtonIsClicked.setResizable(false);
        
        Scene agentsList = new Scene(new Group());
        
        ((Group) agentsList.getRoot()).getChildren().add(setAndGetAgentsList(stageAfterSearchAgentButtonIsClicked, agent));
        stageAfterSearchAgentButtonIsClicked.setScene(agentsList);
        stageAfterSearchAgentButtonIsClicked.show();
    }
    
    // Sanctioninput and SanctionSearch
    public VBox setAndGetAgentsList(Stage mainStage, Agent agent) {
        ObservableList<Agent> listToShowOnTable = FXCollections.observableArrayList(queriesForLoggInUser.getAgentsList());
        
        final VBox containsTheAgentsList = new VBox();
        containsTheAgentsList.setSpacing(5);
        containsTheAgentsList.setPadding(new Insets(10, 0, 0, 10));
        
        TableView<Agent> agentsTable = createAgentsTable(listToShowOnTable);
        
        HBox chooseAgentContainer = new HBox();
        Button chooseAgentButton = new Button("Auswählen");
        chooseAgentContainer.getChildren().add(chooseAgentButton);
        
        chooseAgentButton.setOnAction((ActionEvent event) -> {
            sanctAgent.setFirstName(agentsTable.getSelectionModel().getSelectedItem().getFirstName());
            sanctAgent.setLastName(agentsTable.getSelectionModel().getSelectedItem().getLastName());
            sanctAgent.setShortCut(agentsTable.getSelectionModel().getSelectedItem().getShortCut());
            System.out.println(sanctAgent.getFirstName() + "\n" + sanctAgent.getLastName() + "\n" + sanctAgent.getShortCut());
            mainStage.close();
        });
        
        containsTheAgentsList.getChildren().add(agentsTable);
        containsTheAgentsList.getChildren().add(chooseAgentContainer);
        
        return containsTheAgentsList;
    }
    
    // Sanctioninput and SanctionSearch
    public TableView createAgentsTable(ObservableList agentsList) {
        TableView<Agent> agentsTable = new TableView<>();
        
        TableColumn firstNameOfAgent = new TableColumn("Vorname");
        firstNameOfAgent.setCellValueFactory(new PropertyValueFactory<Agent, String> ("firstName"));
        
        TableColumn lastNameOfAgent = new TableColumn("Nachname");
        lastNameOfAgent.setCellValueFactory(new PropertyValueFactory<Agent, String> ("lastName"));
        
        TableColumn shortCutOfAgent = new TableColumn("Kürzel");
        shortCutOfAgent.setCellValueFactory(new PropertyValueFactory<Agent, String> ("shortCut"));
        
        agentsTable.setItems(agentsList);
        agentsTable.getColumns().addAll(firstNameOfAgent, lastNameOfAgent, shortCutOfAgent);
        
        return agentsTable;
    }
    
    // Sanctioninput and SanctionSearch
    public void openOverHeadList (Stage mainStage, Agent agent) {
        final Stage overHeadstage = new Stage();
        
        overHeadstage
                .initModality(Modality.APPLICATION_MODAL);
        overHeadstage
                .initOwner(mainStage);
        overHeadstage
                .getIcons().add(mainStage.getIcons().get(0));
        overHeadstage.setResizable(false);
        
        Scene overHeadscene = new Scene(new Group());
        
        ((Group) overHeadscene.getRoot()).getChildren().add(setAndGetOverHeadList(overHeadstage, agent));
        
        overHeadstage.setScene(overHeadscene);
        overHeadstage.show();
    }
    
    // Sanctioninput and SanctionSearch
    public VBox setAndGetOverHeadList (Stage mainStage, Agent agent) {
        queriesForLoggInUser.getOverHeadList();
        ObservableList<Agent> overHeadList = FXCollections.observableArrayList(queriesForLoggInUser.getOverHeadList());
        
        final VBox overHeadBox = new VBox();
        overHeadBox.setSpacing(5);
        overHeadBox.setPadding(new Insets(10, 0, 0, 10));
        TableView<Agent> overHeadTable = createOverHeadTable(overHeadList);
        
        HBox chooseAgentContainer = new HBox();
        Button chooseAgentButton = new Button("Auswählen");
        chooseAgentContainer.getChildren().add(chooseAgentButton);
        
        chooseAgentButton.setOnAction((ActionEvent event) -> {
            agent.setFirstName(overHeadTable.getSelectionModel().getSelectedItem().getFirstName());
            agent.setLastName(overHeadTable.getSelectionModel().getSelectedItem().getLastName());
            agent.setShortCut(overHeadTable.getSelectionModel().getSelectedItem().getShortCut());
            System.out.println(agent.getFirstName() + "\n" + agent.getLastName() + "\n" + agent.getShortCut());
            mainStage.close();
        });
        
        overHeadBox.getChildren().add(overHeadTable);
        overHeadBox.getChildren().add(chooseAgentButton);
        
        return overHeadBox;
    }
    
    // Sanctioninput and SanctionSearch
    public TableView createOverHeadTable (ObservableList overHeadList) {
        TableView<Agent> overHeadTable = new TableView<>();
        
        TableColumn firstNameOfOverHead = new TableColumn("Vorname");
        firstNameOfOverHead.setCellValueFactory(new PropertyValueFactory<Agent, String> ("firstName"));
        
        TableColumn lastNameOfOverHead = new TableColumn("Nachname");
        lastNameOfOverHead.setCellValueFactory(new PropertyValueFactory<Agent, String> ("lastName"));
        
        TableColumn shortCutOfOverHead = new TableColumn("Kürzel");
        shortCutOfOverHead.setCellValueFactory(new PropertyValueFactory<Agent, String> ("shortCut"));
        
        overHeadTable.getColumns().addAll(firstNameOfOverHead, lastNameOfOverHead, shortCutOfOverHead);
        overHeadTable.setItems(overHeadList);
        
        return overHeadTable;
    }
}
