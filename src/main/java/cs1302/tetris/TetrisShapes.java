package cs1302.tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** This class creates and determines layout of the {@code TetrisShapes}. */
public class TetrisShapes {

    /** Variables from {@link cs1302.tetris.KeyControl}. */
    private int xmax = KeyControl.XGRID;
    private int ymax = KeyControl.YGRID;
    private int size = KeyControl.SIZE;

    /** These variables represent individual rectangles. */
    Rectangle a = new Rectangle(size - 1, size - 1);
    Rectangle b = new Rectangle(size - 1, size - 1);
    Rectangle c = new Rectangle(size - 1, size - 1);
    Rectangle d = new Rectangle(size - 1, size - 1);

    /** Private variables within this class. */
    private String name;
    private Color color;
    private Pane pane;
    private int form;

    /**
     * Constructs a {@code TetrisShapes} object.
     * @param pane Pane
     * @param name name of shape
     */
    public TetrisShapes(Pane pane, String name) {
        makeShape(pane, name);
        a.setX(a.getX() + 350);
        a.setY(a.getY() + 400);
        b.setX(b.getX() + 350);
        b.setY(b.getY() + 400);
        c.setX(c.getX() + 350);
        c.setY(c.getY() + 400);
        d.setX(d.getX() + 350);
        d.setY(d.getY() + 400);
        setVisual(true);
    } // TetrisShapes

    /**
     * Constructs a {@code TetrisShapes}.
     * The {@code name} of the {@code TetrisShapes} shape is determined by the
     * shape the piece represents (i.e. "j" refers to the shape that looks like a "j",
     * "o" refers to the shape that is a 2x2 square, "i" represents the 4x1 rectangle,
     * etc.). The {@code TetrisShapes} shape is then given a {@code color} based on
     * its {@code name} in the makeShape() method.
     *
     * @param pane Pane
     */
    public TetrisShapes(Pane pane) {
        form = 1;
        double random = Math.random() * 700;
        if (random < 100) {
            makeShape(pane, "s");
        } else if (random < 200) {
            makeShape(pane, "z");
        } else if (random < 300) {
            makeShape(pane, "l");
        } else if (random < 400) {
            makeShape(pane, "j");
        } else if (random < 500) {
            makeShape(pane, "t");
        } else if (random < 600) {
            makeShape(pane, "i");
        } else {
            makeShape(pane, "o");
        } // if
    } // TetrisShapes

    /**
     * Makes the {@code TetrisShapes} shape.
     * @param pane Pane
     * @param name String
     */
    private void makeShape(Pane pane, String name) {
        this.pane = pane;
        this.name = name;
        setColor();
        setPosition();
    } // makeShape

    /** Sets the color of the shapes. */
    private void setColor() {
        // Assigns a color to the shape, depending on the shape.
        if (name == "j") {
            color = Color.LIGHTSKYBLUE;
        } else if (name == "l") {
            color = Color.LIGHTPINK;
        } else if (name == "t") {
            color = Color.LIGHTGREEN;
        } else if (name == "s") {
            color = Color.LIGHTCORAL;
        } else if (name == "z") {
            color = Color.KHAKI;
        } else if (name == "o") {
            color = Color.SLATEGRAY;
        } else if (name == "i") {
            color = Color.MEDIUMPURPLE;
        } // if

        // Sets the individual rectangles to the color of the shape.
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    } // setColor

    /** Sets the position of each rectangle. */
    private void setPosition() {
        form = 0;
        if (name == "s") {
            setS();
        } else if (name == "z") {
            setZ();
        } else if (name == "l") {
            setL();
        } else if (name == "j") {
            setJ();
        } else if (name == "t") {
            setT();
        } else if (name == "i") {
            setI();
        } else if (name == "o") {
            setO();
        } // if
    } // setPosition

    /**
     * Helper method for the setPosition() method.
     * Mostly for checkstyle errors.
     */
    private void setS() {
        a.setX(xmax / 2);
        a.setY(0);
        b.setX(xmax / 2 - size);
        b.setY(0);
        c.setX(b.getX());
        c.setY(b.getY() + size);
        d.setX(c.getX() - size);
        d.setY(c.getY());
    } // setS

    /**
     * Helper method for the setPosition() method.
     * Mostly for checkstyle errors.
     */
    private void setZ() {
        c.setX(xmax / 2 - size);
        c.setY(0);
        d.setX(c.getX() - size);
        d.setY(c.getY());
        b.setX(c.getX());
        b.setY(c.getY() + size);
        a.setX(c.getX() + size);
        a.setY(b.getY());
    } // setZ

