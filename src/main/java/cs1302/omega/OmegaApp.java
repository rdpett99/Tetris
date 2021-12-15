package cs1302.omega;

import cs1302.tetris.*;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    private int xmax = KeyControl.XGRID;
    private int ymax = KeyControl.YGRID;
    public static boolean game;

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        game = true;
        Pane pane = new Pane();
        Scene scene = new Scene(pane, xmax + 300, ymax);
        stage.setResizable(true);
        Line line = new Line(xmax, 0, xmax, ymax);
        Text score = new Text("Score: ");
        score.setStyle("-fx-font: 20 arial;");
        score.setX(xmax + 10);
        score.setY(50);
        Text lines = new Text("Lines: ");
        lines.setStyle("-fx-font: 20 arial;");
        lines.setX(xmax + 10);
        lines.setY(100);
        lines.setFill(Color.GREEN);
        Text next = new Text("Next Shape: ");
        next.setStyle("-fx-font: 20 arial;");
        next.setX(xmax + 10);
        next.setY(360);
        Text rules = new Text("Rules: ");
        rules.setStyle("-fx-font: 20 arial;");
        rules.setX(xmax + 10);
        rules.setY(180);
        Text ruletext = new Text("Use the WADS keys for movement.\nW = turn shape\n" +
                                 "A = move left\nD = move right\nS = move down\n" +
                                 "Press start to begin. You may pause,\nrestart, or quit " +
                                 "at any time.");
        ruletext.setStyle("-fx-font: 15 arial;");
        ruletext.setX(xmax + 10);
        ruletext.setY(210);
        pane.getChildren().addAll(line, score, lines, rules, ruletext, next);
        KeyControl keyControl = new KeyControl(scene, pane);
        createButtons(pane, keyControl, stage);

        stage.setTitle("Tetris Arcade Game");
        stage.setScene(scene); // note
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        BackgroundFill bkg = new BackgroundFill(Paint.valueOf("#F0FFFF"),
                                                new CornerRadii(0), new Insets(0));
        Background bg = new Background(bkg);
        pane.setBackground(bg);
        stage.show();
        createTimer(pane, keyControl, score, lines);
    } // start

    /**
     * Restarts the game and clears the board.
     * @param stage the stage
     * @param key KeyControl object
     */
    private void restart(Stage stage, KeyControl key) {
        key.clearBoard();
        start(stage);
    } // restart

    /**
     * Creates buttons for the scene.
     * @param pane Pane
     * @param keyControl KeyControl object
     * @param stage Stage
     */
    private void createButtons(Pane pane, KeyControl keyControl, Stage stage) {
        Button start = new Button("Pause\\Start");
        start.setPrefSize(150, 50);
        start.setLayoutX(xmax + 10);
        start.setLayoutY(ymax - 80);
        Button quit = new Button("Quit");
        quit.setPrefSize(100, 50);
        quit.setLayoutX(xmax + 180);
        quit.setLayoutY(ymax - 80);
        Button restart = new Button("Restart");
        restart.setPrefSize(100, 50);
        restart.setLayoutX(xmax + 180);
        restart.setLayoutY(ymax - 140);
        pane.getChildren().addAll(start, quit, restart);

        quit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        start.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                keyControl.pause();
            }
        });

        restart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                restart(stage, keyControl);
            }
        });

    } // createButtons

    /**
     * Creates a timer for the game.
     * @param pane Pane
     * @param keyControl KeyControl object
     * @param score score of the game
     * @param lines lines cleared
     */
    private void createTimer(Pane pane, KeyControl keyControl, Text score, Text lines) {
        Timer fallTime = new Timer();
        TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (game && !keyControl.pause) {
                                keyControl.fallDown();
                                score.setText("Score: " + keyControl.score);
                                lines.setText("Lines: " + keyControl.lines);
                            } else if (!game) {
                                keyControl.stop();
                                Text gameover = new Text("GAME OVER");
                                gameover.setStyle("-fx-font: 100 arial;");
                                gameover.setFill(Color.RED);
                                gameover.setX(25);
                                gameover.setY(ymax / 2);
                                pane.getChildren().add(gameover);
                            } // if
                        } // run
                    }); // Runnable
                } // run
            }; // TimerTask
        fallTime.schedule(task, 0, 400);

    } // createTimer

} // OmegaApp
