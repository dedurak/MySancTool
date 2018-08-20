/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.viewobjects;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mysanctool.modelobjects.Agent;
import mysanctool.modelobjects.QueryObjectForOverhead;
import mysanctool.modelobjects.Sanction;

/**
 *
 * @author Deniz
 */
public class SanctionInput extends SanctionObject{
    VBox centerPane;
    
    private String userNameOfUser;
    private String firstNameOfUser;
    private String lastNameOfUser;
    
    HBox agentNamePane;
    HBox departmentAndDescriptionPane;
    HBox authorPane;
    HBox reporterPane;
    Sanction sancToInsert = new Sanction();
    Agent authorOverhead = new Agent(firstNameOfUser, lastNameOfUser, userNameOfUser);
    
    
    public SanctionInput(Stage mainStage, QueryObjectForOverhead queriesForLoggInUser) {
        super(mainStage, queriesForLoggInUser);
        this.centerPane = new VBox();
    }
    
    public VBox createMySanctionInputCenterPane(Stage mainStage) {
        queriesForLoggInUser.getLoggedInOHFirstName();
        queriesForLoggInUser.getLoggedInOHLastName();
        authorOverhead.setFirstName(queriesForLoggInUser.getFirstName());
        authorOverhead.setLastName(queriesForLoggInUser.getLastName());
        authorOverhead.setShortCut(queriesForLoggInUser.getUserName());
        
        agentNamePane = createAgentNameField(mainStage);
        departmentAndDescriptionPane = createDepAndDescField();
        authorPane = createAuthorField();
        reporterPane = createReporterField(mainStage);
        
        Text agentName = new Text("Agent auswählen*");
        agentName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Separator hLine = new Separator();
        hLine.setOrientation(Orientation.HORIZONTAL);
    
        Text departmentAndDescr = new Text("Bereich und Beschreibung*");
        departmentAndDescr.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
    
        Separator hLine2 = new Separator();
        hLine2.setOrientation(Orientation.HORIZONTAL);
    
        Text authorName = new Text("Eingetragen von*");
        authorName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
    
        Separator hLine3 = new Separator();
        hLine3.setOrientation(Orientation.HORIZONTAL);
    
        Text reporterName = new Text("Gemeldet von");
        reporterName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
    
        Button insertSanctionButton = new Button("Eintrag speichern");
        insertSanctionButton.setOnAction((ActionEvent event) -> {
            sancToInsert.setAuthor(authorOverhead.getShortCut());
            sancToInsert.setAgent(sanctAgent.getShortCut());
            sancToInsert.setReporter(reporterOverhead.getShortCut());
            if(checkValues()) {
                boolean checkIfQueryIsSucc = runAndCheckQuerySuccess(sancToInsert, mainStage);
                if(checkIfQueryIsSucc) {
                    delValues();
                }
            }
            else {
                createPopupWindow(mainStage, "OK", "Bitte alle Felder ausfüllen!");
            }
        });
        HBox insertSanctionContainer = new HBox();
        insertSanctionContainer.getChildren().add(insertSanctionButton);
        
        Text titleOfThisBox = new Text("Eintrag erfassen");
        titleOfThisBox.setId("titleStyle");
        titleOfThisBox.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        
        centerPane.setId("centerPane");
        centerPane.setPrefSize(850, 700);
        centerPane.setMaxWidth(850);
        centerPane.setMaxHeight(700);
        centerPane.getChildren().add(titleOfThisBox);
        centerPane.getChildren().add(agentName);
        centerPane.getChildren().add(agentNamePane);
        centerPane.getChildren().add(hLine);
        centerPane.getChildren().add(departmentAndDescr);
        centerPane.getChildren().add(departmentAndDescriptionPane);
        centerPane.getChildren().add(hLine2);
        centerPane.getChildren().add(authorName);
        centerPane.getChildren().add(authorPane);
        centerPane.getChildren().add(hLine3);
        centerPane.getChildren().add(reporterName);
        centerPane.getChildren().add(reporterPane);
        centerPane.getChildren().add(insertSanctionContainer);
        centerPane.setPadding(new Insets(10, 10, 10, 10));
        centerPane.setSpacing(10);
        
        return centerPane;
    }
    
    // Sanctioninput
    public boolean runAndCheckQuerySuccess (Sanction sanct, Stage mainStage) {
        boolean checker;
        if (queriesForLoggInUser.insertSanction(sanct)) {
            createQueryFailedWindow(mainStage);
            checker = false;
        } else {
            createSuccessWindow(mainStage);
            checker = true;
        }
        return checker;
    }
    
    // Sanctioninput
    public void createQueryFailedWindow(Stage mainStage) {
        String msgText = "Es ist ein Fehler aufgetreten! \n" + queriesForLoggInUser.getQueryFailedMsg();
        String btnText = "OK";
        createPopupWindow(mainStage, btnText, msgText);
    }
    
