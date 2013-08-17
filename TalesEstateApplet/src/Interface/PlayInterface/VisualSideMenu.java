/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author NightFiyah
 */
public class VisualSideMenu extends JPanel{
    
    int PropertyID;
    ArrayList buildings;
    RestFullDBAdapter wrapper=new RestFullDBAdapter();
    
    public VisualSideMenu(int PropertyId)
    {
        PropertyID=PropertyId;
       // buildings= wrapper.getAllBuildings(PropertyId);
    }
    
}
