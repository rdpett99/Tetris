package cs1302.tetris;

import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

/** This class creates a {@code TetrisBoard} for the {@code TetrisGame}. */
public class TetrisBoard extends VBox {

    /** Instance variable for the amount of grid rows. */
    private int gridRows;
    /** Instance variable for the amount of grid columns. */
    private int gridColumns;

    /** This game board will be a GridPane object. */
    GridPane gameBoard;

    /**
     * Constructs a {@code TetrisBoard} object.
     * @param rows number of gridRows
     * @param columns number of gridColumns
     */
    public TetrisBoard(int rows, int columns) {
        this.gridRows = rows;
        this.gridColumns = columns;

        gameBoard = drawBoard();
        this.getChildren().add(gameBoard);
    } // TetrisBoard

    /** Draws the {@code TetrisBoard} object. */
    private GridPane drawBoard() {
        GridPane pane = new GridPane();
        pane.setPrefSize(200, 200);
        pane.setAlignment(Pos.CENTER);
        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridColumns; j++) {
                Rectangle tile = new Rectangle(25, 25);
                tile.setFill(Color.LIGHTSKYBLUE);
                tile.setStroke(Color.BLACK);
                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
                pane.getChildren().add(tile);
            } // for
        } // for
        return pane;
    } // drawBoard

} // TetrisBoard
