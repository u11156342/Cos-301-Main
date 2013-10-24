package Interface.PlayerManagement;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;


public class CharacterLog extends BasePanel {

    public JTextPane textZone = new JTextPane();

    public CharacterLog(String name, TransferContainer tc) {
        super(name, tc);
    }

    public void init(final TransferContainer tc) {

        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(13)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        add(Title, BorderLayout.NORTH);
        textZone.setEditable(false);
        textZone.setContentType("text/html");
        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(scrollText, BorderLayout.CENTER);
        JButton back = new JButton(new ImageIcon(tc.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "PlayerMan");
            }
        });

        add(back, BorderLayout.SOUTH);
    }

    public void refres(TransferContainer tc) {
        String month = "";
        Calendar cal = Calendar.getInstance();
        DateFormat monthF = new SimpleDateFormat("MM");
        month = monthF.format(cal.getTime());
        textZone.setText(tc.rdb.getLogChar(tc.rdb.retrieveCharacterID(tc.CharacterName), month));
    }
}
