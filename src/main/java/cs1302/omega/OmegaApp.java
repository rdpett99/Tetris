package cs1302.omega;

import cs1302.game.DemoGame;
import cs1302.tetris.TetrisGame;
import cs1302.tetris.TetrisBoard;
import cs1302.tetris.TetrisBanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        // some labels to display information
        Label notice = new Label("Modify the starter code to suit your needs.");
        Label instructions
            = new Label("Move left/right with arrow keys; click rectangle to teleport.");

        // demo game provided with the starter code
        TetrisGame game = new TetrisGame(640, 240);
        TetrisBoard board = new TetrisBoard(15, 10);
        TetrisBanner banner = new TetrisBanner();

        // setup scene
        BorderPane pane = new BorderPane();
        pane.setTop(banner);
        pane.setCenter(board);
        pane.setLeft(null);
        pane.setRight(null);
        //VBox root = new VBox(board);
        Scene scene = new Scene(pane);

           // setup stage
        stage.setTitle("Tetris Arcade Game");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

        // play the game
        game.play();

    } // start

} // OmegaApp
