package sk.stuba.fei.uim.oop.pipes;

import java.awt.Point;
import java.util.List;


public class PointWithStatus extends Point {
    private boolean straight;
    private boolean curved;
    private int numTurns;
    private boolean correct;
    private boolean incorrect;
    private boolean notChecked;
    private boolean checked;

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isIncorrect() {
        return incorrect;
    }

    public void setIncorrect(boolean incorrect) {
        this.incorrect = incorrect;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isNotChecked() {
        return notChecked;
    }

    public void setNotChecked(boolean notChecked) {
        this.notChecked = notChecked;
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    public void setStraight(boolean straight) {
        this.straight = straight;
    }

    public void setCurved(boolean curved) {
        this.curved = curved;
    }

    public boolean isStraight() {
        return this.straight;
    }

    public boolean isCurved() {
        return this.curved;
    }

    public PointWithStatus(int x, int y, boolean straight, boolean curved) {
        super(x, y);
        this.straight = straight;
        this.curved = curved;
        this.numTurns = 0;
        this.correct = false;
        this.incorrect = false;
        this.notChecked = true;
        this.checked = false;

    }

    public void checkTurns(List<PointWithStatus> path, int currentXY) {
        if (path.get(currentXY).getNumTurns() >= 4) {
            path.get(currentXY).setNumTurns(getNumTurns() - 4);
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PointWithStatus)) {
            return false;
        }
        PointWithStatus other = (PointWithStatus) obj;
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

}