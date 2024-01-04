package org.codeforall.ooptimus;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.*;

import java.util.ArrayList;
import java.util.List;

public class EventHandler implements KeyboardHandler, Grid {

    private static final int PADDING = 10;
    private static final int MIN_SIZE = 50;
    private static final Color CURSOR_COLOR = Color.DARK_GRAY;
    private static final Color[] RECTANGLE_COLORS = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};
    private int colorIndex = 0;

    private int cols, rows, customWidth, customHeight;
    private Rectangle cursorRectangle;
    private List<Rectangle> newRectangles = new ArrayList<>();

    public EventHandler(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void initKey(int customWidth, int customHeight) {
        this.customWidth = customWidth;
        this.customHeight = customHeight;
        createGrid();
        createCursor();
        setupKeyboard();
    }

    private void createGrid() {
        int startX = PADDING;
        int startY = PADDING;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rectangle = new Rectangle(startX + col * customWidth, startY + row * customHeight, customWidth, customHeight);
                rectangle.setColor(RECTANGLE_COLORS[colorIndex]);
                rectangle.draw();
            }
        }
    }

    private void createCursor() {
        cursorRectangle = new Rectangle(PADDING, PADDING, customWidth, customHeight);
        cursorRectangle.setColor(CURSOR_COLOR);
        cursorRectangle.draw();
        cursorRectangle.fill();
    }

    private void setupKeyboard() {
        Keyboard keyboard = new Keyboard(this);
        int[] keys = {KeyboardEvent.KEY_H, KeyboardEvent.KEY_J, KeyboardEvent.KEY_K, KeyboardEvent.KEY_L, KeyboardEvent.KEY_I, KeyboardEvent.KEY_U, KeyboardEvent.KEY_R};
        for (int key : keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            event.setKey(key);
            keyboard.addEventListener(event);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        int moveDistance = customWidth;
        switch (event.getKey()) {
            case KeyboardEvent.KEY_H:
                moveCursor(-moveDistance, 0);
                break;
            case KeyboardEvent.KEY_J:
                moveCursor(0, moveDistance);
                break;
            case KeyboardEvent.KEY_K:
                moveCursor(0, -moveDistance);
                break;
            case KeyboardEvent.KEY_L:
                moveCursor(moveDistance, 0);
                break;
            case KeyboardEvent.KEY_I:
                drawNewRectangle();
                break;
            case KeyboardEvent.KEY_U:
                cleanNewRectangles();
                break;
            case KeyboardEvent.KEY_R:
                changeRectangleColor();
                break;
        }
    }

    private void changeRectangleColor() {
        colorIndex = (colorIndex + 1) % RECTANGLE_COLORS.length;
        createGrid();
    }

    private void moveCursor(int dx, int dy) {
        int newPosX = cursorRectangle.getX() + dx;
        int newPosY = cursorRectangle.getY() + dy;

        if (newPosX >= PADDING && newPosX + cursorRectangle.getWidth() <= PADDING + cols * customWidth &&
                newPosY >= PADDING && newPosY + cursorRectangle.getHeight() <= PADDING + rows * customHeight) {
            cursorRectangle.translate(dx, dy);
        }
    }

    private boolean isValidMove(int dx, int dy) {
        return cursorRectangle.getX() + dx >= PADDING && cursorRectangle.getX() + dx + cursorRectangle.getWidth() <= PADDING + cols * MIN_SIZE &&
                cursorRectangle.getY() + dy >= PADDING && cursorRectangle.getY() + dy + cursorRectangle.getHeight() <= PADDING + rows * MIN_SIZE;
    }

    private void drawNewRectangle() {
        Rectangle newRectangle = new Rectangle(cursorRectangle.getX(), cursorRectangle.getY(), cursorRectangle.getWidth(), cursorRectangle.getHeight());
        newRectangle.setColor(RECTANGLE_COLORS[colorIndex]);
        newRectangle.draw();
        newRectangle.fill();
        newRectangles.add(newRectangle);

        refreshCursor();
    }

    private void refreshCursor() {
        cursorRectangle.delete();
        cursorRectangle.draw();
        cursorRectangle.fill();
    }

    private void cleanNewRectangles() {
        for (Rectangle rect : newRectangles) {
            rect.delete();
        }
        newRectangles.clear();
    }

    @Override
    public void keyReleased(KeyboardEvent event) {}

    @Override
    public void init() {}
}
