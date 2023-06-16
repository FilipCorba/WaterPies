package sk.stuba.fei.uim.oop.pipes;

import sk.stuba.fei.uim.oop.window.MyPanel;

import javax.swing.*;
import java.awt.*;

public class Start {


    public Start(Graphics g, MyPanel panel, PointWithStatus currentPathXY) {




        Graphics2D g2d = (Graphics2D) g.create();
        int centerX = panel.getWidth() / 2;
        int centerY = panel.getHeight() / 2;
        g2d.rotate(Math.toRadians(panel.getAngle()), centerX, centerY);
        if (currentPathXY.isCorrect()) {
            panel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
            g2d.setColor(Color.green);

        } else if (currentPathXY.isIncorrect()) {
            g2d.setColor(Color.green);
            panel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        } else if (currentPathXY.isNotChecked()) {
            panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            g2d.setColor(Color.green);

        }
        g2d.setStroke(new BasicStroke(9));
        g2d.drawLine(0, centerY, centerX, centerY);
        g2d.dispose();

    }

}
