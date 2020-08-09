/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsiMaven.Sfile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TooManyListenersException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author alexa
 */
public class TestUpload extends javax.swing.JFrame {

    /**
     * Creates new form TestUpload
     */
    public TestUpload() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new DropPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageText = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageText.setText("jLabel1");

        imageLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(messageText, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(messageText, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new TestUpload();
    }

    // IDK
    public class DropPane extends JPanel {

        private DropTarget dropTarget;
        private DropTargetHandler dropTargetHandler;
        private Point dragPoint;

        private boolean dragOver = false;
        private BufferedImage target;

//        private JLabel message;
        public DropPane() {
            System.out.println("THIS IS PANE");
            try {
                target = ImageIO.read(new File("TestImage.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            setLayout(new GridBagLayout());
//            messageText = new JLabel();
//            messageText.setFont(messageText.getFont().deriveFont(Font.BOLD, 24));
//            add(messageText);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        protected DropTarget getMyDropTarget() {
            if (dropTarget == null) {
                dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, null);
            }
            return dropTarget;
        }

        protected DropTargetHandler getDropTargetHandler() {
            if (dropTargetHandler == null) {
                dropTargetHandler = new DropTargetHandler();
            }
            return dropTargetHandler;
        }

        @Override
        public void addNotify() {
            super.addNotify();
            System.out.println("THIS IS NOTIFY");
            try {
                getMyDropTarget().addDropTargetListener(getDropTargetHandler());
            } catch (TooManyListenersException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void removeNotify() {
            super.removeNotify();
            getMyDropTarget().removeDropTargetListener(getDropTargetHandler());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (dragOver) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 255, 0, 64));
                g2d.fill(new Rectangle(getWidth(), getHeight()));
                if (dragPoint != null && target != null) {
                    int x = dragPoint.x - 12;
                    int y = dragPoint.y - 12;
                    g2d.drawImage(target, x, y, this);
                }
                g2d.dispose();
            }
        }

        protected void importFiles(final List files) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        FileOutputStream out = null;
                        FileInputStream in = null;
                        int cursor;
                        in = new FileInputStream((File) files.get(0));
                        out = new FileOutputStream("TestImage.png");
                        while ((cursor = in.read()) != -1) {
                            out.write(cursor);
                        }
                        File pathToFile = new File("TestImage.png");
                        boolean exists = pathToFile.exists();
                        long fileSize = pathToFile.length();
//                        Image image = ImageIO.read(pathToFile);
//                        ImageIcon icon = new ImageIcon("TestImage.png"); 
//                        imageLabel.setIcon(icon);
                        messageText.setText("You dropped " + files.size() + " files, SIze: " + fileSize + " EXSIST: " + exists);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            SwingUtilities.invokeLater(run);
        }

        protected class DropTargetHandler implements DropTargetListener {

            protected void processDrag(DropTargetDragEvent dtde) {
                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrag(DnDConstants.ACTION_COPY);
                } else {
                    dtde.rejectDrag();
                }
            }

            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                processDrag(dtde);
                SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
                repaint();
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                processDrag(dtde);
                SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
                repaint();
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                SwingUtilities.invokeLater(new DragUpdate(false, null));
                repaint();
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                String file_address = "/file/id/namaProject/@Resources";
                Path path = Paths.get(file_address);
                System.out.println("THIS IS DROP");

                SwingUtilities.invokeLater(new DragUpdate(false, null));

                Transferable transferable = dtde.getTransferable();

                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    try {

                        List transferData = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
//                        jLabel1.setIcon(new ImageIcon(transferData.get(0)));
                        importFiles(transferData);
                        dtde.dropComplete(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
//                        System.out.println("THIS IS DROP IMAGE");
//                        try {
//                            Image image = (Image) dtd
//                        DataFlavor.imageFlavor
//                            );
//                        jLabel1.setIcon(new ImageIcon(image));
//                        } catch (UnsupportedFlavorException | IOException e) {
//                            e.printStackTrace();
//                        }
//                    }

//                    try {
////                        if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
////                            Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
////                            BufferedImage img = (BufferedImage) cb.getData(DataFlavor.imageFlavor);
////                            System.out.println("FILE IMAGE");
////                            File file = new File("newimage.png");
////                            ImageIO.write(img, "png", file);
////                        }
//                        List<File> transferData = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
//                        if (transferData != null && transferData.size() > 0) {
//                            System.out.println("FILE IMAGE" + transferData.get(0));
//                            File f = transferData.get(0);
//                            ImageIcon iIcon = new ImageIcon(f.getAbsolutePath());
//                            image = iIcon.getImage();
//                            jLabel1.setIcon(iIcon);
////                            System.out.println("FILE IMAGEA");
////                            FileOutputStream fout = new FileOutputStream("gambar1.png");
////                            fout.write(data);
////                            fout.close();
////                            input.close();
////                            BufferedImage img = ;
////                            File source = new File(transferData.get(0));
////                            File dest = new File(file_address);
////                            FileUtils.copyDirectory(source, dest);
//
//                            importFiles(transferData);
//                            dtde.dropComplete(true);
//                        }
//
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
                } else {
                    dtde.rejectDrop();
                }
            }
        }

        public class DragUpdate implements Runnable {

            private boolean dragOver;
            private Point dragPoint;

            public DragUpdate(boolean dragOver, Point dragPoint) {
                this.dragOver = dragOver;
                this.dragPoint = dragPoint;
            }

            @Override
            public void run() {
                DropPane.this.dragOver = dragOver;
                DropPane.this.dragPoint = dragPoint;
                DropPane.this.repaint();
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel messageText;
    // End of variables declaration//GEN-END:variables
}