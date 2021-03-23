import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class PuzzleOpener extends JFileChooser {

    public PuzzleOpener(String path) {
        super(path);
        setAcceptAllFileFilterUsed(false);
        setFileFilter(new FileNameExtensionFilter("(*.puzzle) Nonogram puzzle", "puzzle"));
    }

    public Puzzle getPuzzle(Component component) {
        switch (showOpenDialog(component)) {
            case JFileChooser.APPROVE_OPTION:
                return PuzzleIO.readPuzzle(getSelectedFile());
            case JFileChooser.CANCEL_OPTION:
                return null;
            case JFileChooser.ERROR_OPTION:
            default:
                System.out.println();
                System.exit(0);
                break;
        }
        return null;
    }

//    @Override
//    protected JDialog createDialog(Component parent) throws HeadlessException {
//        JDialog dialog = super.createDialog(parent);
//        dialog.getContentPane().setBackground(Colors.DIALOG_BUTTON_BG);
//        return dialog;
//    }

}
