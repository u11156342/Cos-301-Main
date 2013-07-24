package Presentation.PlayMap;

import Data.Communication.RestFullDBAdapter;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class tabPanel extends JPanel {

    int with;
    int height;
    RestFullDBAdapter wrapper=new RestFullDBAdapter();

    tabPanel(int withs, int heights, int propertyID, String duchy, int quality) {
        with = withs;
        height = heights;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        BuildtabPanel build = new BuildtabPanel(with /4*3, height,duchy);
        add(build, BorderLayout.CENTER);
        StatusPane stat = new StatusPane(200, height,propertyID);
        add(stat, BorderLayout.EAST);

    }
 
}
