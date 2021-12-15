package cs1302.tetris;

import cs1302.omega.OmegaApp;
import cs1302.tetris.TetrisShapes;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * This class uses shapes from {@link cs1302.tetris.TetrisShapes} and
 * gives functions to those shapes when pressing the arrow keys. This class
 * also has methods to determine if the game is over, whether the user wants
 * to pause or play the game, and other functions for the shapes.
 */
public class KeyControl {

    /** Static variables from the {@link cs1302.tetris.TetrisGame} class. */
    public static final int SIZE = 30;
    public static int XGRID = SIZE * 12;
    public static int YGRID = SIZE * 24;
    public static int[][] BOARD = new int[XGRID / SIZE][YGRID / SIZE];

    /** Private variables we'll manipulate within the class. */
    private Scene scene;
    private Pane pane;
    private TetrisShapes currentSh;
    private TetrisShapes nextSh;
    private TetrisShapes paint;
    private boolean game = true;
    public int top = 0;

    /** Variables for score and lines. */
    public boolean pause = true;
    public int score = 0;
    public int lines = 0;
    public TetrisShapes omegaSh;

    /**
     * This constructor is formed to create a new scene and new
     * {@code TetrisShapes} shapes.
     * @param scene updated Scene
     * @param pane updated Pane
     */
    public KeyControl(Scene scene, Pane pane) {
        this.currentSh = new TetrisShapes(pane);
        currentSh.setVisual(true);
        omegaSh = currentSh;
        this.nextSh = new TetrisShapes(pane);
        this.pane = pane;
        this.scene = scene;
        paint();
        moveOnKeyPressed();
    } // KeyControl

    /** Updates the scene when a key is pressed. */
    public void moveOnKeyPressed() {
        scene.setOnKeyPressed(e -> {
            if (game && !pause) {
                if (e.getCode() == KeyCode.D) {
                    if (currentSh.canMove("a", 1, 0) && currentSh.canMove("b", 1, 0)
                        && currentSh.canMove("c", -1, 0) && currentSh.canMove("d", 1, 0)) {
                        currentSh.moveRight();
                    } // if
                } // if
                if (e.getCode() == KeyCode.A) {
                    if (currentSh.canMove("a", -1, 0) && currentSh.canMove("b", -1, 0)
                        && currentSh.canMove("c", -1, 0) && currentSh.canMove("d", -1, 0)) {
                        currentSh.moveLeft();
                    } // if
                } // if
                if (e.getCode() == KeyCode.S) {
                    fallDown();
                } // if
                if (e.getCode() == KeyCode.W) {
                    currentSh.turnShape();
                } // if
            } // if
        });
    } // moveOnKeyPress

