/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.viewobjects;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mysanctool.modelobjects.Agent;
import mysanctool.modelobjects.QueryObjectForOverhead;

/**
 *
 * @author Deniz
 */
public class SanctionSearch extends SanctionObject{
    
    HBox centerPane;
    Agent authorOverhead = new Agent("", "", "");
    
    public SanctionSearch(Stage mainStage, QueryObjectForOverhead queriesForLoggInUser) {
        super(mainStage, queriesForLoggInUser);
        centerPane = new HBox();
    }
    
    public VBox createMySearchField(Stage mainStage) {
        centerPane = setCenterPane();
        
        VBox centerPaneContainer = new VBox();
        centerPaneContainer.setId("centerPane");
        centerPaneContainer.setPrefSize(850, 700);
        centerPaneContainer.setMaxWidth(850);
        centerPaneContainer.setMaxHeight(700);
        
        VBox searchForNameBox = setAgentParamField(mainStage);
        VBox searchForAuthorNameBox = setAuthorParamField(mainStage);
        VBox searchForReportedOfNameBox = setReporterParamField(mainStage);
        VBox searchForDateBox = setCalParamField();
        
        Text titleOfThisBox = new Text("Eintrag suchen");
        titleOfThisBox.setId("titleStyle");
        titleOfThisBox.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        
        HBox searchButtonBox = new HBox();
        Button srcBtn = new Button("Suchen");
        searchButtonBox.getChildren().add(srcBtn);
        searchButtonBox.setAlignment(Pos.CENTER);
        
        centerPane.getChildren().add(titleOfThisBox);
        centerPane.getChildren().add(searchForNameBox);
        centerPane.getChildren().add(searchForAuthorNameBox);
        centerPane.getChildren().add(searchForReportedOfNameBox);
        centerPane.getChildren().add(searchForDateBox);
        
        centerPaneContainer.getChildren().add(titleOfThisBox);
        centerPaneContainer.getChildren().add(centerPane);
        centerPaneContainer.getChildren().add(searchButtonBox);
        
        centerPaneContainer.setPrefSize(700, 700);
        
        /********************************************************** */
        
        return centerPaneContainer;
    }
    
    // returns and setups prop for centerpane
    private HBox setCenterPane() {
        HBox setAndReturnCenter = new HBox();
        setAndReturnCenter.setMaxWidth(850);
        setAndReturnCenter.setMaxHeight(700);
        setAndReturnCenter.setPrefSize(700, 700);
        
        return setAndReturnCenter;
    }
    
    private VBox setNameSearchField(Agent agent) {
        VBox nameBox = new VBox();
        
        VBox searchForFirstNameBox = new VBox();
        VBox searchForLastNameBox = new VBox();
        VBox searchForShortCutBox = new VBox();
        
        Label firstName = new Label("Vorname: ");
        TextField inputFirstName = new TextField();
        inputFirstName.textProperty().bindBidirectional(agent.firstNameProperty());
        
        searchForFirstNameBox.getChildren().add(firstName);
        searchForFirstNameBox.getChildren().add(inputFirstName);
        
        Label lastName = new Label("Nachname");
        TextField inputLastName = new TextField();
        inputLastName.textProperty().bindBidirectional(agent.lastNameProperty());
        
        searchForLastNameBox.getChildren().add(lastName);
        searchForLastNameBox.getChildren().add(inputLastName);
        
        Label agentShortCut = new Label("Kürzel: ");
        TextField inputAgentShortCut = new TextField();
        inputAgentShortCut.textProperty().bindBidirectional(agent.shortCutProperty());
        
        searchForShortCutBox.getChildren().add(agentShortCut);
        searchForShortCutBox.getChildren().add(inputAgentShortCut);
        
        nameBox.getChildren().addAll(searchForFirstNameBox, searchForLastNameBox, searchForShortCutBox);
        nameBox.setPadding(new Insets(10, 10, 10, 10));
        
        return nameBox;
    }
    
