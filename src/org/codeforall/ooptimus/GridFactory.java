package org.codeforall.ooptimus;

public class GridFactory {
    public static Grid makeGrid(GridType gridType, int cols, int rows) {

        switch (gridType) {
            case SIMPLE_GFX:
                return new EventHandler(cols, rows);
            default:
                return new EventHandler(cols, rows);
        }
    }
}
