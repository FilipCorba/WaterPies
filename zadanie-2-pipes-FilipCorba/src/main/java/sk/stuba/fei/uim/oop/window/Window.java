package sk.stuba.fei.uim.oop.window;

import sk.stuba.fei.uim.oop.logic.GameLogic;
import sk.stuba.fei.uim.oop.pipes.PointWithStatus;
import sk.stuba.fei.uim.oop.path.RandomPathFinder;
import sk.stuba.fei.uim.oop.path.RandomizeDFS;


import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;


public class Window extends JFrame {

    public static final String RESETLABEL = "Reset";
    public static final String CHECKPATH = "Check path";
    private JPanel buttonPanel;
    private JPanel playingArea;
    private int level;
    private int wins;

    public void setSizeOfPlayingArea(int sizeOfPlayingArea) {
        this.sizeOfPlayingArea = sizeOfPlayingArea;
    }

    public int getWins() {
        return wins;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public JLabel getSizeLabel() {
        return sizeLabel;
    }


    public JPanel getPlayingArea() {
        return playingArea;
    }

    private int sizeOfPlayingArea;

    private JLabel sizeLabel;

    public List<PointWithStatus> getOvrPath() {
        return ovrPath;
    }

    private List<PointWithStatus> ovrPath = new ArrayList<>();

    public int getSizeOfPlayingArea() {
        return sizeOfPlayingArea;
    }

    public void setOvrPath(List<PointWithStatus> ovrPath) {
        this.ovrPath = ovrPath;
    }

    public Window() throws HeadlessException {
        super();
        this.sizeOfPlayingArea = 8;
        setTitle("WaterPipes");

        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        buttonPanel = new JPanel();
        setLevel(1);
        setWins(0);
        GameLogic gameLogic = new GameLogic(this);
        this.addKeyListener(gameLogic);


        JButton buttonCh = new JButton(CHECKPATH);
        JButton buttonR = new JButton(RESETLABEL);
        buttonR.setFocusable(false);
        buttonCh.setFocusable(false);
        buttonR.addActionListener(gameLogic);
        buttonCh.addActionListener(gameLogic);
        JSlider jSlider = new JSlider(JSlider.HORIZONTAL, 8, 14, 8);
        jSlider.setMajorTickSpacing(1);
        jSlider.setPaintLabels(true);
        jSlider.setPaintTicks(true);
        jSlider.setFocusable(false);
        jSlider.setSnapToTicks(true);
        jSlider.addChangeListener(gameLogic);

        buttonPanel.add(jSlider);
        buttonPanel.add(buttonR);
        buttonPanel.add(buttonCh);


        RandomizeDFS randomizeDFS = new RandomizeDFS(this);
        playingArea = new JPanel();
        playingArea.setLayout(new GridLayout(sizeOfPlayingArea, sizeOfPlayingArea));
        RandomPathFinder randomPathFinder = new RandomPathFinder(sizeOfPlayingArea);
        randomPathFinder.findPath(randomizeDFS);

        createPlayground(sizeOfPlayingArea, randomPathFinder.getPath());

        sizeLabel = new JLabel("Playing Area size: " + sizeOfPlayingArea + "x" + sizeOfPlayingArea + " Level " + this.getLevel() + " wins " + getWins());
        buttonPanel.add(sizeLabel);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.NORTH);

        this.getContentPane().add(playingArea, BorderLayout.CENTER);

        this.setVisible(true);
        ;
    }


    public void createPlayground(int number, List<PointWithStatus> path) {
        for (int i = 0; i < number; i++) {
            for (int a = 0; a < number; a++) {
                playingArea.add(new MyPanel(a, i, path, this));

            }
        }

    }


}
