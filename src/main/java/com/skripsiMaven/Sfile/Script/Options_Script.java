/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsiMaven.Sfile.Script;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author alexa
 */
public class Options_Script {

    private String path_local = "src/images/";

    public BufferedImage colorImage(BufferedImage loadImg, int red, int green, int blue) {
        BufferedImage img = new BufferedImage(loadImg.getWidth(), loadImg.getHeight(),
                BufferedImage.TRANSLUCENT);
        Graphics2D graphics = img.createGraphics();
        Color newColor = new Color(red, green, blue, 1 /* alpha needs to be zero */);
        graphics.setXORMode(newColor);
        graphics.drawImage(loadImg, null, 0, 0);
        graphics.dispose();
        return img;
    }

    public ImageIcon Update_Label_PROPS(String fileName, int W, int H) {
        BufferedImage img = null;
        System.out.println("PATH IMAGE: "+fileName);
        try {
            img = ImageIO.read(new File( fileName));
        } catch (Exception e) {
        }
//        Image dimg = img.getScaledInstance(W, H, Image.);
        Image dimg = img.getScaledInstance(W, H, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(dimg);
        return icon;
    }
}
