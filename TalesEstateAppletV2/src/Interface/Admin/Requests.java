/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class Requests extends JPanel {

    public Requests(TransferContainer tc, ArrayList<String[]> ar) {
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));

        ArrayList<String[]> allRequests = ar;
        setLayout(new GridLayout(0, 1));

        for (int i = 0; i < allRequests.size(); i++) {
            RequestUnit temp = new RequestUnit(tc, ar);
            temp.RequestID = Integer.parseInt(allRequests.get(i)[0]);
            temp.RequestMessage = allRequests.get(i)[1];
            temp.init(tc);
            add(temp);
        }
    }
}
