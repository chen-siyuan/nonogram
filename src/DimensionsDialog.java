import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DimensionsDialog extends JDialog implements ActionListener {

    public static final String BUTTON_CONFIRM = "Confirm";
    public static final String BUTTON_CANCEL = "Cancel";
    public static final int CODE_CANCEL = 0;
    public static final int CODE_INVALID = -1;

    private final JLabel messageLabel;
    private final JTextField widthField;
    private final JTextField heightField;
    private final JButton confirmButton;
    private final JButton cancelButton;
    private final int[] dimensions;

    public DimensionsDialog(Frame frame) {
        super(frame, true);
        messageLabel = createLabel("New puzzle: [W] X [H]");
        widthField = createTextField();
        heightField = createTextField();
        confirmButton = createButton(BUTTON_CONFIRM);
        cancelButton = createButton(BUTTON_CANCEL);
        dimensions = new int[2];
        initUI();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Colors.DIALOG_MESSAGE_FG);
        label.setFont(Fonts.DIALOG_MESSAGE);
        label.setBackground(Colors.DIALOG_MESSAGE_BG);
        label.setOpaque(true);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setForeground(Colors.DIALOG_TEXT_FIELD_FG);
        textField.setFont(Fonts.DIALOG_TEXT_FIELD);
        textField.setBackground(Colors.DIALOG_TEXT_FIELD_BG);
        textField.setOpaque(true);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Colors.DIALOG_BUTTON_FG);
        button.setFont(Fonts.DIALOG_BUTTON);
        button.setBackground(Colors.DIALOG_BUTTON_BG);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(this);
        return button;
    }

    private void initUI() {
        setTitle("Puzzle Dimensions");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Colors.DIMENSIONS_DIALOG);
        setLayout(new MigLayout(new LC().wrapAfter(2).fill().width("300:300:300").height("200:200:200")));
        add(messageLabel, new CC().spanX(2).grow());
        add(widthField, new CC().spanX(2).grow());
        add(heightField, new CC().spanX(2).grow());
        add(confirmButton, new CC().grow());
        add(cancelButton, new CC().grow());
        pack();
        setLocationRelativeTo(null);
    }

    public int[] getDimensions() {
        setVisible(true);
        return dimensions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case BUTTON_CONFIRM:
                try {
                    dimensions[0] = Integer.parseInt(widthField.getText());
                    dimensions[1] = Integer.parseInt(heightField.getText());
                    for (int dimension : dimensions) if (dimension <= 0 || dimension > 30) throw new Exception();
                } catch (Exception exception) {
                    dimensions[0] = CODE_INVALID;
                    dimensions[1] = CODE_INVALID;
                }
                dispose();
                break;
            case BUTTON_CANCEL:
                dimensions[0] = CODE_CANCEL;
                dimensions[1] = CODE_CANCEL;
                dispose();
                break;
            default:
                System.out.println("[DD.actionPerformed()] Unknown code: " + e.getActionCommand());
                System.exit(0);
                break;
        }
    }

}
