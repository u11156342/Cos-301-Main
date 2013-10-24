package talesestateappletv2;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BasePanel extends JPanel {

    private static final float FONT_SIZE = 24f;
    private JButton next = new JButton();
    public TransferContainer tain;

    public BasePanel(String name, TransferContainer tc) {
        setName(name);
        tain = tc;
        setLayout(new BorderLayout());
    }
    public void addNextActionListener(ActionListener listener) {
        next.addActionListener(listener);
    }
}
