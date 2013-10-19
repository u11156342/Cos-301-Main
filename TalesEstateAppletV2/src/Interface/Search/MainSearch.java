package Interface.Search;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class MainSearch extends BasePanel {

    public MainSearch(String name,TransferContainer tc) {
        super(name,tc);
    }

    public void init(final TransferContainer tc) {

        JButton title = new JButton(new ImageIcon(tc.ad.ImageAdapter(18)));

        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        add(title, BorderLayout.NORTH);

        MainSearchInterface msi = new MainSearchInterface();
        msi.init(tc);
        add(msi, BorderLayout.CENTER);


        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });
        add(back, BorderLayout.SOUTH);

    }
}
