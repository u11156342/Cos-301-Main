/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextPane;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class DetailedStatus extends BasePanel{
    
    public JTextPane textZone = new JTextPane();
    JButton back=new JButton("back");
    
    public DetailedStatus(String name,int Pid,final TransferContainer tc)
    {
        super(name);
        textZone.setContentType("text/html"); 
        textZone.setText(tc.rdb.getStatus(Pid));
        
        this.add(textZone,BorderLayout.CENTER);
        
        back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               tc.cardlayout.show(tc.contentpane, "AdminS");
            }
        });
        add(back,BorderLayout.SOUTH);
        
        
        
        
    }
    
}
