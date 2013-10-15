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

    public MainAdminSearch(String name) {
        super(name);
    }

    public void init(final TransferContainer tc) {
        RestFullAdapter picAdapter = new RestFullAdapter();
        JButton title = new JButton(new ImageIcon(tc.ad.ImageAdapter(18)));

        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        add(title, BorderLayout.NORTH);

        MainAdminSearchInterface masi = new MainAdminSearchInterface(tc);
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