    /**
     * Helper method for the setPosition() method.
     * Mostly for checkstyle errors.
     */
    private void setL() {
        b.setX(xmax / 2 - size);
        b.setY(size);
        a.setX(b.getX() - size);
        a.setY(b.getY());
        c.setX(b.getX() + size);
        c.setY(b.getY());
        d.setX(c.getX());
        d.setY(b.getY() - size);
    } // setL

    /**
     * Helper method for the setPosition() method.
     * Mostly for checkstyle errors.
     */
    private void setJ() {
        b.setX(xmax / 2 - size);
        b.setY(0);
        a.setX(b.getX() - size);
        a.setY(b.getY());
        c.setX(b.getX() + size);
        c.setY(b.getY());
        d.setX(c.getX());
        d.setY(c.getY() + size);
    } // setJ

    /**
     * Helper method for the setPosition() method.
     * Mostly for checkstyle errors.
     */
    private void setT() {
        b.setX(xmax / 2 - size);
        b.setY(size);
        a.setX(b.getX() + size);
        a.setY(b.getY());
        c.setX(b.getX() - size);
        c.setY(b.getY());
        d.setX(b.getX());
        d.setY(0);
    } // setT

    /**
     * Helper method for the setPosition() method.
     * Mostly for checkstyle errors.
     */
    private void setI() {
        a.setX(xmax / 2 - 2 * size);
        a.setY(0);
        b.setX(xmax / 2 - size);
        b.setY(0);
        c.setX(xmax / 2);
        c.setY(0);
        d.setX(xmax / 2 + size);
        d.setY(0);
    } // setI

    /**
     * Helper method for the setPosition() method.
     * Mostly for checkstyle errors.
     */
    private void setO() {
        a.setX(xmax / 2 - size);
        a.setY(0);
        b.setX(a.getX());
        b.setY(a.getY() + size);
        c.setX(b.getX() - size);
        c.setY(b.getY());
        d.setX(c.getX());
        d.setY(a.getY());
    } // setO

    /**
     * Adds rectangles to pane if true.
     * @param bo boolean
     */
    public void setVisual(boolean bo) {
        if (bo) {
            pane.getChildren().addAll(a, b, c, d);
        } // if
    } // setVisual

    /** Moves the shape to the right. */
    public void moveRight() {
        a.setX(a.getX() + size);
        b.setX(b.getX() + size);
        c.setX(c.getX() + size);
        d.setX(d.getX() + size);
    } // moveRight

    /** Moves the shape to the left. */
    public void moveLeft() {
        a.setX(a.getX() - size);
        b.setX(b.getX() - size);
        c.setX(c.getX() - size);
        d.setX(d.getX() - size);
    } // moveLeft

    /** Moves the shape down the screen. */
    public void moveDown() {
        a.setY(a.getY() + size);
        b.setY(b.getY() + size);
        c.setY(c.getY() + size);
        d.setY(d.getY() + size);
    } // moveDown

    /** Turns the orientation of the {@code TetrisShapes} shape. */
    public void turnShape() {
        form = (form + 1) % 4;
        switch (name) {
        case "s":
            turnS();
            break;
        case "z":
            turnZ();
            break;
        case "l":
            turnL();
            break;
        case "j":
            turnJ();
            break;
        case "t":
            turnT();
            break;
        case "i":
            turnI();
            break;
        case "o":
            break;
        } // switch
    } // turnShape

    /**
     * Checks if the shape can move on the board.
     * @param name name of shape
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if shape can move; false otherwise
     */
    public boolean canMove(String name, int x, int y) {
        Rectangle r = new Rectangle();
        if (name == "a") {
            r = a;
        } else if (name == "b") {
            r = b;
        } else if (name == "c") {
            r = c;
        } else if (name == "d") {
            r = d;
        } // if
        boolean xBo = false;
        boolean yBo = false;
        if (x >= 0) {
            xBo = r.getX() + x * size <= xmax - size;
        } // if
        if (x < 0) {
            xBo = r.getX() + x * size >= 0;
        } // if
        if (y >= 0) {
            yBo = r.getY() + y * size <= ymax - size;
        } // if
        if (y < 0) {
            yBo = r.getY() + y * size >= 0;
        } // if
        return xBo && yBo &&
            KeyControl.BOARD[(int) r.getX() / size + x][(int) r.getY() / size + y] == 0;
    } // canMove

