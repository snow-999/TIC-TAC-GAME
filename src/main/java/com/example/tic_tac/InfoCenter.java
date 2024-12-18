package com.example.tic_tac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class InfoCenter {
    private StackPane pane;
    private Label massge;
    private Button startBtn;
    private Button levelOneBtn;
    private Button levelTwoBtn;
    private Button levelThreeBtn;

    public InfoCenter() {
        pane = new StackPane();
        pane.setMinSize(UiConstant.APP_WIDTH, UiConstant.INFO_HEIGHT);
        pane.setTranslateX((double) UiConstant.APP_WIDTH / 2);
        pane.setTranslateY((double) UiConstant.INFO_HEIGHT / 2);

        massge = new Label("TIC-TAC");
        massge.setMinSize(UiConstant.APP_WIDTH, UiConstant.INFO_HEIGHT);
        massge.setFont(Font.font(24));
        massge.setAlignment(Pos.CENTER);
        massge.setTranslateY(-20);
        pane.getChildren().add(massge);

        startBtn = new Button("Start New Game");
        levelOneBtn = new Button("easy");
        levelTwoBtn = new Button("medium");
        levelThreeBtn = new Button("hard");

        levelOneBtn.setMinSize(100, 30);
        levelOneBtn.setFont(Font.font(10));
        levelOneBtn.setTranslateY(20);
        levelOneBtn.setTranslateX(-150);

        levelTwoBtn.setMinSize(100, 30);
        levelTwoBtn.setFont(Font.font(10));
        levelTwoBtn.setTranslateY(20);
        levelTwoBtn.setTranslateX(0);

        levelThreeBtn.setMinSize(100, 30);
        levelThreeBtn.setFont(Font.font(10));
        levelThreeBtn.setTranslateY(20);
        levelThreeBtn.setTranslateX(150);

        startBtn.setMinSize(100, 30);
        startBtn.setFont(Font.font(10));
        startBtn.setTranslateY(60);

        pane.getChildren().add(levelOneBtn);
        pane.getChildren().add(levelTwoBtn);
        pane.getChildren().add(levelThreeBtn);
        pane.getChildren().add(startBtn);
    }
    public StackPane getPane() {
        return pane;
    }

    public void updateMsg(String msg) {
        this.massge.setText(msg);
    }

    public void showBtn() {
        startBtn.setVisible(true);
        levelOneBtn.setVisible(true);
        levelTwoBtn.setVisible(true);
        levelThreeBtn.setVisible(true);
    }

    public void hideBtn() {
        startBtn.setVisible(false);
        levelOneBtn.setVisible(false);
        levelTwoBtn.setVisible(false);
        levelThreeBtn.setVisible(false);
    }

    public void setStartBtnAction(EventHandler<ActionEvent> onAction) {
        startBtn.setOnAction(onAction);
        levelOneBtn.setOnAction(onAction);
        levelTwoBtn.setOnAction(onAction);
        levelThreeBtn.setOnAction(onAction);
    }
}


