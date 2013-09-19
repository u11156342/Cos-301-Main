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

    public AddPlayer(String name) {
        super(name);
    }

    public void init(final TransferContainer tc) {

        AddPlayerForm pform=new AddPlayerForm();
        
        add(pform,BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MPlay");
            }
        });
        
        add(back,BorderLayout.SOUTH);
    }
}
