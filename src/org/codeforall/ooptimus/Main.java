package org.codeforall.ooptimus;

public class Main {
    public static void main(String[] args) {

        Game g = new Game(GridType.SIMPLE_GFX,30,15);
        System.out.println("COMMANDS: \n h left \n j down \n k up \n l right \n i paint \n u clean all");
        g.init();

    }
}