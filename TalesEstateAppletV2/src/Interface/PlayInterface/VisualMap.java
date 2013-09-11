package Interface.PlayInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javax.swing.JScrollPane;

public class VisualMap extends JFXPanel {

    public int gridsize;
    public int[][] gridstates;
    public int[][] tileStates;
    public int[][] tempplacinggrid = null;
    public int wdOfcell;
    public int htOfcell;
    public TileManager tiles = new TileManager();
    boolean build = false;
    public JScrollPane scroller;
    int topoffset = 100;
    int sideoffset = 500;
    int globalwidth;
    int globalheight;
    Graphics2D g2d;

    public VisualMap(int size) throws IOException {
    }

    @Override
    public void paint(final Graphics g) {

        gridsize = tileStates.length;
        wdOfcell = 160;
        htOfcell = 80;
        globalwidth = wdOfcell * tileStates.length;
        globalheight = globalheight * tileStates.length;
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
            int tempx = 0;
            int tempy = 0;

            @Override
            public void mouseDragged(MouseEvent e) {
                scroller.getHorizontalScrollBar().setValue(scroller.getHorizontalScrollBar().getValue() + (tempx - e.getX()));
                tempx = e.getX();
                scroller.getVerticalScrollBar().setValue(scroller.getVerticalScrollBar().getValue() + (tempy - e.getY()));
                tempy = e.getY();
            }
            // some idea of how we can show placing
            // bug grid draws over the it
            // very bad performance

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
                                tempplacinggrid[x][y] = 5;
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
                                gridstates[x][y] = 5;
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
