package cs1302.tetris;

import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class creates a {@code TetrisBanner} object for
 * the {@code TetrisGame}.
 */
public class TetrisBanner extends HBox {

    /** Constucts a {@code TetrisBanner} object. */
    public TetrisBanner() {
        super();

        Image bannerImage = new Image("file:resources/TetrisBanner.jpeg");
        ImageView banner = new ImageView(bannerImage);
        banner.setPreserveRatio(true);
        banner.setFitWidth(640);

        this.getChildren().add(banner);
    } // TetrisBanner

} // TetrisBanner
