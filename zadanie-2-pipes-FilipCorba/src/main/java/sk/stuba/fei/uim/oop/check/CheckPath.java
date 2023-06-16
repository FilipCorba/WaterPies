package sk.stuba.fei.uim.oop.check;

import sk.stuba.fei.uim.oop.pipes.PointWithStatus;

import java.util.List;

public class CheckPath {
    private PointWithStatus currentPoint;
    private PointWithStatus nextPoint;
    private PointWithStatus last;
    private PointWithStatus previous;
    private boolean continueOrNot;
    private int currentIndex;

    public boolean isLevelManaged() {
        return levelManaged;
    }

    public void setLevelManaged(boolean levelManaged) {
        this.levelManaged = levelManaged;
    }

    private boolean levelManaged;

    public CheckPath(List<PointWithStatus> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            currentIndex =i;
            currentPoint = path.get(i);
            nextPoint = path.get(i + 1);
            last = path.get(path.size() - 1);
            if (i == 0) {
                if (nextPoint.x == currentPoint.x + 1) {
                    continueOrNot = setColor(2, 2, currentPoint);

                } else if (nextPoint.x == currentPoint.x - 1) {
                    continueOrNot = setColor(0, 0, currentPoint);


                } else if (nextPoint.y == currentPoint.y + 1) {
                    continueOrNot = setColor(3, 3, currentPoint);

                } else {
                    continueOrNot = setColor(1, 1, currentPoint);

                }
            } else {
                previous = path.get(i - 1);

                if (currentPoint.isStraight()) {
                    if (previous.x == currentPoint.x - 1 || previous.x == currentPoint.x + 1) {
                        continueOrNot = setColor(0, 2, currentPoint);
                    } else {
                        continueOrNot = setColor(1, 3, currentPoint);
                    }
                } else {
                    if ((previous.y == currentPoint.y - 1) && (nextPoint.x == currentPoint.x + 1)) {
                        continueOrNot = setColor(1, 1, currentPoint);
                    } else if ((previous.y == currentPoint.y - 1) && (nextPoint.x == currentPoint.x - 1)) {
                        continueOrNot = setColor(0, 0, currentPoint);
                    } else if ((previous.y == currentPoint.y + 1) && (nextPoint.x == currentPoint.x - 1)) {
                        continueOrNot = setColor(3, 3, currentPoint);
                    } else if ((previous.y == currentPoint.y + 1) && (nextPoint.x == currentPoint.x + 1)) {
                        continueOrNot = setColor(2, 2, currentPoint);
                    } else if ((previous.y == currentPoint.y) && (nextPoint.y == currentPoint.y + 1)) {
                        if ((previous.x == currentPoint.x - 1)) {
                            continueOrNot = setColor(3, 3, currentPoint);
                        } else {
                            continueOrNot = setColor(2, 2, currentPoint);
                        }

                    } else if ((previous.y == currentPoint.y) && (nextPoint.y == currentPoint.y - 1)) {
                        if ((previous.x == currentPoint.x - 1)) {
                            continueOrNot = setColor(0, 0, currentPoint);
                        } else {
                            continueOrNot = setColor(1, 1, currentPoint);
                        }
                    }

                }
            }
            if (!continueOrNot) {

                for(int o = currentIndex; o < path.size()-1;o++ ){
                    path.get(o+1).setChecked(false);
                    path.get(o+1).setCorrect(false);
                    path.get(o+1).setNotChecked(true);
                    path.get(o+1).setIncorrect(false);

                }break;
            } else {
                if (i == path.size() - 2) {

                    if (last.x == currentPoint.x - 1 ) {
                        continueOrNot =setColor(2,2,last);
                        if (!continueOrNot) {
                            break;
                        }
                        setLevelManaged(true);
                        System.out.println("Correct position 2");
                    } else if (last.x == currentPoint.x + 1 ) {
                        continueOrNot =setColor(0,0,last);
                        if (!continueOrNot) {
                            break;
                        }
                        setLevelManaged(true);
                        System.out.println("Correct position 0");
                    } else if (last.y == currentPoint.y + 1 && last.getNumTurns() == 1) {
                        continueOrNot =setColor(1,1,last);
                        if (!continueOrNot) {
                            break;
                        }
                        setLevelManaged(true);
                        System.out.println("Correct position 3");
                    } else if (last.y == currentPoint.y - 1 && last.getNumTurns() == 3) {
                        continueOrNot =setColor(3,3,last);
                        if (!continueOrNot) {
                            break;
                        }
                        setLevelManaged(true);
                        System.out.println("Correct position 1");
                    }

                }
            }


        }

    }

    private boolean setColor(int number1, int number2, PointWithStatus currentPoint) {
        if (currentPoint.getNumTurns() == number1 || currentPoint.getNumTurns() == number2) {
            currentPoint.setCorrect(true);
            currentPoint.setNotChecked(false);
            return true;
        } else {
            currentPoint.setIncorrect(true);
            currentPoint.setNotChecked(false);
            return false;
        }
    }
}
