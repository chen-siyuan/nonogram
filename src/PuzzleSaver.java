import javax.swing.*;
import java.awt.*;

public class PuzzleSaver extends JFileChooser {

    public static final int SUCCESS = 0;
    public static final int ERROR_EMPTY_PUZZLE = -1;
    public static final int ERROR_EMPTY_FILE = -2;

    private final Puzzle puzzle;

    public PuzzleSaver(String path, Puzzle puzzle) {
        super(path);
        this.puzzle = puzzle;
    }

    public int savePuzzle(Component component) {
        if (puzzle == null) return ERROR_EMPTY_PUZZLE;
        switch (showSaveDialog(component)) {
            case JFileChooser.APPROVE_OPTION:
                PuzzleIO.writePuzzle(getSelectedFile(), puzzle);
                break;
            case JFileChooser.CANCEL_OPTION:
                return ERROR_EMPTY_FILE;
            case JFileChooser.ERROR_OPTION:
            default:
                System.out.println("[PS.savePuzzle()] Unknown code.");
                System.exit(0);
                break;
        }
        return SUCCESS;
    }

}
