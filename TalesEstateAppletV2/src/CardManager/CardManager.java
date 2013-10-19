/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManager;

import Interface.Admin.*;
import Interface.BrowseInterface.BrowseInterface;
import Interface.MyProperties.MyPropertiesInterface;
import Interface.PlayInterface.PlayInterface;
import Interface.RightsManagement.AddPlayer;
import Interface.RightsManagement.RightsInterface;
import Interface.Search.SearchInterface;
import Interface.TextManage.MainPlayInterface;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author user
 */
public class CardManager {

    //my properties cards
    public MyPropertiesInterface[] MyProperties = new MyPropertiesInterface[50];
    public int currentMyPropertiesCard = -1;
    public SearchInterface[] SearchInterfaces = new SearchInterface[50];
    public int currentSearchInterfaceCard = -1;
    public MainAdminSearch[] MainAdminSearches = new MainAdminSearch[50];
    public int currentMainAdminSearchCard = -1;
    public ManageGold[] ManageGoldInters = new ManageGold[10];
    public int currentManageGoldCard = -1;
    public AdminSearchInterface[] AdminSearchInterfaces = new AdminSearchInterface[50];
    public int currentAdminSearchInterfaceCard = -1;
    public DetailedStatus[] DetailedStatuses = new DetailedStatus[50];
    public int currentDetailedStatusCard = -1;
    public AddEvent[] AddEvents = new AddEvent[100];
    public int currentAddEventCard = -1;
    public BrowseInterface[] BrowseInterfaces = new BrowseInterface[50];
    public int currentBrowseInterfaceCard = -1;
    public MainPlayInterface[] MainPlayInterfaces = new MainPlayInterface[50];
    public int currentMainPlayInterfaceCard = -1;
    public RightsInterface[] RightsInterfaces = new RightsInterface[50];
    public int currentRightsInterfaceCard = -1;
    public AddPlayer[] AddPlayere = new AddPlayer[50];
    public int currentAddPlayerCard = -1;
    public PlayInterface[] PlayInterfaces = new PlayInterface[100];
    public int currentPlayInterfaceCard = -1;

    public CardManager(TransferContainer tc) {
        for (int c = 0; c < MyProperties.length; c++) {
            MyProperties[c] = new MyPropertiesInterface("MyProp" + c, tc);
        }
        for (int c = 0; c < SearchInterfaces.length; c++) {
            SearchInterfaces[c] = new SearchInterface("mainS" + c, tc);
        }
        for (int c = 0; c < MainAdminSearches.length; c++) {
            MainAdminSearches[c] = new MainAdminSearch("Admin" + c, tc);
        }
        for (int c = 0; c < ManageGoldInters.length; c++) {
            ManageGoldInters[c] = new ManageGold("CGold" + c, tc);
        }
        for (int c = 0; c < AdminSearchInterfaces.length; c++) {
            AdminSearchInterfaces[c] = new AdminSearchInterface("mainSearchInterface" + c, tc);
        }
        for (int c = 0; c < DetailedStatuses.length; c++) {
            DetailedStatuses[c] = new DetailedStatus("DetailedStatus" + c, tc);
        }
        for (int c = 0; c < AddEvents.length; c++) {
            AddEvents[c] = new AddEvent("AddEvent" + c, tc);
        }
        for (int c = 0; c < BrowseInterfaces.length; c++) {
            BrowseInterfaces[c] = new BrowseInterface("BrowseInterface" + c, tc);
        }
        for (int c = 0; c < MainPlayInterfaces.length; c++) {
            MainPlayInterfaces[c] = new MainPlayInterface("MainPlayInterface" + c, tc);
        }
        for (int c = 0; c < RightsInterfaces.length; c++) {
            RightsInterfaces[c] = new RightsInterface("RightsInterface" + c, tc);
        }
        for (int c = 0; c < AddPlayere.length; c++) {
            AddPlayere[c] = new AddPlayer("AddPlayer" + c, tc);
        }
        for (int c = 0; c < PlayInterfaces.length; c++) {
            PlayInterfaces[c] = new PlayInterface("PlayInterfaces" + c, tc);
        }
    }

    public MyPropertiesInterface getMyPropertiesCard() {
        currentMyPropertiesCard++;
        return MyProperties[currentMyPropertiesCard];
    }

    public SearchInterface getSearchInterfacesCard() {
        currentSearchInterfaceCard++;
        return SearchInterfaces[currentSearchInterfaceCard];
    }

    public MainAdminSearch getMainAdminSearchesCard() {
        currentMainAdminSearchCard++;
        return MainAdminSearches[currentMainAdminSearchCard];
    }

    public ManageGold getManageGoldIntersCard() {
        currentManageGoldCard++;
        return ManageGoldInters[currentManageGoldCard];
    }

    public AdminSearchInterface getAdminSearchInterfaceCard() {
        currentAdminSearchInterfaceCard++;
        return AdminSearchInterfaces[currentAdminSearchInterfaceCard];
    }

    public DetailedStatus getDetailedStatusCard() {
        currentDetailedStatusCard++;
        return DetailedStatuses[currentDetailedStatusCard];
    }

    public AddEvent getAddEventCard() {
        currentAddEventCard++;
        return AddEvents[currentAddEventCard];
    }

    public BrowseInterface getBrowseInterfacesCard() {
        currentBrowseInterfaceCard++;
        return BrowseInterfaces[currentBrowseInterfaceCard];
    }

    public MainPlayInterface getMainPlayInterfaceCard() {
        currentMainPlayInterfaceCard++;
        return MainPlayInterfaces[currentMainPlayInterfaceCard];
    }

    public RightsInterface getRightsInterfacesCard() {
        currentRightsInterfaceCard++;
        return RightsInterfaces[currentRightsInterfaceCard];
    }

    public AddPlayer getAddPlayereCard() {
        currentAddPlayerCard++;
        return AddPlayere[currentAddPlayerCard];
    }

    public PlayInterface getPlayInterfacesCard() {
        currentPlayInterfaceCard++;
        return PlayInterfaces[currentPlayInterfaceCard];
    }
}
