package com.example.tic_tac;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacApplication extends Application {

    private InfoCenter infoCenter;
    private TileBoard tileBoard;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UiConstant.APP_WIDTH, UiConstant.APP_HEIGHT);
        initLayout(root);
        stage.setScene(scene);
        stage.show();
    }

    private void initLayout(BorderPane root) {
        initInfoCenter(root);
        initTitleBoard(root);
    }

    private void initTitleBoard(BorderPane root) {
        tileBoard = new TileBoard(infoCenter);
        root.getChildren().add(tileBoard.getTileBoardPane());
    }

    private void initInfoCenter(BorderPane root) {
        infoCenter = new InfoCenter();
        infoCenter.setBtnAction(startGame());
        root.getChildren().add(infoCenter.getPane());
    }

    private EventHandler<ActionEvent> startGame() {
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                tileBoard.startNewGAme();
                tileBoard.addTiles();
                infoCenter.hideBtn();
                infoCenter.updateMsg("Player's Turn");
            }
        };
    }

    public static void main(String[] args) {
        launch();
    }
}