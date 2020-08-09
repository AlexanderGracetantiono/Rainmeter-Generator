/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsiMaven.Sfile;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.ini4j.Wini;

/**
 *
 * @author alexa
 */
public class ExportIniFiles {

    public static void ExportA(String namaID, String namaProject, String fileName) {
        try {

            Wini ini = new Wini(new File("C:\\Users\\alexa\\Desktop\\Semester 6\\Sfile\\src\\template\\Icon\\Icon BoxA\\IconSingleA.ini"));

            ini.put("block_name", "property_name", "value");
            ini.put("block_name", "property_name_2", 45.6);
            ini.store();
            // To catch basically any error related to writing to the file
            // (The system cannot find the file specified)
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void CreateDirectory(String namaID, String namaProject) {
        try {

            Path path1 = Paths.get("src/file/" + namaID + "/@Resources/Images");
            Path path2 = Paths.get("src/file/" + namaID + "/" + namaProject);
            Files.createDirectories(path1);
            Files.createDirectories(path2);
            System.out.println("Directory is created!");

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
    }

    public static void PutFileINIinPROJECT(String namaID, String namaProject, String fileName) {
        String file_address = "src/file/" + namaID + "/" + namaProject;
        File iniFile = new File(file_address + "/" + fileName + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);

            ini.put("Rainmeter", "Update", "1000");
            ini.put("Variables", "@include", "#@#Variables.inc");
            ini.put("Variables", "ImageW", 100);
            ini.put("Variables", "ImageH", 100);
            ini.put("Variables", "Scale", 1);
            // Image File
            ini.put("Frame", "Meter", "Image");
            ini.put("Frame", "ImageName", "#@#Images/"+"IconA.png");
            ini.put("Frame", "W", "(#ImageW#*#scale#)");
            ini.put("Frame", "H", "(#ImageH#*#scale#)");
            ini.put("Frame", "AntiAlias", "Image");
//            ini.put("Frame", "Greyscale", "Image");
//            ini.put("Frame", "ImageTint", "Image");

            
            ini.store();
            // To catch basically any error related to writing to the file
            // (The system cannot find the file specified)
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void PutFileImageinRes(String namaID) {
        String path_local = "src/images/";
        String file_address = "src/file/" + namaID + "/@Resources/Images/";
        try {
            FileOutputStream out = null;
            FileInputStream in = null;
            int cursor;
            in = new FileInputStream(new File(path_local + "TestImage.png"));
            out = new FileOutputStream(file_address + "IconA.png");
            while ((cursor = in.read()) != -1) {
                out.write(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
