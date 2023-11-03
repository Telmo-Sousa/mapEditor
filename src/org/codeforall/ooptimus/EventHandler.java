package org.codeforall.ooptimus;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.*;

import java.util.ArrayList;
import java.util.List;

public class EventHandler implements KeyboardHandler, Grid {

    private int cols;
    private int rows;
    private int minCols = 50;
    private int minRows = 50;
    private Rectangle miniRectangle;
    private Rectangle cursorRectangle;
    public static int PADDING = 10;
    private int customMiniWidth;
    private int customMiniHeight;
    private List<Rectangle> newRectangles = new ArrayList<>();

    public EventHandler(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void initKey(int customMiniWidth, int customMiniHeight) {
        this.customMiniWidth = customMiniWidth;
        this.customMiniHeight = customMiniHeight;
        int currentWidth = cols * minCols;
        int currentHeight = rows * minRows;
        int startX = PADDING;
        int startY = PADDING;

        while (currentWidth > 0 && currentHeight > 0) {
            miniRectangle = new Rectangle(startX, startY, customMiniWidth, customMiniHeight); // this let's me use custom dimensions
            miniRectangle.setColor(Color.BLACK);
            miniRectangle.draw();

            currentWidth -= customMiniWidth;

            if (currentWidth <= 0) {
                currentWidth = cols * minCols;
                currentHeight -= customMiniHeight;
                startX = PADDING;
                startY += customMiniHeight;
            } else {
                startX += customMiniWidth;
            }
        }

    cursorRectangle = new Rectangle(PADDING, PADDING, customMiniWidth, customMiniHeight);
        cursorRectangle.setColor(Color.DARK_GRAY);
        cursorRectangle.draw();
        cursorRectangle.fill();

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent h = new KeyboardEvent();
        h.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        h.setKey(KeyboardEvent.KEY_H);
        keyboard.addEventListener(h);

        KeyboardEvent j = new KeyboardEvent();
        j.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        j.setKey(KeyboardEvent.KEY_J);
        keyboard.addEventListener(j);

        KeyboardEvent k = new KeyboardEvent();
        k.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.setKey(KeyboardEvent.KEY_K);
        keyboard.addEventListener(k);

        KeyboardEvent l = new KeyboardEvent();
        l.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        l.setKey(KeyboardEvent.KEY_L);
        keyboard.addEventListener(l);

        KeyboardEvent i = new KeyboardEvent();
        i.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        i.setKey(KeyboardEvent.KEY_I);
        keyboard.addEventListener(i);

        KeyboardEvent u = new KeyboardEvent();
        u.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        u.setKey(KeyboardEvent.KEY_U);
        keyboard.addEventListener(u);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        int moveDistance = customMiniWidth;

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_H:
                if (cursorRectangle.getX() > PADDING) {
                    cursorRectangle.translate(-moveDistance, 0);
                }
                break;

            case KeyboardEvent.KEY_J:
                if (cursorRectangle.getY() + cursorRectangle.getHeight() < PADDING + rows * minRows) {
                    cursorRectangle.translate(0, moveDistance);
                }
                break;

            case KeyboardEvent.KEY_K:
                if (cursorRectangle.getY() > PADDING) {
                    cursorRectangle.translate(0, -moveDistance);
                }
                break;

            case KeyboardEvent.KEY_L:
                if (cursorRectangle.getX() + cursorRectangle.getWidth() < PADDING + cols * minCols) {
                    cursorRectangle.translate(moveDistance, 0);
                }
                break;

            case KeyboardEvent.KEY_I:
                Rectangle newRectangle = new Rectangle(cursorRectangle.getX(), cursorRectangle.getY(), cursorRectangle.getWidth(), cursorRectangle.getHeight());
                newRectangle.setColor(Color.BLACK);
                newRectangle.draw();
                newRectangle.fill();
                newRectangles.add(newRectangle);
                break;

            case KeyboardEvent.KEY_U:
                cleanNewRectangles();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private void cleanNewRectangles() {
        for (Rectangle rect : newRectangles) {
            rect.delete();
        }
        newRectangles.clear();
    }

    @Override
    public void init() {

    }

}
