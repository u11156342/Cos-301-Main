package Interface.Search;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class MainSearch extends BasePanel {

    public MainSearch(String name, TransferContainer tc) {
        super(name, tc);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
    }

    public void init(final TransferContainer tc) {

        JButton title = new JButton(new ImageIcon(tc.ad.ImageAdapter(18)));

        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        add(title, BorderLayout.NORTH);

        MainSearchInterface msi = new MainSearchInterface();
        msi.init(tc);
        add(msi, BorderLayout.CENTER);
        

        JButton back = new JButton(new ImageIcon(tc.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });
        add(back, BorderLayout.SOUTH);

    }
}
