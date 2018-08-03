/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Deniz Durak
 */
public class MySancTool extends Application {
    
    String typedInUserName;
    String typedInPassWord;
    
    @Override
    public void start(Stage loginStage) {
        
        /* // description //
         * The title of the start window.
         * The start window is the login container. 
         * For the knowledge of the following code remind after successful login the forwarding to the main window.
         * In case of no authorization there will be popup window that alerts the user.
         */
        String title = "Sanction Tool Monitoring Capita";
        Image icon = new Image(getClass().getResourceAsStream("capitaIcon_32.png"));

        loginStage.setTitle(title);
        
        // create new GridPane for the login items
        GridPane loginContainer = new GridPane();
        BorderPane iContainTheLoginContainer = new BorderPane(loginContainer);
        
        
        /**
         * Following until my MARK point is to describe the borders of the Borderpane.
         * With this technique we ensure that that the login field is highlighted within the whole stage. 
         * This is necessary. Without it you may have only one scene within the container which maximizes the login field to the current screen size.
         */
        VBox header = new VBox();
        VBox rightSide = new VBox();
        VBox leftSide = new VBox();
        VBox bottom = new VBox();


        header.setPrefHeight(25);
        header.setId("cssHeader");

        rightSide.setPrefHeight(25);
        rightSide.setId("cssRightSide");

        leftSide.setPrefHeight(25);
        leftSide.setId("cssLeftSide");

        bottom.setPrefHeight(25);
        bottom.setId("cssBottom");

        
        iContainTheLoginContainer.setTop(header);
        iContainTheLoginContainer.setRight(rightSide);
        iContainTheLoginContainer.setLeft(leftSide);
        iContainTheLoginContainer.setBottom(bottom);


        //maybe i need this one?
         /**
        loginContainer.setPrefSize(200, 200);
        loginContainer.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        */
        
        // set position
        //loginContainer.setAlignment(Pos.CENTER);
        
        // set a horizontal and vertical gap
        loginContainer
                .setHgap(10);
        loginContainer
                .setVgap(10);
        loginContainer
                .setPadding(new Insets(25, 25, 25, 25));
        loginContainer
                .setBorder(new Border(
                        new BorderStroke(
                                Paint.valueOf("black"), 
                                BorderStrokeStyle.SOLID, 
                                new CornerRadii(5),
                                BorderWidths.DEFAULT)));
        
        Scene loginScene = new Scene(iContainTheLoginContainer, 325, 245);
        
        // set resource of css file in use for the starting window
        loginStage.setScene(loginScene);
        loginScene
                .getStylesheets()
                .add(getClass()
                        .getResource("loginStyleSheet.css")
                        .toExternalForm());
        
        // welcome title above the login fields
        Text welcomeText = new Text("Willkommen");
        welcomeText.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        loginContainer.add(welcomeText, 0, 0, 2, 1);
        
        // set the username label 
        Label userName = new Label("Username: ");
        loginContainer
                .add(userName, 0, 1);
        
        // add the textfield for userName
        TextField textFieldUserName = new TextField();
        loginContainer
                .add(textFieldUserName, 1, 1);
        
        // add pwd label
        Label password = new Label("Password: ");
        loginContainer
                .add(password, 0, 2);
        
        // add pwd textfield
        PasswordField passwordField = new PasswordField();
        loginContainer
                .add(passwordField, 1, 2);
        
        // add Login Button with of HBox to assign an own layout
        Button loginButton = new Button("Login");
        
        
        
        // set action for the button
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // assign the typed in strings username and the password to the equal strings
                typedInUserName = textFieldUserName.getText();
                typedInPassWord = passwordField.getText();
                
                // create a new stage for the popup window which is just deved for test cases ;)
                final Stage stageAfterLoginButtonIsClicked = new Stage();
                stageAfterLoginButtonIsClicked
                        .initModality(Modality.APPLICATION_MODAL);
                stageAfterLoginButtonIsClicked
                        .initOwner(loginStage);
                stageAfterLoginButtonIsClicked
                        .getIcons().add(icon);
                stageAfterLoginButtonIsClicked.setResizable(false);
                
                GridPane displayValues = new GridPane();
                displayValues
                        .setAlignment(Pos.CENTER);
                displayValues
                        .setVgap(10);
                displayValues
                        .setHgap(10);
                displayValues
                        .setPadding(new Insets(5, 5, 20, 5));
                
                
                if(typedInUserName.equals("") || typedInPassWord.equals("")) {
                    displayValues.add(new Text("Benutzername oder Passwort eingeben!"), 0, 0, 1, 2);
                } else {
                    displayValues.add(new Text(typedInUserName + " is logged in!"), 0, 0, 1, 2);
                }
                
                Button closeButton = new Button();
                HBox boxWithTheCloseButton = new HBox();
                boxWithTheCloseButton.setAlignment(Pos.BOTTOM_CENTER);
                
                closeButton.setText("Schliessen");
                closeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stageAfterLoginButtonIsClicked.close();
                    }
                });
                
                boxWithTheCloseButton.getChildren().add(closeButton);
                
                displayValues.add(boxWithTheCloseButton, 0, 1, 1, 4);
                
                Scene sceneForThatStage = new Scene(displayValues, 250,100);
                stageAfterLoginButtonIsClicked.setScene(sceneForThatStage);
                
                stageAfterLoginButtonIsClicked.show();
            }
        });
        
        
        
        
        
        HBox hLoginButton = new HBox(10);
        hLoginButton
                .setAlignment(Pos.BOTTOM_RIGHT);
        
        hLoginButton
                .getChildren()
                .add(loginButton);
        
        loginContainer.add(hLoginButton, 1, 4);
        
        
        loginStage
                .getIcons()
                .add(icon);

        loginStage.setResizable(false);
        
        loginStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
