/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiv3;

import Data.Communication.RestFullAdapter;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BasePanel extends JPanel {

    private static final float FONT_SIZE = 24f;
    private JButton next = new JButton("Next");
    public RestFullAdapter adapter = new RestFullAdapter();

    public BasePanel(String name) {
        setName(name);
        JLabel label = new JLabel(getName(), SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.BOLD, FONT_SIZE));
        JPanel btnPanel = new JPanel(new GridBagLayout());

        setLayout(new BorderLayout());
        add(btnPanel, BorderLayout.SOUTH);

    }

    public void addNextActionListener(ActionListener listener) {
        next.addActionListener(listener);
    }
}
