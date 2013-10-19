/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.RightsManagement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class AddPlayer extends BasePanel {

    public AddPlayer(String name,TransferContainer tc) {
        super(name,tc);
    }

    public void init(final TransferContainer tc,String PlayerName,int PlotID) {

        AddPlayerForm pform=new AddPlayerForm(PlayerName,tc,PlotID);
        
        add(pform,BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, tc.Cmanager.MainPlayInterfaces[tc.Cmanager.currentMainPlayInterfaceCard].getName());
            }
        });
        
        add(back,BorderLayout.SOUTH);
    }
}
