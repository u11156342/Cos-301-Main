package Presentation.Search;

import Data.Communication.RestFullDBAdapter;
import guiv3.BasePanel;
import guiv3.GuiV3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainSearch extends BasePanel {

    public MainSearch(String name,final int JFXPANEL_WIDTH_INT,final int JFXPANEL_HEIGHT_INT, final GuiV3 aThis, final CardLayout cardlayout, final Container contentPane) {
        super(name);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        final String[] characters;

        RestFullDBAdapter wrapper = new RestFullDBAdapter();
        ArrayList<String[]> result = wrapper.retrieveAllCharacters();

        characters = new String[result.size() + 1];

        characters[0] = "";
        for (int a = 1; a < result.size() + 1; a++) {
            characters[a] = result.get(a-1)[1];
        }


        final JComboBox charc = new JComboBox(characters);

        final String[] duchylist;


        ArrayList<String> result2 = wrapper.retrieveDuchyList();

        duchylist = new String[result2.size()+1];
        
        duchylist[0]="";

        for (int a = 1; a < result2.size()+1; a++) {
            duchylist[a] = result2.get(a-1);
        }

        final JComboBox duchy = new JComboBox(duchylist);

        final String[] qualitylist = {"","Poor", "Fine", "Exquisite"};
        final JComboBox quality = new JComboBox(qualitylist);
        final JTextField size = new JTextField();
        size.setPreferredSize(new Dimension(100, 25));

        JLabel clabe = new JLabel("Character name : ");
        JLabel dlabe = new JLabel("Duchy          : ");
        JLabel qlabe = new JLabel("Quality        : ");
        JLabel slabe = new JLabel("Size           : ");

        c.insets = new Insets(10, 0, 0, 0);

        c.gridy = 1;
        add(clabe, c);
        add(charc, c);

        c.gridy = 2;
        add(dlabe, c);
        add(duchy, c);

        c.gridy = 3;
        add(qlabe, c);
        add(quality, c);

        c.gridy = 4;
        add(slabe, c);
        add(size, c);

        Button btn = new Button("search");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RestFullDBAdapter wrap = new RestFullDBAdapter();

                int s=0;
                if("".equals(size.getText()))
                    s=0;
                else{
                    
                    try{
                    s=Integer.parseInt(size.getText());
                    }
                    catch(java.lang.NumberFormatException ex)
                    {
                        
                    }
                }
                ArrayList<String[]> result = wrap.searchPlotBy(characters[charc.getSelectedIndex()],duchylist[duchy.getSelectedIndex()],s,qualitylist[quality.getSelectedIndex()]);
                SearchInterface si = new SearchInterface("mainS", JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT,result,aThis,cardlayout,contentPane);
               aThis.add(si, si.getName());
               cardlayout.show(contentPane, "mainS");
            }
        });

        c.gridy = 5;
        c.gridwidth = 2;
        add(btn, c);

    }
}
