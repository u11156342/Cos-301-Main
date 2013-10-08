/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package talesestateappletv2;

import CardManager.CardManager;
import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import Interface.Admin.AdminMenu;
import Interface.BuyBuilding.BuildtabPanel;
import Interface.BuyProperty.BuyInterface;
import Interface.MainMenu.CharSelectMenu;
import Interface.MainMenu.MainMenu;
import Interface.MyProperties.MyPropertiesInterface;
import Interface.PlayInterface.PlayInterface;
import Interface.PlayerManagement.PlayerManagementInterface;
import Interface.RightsManagement.RightsInterface;
import Interface.Search.MainSearch;
import Interface.TextManage.MainPlayInterface;
import java.awt.CardLayout;
import java.awt.Container;
import javafx.embed.swing.JFXPanel;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author NightFiyah
 */
public class TransferContainer {

    public int JFXPANEL_WIDTH_INT = 1024;
    public int JFXPANEL_HEIGHT_INT = 768;
    public JFXPanel fxContainer;
    public CardLayout cardlayout;
    public int CharacterID;
    public String CharacterName;
    public JFrame mainframe;
    public JApplet mainapplet;
    public RestFullAdapter ad = new RestFullAdapter();
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
    public int BuildingRef=5;

    public TransferContainer() {
        cardlayout = new CardLayout();
        Cinit();
    }
    public void Cinit()
    {
        Cmanager=new CardManager(this);
    }
}
