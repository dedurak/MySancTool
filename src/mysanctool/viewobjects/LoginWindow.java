/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysanctool.viewobjects;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mysanctool.modelobjects.Overhead;
import mysanctool.modelobjects.QueryObjectForOverhead;

/**
 *
 * @author Deniz Durak
 */
public class LoginWindow extends Application {
    
    String typedInUserName;
    String typedInPassWord;
    FileChooser fileChooseAccessdb = new FileChooser();
    String urlToAccessDb = "";
    
    @Override
    public void start(Stage loginStage) {
        String title = "Verstossliste Monitoring Capita";
        Image icon = createTheProgIcon();
        loginStage.setTitle(title);
        
        // set a horizontal and vertical gap
        GridPane loginContainer;
        loginContainer = new GridPane();
        
        BorderPane iContainTheLoginContainer = new BorderPane(loginContainer);
                
        // welcome title above the login fields
        Text welcomeText = new Text("Willkommen");
        welcomeText.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        // set the username label 
        Label userName = new Label("Username: ");
        // add the textfield for userName
        TextField textFieldUserName = new TextField();
        // add pwd label
        Label password = new Label("Password: ");
        // add pwd textfield
        PasswordField passwordField = new PasswordField();
        // add Login Button with of HBox to assign an own layout
        Button loginButton = new Button("Login");
        
        // set action for the button
        loginButton.setOnAction((ActionEvent event) -> {
            // assign the typed in strings username and the password to the equal strings
            typedInUserName = textFieldUserName.getText();
            typedInPassWord = passwordField.getText();
            
            if (checkIfNoValue(urlToAccessDb)) {
                createMissingPathWindow(loginStage, icon);
            }
            // first check if the userName and the password is typed in. otherwise output new window
            else if(checkIfNoValue(typedInUserName) || checkIfNoValue(typedInPassWord)) {
                createMissingUnameOrPwdWindow (loginStage, icon);
            }
            // if password and username are typed in start following
            else {
                Overhead userWhoWantsToLogin = new Overhead(typedInUserName, typedInPassWord, urlToAccessDb);
                QueryObjectForOverhead forTheQueries;
                if (loginIsFailed(urlToAccessDb, userWhoWantsToLogin)) {
                    createLoginFailedWindow(loginStage);
                } else {
                    //userWhoWantsToLogin.login(urlToAccessDb);
                    forTheQueries = userWhoWantsToLogin.getQueryObject();
                    MainWindow startMainWindow = new MainWindow(userWhoWantsToLogin, forTheQueries);
                    startMainWindow.start(new Stage());
                    loginStage.close();
                }
            }
        });
        
        HBox hLoginButton = new HBox(10);
        hLoginButton.setAlignment(Pos.BOTTOM_RIGHT);
        hLoginButton.getChildren().add(loginButton);
        
        Text chooseAccdb = new Text("Pfad zu Access Datenbank eingeben");
        TextField inputForPathToAccdb = new TextField();
        
        Button searchButtonForPathToAccdb = new Button("Suchen");
        searchButtonForPathToAccdb.setOnAction((ActionEvent event) -> {
            File accessFile = fileChooseAccessdb.showOpenDialog(loginStage);
            urlToAccessDb = accessFile.getAbsolutePath();
            inputForPathToAccdb.setText(urlToAccessDb);
        });
        
        Scene loginScene = new Scene(iContainTheLoginContainer, 345, 300);
        loginScene.getStylesheets().add(getClass().getResource("loginStyleSheet.css").toExternalForm());
        
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.setHgap(10);
        loginContainer.setVgap(10);
        loginContainer.setPadding(new Insets(25, 25, 25, 25));
        loginContainer.setBorder(new Border(new BorderStroke(Paint.valueOf("black"),BorderStrokeStyle.SOLID,new CornerRadii(15),BorderWidths.DEFAULT)));
        loginContainer.add(welcomeText, 0, 0, 2, 1);
        loginContainer.add(userName, 0, 1);
        loginContainer.add(password, 0, 2);
        loginContainer.add(textFieldUserName, 1, 1);
        loginContainer.add(passwordField, 1, 2);
        loginContainer.add(hLoginButton, 1, 4);
        loginContainer.add(chooseAccdb, 0, 5, 2, 1);
        loginContainer.add(inputForPathToAccdb, 0, 6, 2, 1);
        loginContainer.add(searchButtonForPathToAccdb, 2, 6);
        
        loginStage.setScene(loginScene);
        loginStage.getIcons().add(icon);  
        loginStage.setResizable(false);
        loginStage.show();
    }
    
    public Image createTheProgIcon() {
        return new Image(getClass().getResourceAsStream("capitaIcon_32.png"));
    }
    
    public boolean checkIfNoValue (String urlToDb) {
        boolean checker;
        checker = urlToDb.equals("");
        return checker;
    }
    
    public void createMissingPathWindow (Stage mainstage, Image icon) {
        String btnText = "Schliessen";
        String msgText = "Pfad zur Access Datenbank fehlt!";
        createPopupWindow(mainstage, btnText, msgText);
    }
    
    public void createMissingUnameOrPwdWindow (Stage mainstage, Image icon) {
        String btnText = "Schliessen";
        String msgText = "Benutzername oder Passwort fehlt!";
        createPopupWindow(mainstage, btnText, msgText);
    }
    
    public boolean loginIsFailed (String urlToDb, Overhead userToLogin) {
        boolean checkIfLoginFailed;
        checkIfLoginFailed = !userToLogin.login(urlToDb);
        System.out.println("loginIsFailed: " + checkIfLoginFailed);
        return checkIfLoginFailed;
    }
    
    public void createLoginFailedWindow (Stage mainStage) {
        String btnText = "Schliessen";
        String msgText = "Login fehlgeschlagen";
        createPopupWindow(mainStage, btnText, msgText);
    }
    
    private void createPopupWindow (Stage mainStage, String buttonText, String msgText) {
        final Stage stageAfterLoginButtonIsClicked = new Stage();
        stageAfterLoginButtonIsClicked.initModality(Modality.APPLICATION_MODAL);
        stageAfterLoginButtonIsClicked.initOwner(mainStage);
        stageAfterLoginButtonIsClicked.getIcons().add(mainStage.getIcons().get(0));
        stageAfterLoginButtonIsClicked.setResizable(false);
        
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
}