    private VBox setAgentParamField(Stage mainStage) {
        VBox searchForAgentBox = new VBox();
        VBox searchForNameBox = setNameSearchField(sanctAgent);
        
        Text menuAgentSearchTitle = new Text("Nach Agenten suchen");
        menuAgentSearchTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Button searchForAgent = new Button("Agenten auswählen");
        searchForAgent.setPadding(new Insets(5, 10, 5, 10));
        searchForAgent.setOnAction((ActionEvent event) -> {
            openAgentsList(mainStage, sanctAgent);
        });
        
        HBox searchAgentNameButton = new HBox();
        searchAgentNameButton.getChildren().add(searchForAgent);
        
        searchForAgentBox.getChildren().add(menuAgentSearchTitle);
        searchForAgentBox.getChildren().add(searchForNameBox);
        searchForAgentBox.getChildren().add(searchAgentNameButton);
        searchForAgentBox.setPadding(new Insets(10, 10, 10, 10));
        searchForAgentBox.setSpacing(10);
        
        return searchForAgentBox;
    }
    
    private VBox setAuthorParamField(Stage mainStage) {
        VBox searchForAuthorNameBox = new VBox();
        VBox searchForNameBox = setNameSearchField(authorOverhead);
        
        Label authorName = new Label("Eingetragen Von: ");
        authorName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Button searchForAuthor = new Button("Mitarbeiter auswählen");
        searchForAuthor.setPadding(new Insets(5, 10, 5, 10));
        searchForAuthor.setOnAction((ActionEvent event) -> {
            openOverHeadList(mainStage, authorOverhead);
        });
        
        HBox searchForAuthorButton = new HBox();
        searchForAuthorButton.getChildren().add(searchForAuthor);
        
        searchForAuthorNameBox.getChildren().add(authorName);
        searchForAuthorNameBox.getChildren().add(searchForNameBox);
        searchForAuthorNameBox.getChildren().add(searchForAuthorButton);
        searchForAuthorNameBox.setPadding(new Insets(10, 10, 10, 10));
        searchForAuthorNameBox.setSpacing(10);
        
        return searchForAuthorNameBox;
    }
    
    private VBox setReporterParamField(Stage mainStage) {
        VBox searchForReportedOfNameBox = new VBox();
        VBox searchForNameBox = setNameSearchField(reporterOverhead);
        
        Label reportedOfName = new Label("Gemeldet Von: ");
        reportedOfName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Button searchForReporter = new Button("Mitarbeiter auswählen");
        searchForReporter.setPadding(new Insets(5, 10, 5, 10));
        searchForReporter.setOnAction((ActionEvent event) -> {
            openOverHeadList(mainStage, reporterOverhead);
        });
        
        HBox searchForReporterButton = new HBox();
        searchForReporterButton.getChildren().add(searchForReporter);
        
        searchForReportedOfNameBox.getChildren().add(reportedOfName);
        searchForReportedOfNameBox.getChildren().add(searchForNameBox);
        searchForReportedOfNameBox.getChildren().add(searchForReporterButton);
        searchForReportedOfNameBox.setPadding(new Insets(10, 10, 10, 10));
        searchForReportedOfNameBox.setSpacing(10);
        
        return searchForReportedOfNameBox;
    }
    
    private VBox setCalWithLabel(String labelText) {
        VBox calAndLblBox = new VBox();
        
        Label setlblDate = new Label(labelText);
        DatePicker setCal = new DatePicker();
        
        calAndLblBox.getChildren().add(setlblDate);
        calAndLblBox.getChildren().add(setCal);
        
        return calAndLblBox;
    }
    
    private VBox setCalParamField() {
        VBox searchForDateBox = new VBox();
        VBox startDateBox = setCalWithLabel("Von: ");
        VBox endDateBox = setCalWithLabel("Bis: ");
        
        Text labelForSearchDate = new Text("Nach Datum suchen");
        labelForSearchDate.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        searchForDateBox.getChildren().add(labelForSearchDate);
        searchForDateBox.getChildren().add(startDateBox);
        searchForDateBox.getChildren().add(endDateBox);
        searchForDateBox.setPadding(new Insets(10, 10, 10, 10));
        searchForDateBox.setSpacing(10);
        
        return searchForDateBox;
    }
}
