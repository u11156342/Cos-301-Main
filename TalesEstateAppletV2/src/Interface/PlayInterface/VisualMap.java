package Interface.PlayInterface;

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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import talesestateappletv2.TransferContainer;

public class VisualMap extends JFXPanel {

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
    TransferContainer tc;
    int oldvalue = 0;
    int currentZoom = 0;
    public int PlotID;
    public boolean placed = false;

    public VisualMap(int size, TransferContainer t) throws IOException {
        tiles = new TileManager(t);
        tc = t;
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


        final VisualMap ref = this;


        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                scroller.getHorizontalScrollBar().setValue(scroller.getHorizontalScrollBar().getValue() + (tempx - e.getX()) / 2);
                tempx = e.getX();
                scroller.getVerticalScrollBar().setValue(scroller.getVerticalScrollBar().getValue() + (tempy - e.getY()) / 2);
                tempy = e.getY();
            }
            // some idea of how we can show placing
            // bug grid draws over the it
            // very bad performance

            @Override
            public void mouseMoved(MouseEvent e) {
                placed = false;
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
                                tempplacinggrid[x][y] = tc.BuildingRef;
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


                            if (tileStates[x][y] != -1 && tileStates[x][y] != 3) {

                                gridstates[x][y] = tc.BuildingRef;
                                //builds
                                if (!placed) {
                                    placed = true;
                                    tc.rdb.PlaceBuilding(PlotID, gridstates);
                                    tc.rdb.MarkBuildingAsPlaced(tc.BuildingLogReference);

                                    tc.BuildingRef = 5;

                                    tc.reference.buildingTokens = new JList();
                                    tc.reference.init(tc);
                                    tc.reference.repaint();
                                }
                                return;
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

    void Build() {
        build = true;
    }

    public void setScrollP(JScrollPane scrollPane) {
        scroller = scrollPane;
    }
}
