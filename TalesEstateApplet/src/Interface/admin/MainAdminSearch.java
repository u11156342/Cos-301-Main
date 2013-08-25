package Interface.admin;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import talesestateapplet.BasePanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

public class MainAdminSearch extends BasePanel {

    public MainAdminSearch(String name, final int JFXPANEL_WIDTH_INT, final int JFXPANEL_HEIGHT_INT,final UserCharacter uc, final TalesEstateApplet aThis, final CardLayout cardlayout, final Container contentPane) {
        super(name);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        RestFullDBAdapter wrapper = new RestFullDBAdapter();



        final JTextField charc = new JTextField("Any");
        charc.setPreferredSize(new Dimension(100, 25));

        final String[] duchylist;


        ArrayList<String> result2 = wrapper.retrieveDuchyList();

        duchylist = new String[result2.size() + 1];

        duchylist[0] = "Any";

        for (int a = 1; a < result2.size() + 1; a++) {
            duchylist[a] = result2.get(a - 1);
        }

        final JComboBox duchy = new JComboBox(duchylist);

        final String[] qualitylist = {"Any", "Poor", "Fine", "Exquisite"};
        final JComboBox quality = new JComboBox(qualitylist);
        final JTextField size = new JTextField();
        size.setPreferredSize(new Dimension(100, 25));

        JLabel clabe = new JLabel("Character name");
        JLabel dlabe = new JLabel("Duchy");
        JLabel qlabe = new JLabel("Quality");
        JLabel slabe = new JLabel("Size");

        RestFullAdapter picAdapter = new RestFullAdapter();
        JButton title = new JButton("Admin Search");
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);
        c.gridwidth = 1;
        c.insets = new Insets(50, 0, 0, 0);

        c.gridy = 1;
        add(clabe, c);
        add(charc, c);

        c.insets = new Insets(20, 0, 0, 0);

        c.gridy = 2;
        add(dlabe, c);
        add(duchy, c);

        c.gridy = 3;
        add(qlabe, c);
        add(quality, c);

        c.gridy = 4;
        add(slabe, c);
        add(size, c);

        JButton btn = new JButton(new ImageIcon(picAdapter.ImageAdapter(19)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.setContentAreaFilled(false);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RestFullDBAdapter wrap = new RestFullDBAdapter();

                int s = 0;
                if ("".equals(size.getText())) {
                    s = 0;
                } else {

                    try {
                        s = Integer.parseInt(size.getText());
                    } catch (java.lang.NumberFormatException ex) {
                    }
                }
                String selectedChar = charc.getText();
                String selectedDuchy = duchylist[duchy.getSelectedIndex()];
                String selectedQuality = qualitylist[quality.getSelectedIndex()];

                if ("Any".equals(selectedChar)) {
                    selectedChar = "";
                }
                if ("Any".equals(selectedDuchy)) {
                    selectedDuchy = "";
                }
                if ("Any".equals(selectedQuality)) {
                    selectedQuality = "";
                }

                ArrayList<String[]> result = wrap.searchPlotBy(selectedChar, selectedDuchy, s, selectedQuality);
                AdminSearchInterface si = new AdminSearchInterface("AdminS", JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT, result, aThis, cardlayout, contentPane,uc);
                aThis.add(si, si.getName());
                cardlayout.show(contentPane, "AdminS");
            }
        });

        c.gridy = 5;
        c.gridwidth = 2;
        add(btn, c);

    }
}
