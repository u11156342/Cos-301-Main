package Interface.RightsManagement;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class AddPlayer extends BasePanel {

    public AddPlayer(String name, TransferContainer tc) {
        super(name, tc);
    }
    public void init(final TransferContainer tc, String PlayerName, int PlotID) {
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(116)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);
        add(Title, BorderLayout.NORTH);
        AddPlayerForm pform = new AddPlayerForm(PlayerName, tc, PlotID);
        add(pform, BorderLayout.CENTER);
        JButton back = new JButton(new ImageIcon(tc.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tc.cardlayout.show(tc.contentpane, tc.Cmanager.MainPlayInterfaces[tc.Cmanager.currentMainPlayInterfaceCard].getName());
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
