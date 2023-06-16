package sk.stuba.fei.uim.oop.path;


import sk.stuba.fei.uim.oop.window.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizeDFS {

    private int randomStartRow;
    private int randomStartCol;
    private int randomEndRow;
    private int randomEndCol;

    private List<Point> startEnd = new ArrayList<>();


    public List<Point> getStartEnd() {
        return startEnd;
    }
    public RandomizeDFS(Window window) {

        Random rand = new Random();
        randomStartRow = rand.nextInt(window.getSizeOfPlayingArea());
        System.out.println("playing area " + window.getSizeOfPlayingArea());
        System.out.println(randomStartRow);
        System.out.println(randomStartCol);
        if (randomStartRow == 0 || randomStartRow == window.getSizeOfPlayingArea() - 1) {
            randomStartCol = rand.nextInt(window.getSizeOfPlayingArea());
        } else {
            int randomNumber = rand.nextInt(2);
            randomStartCol = (randomNumber == 0) ? 0 : window.getSizeOfPlayingArea() - 1;
        }
        Point startXY = new Point( randomStartRow,randomStartCol);

        startEnd.add(startXY);
        if(startXY.x == 0 && startXY.y != 0 && startXY.y != window.getSizeOfPlayingArea()-1) {
            randomEndRow =  window.getSizeOfPlayingArea()-1 ;

        } else if (startXY.x == window.getSizeOfPlayingArea()-1 && startXY.y != 0 && startXY.y != window.getSizeOfPlayingArea()-1) {
            randomEndRow =  0 ;
        } else{
            randomEndRow = rand.nextInt(window.getSizeOfPlayingArea());
        }
        if((startXY.x == 0 && startXY.y == 0)&& randomEndRow != window.getSizeOfPlayingArea()-1){
            randomEndCol = window.getSizeOfPlayingArea()-1;
        }

        else if((startXY.x == window.getSizeOfPlayingArea()-1 && startXY.y == window.getSizeOfPlayingArea()-1)&& randomEndRow != window.getSizeOfPlayingArea()-1){
            randomEndCol = 0;
        }else{
            if(startXY.y == 0){
                randomEndCol = window.getSizeOfPlayingArea()-1;
            }
            else{
                randomEndCol = 0;
            }
        }


        Point endXY = new Point(randomEndRow, randomEndCol);
        startEnd.add(endXY);

    }


}