    /**
     * Checks if the current shape can move, and allows the
     * {@code TetrisShapes} shape to fall downward.
     */
    public void fallDown() {
        if (currentSh.canMove("a", 0, 1) && currentSh.canMove("b", 0, 1) &&
            currentSh.canMove("c", 0, 1) && currentSh.canMove("d", 0, 1)) {
            currentSh.moveDown();
            score++;
        } // if
        if (shouldShChange()) {
            setBOARD();
            currentSh = nextSh;
            currentSh.setVisual(true);
            setCurrent(currentSh);
            nextSh = new TetrisShapes(pane);
            paint.remove();
            paint();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    removeRows();
                } // run
            });
        } // if
        OmegaApp.game = isGameOver();
    } // moveDown

    /**
     * Setter method for the current shape.
     * @param currentSh the current shape
     */
    private void setCurrent(TetrisShapes currentSh) {
        this.currentSh = currentSh;
    } // setCurrent

    /**
     * Determines if the shapes should change.
     * @return boolean
     */
    private boolean shouldShChange() {
        return det(currentSh.a) || det(currentSh.b) || det(currentSh.c) || det(currentSh.d);
    } // shouldShChange

    /**
     * Helper method for {@code shouldShChange} method.
     * @param r Rectangle
     * @return boolean
     */
    private boolean det(Rectangle r) {
        return (int) r.getY() / SIZE == 23 ||
            BOARD[(int) r.getX() / SIZE][(int) r.getY() / SIZE + 1] == 1;
    } // det

    /** Sets the board with the rectangles. */
    private void setBOARD() {
        setRect(currentSh.a);
        setRect(currentSh.b);
        setRect(currentSh.c);
        setRect(currentSh.d);
    } // setBOARD

    /**
     * Helper method for {@code setBOARD} method.
     * @param r Rectangle
     */
    private void setRect(Rectangle r) {
        BOARD[(int) r.getX() / SIZE][(int) r.getY() / SIZE] = 1;
    } // setRect

    /** Paints the {@code TetrisShapes} shape. */
    public void paint() {
        paint = new TetrisShapes(pane, nextSh.getName());
    } // paint

    /**
     * Determines if the game is over or not.
     * @return true if game is over; false otherwise
     */
    private boolean isGameOver() {
        if (currentSh.onTheTop()) {
            top++;
        } else {
            top = 0;
        } // if
        return !(top >= 2);
    } // isGameOver

    /** Stops the game. */
    public void stop() {
        game = false;
        pause = true;
    } // stop

    /** Pauses the game. */
    public void pause() {
        pause = !pause;
    } // pause

    /** Clears the game board. */
    public void clearBoard() {
        BOARD = new int[XGRID / SIZE][YGRID / SIZE];
    } // clearBoard

    /** Method for determining when to remove the rows of blocks in the game. */
    private void removeRows() {
        ArrayList<Node> rectList = new ArrayList<>();
        ArrayList<Integer> lineList = new ArrayList<>();
        ArrayList<Integer> moveLines = new ArrayList<>();
        ArrayList<Node> newRectList = new ArrayList<>();
        int num = 0;
        chBoard(lineList, moveLines, num);
        if (lineList.size() > 0) {
            for (Node n : pane.getChildren()) {
                if (n instanceof Rectangle) {
                    rectList.add(n);
                } // if
            } // for
            score += 50 * lineList.size();
            lines += lineList.size();
            while (lineList.size() > 0) {
                for (Node n : rectList) {
                    Rectangle r = (Rectangle) n;
                    if (r.getY() == lineList.get(0) * SIZE && r.getX() < XGRID) {
                        BOARD[(int) r.getX() / SIZE][(int) r.getY() / SIZE] = 0;
                        pane.getChildren().remove(n);
                    } // if
                } // for
                lineList.remove(0);
            } // while
            rectList.clear();
            for (Node n : pane.getChildren()) {
                if (n instanceof Rectangle) {
                    newRectList.add(n);
                } // if
            } // for
            if (moveLines.size() > 0) {
                while (moveLines.size() > 0) {
                    for (Node n : newRectList) {
                        Rectangle r = (Rectangle) n;
                        if (r.getY() < moveLines.get(0) * SIZE &&
                            r.getX() < XGRID) {
                            r.setY(r.getY() + SIZE * moveLines.size());
                        } // if
                    } // for
                    moveLines.remove(0);
                } // while
                BOARD = new int[XGRID / SIZE][YGRID / SIZE];
                for (Node n : pane.getChildren()) {
                    if (n instanceof Rectangle) {
                        rectList.add(n);
                    } // if
                } // for
                for (Node n : rectList) {
                    Rectangle r = (Rectangle) n;
                    if (r.getX() < XGRID && r != nextSh.a && r != nextSh.b &&
                        r != nextSh.c && r != nextSh.d && r != currentSh.a &&
                        r != currentSh.b && r != currentSh.c && r != currentSh.d) {
                        BOARD[(int) r.getX() / SIZE][(int) r.getY() / SIZE] = 1;
                    } // if
                } // for
            } // if
        } // if
    } // removeRows

    /**
     * Helper method for the removeRows() method.
     * Mostly for check style errors.
     * @param lineList an ArrayList
     * @param moveLines an ArrayList
     * @param num int
     */
    private void chBoard(ArrayList<Integer> lineList, ArrayList<Integer> moveLines, int num) {
        for (int i = 0; i < BOARD[0].length; i++) {
            for (int j = 0; j < BOARD.length; j++) {
                if (BOARD[j][i] == 1) {
                    num++;
                } // if
            } // for
            if (num == BOARD.length) {
                lineList.add(i);
            } // if
            num = 0;
        } // for
        for (int i = 0; i < lineList.size(); i++) {
            moveLines.add(lineList.get(lineList.size() - i - 1));
        } // for
    } // chBoard
} // KeyControl
