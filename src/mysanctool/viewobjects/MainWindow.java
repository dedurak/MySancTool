/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.viewobjects;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mysanctool.modelobjects.Agent;
import mysanctool.modelobjects.Overhead;
import mysanctool.modelobjects.QueryObjectForOverhead;
import mysanctool.modelobjects.Sanction;

/**
 *
 * @author Deniz Durak
 */
public class MainWindow extends Application {
    
    private String userNameOfUser;
    private String firstNameOfUser;
    private String lastNameOfUser;
    
    StringProperty firstProperty = new SimpleStringProperty();
    StringProperty lastProperty = new SimpleStringProperty();
    StringProperty shortProperty = new SimpleStringProperty();
    Agent sanctAgent = new Agent(firstProperty.get(), lastProperty.get(), shortProperty.get());
    Agent authorOverhead = new Agent(firstNameOfUser, lastNameOfUser, userNameOfUser);
    Agent reporterOverhead = new Agent(firstProperty.get(), lastProperty.get(), shortProperty.get());
    Sanction sancToInsert = new Sanction();
    
    HBox appMenu;
    BorderPane mainPane = new BorderPane();
    Overhead loggedInUser;
    QueryObjectForOverhead queriesForLoggInUser;
    
    public MainWindow(Overhead loggedInUser, QueryObjectForOverhead queriesObject) {
        this.loggedInUser = loggedInUser;
        this.userNameOfUser = loggedInUser.getShortCut();
        queriesForLoggInUser = queriesObject;
        System.out.println(queriesForLoggInUser.connectionToAccdb);
    }
    
    @Override
    public void start(Stage mainStage) {
        String title = "Verstossliste Monitoring Capita";
        Image icon = new Image(getClass().getResourceAsStream("capitaIcon_32.png"));
        
        // following is the upper application menu.
        appMenu = createMyMenu(mainStage);
        
        mainPane.setTop(appMenu);
        Scene mainScene = new Scene(mainPane, 1000, 800);
        mainScene.getStylesheets().add(
                getClass().getResource("mainWindowStyleSheet.css").toExternalForm());
        
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(icon);
        mainStage.setResizable(false);
        mainStage.setTitle(title);
        mainStage.show();
    }
    
    public HBox createMyMenu(Stage mainStage) {
        HBox appMenu = new HBox();
        appMenu.setPrefHeight(80);
        appMenu.setPrefWidth(1000);
        appMenu.setMaxHeight(80);
        appMenu.setMaxWidth(1000);
        
        Button insertSanction = new Button("Eintrag erfassen");
        insertSanction.setId("insertSanctionButton");
        insertSanction.setPadding(new Insets(10, 10, 10, 10));
        insertSanction.setPrefSize(145, 70);
        appMenu.getChildren().add(insertSanction);
        
        insertSanction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainPane.setCenter(createMySanctionInputCenterPane(mainStage));
            }
        });
        
        Button searchSanction = new Button("Eintrag suchen");
        searchSanction.setId("searchForSanctionButton");
        searchSanction.setPadding(new Insets(10, 10, 10, 10));
        searchSanction.setPrefSize(145, 70);
        appMenu.getChildren().add(searchSanction);
        
        searchSanction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainPane.setCenter(createMySearchFieldCenterPane(mainStage));
            }
        });
        
        Button lookForLogs = new Button("Eigene Logs ansehen");
        lookForLogs.setId("lookForLogsButton");
        lookForLogs.setPadding(new Insets(10, 0, 10, 0));
        lookForLogs.setPrefSize(145, 70);
        appMenu.getChildren().add(lookForLogs);
        
        Button createUser = new Button("Neuer Benutzer");
        createUser.setId("createNewUserButton");
        createUser.setPadding(new Insets(10, 10, 10, 10));
        createUser.setPrefSize(145, 70);
        appMenu.getChildren().add(createUser);
        
        Button adminUser = new Button("Benutzer verwalten");
        adminUser.setId("adminUserButton");
        adminUser.setPadding(new Insets(10, 10, 10, 10));
        adminUser.setPrefSize(145, 70);
        appMenu.getChildren().add(adminUser);
        
        Label showUserName = new Label("Logged In: " + userNameOfUser);
        showUserName.setPadding(new Insets(10, 0, 0, 10));
        appMenu.getChildren().add(showUserName);
        
        appMenu.setSpacing(5);
        
        return appMenu;
    }
    
    public VBox createMySanctionInputCenterPane(Stage mainStage) {
        queriesForLoggInUser.getLoggedInOHFirstName();
        queriesForLoggInUser.getLoggedInOHLastName();
        authorOverhead.setFirstName(queriesForLoggInUser.getFirstName());
        authorOverhead.setLastName(queriesForLoggInUser.getLastName());
        authorOverhead.setShortCut(queriesForLoggInUser.getUserName());
        
        SanctionInput createInputField = new SanctionInput(mainStage, queriesForLoggInUser);
        VBox centerPane = createInputField.createMySanctionInputCenterPane(mainStage);
        
        return centerPane;
    }
    
    public VBox createMySearchFieldCenterPane(Stage mainStage) {
        SanctionSearch searchSanctionField = new SanctionSearch(mainStage, queriesForLoggInUser);
        VBox centerPaneContainer = searchSanctionField.createMySearchField(mainStage);
        
        return centerPaneContainer;
    }
    
    public String[] departments() {
        String[] depArray = {"RBC", "BM", "SU", "CCD", "SCT", "PRC", "PTS"};
        return depArray;
    }
}