    /** Removes rectangles from pane. */
    public void remove() {
        pane.getChildren().removeAll(a, b, c, d);
    } // remove

    /**
     * Checks if rectangles are at the top of the board.
     * @return true if yes; false otherwise
     */
    public boolean onTheTop() {
        return a.getY() <= 0 || b.getY() <= 0 || c.getY() <= 0 || d.getY() <= 0;
    } // onTheTop

    /**
     * Returns the name of the {@code TetrisShapes} shape.
     * @return name name of the shape
     */
    public String getName() {
        return name;
    } // getName

    /**
     * Helper method for the turnShape() method.
     * Mostly to avoid check style errors.
     */
    private void turnS() {
        if (form == 1 || form == 3) {
            if (canMove("b", 0, -1) && canMove("b", 1, 0) && canMove("b", 1, 1)) {
                a.setY(b.getY() - size);
                a.setX(b.getX());
                c.setX(b.getX() + size);
                c.setY(b.getY());
                d.setX(b.getX() + size);
                d.setY(b.getY() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 0 || form == 2) {
            if (canMove("b", 1, 0) && canMove("b", 0, 1) && canMove("b", -1, 1)) {
                a.setY(b.getY());
                a.setX(b.getX() + size);
                c.setX(b.getX());
                c.setY(b.getY() + size);
                d.setX(b.getX() - size);
                d.setY(b.getY() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
    } // turnS

    /**
     * Helper method for the turnShape() method.
     * Mostly to avoid check style errors.
     */
    private void turnZ() {
        if (form == 1 || form == 3) {
            if (canMove("b", 0, -1) && canMove("b", -1, 0) && canMove("b", -1, 1)) {
                a.setY(b.getY() - size);
                a.setX(b.getX());
                c.setX(b.getX() - size);
                c.setY(b.getY());
                d.setX(b.getX() - size);
                d.setY(b.getY() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 0 || form == 2) {
            if (canMove("b", 1, 0) && canMove("b", 0, -1) && canMove("b", -1, -1)) {
                a.setX(b.getX() + size);
                a.setY(b.getY());
                c.setX(b.getX());
                c.setY(b.getY() - size);
                d.setX(b.getX() - size);
                d.setY(b.getY() - size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
    } // turnZ

    /**
     * Helper method for the turnShape() method.
     * Mostly to avoid check style errors.
     */
    private void turnL() {
        if (form == 1) {
            if (canMove("b", 0, -1) && canMove("b", -1, -2) && canMove("b", -1, 0)
                && canMove("b", 0, 0) && canMove("b", -1, -1)) {
                sL();
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 2) {
            if (canMove("b", 1, 0) && canMove("b", 2, -1) && canMove("b", 0, -1)
                && canMove("b", 0, 0) && canMove("b", 1, -1)) {
                b.setX(b.getX() + size);
                a.setX(b.getX() + size);
                a.setY(b.getY() - size);
                c.setX(b.getX() - size);
                c.setY(b.getY() - size);
                d.setY(b.getY());
                d.setX(b.getX() - size);
                b.setY(b.getY() - size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 3) {
            if (canMove("b", 0, 1) && canMove("b", 1, 2) && canMove("b", 1, 1)
                && canMove("b", 0, 0) && canMove("b", 1, 1)) {
                b.setY(b.getY() + size);
                a.setX(b.getX() + size);
                a.setY(b.getY() + size);
                c.setX(b.getX() + size);
                c.setY(b.getY() - size);
                d.setX(b.getX());
                d.setY(b.getY() - size);
                b.setX(b.getX() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 0) {
            if (canMove("b", -1, 0) && canMove("b", -2, 1) && canMove("b", 0, 1)
                && canMove("b", 0, 0) && canMove("b", -1, 1)) {
                b.setX(b.getX() - size);
                a.setX(b.getX() - size);
                a.setY(b.getY() + size);
                c.setX(b.getX() + size);
                c.setY(b.getY() + size);
                d.setY(b.getY());
                d.setX(b.getX() + size);
                b.setY(b.getY() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
    } // turnL

    /**
     * Helper method for the turnShape() method.
     * Mostly to avoid check style errors.
     */
    private void turnJ() {
        if (form == 1) {
            if (canMove("b", 0, 1) && canMove("b", 1, 0) && canMove("b", 1, 2)
                && canMove("b", 0, 2) && canMove("b", 1, 1)) {
                sJ();
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 2) {
            if (canMove("b", -1, 0) && canMove("b", 0, 1) && canMove("b", -2, 1)
                && canMove("b", -2, 0) && canMove("b", -1, 1)) {
                b.setX(b.getX() - size);
                a.setX(b.getX() + size);
                a.setY(b.getY() + size);
                c.setX(b.getX() - size);
                c.setY(b.getY() + size);
                d.setX(b.getX() - size);
                d.setY(b.getY());
                b.setY(b.getY() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 3) {
            if (canMove("b", 0, -1) && canMove("b", -1, 0) && canMove("b", -1, -2)
                && canMove("b", 0, -2) && canMove("b", -1, -1)) {
                b.setY(b.getY() - size);
                a.setX(b.getX() - size);
                a.setY(b.getY() + size);
                c.setX(b.getX() - size);
                c.setY(b.getY() - size);
                d.setX(b.getX());
                d.setY(b.getY() - size);
                b.setX(b.getX() - size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 0) {
            if (canMove("b", 1, 0) && canMove("b", 0, -1) && canMove("b", 2, -1)
                && canMove("b", 2, 0) && canMove("b", 1, -1)) {
                b.setX(b.getX() + size);
                a.setX(b.getX() - size);
                a.setY(b.getY() - size);
                c.setX(b.getX() + size);
                c.setY(b.getY() - size);
                d.setX(b.getX() + size);
                d.setY(b.getY());
                b.setY(b.getY() - size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
    } // turnJ

    /**
     * Helper method for the turnShape() method.
     * Mostly to avoid check style errors.
     */
    private void turnT() {
        if (form == 1) {
            if (canMove("b", 0, 1) && canMove("b", 0, -1) && canMove("b", 1, 0)) {
                a.setX(b.getX());
                a.setY(b.getY() + size);
                c.setY(b.getY() - size);
                c.setX(b.getX());
                d.setX(b.getX() + size);
                d.setY(b.getY());
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 2) {
            if (canMove("b", -1, 0) && canMove("b", 1, 0) && canMove("b", 0, 1)) {
                a.setX(b.getX() - size);
                a.setY(b.getY());
                c.setY(b.getY());
                c.setX(b.getX() + size);
                d.setX(b.getX());
                d.setY(b.getY() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 3) {
            if (canMove("b", 0, -1) && canMove("b", 0, 1) && canMove("b", -1, 0)) {
                a.setX(b.getX());
                a.setY(b.getY() - size);
                c.setY(b.getY() + size);
                c.setX(b.getX());
                d.setX(b.getX() - size);
                d.setY(b.getY());
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 0) {
            if (canMove("b", 1, 0) && canMove("b", -1, 0) && canMove("b", 0, -1)) {
                a.setX(b.getX() + size);
                a.setY(b.getY());
                c.setY(b.getY());
                c.setX(b.getX() - size);
                d.setX(b.getX());
                d.setY(b.getY() - size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
    } // turnT

    /**
     * Helper method for the turnShape() method.
     * Mostly to avoid check style errors.
     */
    private void turnI() {
        if (form == 1 || form == 3) {
            if (canMove("c", 0, -2) && canMove("c", 0, -1) && canMove("c", 0, 1)) {
                a.setX(c.getX());
                a.setY(c.getY() - size * 2);
                b.setY(c.getY() - size);
                b.setX(c.getX());
                d.setX(c.getX());
                d.setY(c.getY() + size);
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
        if (form == 0 || form == 2) {
            if (canMove("c", 2, 0) && canMove("c", 1, 0) && canMove("c", -1, 0)) {
                a.setX(c.getX() + size * 2);
                a.setY(c.getY());
                b.setY(c.getY());
                b.setX(c.getX() + size);
                d.setX(c.getX() - size);
                d.setY(c.getY());
            } else {
                form = (form - 1) % 4;
            } // if
        } // if
    } // turnI

    /** Helper method to avoid check style error. */
    private void sL() {
        b.setY(b.getY() - size);
        a.setX(b.getX() - size);
        a.setY(b.getY() - size);
        c.setX(b.getX() - size);
        c.setY(b.getY() + size);
        d.setX(b.getX());
        d.setY(b.getY() + size);
        b.setX(b.getX() - size);
    } // sL

    /** Helper method to avoid check style error. */
    private void sJ() {
        b.setY(b.getY() + size);
        a.setX(b.getX() + size);
        a.setY(b.getY() - size);
        c.setY(b.getY() + size);
        c.setX(b.getX() + size);
        d.setY(b.getY() + size);
        d.setX(b.getX());
        b.setX(b.getX() + size);
    } // sT

} // TetrisShapes
