/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class RequestInterface extends BasePanel {

    public RequestInterface(String name, TransferContainer tc) {
        super(name, tc);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
    }

    public void init(final TransferContainer tc, ArrayList<String[]> ar) {
        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(13)));
        Title.setContentAreaFilled(false);
        Title.setBorderPainted(false);
        add(Title, BorderLayout.NORTH);

        Requests AllRequests = new Requests(tc, ar);
        final JScrollPane requestsScroll;
        requestsScroll = new JScrollPane(AllRequests, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(requestsScroll, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "AMain");
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
