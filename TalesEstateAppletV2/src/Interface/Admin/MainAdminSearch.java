package Interface.Admin;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class MainAdminSearch extends BasePanel {

    public MainAdminSearch(String name,TransferContainer tc) {
        super(name,tc);
    }

    public void init(final TransferContainer tc) {
        JButton title = new JButton(new ImageIcon(tc.ad.ImageAdapter(18)));
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));

        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        add(title, BorderLayout.NORTH);

        MainAdminSearchInterface masi = new MainAdminSearchInterface();
        masi.init(tc);
        add(masi, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "AMain");
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
