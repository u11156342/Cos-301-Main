package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

public class StatusPane extends JPanel {

    JTextArea statusArea = new JTextArea();
    RestFullDBAdapter wrapper=new RestFullDBAdapter();

    public StatusPane(int width, int height, int PropertyID) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statusArea.setEditable(false);
        System.out.println(PropertyID);
        ArrayList<String> results = wrapper.retrievePlotDetails(PropertyID);
        statusArea.append("PlotID : " + results.get(0) + " located in " + results.get(2) + "\n");
        statusArea.append("Size : " + results.get(3) + " Quality " + results.get(4) + "\n");
        statusArea.append("Happiness : " + results.get(9) + "\n");
        statusArea.append("Income : " + results.get(10) + "\n");
        statusArea.append("Workers : " + results.get(11) + "/" + results.get(12) + "\n");
        JScrollPane statusScroll = new JScrollPane(statusArea);
        statusScroll.setPreferredSize(new Dimension(width, height));
        statusScroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(statusScroll, BorderLayout.CENTER);
    }
}