    // Sanctioninput
    public void createSuccessWindow(Stage mainStage) {
        String msgText = "Eintrag gespeichert";
        String btnText = "OK";
        createPopupWindow(mainStage, btnText, msgText);
    }
    
    // Sanctioninput
    private void createPopupWindow (Stage mainStage, String buttonText, String msgText) {
        final Stage stageAfterLoginButtonIsClicked = new Stage();
        stageAfterLoginButtonIsClicked.initModality(Modality.APPLICATION_MODAL);
        stageAfterLoginButtonIsClicked.initOwner(mainStage);
        stageAfterLoginButtonIsClicked.getIcons().add(mainStage.getIcons().get(0));
        //stageAfterLoginButtonIsClicked.setResizable(false);
        
        GridPane displayValues = new GridPane();
        displayValues.setAlignment(Pos.CENTER);
        displayValues.setVgap(10);
        displayValues.setHgap(10);
        displayValues.setPadding(new Insets(5, 5, 5, 5));
        
        Button closeButton = new Button();
        HBox boxWithTheCloseButton = new HBox();
        boxWithTheCloseButton.setAlignment(Pos.BOTTOM_CENTER);
        
        closeButton.setText(buttonText);
        closeButton.setOnAction((ActionEvent event) -> {
            stageAfterLoginButtonIsClicked.close();
        });
                
        boxWithTheCloseButton.getChildren().add(closeButton);
        
        displayValues.add(new Text(msgText), 0, 0, 1, 2);
        displayValues.add(boxWithTheCloseButton, 0, 1, 1, 4);
        Scene sceneForThatStage = new Scene(displayValues, 250,100);
        
        stageAfterLoginButtonIsClicked.setScene(sceneForThatStage);
        stageAfterLoginButtonIsClicked.show();
    }
    
    // Sanctioninput and SanctionSearch
    private void delValues() {
        sanctAgent.setFirstName("");
        sanctAgent.setLastName("");
        sanctAgent.setShortCut("");
        sancToInsert.setDesc("");
        reporterOverhead.setFirstName("");
        reporterOverhead.setLastName("");
        reporterOverhead.setShortCut("");
    }
    
    // Sanctioninput
    private HBox createAgentsListButtonBox (Stage mainStage, String btnText) {
        Button btn = new Button(btnText);
        btn.setOnAction((ActionEvent event) -> {
            openAgentsList(mainStage, sanctAgent);
        });
        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().add(btn);
        return buttonContainer;
    }
    
    // Sanctioninput
    private HBox createAgentNameField(Stage mainStage) {
        HBox agentPane = new HBox();
        
        // agent name and shortcut input
        Label firstName = new Label("Vorname: ");
        firstName.setPadding(new Insets(0, 2, 0, 10));
        TextField firstNameInput = new TextField(/*sanctAgent.getFirstName()*/);
        firstNameInput.textProperty().bindBidirectional(sanctAgent.firstNameProperty());
        firstNameInput.setDisable(true);
        
        Label lastName = new Label("Nachname: ");
        lastName.setPadding(new Insets(0, 2, 0, 10));
        TextField lastNameInput = new TextField(/*sanctAgent.getLastName()*/);
        lastNameInput.textProperty().bindBidirectional(sanctAgent.lastNameProperty());
        lastNameInput.setDisable(true);
        
        Label shortCut = new Label("Kürzel: ");
        shortCut.setPadding(new Insets(0, 2, 0, 10));
        TextField shortCutInput = new TextField(/*sanctAgent.getShortCut()*/);
        shortCutInput.textProperty().bindBidirectional(sanctAgent.shortCutProperty());
        shortCutInput.setDisable(true);
        
        HBox buttonContainer = createAgentsListButtonBox(mainStage, "Agent suchen");
        
        agentPane.getChildren().addAll(firstName);
        agentPane.getChildren().add(firstNameInput);
        agentPane.getChildren().add(lastName);
        agentPane.getChildren().add(lastNameInput);
        agentPane.getChildren().add(shortCut);
        agentPane.getChildren().add(shortCutInput);
        agentPane.getChildren().add(buttonContainer);
        agentPane.setSpacing(10);
        
        return agentPane;
    }
    
    // Sanctioninput
    private HBox createDepAndDescField() {
        HBox departmentAndDescription = new HBox();
        
        Label department = new Label("Bereich: ");
        department.setPadding(new Insets(0, 2, 0, 10));
        ChoiceBox departmentInput = new ChoiceBox(FXCollections.observableArrayList(departments()));
        Label description = new Label("Kommentar: ");
        description.setPadding(new Insets(0, 2, 0, 10));
        TextArea descriptionInputArea = new TextArea();
        descriptionInputArea.textProperty().bindBidirectional(sancToInsert.descProperty());
        descriptionInputArea.setPrefRowCount(5);
        descriptionInputArea.setPrefWidth(250);
        
        departmentAndDescription.getChildren().add(department);
        departmentAndDescription.getChildren().add(departmentInput);
        departmentAndDescription.getChildren().add(description);
        departmentAndDescription.getChildren().add(descriptionInputArea);
        departmentAndDescription.setSpacing(10);
        
        return departmentAndDescription;
    }
    
