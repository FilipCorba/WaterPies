package sk.stuba.fei.uim.oop.pipes;

import sk.stuba.fei.uim.oop.window.MyPanel;

import java.awt.*;

public class Curved {


    public Curved(Graphics g, MyPanel panel, PointWithStatus currentPathXY) {
        Graphics2D g2d = (Graphics2D) g.create();
        int centerX = panel.getWidth() / 2;
        int centerY = panel.getHeight() / 2;
        g2d.rotate(Math.toRadians(panel.getAngle() ), centerX, centerY);
        if(currentPathXY.isCorrect()){
            g2d.setColor(Color.blue);
        }
        else if (currentPathXY.isIncorrect()) {
            g2d.setColor(Color.red);
        }
        else if (currentPathXY.isNotChecked()){
            g2d.setColor(Color.gray);
        }
        g2d.setStroke(new BasicStroke(9));
        g2d.drawLine(0, centerY, centerX, centerY);
        g2d.drawLine(centerX, 0, centerX, centerY);
        g2d.dispose();
    }
}
