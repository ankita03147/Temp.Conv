package com.company.hellofx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MyMain extends Application
{
    public static void main(String[] args)
    {
        System.out.println("Main");
        launch();
    }

    @Override
    public void init() throws Exception
    {
        System.out.println("INIT");
        super.init();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        System.out.println("START");

        FXMLLoader fxmlLoader = new FXMLLoader(MyMain.class.getResource("app_layout.fxml"));
        VBox rootNode = fxmlLoader.load();

        MenuBar menuBar = createMenu();
        rootNode.getChildren().add(0, menuBar);

        Scene scene = new Scene(rootNode);

        stage.setTitle("Temperature Converter Tool");
        //stage.setResizable(false) --- to make the window size fixed and non-resizable
        stage.setScene(scene);
        stage.show();
    }
    private MenuBar createMenu()
    {
        //file menu
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");

        newMenuItem.setOnAction(actionEvent -> System.out.println("New Menu Item Clicked."));

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem quitMenuItem = new MenuItem("Quit");

        quitMenuItem.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });

        fileMenu.getItems().addAll(newMenuItem, separatorMenuItem, quitMenuItem);

        //help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutApp = new MenuItem("About");

        aboutApp.setOnAction(actionEvent -> aboutApp());

        helpMenu.getItems().addAll(aboutApp);

        //menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }

    private void aboutApp()
    {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("My First Desktop App");
        alertDialog.setHeaderText("Learning JavaFX");
        alertDialog.setContentText("I am just a beginner and soon I will be pro and stat developing awesome apps.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alertDialog.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> clickedButton = alertDialog.showAndWait();

        if(clickedButton.isPresent() && clickedButton.get() == yesButton)
        {
            System.out.println("Yes Button Clicked");
        }
        else
        {
            System.out.println("No Button clicked");
        }
    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("STOP");   //called when app is stopped and is about to shut down
        super.stop();
    }
}