    // Sanctioninput
    private HBox createAuthorField() {
        HBox authorPane = new HBox();
        
        Label authorFirstName = new Label("Vorname: ");
        authorFirstName.setPadding(new Insets(0, 2, 0, 10));
        TextField authorFirstNameInput = new TextField(authorOverhead.getFirstName());
        authorFirstNameInput.setDisable(true);
        
        Label authorLastName = new Label("Nachname: ");
        authorLastName.setPadding(new Insets(0, 2, 0, 10));
        TextField authorLastNameInput = new TextField(authorOverhead.getLastName());
        authorLastNameInput.setDisable(true);
        
        Label authorShortcut = new Label("Kürzel: ");
        authorShortcut.setPadding(new Insets(0, 2, 0, 10));
        TextField authorShortcutInput = new TextField(authorOverhead.getShortCut());
        authorShortcutInput.setDisable(true);
        
        authorPane.getChildren().add(authorFirstName);
        authorPane.getChildren().add(authorFirstNameInput);
        authorPane.getChildren().add(authorLastName);
        authorPane.getChildren().add(authorLastNameInput);
        authorPane.getChildren().add(authorShortcut);
        authorPane.getChildren().add(authorShortcutInput);
        authorPane.setSpacing(10);
        
        return authorPane;
    }
    
    // Sanctioninput
    private HBox createReporterField(Stage mainStage) {
        HBox reporterPane = new HBox();
        
        Label reporterFirstName = new Label("Vorname: ");
        reporterFirstName.setPadding(new Insets(0, 2, 0, 10));
        TextField reporterFirstNameInput = new TextField(/*ohFirstName.get()*/);
        reporterFirstNameInput.textProperty().bindBidirectional(reporterOverhead.firstNameProperty());
        reporterFirstNameInput.setDisable(true);
        
        Label reporterLastName = new Label("Nachname: ");
        reporterLastName.setPadding(new Insets(0, 2, 0, 10));
        TextField reporterLastNameInput = new TextField(/*ohLastName.get()*/);
        reporterLastNameInput.textProperty().bindBidirectional(reporterOverhead.lastNameProperty());
        reporterLastNameInput.setDisable(true);
        
        Label reporterShortcut = new Label("Kürzel: ");
        reporterShortcut.setPadding(new Insets(0, 2, 0, 10));
        TextField reporterShortcutInput = new TextField(/*ohShortCut.get()*/);
        reporterShortcutInput.textProperty().bindBidirectional(reporterOverhead.shortCutProperty());
        reporterShortcutInput.setDisable(true);
        
        HBox reporterButtonContainer = new HBox();
        Button takeReporter = new Button("OVH wählen");
        reporterButtonContainer.getChildren().add(takeReporter);
        
        takeReporter.setOnAction((ActionEvent event) -> {
            openOverHeadList(mainStage, reporterOverhead);
        });
        
        reporterPane.getChildren().add(reporterFirstName);
        reporterPane.getChildren().add(reporterFirstNameInput);
        reporterPane.getChildren().add(reporterLastName);
        reporterPane.getChildren().add(reporterLastNameInput);
        reporterPane.getChildren().add(reporterShortcut);
        reporterPane.getChildren().add(reporterShortcutInput);
        reporterPane.getChildren().add(reporterButtonContainer);
        reporterPane.setSpacing(10);
        
        return reporterPane;
    }
    
    // Sanctioninput
    private boolean checkValues() {
        boolean checkSucc; // if true all nec. values are inserted
        
        System.out.println("sanctAgent.getShortCut(): " + sanctAgent.getShortCut() 
                +"\nauthorOverhead.getShortCut(): " + authorOverhead.getShortCut()
                +"\nsancToInsert.getDesc(): " + sancToInsert.getDesc());
        
        if(sanctAgent.getShortCut().equals("") || authorOverhead.getShortCut().equals("") || sancToInsert.getDesc().equals("")) {
            System.out.println("inside false:\nsanctAgent.equals(\"\"): " + sanctAgent.equals("") + " authorOverhead.equals(\"\"): " + authorOverhead.equals("") + " sancToInsert.equals(\"\"): " + sancToInsert.equals(""));
            checkSucc = false;
        } else {
            System.out.println("inside true:\nsanctAgent.equals(\"\"): " + sanctAgent.equals("") + "authorOverhead.equals(\"\"): " + authorOverhead.equals("") + "sancToInsert.equals(\"\"): " + sancToInsert.equals(""));
            checkSucc = true;
        }
        
        return checkSucc;
    }
    
    // Sanctioninput and SanctionSearch
    public String[] departments() {
        String[] depArray = {"RBC", "BM", "SU", "CCD", "SCT", "PRC", "PTS"};
        return depArray;
    }
}
