package Interface.BrowseInterface;

import Interface.PlayInterface.TileManager;
import Interface.PlayInterface.VisualMap;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import talesestateappletv2.TransferContainer;

public class GameBrowseGrid extends JFXPanel {

    public int gridsize;
    public int[][] gridstates;
    public int[][] tileStates;
    public int[][] tempplacinggrid = null;
    public int wdOfcell;
    public int htOfcell;
    public TileManager tiles;
    boolean build = false;
    public JScrollPane scroller;
    int topoffset = 100;
    int sideoffset = 500;
    int globalwidth;
    int globalheight;
    Graphics2D g2d;
    int tempx = 0;
    int tempy = 0;
    int oldvalue = 0;
    int currentZoom = 0;
    TransferContainer tain;
    int PlotID;

    public GameBrowseGrid(int size, TransferContainer tc, int pid) throws IOException {
        PlotID = pid;
        tain = tc;
        tiles = new TileManager(tc);
        wdOfcell = 160;
        htOfcell = 80;

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                if (oldvalue == 0) {
                    oldvalue = e.getWheelRotation();
                } else {
                    int r = e.getWheelRotation();
                    r = r * -1;
                    currentZoom = currentZoom + r;

                    if (currentZoom == -5) {
                        wdOfcell = 60;
                        htOfcell = 30;

                    } else if (currentZoom == -4) {
                        wdOfcell = 80;
                        htOfcell = 40;

                    } else if (currentZoom == -3) {
                        wdOfcell = 100;
                        htOfcell = 50;

                    } else if (currentZoom == -2) {
                        wdOfcell = 120;
                        htOfcell = 60;

                    } else if (currentZoom == -1) {
                        wdOfcell = 140;
                        htOfcell = 70;

                    } else if (currentZoom == 0) {
                        wdOfcell = 160;
                        htOfcell = 80;

                    } else if (currentZoom == 1) {
                        wdOfcell = 200;
                        htOfcell = 100;

                    } else if (currentZoom == 2) {
                        wdOfcell = 240;
                        htOfcell = 120;

                    } else if (currentZoom == 3) {
                        wdOfcell = 280;
                        htOfcell = 140;

                    } else if (currentZoom == 4) {
                        wdOfcell = 320;
                        htOfcell = 160;

                    } else if (currentZoom == 5) {
                        wdOfcell = 360;
                        htOfcell = 180;

                    } else {
                        if (currentZoom < 0) {
                            currentZoom = -5;
                        } else {
                            currentZoom = 5;
                        }
                    }

                    repaint();
                }

            }
        });
    }

    @Override
    public void paint(final Graphics g) {

        globalwidth = wdOfcell * tileStates.length;
        globalheight = globalheight * tileStates.length;
        gridsize = tileStates.length;

        if (tempplacinggrid == null) {
            tempplacinggrid = new int[tileStates.length][tileStates.length];
        }

        int xc = 0;
        int yc = 0;
        g2d = (Graphics2D) g.create();
        double move = 0;
        double move2 = 0;
        for (int x = 0; x < gridsize; x++) {

            for (int y = 0; y < gridsize; y++) {
                xc = -this.scroller.getHorizontalScrollBar().getValue() + ((y * (int) (wdOfcell)) / 2) + (int) move + globalwidth / 2;
                yc = -this.scroller.getVerticalScrollBar().getValue() + ((y * (int) (htOfcell)) / 2) + (int) move2;
                try {
                    g2d.drawImage(tiles.get(tileStates[x][y]), xc, yc + topoffset, wdOfcell, htOfcell, this);
                } catch (IOException ex) {
                    Logger.getLogger(VisualMap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            move = move - (wdOfcell / 2);
            move2 = move2 + (htOfcell / 2);
        }
        move = 0;
        move2 = 0;
        for (int x = 0; x < gridsize; x++) {
            for (int y = 0; y < gridsize; y++) {
                if (gridstates[x][y] != 0) {
                    xc = -this.scroller.getHorizontalScrollBar().getValue() + ((y * (int) (wdOfcell)) / 2) + (int) move + globalwidth / 2;
                    yc = -this.scroller.getVerticalScrollBar().getValue() + ((y * (int) (htOfcell)) / 2) + (int) move2;
                    try {
                        g2d.drawImage(tiles.get(gridstates[x][y]), xc, topoffset + yc, wdOfcell, htOfcell, this);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            move = move - (wdOfcell / 2);
            move2 = move2 + (htOfcell / 2);
        }

        move = 0;
        move2 = 0;
        for (int x = 0; x < tempplacinggrid.length; x++) {
            for (int y = 0; y < tempplacinggrid.length; y++) {
                if (tempplacinggrid[x][y] != 0) {
                    xc = -this.scroller.getHorizontalScrollBar().getValue() + ((y * (int) (wdOfcell)) / 2) + (int) move + globalwidth / 2;
                    yc = -this.scroller.getVerticalScrollBar().getValue() + ((y * (int) (htOfcell)) / 2) + (int) move2;
                    try {
                        g2d.drawImage(tiles.get(tempplacinggrid[x][y]), xc, topoffset + yc, wdOfcell, htOfcell, this);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            move = move - (wdOfcell / 2);
            move2 = move2 + (htOfcell / 2);
        }

    }

    public void init(int size, TransferContainer tc, int pid) {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                scroller.getHorizontalScrollBar().setValue(scroller.getHorizontalScrollBar().getValue() + (tempx - e.getX()) / 2);
                tempx = e.getX();
                scroller.getVerticalScrollBar().setValue(scroller.getVerticalScrollBar().getValue() + (tempy - e.getY()) / 2);
                tempy = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

                tempx = e.getX();
                tempy = e.getY();

                int clickedx = e.getX();
                int clickedy = e.getY();
                double move = 0;
                double move2 = 0;
                int xc = 0;
                int yc = 0;
                boolean valid = false;
                for (int x = 0; x < gridsize; x++) {
                    for (int y = 0; y < gridsize; y++) {
                        xc = -scroller.getHorizontalScrollBar().getValue() + ((y * (int) (wdOfcell)) / 2) + (int) move + globalwidth / 2;
                        yc = -scroller.getVerticalScrollBar().getValue() + ((y * (int) (htOfcell)) / 2) + (int) move2 + topoffset;
                        if ((clickedx > (xc + wdOfcell / 2 - wdOfcell / 4) && clickedx < ((xc + wdOfcell / 2 - wdOfcell / 4) + wdOfcell / 2)) && (clickedy > (yc + htOfcell / 2 - htOfcell / 4) && clickedy < ((yc + htOfcell / 2 - htOfcell / 4) + htOfcell / 2))) {

                            if (tileStates[x][y] != -1 && tileStates[x][y] != 3) {

                                for (int b = 0; b < tempplacinggrid.length; b++) {
                                    for (int n = 0; n < tempplacinggrid.length; n++) {

                                        tempplacinggrid[b][n] = 0;
                                    }
                                }
                            }
                            valid = true;
                            break;
                        }
                        if (valid) {

                            break;
                        }
                    }
                    move = move - (wdOfcell / 2);
                    move2 = move2 + (htOfcell / 2);
                }

                if (!valid) {
                    for (int b = 0; b < tempplacinggrid.length; b++) {
                        for (int n = 0; n < tempplacinggrid.length; n++) {
                            tempplacinggrid[b][n] = 0;
                        }
                    }

                }
                repaint();
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedx = e.getX();
                int clickedy = e.getY();

                double move = 0;
                double move2 = 0;
                int xc = 0;
                int yc = 0;

                for (int x = 0; x < gridsize; x++) {
                    for (int y = 0; y < gridsize; y++) {
                        xc = -scroller.getHorizontalScrollBar().getValue() + ((y * (int) (wdOfcell)) / 2) + (int) move + globalwidth / 2;
                        yc = -scroller.getVerticalScrollBar().getValue() + ((y * (int) (htOfcell)) / 2) + (int) move2 + topoffset;
                        if ((clickedx > (xc + wdOfcell / 2 - wdOfcell / 4) && clickedx < ((xc + wdOfcell / 2 - wdOfcell / 4) + wdOfcell / 2)) && (clickedy > (yc + htOfcell / 2 - htOfcell / 4) && clickedy < ((yc + htOfcell / 2 - htOfcell / 4) + htOfcell / 2))) {

                            if (tain.lastAdminBrowse) {

                                if (tileStates[x][y] != -1) {
                                    String picked;
                                    String[] choices = {"Water", "Wild land", "Poor land", "Fine land", "Exquisite land"};

                                    picked = (String) JOptionPane.showInputDialog(tain.mainapplet, "Choose wat tile", "", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);



                                    if ("".equals(picked) || picked == null) {
                                        return;
                                    }
                                    int num = 0;
                                    switch (picked) {
                                        case "Water":
                                            num = 3;
                                            break;
                                        case "Wild land":
                                            num = 133;
                                            break;
                                        case "Poor land":
                                            num = 0;
                                            break;
                                        case "Fine land":
                                            num = 1;
                                            break;
                                        case "Exquisite land":
                                            num = 2;
                                            break;

                                    }
                                    tileStates[x][y] = num;
                                    tain.rdb.placeWater(PlotID, tileStates);
                                    return;
                                }
                            }
                            repaint();
                        }
                    }
                    move = move - (wdOfcell / 2);
                    move2 = move2 + (htOfcell / 2);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void setScrollP(JScrollPane scrollPane) {
        scroller = scrollPane;
    }
}
