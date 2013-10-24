package Interface.MyProperties;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class MyPropertiesInterface extends BasePanel {

    TransferContainer tain;
    JButton title;
    int tempx;
    int tempy;

    public MyPropertiesInterface(String name, TransferContainer tc) {
        super(name, tc);
        tain = tc;
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
    }

    public void init() {

        title = new JButton(new ImageIcon(tain.ad.ImageAdapter(12)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        PlayerProperties pprop = new PlayerProperties(tain);
        final JScrollPane myProp = new JScrollPane(pprop, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        myProp.setBackground(java.awt.Color.getHSBColor(tain.c1[0], tain.c1[1], tain.c1[2]));
        myProp.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                myProp.getHorizontalScrollBar().setValue(myProp.getHorizontalScrollBar().getValue() + (tempx - e.getX()) / 2);
                tempx = e.getX();
                myProp.getVerticalScrollBar().setValue(myProp.getVerticalScrollBar().getValue() + (tempy - e.getY()) / 2);
                tempy = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                tempx = e.getX();
                tempy = e.getY();
            }
        });
        myProp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(myProp, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);

        JButton back = new JButton(new ImageIcon(tain.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tain.cardlayout.show(tain.contentpane, "MainMenu");
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
