import java.io.*;
import java.util.ArrayList;

public class PuzzleIO {

    public static Puzzle readPuzzle(File file) {
        FastReader fastReader = new FastReader(file);
        int height = fastReader.nextInt();
        int width = fastReader.nextInt();
        Puzzle puzzle = new Puzzle(width, height);
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++) puzzle.setCell(i, j, fastReader.nextInt() == 1);
        return puzzle;
    }

    public static void writePuzzle(File file, Puzzle puzzle) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            int width = puzzle.getWidth();
            int height = puzzle.getHeight();
            printWriter.println(String.format("%d %d", height, width));
            ArrayList<String> row;
            for (int j = 0; j < height; j++) {
                row = new ArrayList<>(width);
                for (int i = 0; i < width; i++) row.add(puzzle.getCell(i, j) ? "1" : "0");
                printWriter.println(String.join(" ", row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
