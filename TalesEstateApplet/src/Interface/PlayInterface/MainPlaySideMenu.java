/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author NightFiyah
 */
public class MainPlaySideMenu extends JPanel {

    JButton exspand, listBuildings, addBuildings, VisualInterface;

    public MainPlaySideMenu() {
        exspand = new JButton("Exspand");
        listBuildings = new JButton("List Buildings");
        addBuildings = new JButton("Add Building");
        VisualInterface = new JButton("Visual Interface");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 2;
        c.gridy = 1;
        add(exspand, c);
        c.gridy = 2;
        c.insets = new Insets(30, 0, 0, 0);
        add(listBuildings, c);
        c.gridy = 3;
        c.insets = new Insets(30, 0, 0, 0);
        add(addBuildings, c);
        c.gridy = 4;
        c.insets = new Insets(30, 0, 0, 0);
        add(VisualInterface, c);
    }
}
