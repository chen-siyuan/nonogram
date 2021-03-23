import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorPanel extends JPanel implements ActionListener {

    private final Frame frame;
    private final ControlPanel controlPanel;
    private final PuzzlePanel puzzlePanel;

    public EditorPanel(Frame frame) {
        this.frame = frame;
        controlPanel = new ControlPanel(this);
        puzzlePanel = new PuzzlePanel();
        initUI();
    }

    private void initUI() {
        setBackground(Colors.EDITOR_PANEL);
        setLayout(new MigLayout(new LC().fill()));
        add(controlPanel, new CC().growY());
        add(puzzlePanel, new CC().width("300:600:").height("300:600:").grow());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ControlPanel.NEW:
                actionNewButton();
                break;
            case ControlPanel.OPEN:
                actionOpenButton();
                break;
            case ControlPanel.SAVE:
                actionSaveButton();
                break;
            case ControlPanel.BACK:
                actionBackButton();
                break;
            default:
                System.out.println("[EP.actionPerformed()] Unknown code: " + e.getActionCommand());
                System.exit(0);
                break;
        }
    }

    private void actionNewButton() {
        int[] dimensions = new DimensionsDialog(frame).getDimensions();
        switch (dimensions[0]) {
            case DimensionsDialog.CODE_CANCEL:
                puzzlePanel.setMessage("[creation cancelled]");
                break;
            case DimensionsDialog.CODE_INVALID:
                puzzlePanel.setMessage("[invalid dimensions]");
                break;
            default:
                puzzlePanel.setPuzzle(new Puzzle(dimensions));
                break;
        }
    }

    private void actionOpenButton() {
        Puzzle puzzle = new PuzzleOpener("puzzles/").getPuzzle(this);
        if (puzzle == null) puzzlePanel.setMessage("[no file selected]");
        else puzzlePanel.setPuzzle(puzzle);
    }

    private void actionSaveButton() {
        switch (new PuzzleSaver("puzzles/", puzzlePanel.getPuzzle()).savePuzzle(this)) {
            case PuzzleSaver.SUCCESS:
                puzzlePanel.setMessage("[puzzle saved]");
                break;
            case PuzzleSaver.ERROR_EMPTY_PUZZLE:
                puzzlePanel.setMessage("[unable to save null puzzle]");
                break;
            case PuzzleSaver.ERROR_EMPTY_FILE:
                puzzlePanel.setMessage("[unable to save null file]");
                break;
            default:
                System.out.println("[EP.actionSaveButton()] Unknown code.");
                System.exit(0);
                break;
        }
    }

    private void actionBackButton() {
        Puzzle puzzle = puzzlePanel.getPuzzle();
        if (puzzle != null) for (int i = 0; i < puzzle.getWidth() + puzzle.getHeight(); i++)
            puzzlePanel.updatePuzzle(
                    (int) Math.floor(Math.random() * puzzle.getWidth()),
                    (int) Math.floor(Math.random() * puzzle.getHeight()));
    }

}
