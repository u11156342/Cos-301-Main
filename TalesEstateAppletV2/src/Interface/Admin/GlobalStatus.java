package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
//Global status page
public class GlobalStatus extends BasePanel {

    public JTextPane textZone = new JTextPane();

    public GlobalStatus(String name, TransferContainer tc) {
        super(name, tc);
    }

    public void init(final TransferContainer tc) {

        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(46)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        add(Title, BorderLayout.NORTH);
        textZone.setEditable(false);
        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(scrollText, BorderLayout.CENTER);
        JButton back = new JButton(new ImageIcon(tain.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "AMain");
            }
        });

        add(back, BorderLayout.SOUTH);
    }

    void refresh(TransferContainer tc) {
        textZone.setContentType("text/html");
        textZone.setText(tc.rdb.GlobalStatus());
    }
}
