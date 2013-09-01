/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.MyProperties;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
public class MyPropertiesInterface extends BasePanel{
    
    TransferContainer tain;
    JButton title;
    public MyPropertiesInterface(String name,TransferContainer tc)
    {
        super(name);
        tain=tc;
        
        title=new JButton(new ImageIcon(tain.ad.ImageAdapter(12)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);
        
        PlayerProperties mmenu = new PlayerProperties(tain);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.setPreferredSize(new Dimension(tain.JFXPANEL_WIDTH_INT -50,tain.JFXPANEL_HEIGHT_INT - 50));
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title,BorderLayout.NORTH);       
        
    }
}
