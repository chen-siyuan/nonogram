import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Puzzle {

    private final int width, height;
    private final boolean[][] cells;
    private final ArrayList<Integer>[] clues;

    public Puzzle(int[] dimensions) {
        this(dimensions[0], dimensions[1]);
    }

    public Puzzle(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new boolean[width][height];
        clues = new ArrayList[width + height];
        for (int i = 0; i < width; i++) clues[i] = new ArrayList<>((height + 1) / 2);
        for (int i = 0; i < height; i++) clues[width + i] = new ArrayList<>((width + 1) / 2);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, boolean value) {
        if (cells[x][y] == value) return;
        cells[x][y] = value;
        updateClue(true, x);
        updateClue(false, y);
    }

    public ArrayList<Integer> getClue(boolean isColumn, int number) {
        if (isColumn) return clues[number];
        else return clues[width + number];
    }

    private void updateClue(boolean isColumn, int number) {
        if (isColumn) {
            clues[number].clear();
            int count = 0;
            for (int i = 0; i < height; i++)
                if (cells[number][i]) count++;
                else if (count != 0) {
                    clues[number].add(count);
                    count = 0;
                }
            if (count != 0) clues[number].add(count);
        } else {
            clues[width + number].clear();
            int count = 0;
            for (int i = 0; i < width; i++)
                if (cells[i][number]) count++;
                else if (count != 0) {
                    clues[width + number].add(count);
                    count = 0;
                }
            if (count != 0) clues[width + number].add(count);
        }
    }

    public void printClues() {
        for (int i = 0; i < width; i++)
            System.out.printf("C(%d):\t%s\n", i,
                    clues[i].stream().map(Objects::toString).collect(Collectors.joining(" ")));
        for (int i = 0; i < height; i++)
            System.out.printf("R(%d):\t%s\n", i,
                    clues[width + i].stream().map(Objects::toString).collect(Collectors.joining(" ")));
    }

}
