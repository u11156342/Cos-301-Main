package Interface.RightsManagement;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;

public class AddPlayerForm extends JPanel {

    JLabel pname = new JLabel("Players name : ");
    JLabel name;
    JLabel deposit = new JLabel("Deposit rights : ");
    JCheckBox deposit_j = new JCheckBox();
    JLabel withdraw = new JLabel("Withdraw rights : ");
    JCheckBox withdraw_j = new JCheckBox();
    JLabel building = new JLabel("Building purchase rights : ");
    JCheckBox building_j = new JCheckBox();
    JLabel Plot_exspansion_rights = new JLabel("Plot exspansion rights : ");
    JCheckBox Plot_exspansion_rights_j = new JCheckBox();
    JLabel status = new JLabel("Status View Rights : ");
    JCheckBox status_j = new JCheckBox();
    JLabel token = new JLabel("Visual Token Placing Rights : ");
    JCheckBox token_j = new JCheckBox();

    public AddPlayerForm(final String playerName, final TransferContainer tc, final int PlotID) {
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        name = new JLabel(playerName);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        add(pname, c);
        c.gridx = 1;
        name.setPreferredSize(new Dimension(100, 40));
        add(name, c);
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        add(deposit, c);
        c.gridx = 1;
        add(deposit_j, c);
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        add(withdraw, c);
        c.gridx = 1;
        add(withdraw_j, c);
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        add(building, c);
        c.gridx = 1;
        add(building_j, c);
        c.gridy = 4;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        add(Plot_exspansion_rights, c);
        c.gridx = 1;
        add(Plot_exspansion_rights_j, c);
        c.gridy = 5;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        add(status, c);
        c.gridx = 1;
        add(status_j, c);
        c.gridy = 6;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        add(token, c);
        c.gridx = 1;
        add(token_j, c);
        c.gridy = 7;
        c.gridwidth = 2;
        c.gridx = 0;
        JButton add = new JButton(new ImageIcon(tc.ad.ImageAdapter(119)));
        add.setContentAreaFilled(false);
        add.setBorderPainted(false);
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean Deposit = false;
                boolean Withdraw = false;
                boolean Building = false;
                boolean Plot_exspansion = false;
                boolean Plot_status = false;
                boolean Plot_visual = false;

                if (deposit_j.isSelected()) {
                    Deposit = true;
                }
                if (withdraw_j.isSelected()) {
                    Withdraw = true;
                }
                if (building_j.isSelected()) {
                    Building = true;
                }
                if (Plot_exspansion_rights_j.isSelected()) {
                    Plot_exspansion = true;
                }
                if (status_j.isSelected()) {
                    Plot_status = true;
                }
                if (token_j.isSelected()) {
                    Plot_visual = true;
                }

                tc.rdb.addPlotAccess(PlotID, tc.rdb.retrieveCharacterID(playerName), Deposit, Withdraw, Building, Plot_visual, Plot_exspansion, Plot_status);
                RightsInterface card = tc.Cmanager.getRightsInterfacesCard();
                card.init(tc, PlotID);
                tc.cardlayout.show(tc.contentpane, card.getName());
            }
        });
        add(add, c);

        c.gridy = 8;
    }
}
