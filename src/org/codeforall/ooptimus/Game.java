package org.codeforall.ooptimus;

public class Game {

    private Grid grid;

    public Game(GridType gridType, int cols, int rows){

        grid = GridFactory.makeGrid(gridType,cols,rows);
        EventHandler eventHandler = new EventHandler(cols, rows);
        eventHandler.initKey(35,35);
    }

    public void init() {

        grid.init();


    }

}
