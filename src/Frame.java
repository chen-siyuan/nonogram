import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class Frame extends JFrame {

    private final EditorPanel editorPanel;

    public Frame() {
        editorPanel = new EditorPanel(this);
        initUI();
    }

    private void initUI() {
        setTitle("Nonogram Editor");
        setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Colors.FRAME);
        setLayout(new MigLayout(new LC().fill()));
        add(editorPanel, new CC().grow());
        pack();
        setLocationRelativeTo(null);
    }

}
