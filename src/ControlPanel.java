import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ControlPanel extends JPanel {

    public static final String NEW = "New";
    public static final String OPEN = "Open";
    public static final String SAVE = "Save";
    public static final String BACK = "Back";

    private final EditorPanel editorPanel;

    public ControlPanel(EditorPanel editorPanel) {
        this.editorPanel = editorPanel;
        initUI();
    }

    private void initUI() {
        setBackground(Colors.CONTROL_PANEL);
        setLayout(new MigLayout(new LC().flowY()));
        JButton newButton = createButton(NEW);
        add(newButton);
        JButton openButton = createButton(OPEN);
        add(openButton);
        JButton saveButton = createButton(SAVE);
        add(saveButton);
        JButton backButton = createButton(BACK);
        add(backButton, new CC().pushY().alignY("bottom"));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Colors.PANEL_BUTTON_FG);
        button.setFont(Fonts.PANEL_BUTTON);
        button.setBackground(Colors.PANEL_BUTTON_BG);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(editorPanel);
        return button;
    }

}
