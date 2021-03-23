import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzlePanel extends JPanel implements ComponentListener {

    private Puzzle puzzle;
    private String message;
    private JLabel[][] cells;
    private JTextArea[] clues;

    public PuzzlePanel() {
        message = "[start by new/open a puzzle]";
        initUI();
    }

    private void initUI() {
        setBackground(Colors.PUZZLE_PANEL);
        addComponentListener(this);
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        message = "[start by new/open a puzzle]";
        cells = null;
        clues = null;
        display();
    }

    public void setMessage(String message) {
        puzzle = null;
        this.message = message;
        cells = null;
        clues = null;
        display();
    }

    private void display() {
        removeAll();
        if (puzzle != null) displayPuzzle();
        else displayMessage();
        revalidate();
        repaint();
    }

    private void displayPuzzle() {
        int width = puzzle.getWidth();
        int height = puzzle.getHeight();
        cells = new JLabel[width][height];
        clues = new JTextArea[width + height];
        double cellSize = calculateCellSize(width, height) - 1;

        setLayout(new MigLayout("", "5%[]1px[]", "5%[]1px[]"));
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                add(cells[i][j] = createCell(puzzle.getCell(i, j)), new CC().cell(i, j)
                        .width(String.format("%f:%f:%f", cellSize, cellSize, cellSize))
                        .height(String.format("%f:%f:%f", cellSize, cellSize, cellSize)));

        for (int i = 0; i < width; i++)
            add(clues[i] = createClue(true, puzzle.getClue(true, i)), new CC().cell(i, height));
        for (int i = 0; i < height; i++)
            add(clues[width + i] = createClue(false, puzzle.getClue(false, i)), new CC().cell(width, i));
    }

    private double calculateCellSize(int width, int height) {
        return Math.min(getSize().getWidth() * 0.8 / width, getSize().getHeight() * 0.8 / height);
    }

    private JLabel createCell(boolean isFilled) {
        JLabel cell = new JLabel();
        cell.setBackground(isFilled ? Colors.FILLED_CELL : Colors.EMPTY_CELL);
        cell.setOpaque(true);
        return cell;
    }

    private JTextArea createClue(boolean isColumn, List<Integer> content) {
        JTextArea clue = new JTextArea(content.stream().map(Object::toString).collect(Collectors.joining(isColumn ? "\n" : " ")));
        clue.setForeground(Colors.CLUE);
        clue.setFont(Fonts.CLUE);
        clue.setBackground(Colors.PUZZLE_PANEL);
        clue.setOpaque(true);
        return clue;
    }

    private void displayMessage() {
        setLayout(new MigLayout(new LC().fill()));
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(Colors.PANEL_MESSAGE_FG);
        messageLabel.setFont(Fonts.PANEL_MESSAGE);
        messageLabel.setBackground(Colors.PANEL_MESSAGE_BG);
        messageLabel.setOpaque(true);
        add(messageLabel, new CC().grow());
    }

    public void updatePuzzle(int x, int y) {
        puzzle.setCell(x, y, !puzzle.getCell(x, y));
        cells[x][y].setBackground(puzzle.getCell(x, y) ? Colors.FILLED_CELL : Colors.EMPTY_CELL);
        clues[x].setText(puzzle.getClue(true, x).stream().map(Object::toString).collect(Collectors.joining("\n")));
        clues[puzzle.getWidth() + y].setText(puzzle.getClue(false, y).stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    @Override
    public void componentResized(ComponentEvent e) {
        display();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}
