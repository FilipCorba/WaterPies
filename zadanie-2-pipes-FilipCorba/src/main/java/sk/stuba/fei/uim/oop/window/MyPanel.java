package sk.stuba.fei.uim.oop.window;


import lombok.Setter;
import sk.stuba.fei.uim.oop.pipes.PointWithStatus;
import sk.stuba.fei.uim.oop.pipes.Curved;
import sk.stuba.fei.uim.oop.pipes.End;
import sk.stuba.fei.uim.oop.pipes.Start;
import sk.stuba.fei.uim.oop.pipes.Straight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyPanel extends JPanel {
    @Setter
    private boolean highlight;
    protected PointWithStatus pointWithStatus;


    private int angle;
    private int row;
    private int randomTurnBeginning;

    public int getAngle() {
        return angle;
    }

    private int column;
    private int currentPathXY;
    private PointWithStatus currentPoint;
    private PointWithStatus nextPoint;
    private PointWithStatus last;
    private PointWithStatus first;
    private PointWithStatus previous;

    private List<PointWithStatus> ovrPath = new ArrayList<>();

    public MyPanel(int row, int column, List<PointWithStatus> path, Window window) {
        this.row = row;
        this.column = column;
        this.ovrPath = path;
        System.out.println(path);
        PointWithStatus point = new PointWithStatus(row, column, true, false);
        if (path.contains(point)) {
            for (int i = 0; i < path.size() - 1; i++) {
                currentPathXY = i;
                currentPoint = path.get(i);
                nextPoint = path.get(i + 1);
                last = path.get(path.size() - 1);
                first = path.get(0);


                if (i != 0) {
                    previous = path.get(i - 1);

                    if (currentPoint.x == row && currentPoint.y == column) {

                        if (nextPoint.x == currentPoint.x + 1 || nextPoint.x == currentPoint.x - 1) {

                            if (previous.y == currentPoint.y - 1 || previous.y == currentPoint.y + 1) {
                                currentPoint.setCurved(true);
                            } else {
                                currentPoint.setStraight(true);
                            }
                            currentPoint.setChecked(true);
                            break;


                        } else if (nextPoint.y == currentPoint.y + 1 || nextPoint.y == currentPoint.y - 1) {

                            if (previous.x == currentPoint.x) {
                                currentPoint.setStraight(true);
                            } else {
                                currentPoint.setCurved(true);
                            }
                            currentPoint.setChecked(true);
                            break;


                        }else{
                            if (last.x == row && last.y == column) {
                                last.setChecked(true);

                                break;
                            }
                        }
                        break;

                    }


                }else{
                    if (first.x == row && first.y == column) {

                        first.setChecked(true);
                        break;
                    }

                }

            }
        }
        this.setBackground(Color.black);

        if (path.get(currentPathXY).isChecked()) {
            Random rand = new Random();
            randomTurnBeginning = rand.nextInt(4) + 1;
            path.get(currentPathXY).setNumTurns(randomTurnBeginning);
            angle = 0;
            angle = (angle + (90 * randomTurnBeginning));
            path.get(currentPathXY).checkTurns(path, currentPathXY);
            window.setOvrPath(path);
            path.get(currentPathXY).setChecked(false);
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                path.get(currentPathXY).setIncorrect(false);
                path.get(currentPathXY).setCorrect(false);
                path.get(currentPathXY).setNotChecked(true);
                angle = ((path.get(currentPathXY).getNumTurns() * 90) + 90);
                path.get(currentPathXY).setNumTurns(path.get(currentPathXY).getNumTurns() + 1);
                path.get(currentPathXY).checkTurns(path, currentPathXY);

                window.setOvrPath(path);
                ovrPath = path;

                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                highlight = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                highlight = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean inPath = false;

        for (int i = 0; i < ovrPath.size(); i++) {
            PointWithStatus currentPoint = ovrPath.get(i);
            if (currentPoint.x == row && currentPoint.y == column) {
                inPath = true;
                currentPathXY = i;
            }
        }
        if (highlight) {
            this.setBackground(Color.yellow);
        } else {
            this.setBackground(Color.black);
        }
        if (inPath) {
            if (ovrPath.get(currentPathXY).isCurved()) {
                Curved curved = new Curved(g, this, ovrPath.get(currentPathXY));
            } else if (ovrPath.get(currentPathXY).isStraight()) {
                Straight straight = new Straight(g, this, ovrPath.get(currentPathXY));
            } else if (ovrPath.get(0) == ovrPath.get(currentPathXY)) {
                Start start = new Start(g, this, ovrPath.get(currentPathXY));
            } else if (ovrPath.size() - 1 == currentPathXY) {
                End end = new End(g, this, ovrPath.get(currentPathXY));

            }
        }


    }

}
