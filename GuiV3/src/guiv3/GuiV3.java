package guiv3;

import Presentation.MainMenu.CharSelectMenu;
import Presentation.MainMenu.MainMenu;
import Presentation.PlotBuyMenu.BuyInterface;
import Presentation.Search.MainSearch;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GuiV3 extends JApplet {

    public static final int JFXPANEL_WIDTH_INT = 1024;
    public static final int JFXPANEL_HEIGHT_INT = 768;
    private static JFXPanel fxContainer;
    private CardLayout cardlayout = new CardLayout();
    private BuyInterface Buy;
    private MainMenu MainMenu;
    MainSearch search;
    CharSelectMenu CSelect;
    public static JFrame frame;
    UserCharacter Pchar = new UserCharacter();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                frame = new JFrame("ToT Estate System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JApplet applet = new GuiV3();
                applet.init();
                frame.setContentPane(applet.getContentPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                applet.start();
            }
        });
    }

    @Override
    public void init() {

        setLayout(cardlayout);
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardlayout.next(getContentPane());
            }
        };

        CSelect = new CharSelectMenu("Char");  

        Buy = new BuyInterface("Buy", JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT, Pchar, this, this.cardlayout, this.getContentPane());
        Buy.addNextActionListener(buttonListener);

        MainMenu = new MainMenu("MainMenu", JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);
        MainMenu.addNextActionListener(buttonListener, Pchar, this, this.cardlayout, this.getContentPane());
        
        CSelect.addNextActionListener(Pchar, cardlayout, this.getContentPane(),MainMenu);

        search = new MainSearch("Search",JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT,this,this.cardlayout,this.getContentPane());
        
        add(CSelect, CSelect.getName());
        add(MainMenu, MainMenu.getName());
        add(Buy, Buy.getName());
        add(search, search.getName()); 
        
        fxContainer = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }

    private void createScene() {
        StackPane root = new StackPane();
        fxContainer.setScene(new Scene(root));
    }
}
