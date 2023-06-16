package sk.stuba.fei.uim.oop.path;

import sk.stuba.fei.uim.oop.pipes.PointWithStatus;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPathFinder {


    private  int size;
    private final static int[][] NEIGHBORS = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    private boolean[][] visited ;
    private Random random = new Random();
    private List<PointWithStatus> path = new ArrayList<>();

    public RandomPathFinder(int sizeOfPlayingArea) {
        size = sizeOfPlayingArea;
        visited = new boolean[sizeOfPlayingArea][sizeOfPlayingArea];
    }


    public List<PointWithStatus> getPath() {
        return path;
    }

    public List<PointWithStatus> findPath(RandomizeDFS randomizeDFS) {
        PointWithStatus current = new PointWithStatus(randomizeDFS.getStartEnd().get(0).x, randomizeDFS.getStartEnd().get(0).y, false, false);
        visited[randomizeDFS.getStartEnd().get(0).x][randomizeDFS.getStartEnd().get(0).y] = true;
        path.add(current);

        while (!current.equals(new PointWithStatus(randomizeDFS.getStartEnd().get(1).x, randomizeDFS.getStartEnd().get(1).y, false, false))) {
            List<PointWithStatus> neighbors = getUnvisitedNeighbors(current);
            if (!neighbors.isEmpty()) {
                PointWithStatus next = neighbors.get(random.nextInt(neighbors.size()));
                path.add(next);
                visited[next.x][next.y] = true;
                current = next;
            } else {
                path.remove(path.size() - 1);
                if (path.isEmpty()) {

                    return null;
                }
                current = path.get(path.size() - 1);
            }
        }

        return path;
    }

    private List<PointWithStatus> getUnvisitedNeighbors(Point p) {
        List<PointWithStatus> neighbors = new ArrayList<>();
        for (Directions direction : Directions.values()) {
            int x = p.x + direction.getX();
            int y = p.y + direction.getY();
            if (x >= 0 && x < size && y >= 0 && y < size && !visited[x][y]) {
                neighbors.add(new PointWithStatus(x, y,false, false));
            }
        }
        return neighbors;
    }
}
