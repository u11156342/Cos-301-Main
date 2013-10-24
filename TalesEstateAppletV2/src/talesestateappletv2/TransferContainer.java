package talesestateappletv2;

import CardManager.CardManager;
import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import Interface.Admin.AdminMenu;
import Interface.Admin.GlobalStatus;
import Interface.Admin.RequestInterface;
import Interface.BuyBuilding.BuildtabPanel;
import Interface.BuyProperty.BuyInterface;
import Interface.MainMenu.CharSelectMenu;
import Interface.MainMenu.MainMenu;
import Interface.MyProperties.MyPropertiesInterface;
import Interface.PlayInterface.PlayInterface;
import Interface.PlayInterface.VisualSideMenu;
import Interface.PlayerManagement.CharacterLog;
import Interface.PlayerManagement.PlayerManagementInterface;
import Interface.RightsManagement.RightsInterface;
import Interface.Search.MainSearch;
import Interface.TextManage.MainPlayInterface;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.image.BufferedImage;
import javafx.embed.swing.JFXPanel;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class TransferContainer {

    public int JFXPANEL_WIDTH_INT = 1024;
    public int JFXPANEL_HEIGHT_INT = 768;
    public JFXPanel fxContainer;
    public CardLayout cardlayout;
    public int CharacterID;
    public String CharacterName;
    public JFrame mainframe;
    public JApplet mainapplet;
    public RestFullAdapter ad;
    public RestFullDBAdapter rdb = new RestFullDBAdapter();
    public Container contentpane;
    public BuyInterface Buy;
    public MainMenu MainMenu;
    public CharSelectMenu CSelect;
    public MyPropertiesInterface mProp;
    public MainSearch search;
    public AdminMenu am;
    public MainPlayInterface mp;
    public BuildtabPanel Build;
    public PlayInterface visual;
    public RightsInterface ri;
    public PlayerManagementInterface pmI;
    public CardManager Cmanager;
    public int BuildingRef = -1;
    public CharacterLog clog;
    public GlobalStatus gstatus;
    public int BuildingLogReference = 0;
    public VisualSideMenu reference;
    public boolean lastAdminBrowse;
    public BufferedImage background;
    public RequestInterface requests;
    public  float[] c1 = java.awt.Color.RGBtoHSB(142, 117, 92, null);
    public int clickorigin=0;

    public TransferContainer() {
        cardlayout = new CardLayout();
        lastAdminBrowse = false;
        Cinit();
    }

    public void Cinit() {
        ad = new RestFullAdapter(this);
        Cmanager = new CardManager(this);
    }
}
