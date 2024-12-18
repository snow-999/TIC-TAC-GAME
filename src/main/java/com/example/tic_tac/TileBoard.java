package com.example.tic_tac;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileBoard {

    private InfoCenter infoCenter;
    private StackPane tileBoardPane;
    private Tile[][] tiles = new Tile[3][3];
    private String playerTurn = "Player";
    private boolean isEndGame = false;
    private Line winningLine;

    public TileBoard(InfoCenter infoCenter) {
        this.infoCenter = infoCenter;
        tileBoardPane = new StackPane();
        tileBoardPane.setMinSize(UiConstant.APP_WIDTH, UiConstant.TILE_BOARD_HEIGHT);
        tileBoardPane.setTranslateX((double) UiConstant.APP_WIDTH / 2);
        tileBoardPane.setTranslateY(((double) UiConstant.APP_WIDTH / 2) + UiConstant.INFO_HEIGHT);


        winningLine = new Line();
        tileBoardPane.getChildren().add(winningLine);
    }

    public void addTiles() {
        for (int row = 0; row < 3; row++) {
            for (int col= 0; col < 3; col++) {
                Tile tile = new Tile();
                tile.getTilePane().setTranslateX((col * 100) - 100);
                tile.getTilePane().setTranslateY((row * 100) - 100);
                tileBoardPane.getChildren().add(tile.getTilePane());
                tiles[row][col] = tile;
            }
        }
    }

    public void startNewGAme() {
        playerTurn = "Player";
        if (isEndGame) {
            for (int row = 0; row < 3; row++) {
                for (int col= 0; col < 3; col++) {
                    tiles[row][col].setTileLabelText("");
                }
            }
        }
        isEndGame = false;
        winningLine.setVisible(false);
    }

    public void changePlayerTurn() {
        if (!isEndGame) {
            if (playerTurn.equals("Player")) {
                playerTurn = "Computer";
            } else {
                playerTurn = "Player";
            }
            infoCenter.updateMsg(playerTurn+ "'s turn");
        }
    }

    public String getPlayerTurn() {
        return playerTurn;
    }

    public void checkForWinner() {
        checkRowsForWinner();
        checkColsForWinner();
        checkTopLeftToRightBottomForWinner();
        checkTopRightToLeftBottomForWinner();
        checkForStalemate();
    }

    private void checkRowsForWinner() {
        for (int row = 0; row < 3; row++) {
            if (tiles[row][0].getTileLabelText().equals(tiles[row][1].getTileLabelText())
                    && tiles[row][0].getTileLabelText().equals(tiles[row][2].getTileLabelText())
                    && !tiles[row][0].getTileLabelText().isEmpty()) {
                String winner = getPlayerTurn().toUpperCase();
                endGame(winner, new WinningTile(tiles[row][0], tiles[row][1], tiles[row][2]));
                return;
            }
        }
    }

    private void checkColsForWinner() {
        if (!isEndGame) {
            for (int col = 0; col < 3; col++) {
                if (tiles[0][col].getTileLabelText().equals(tiles[1][col].getTileLabelText())
                        && tiles[0][col].getTileLabelText().equals(tiles[2][col].getTileLabelText())
                        && !tiles[0][col].getTileLabelText().isEmpty()) {
                    String winner = getPlayerTurn().toUpperCase();
                    endGame(winner, new WinningTile(tiles[0][col], tiles[1][col], tiles[2][col]));
                    return;
                }
            }
        }
    }

    private void checkTopLeftToRightBottomForWinner() {
        if (!isEndGame) {
            if (tiles[0][0].getTileLabelText().equals(tiles[1][1].getTileLabelText())
                    && tiles[0][0].getTileLabelText().equals(tiles[2][2].getTileLabelText())
                    && !tiles[0][0].getTileLabelText().isEmpty()) {
                String winner = getPlayerTurn().toUpperCase();
                endGame(winner, new WinningTile(tiles[0][0], tiles[1][1], tiles[2][2]));
                return;
            }
        }
    }

    private void checkTopRightToLeftBottomForWinner() {
        if (!isEndGame) {
            if (tiles[0][2].getTileLabelText().equals(tiles[1][1].getTileLabelText())
                    && tiles[0][2].getTileLabelText().equals(tiles[2][0].getTileLabelText())
                    && !tiles[0][2].getTileLabelText().isEmpty()) {
                String winner = getPlayerTurn().toUpperCase();
                endGame(winner, new WinningTile(tiles[0][2], tiles[1][1], tiles[2][0]));
                return;
            }
        }
    }

    private void checkForStalemate() {
        if (!isEndGame) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (tiles[row][col].getTileLabelText().isEmpty()) {
                        return;
                    }
                }
            }
            isEndGame = true;
            infoCenter.updateMsg("Stalemate");
            infoCenter.showBtn();
        }
    }

    private void endGame(String winner, WinningTile lines) {
        isEndGame = true;
        makeWinningLine(lines);
        infoCenter.updateMsg(winner + " HAS WON!!");
        infoCenter.showBtn();
    }

    public StackPane getTileBoardPane() {
        return tileBoardPane;
    }

    public void makeWinningLine(WinningTile lines) {
        winningLine.setStartX(lines.start.getTilePane().getTranslateX());
        winningLine.setStartY(lines.start.getTilePane().getTranslateY());
        //
        winningLine.setEndX(lines.end.getTilePane().getTranslateX());
        winningLine.setEndY(lines.end.getTilePane().getTranslateY());
        //
        winningLine.setTranslateX(lines.midle.getTilePane().getTranslateX());
        winningLine.setTranslateY(lines.midle.getTilePane().getTranslateY());
        winningLine.setVisible(true);
    }

    private class WinningTile {
        Tile start;
        Tile midle;
        Tile end;

        public WinningTile(Tile start, Tile midle, Tile end) {
            this.start = start;
            this.midle = midle;
            this.end = end;
        }
    }

        public void playRandom() {
            if (!isEndGame) {
                int randomRow = (int)(Math.random() * 3);
                int randomCol = (int)(Math.random() * 3);
                if (tiles[randomRow][randomCol].getTileLabelText().isEmpty()) {
                    tiles[randomRow][randomCol].setTileLabelText("O");
                } else {
                    playRandom();
                }
            }
        }

    private class Tile {

        private StackPane tilePane;
        private Label tileLabel;
        private char playerChar;

        public Tile() {
            tilePane = new StackPane();
            tilePane.setMinSize(100, 100);

            Rectangle border = new Rectangle();
            border.setWidth(100);
            border.setHeight(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            tilePane.getChildren().add(border);

            tileLabel = new Label("");
            tileLabel.setAlignment(Pos.CENTER);
            tileLabel.setFont(Font.font(24));
            tilePane.getChildren().add(tileLabel);

            tilePane.setOnMouseClicked(event -> {
                if (tileLabel.getText().isEmpty() && !isEndGame) {
                    tileLabel.setText(getPlayerChar());
                    checkForWinner();
                    changePlayerTurn();
                    playRandom();
                    checkForWinner();
                    changePlayerTurn();
                }
            });
        }

        public String getPlayerChar() {
            if (!isEndGame) {
                if (getPlayerTurn().equals("Player")) {
                    playerChar = 'X';
                }
            }
            return String.valueOf(playerChar);
        }

        public StackPane getTilePane() {
            return tilePane;
        }

        public String getTileLabelText() {
            return tileLabel.getText();
        }

        public void setTileLabelText(String text) {
            tileLabel.setText(text);
        }
    }
}
