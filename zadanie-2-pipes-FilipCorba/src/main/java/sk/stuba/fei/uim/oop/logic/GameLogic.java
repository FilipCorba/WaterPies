package sk.stuba.fei.uim.oop.logic;

import sk.stuba.fei.uim.oop.check.CheckPath;
import sk.stuba.fei.uim.oop.path.RandomPathFinder;
import sk.stuba.fei.uim.oop.path.RandomizeDFS;
import sk.stuba.fei.uim.oop.window.Window;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;

public class GameLogic extends UniversalAdapter {

    private Window window;


    private final String resetLabel = window.RESETLABEL;
    private final String checkPath = window.CHECKPATH;

    public GameLogic(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(checkPath)) {
            CheckPath();

        }
        if (e.getActionCommand().equals(resetLabel)) {
            Restart();
        }


    }


    @Override
    public void stateChanged(ChangeEvent e) {

        window.setSizeOfPlayingArea(((JSlider) e.getSource()).getValue());
        window.setLevel(1);
        Repaint(window.getSizeOfPlayingArea());


    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.CheckPath();
                break;
            case KeyEvent.VK_R:
                this.Restart();
                break;
            case KeyEvent.VK_ESCAPE:
                window.dispose();
                System.exit(0);
                break;
        }
    }

    private void Repaint(int sizeOfPlayingArea) {

            window.getPlayingArea().removeAll();
            window.getPlayingArea().setLayout(new GridLayout(sizeOfPlayingArea, sizeOfPlayingArea));
            RandomPathFinder randomPathFinder = new RandomPathFinder(sizeOfPlayingArea);
            RandomizeDFS randomizeDFS = new RandomizeDFS(window);
            randomPathFinder.findPath(randomizeDFS);
            window.createPlayground(sizeOfPlayingArea, randomPathFinder.getPath());
            window.getSizeLabel().setText("Playing Area size: " + sizeOfPlayingArea + "x" + sizeOfPlayingArea + " Level " + window.getLevel() + " wins " + window.getWins());
            window.getButtonPanel().add(window.getSizeLabel());

            window.invalidate();
            window.validate();
            window.repaint();

    }

    private void CheckPath() {
        CheckPath checkPath = new CheckPath(window.getOvrPath());

        if (checkPath.isLevelManaged()) {
            window.setLevel(window.getLevel() + 1);
            window.setWins(window.getWins() + 1);
            Repaint(window.getSizeOfPlayingArea());

        } else {
            window.repaint();
        }
    }

    private void Restart() {

        window.setLevel(1);
        window.setWins(0);
        Repaint(window.getSizeOfPlayingArea());
    }

}
