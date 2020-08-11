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
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.ini4j.Wini;

/**
 *
 * @author alexa
 */
public class ExportIniFiles {

    private String folder_address;
    private String file_name;
    private String local_res_path;
    private String namaID;
    private String namaFolder;

    public ExportIniFiles(String file_name, String namaID, String namaFolder) {
        this.file_name = file_name;
        this.namaID = namaID;
        this.namaFolder = namaFolder;
        this.folder_address = "src/file/" + namaID + "/" + namaFolder;
    }

    public static void ExportA(String namaID, String namaFolder, String fileName) {
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

    public void CreateDirectory() {
        try {

            Path path1 = Paths.get("src/file/" + this.namaID + "/@Resources/Images");
            Path path2 = Paths.get("src/file/" + this.namaID + "/" + this.namaFolder);
            Files.createDirectories(path1);
            Files.createDirectories(path2);
            System.out.println("Directory is created!");

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
    }

    public void PutManyIcons(String iconName, int Xcoordinate, int Ycoordinate, Map<String, Integer> label_stat) {
        String file_address = "src/file/" + this.namaID + "/" + this.namaFolder;
        File iniFile = new File(file_address + "/" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
            // Image File
            ini.put("ICON" + iconName, "Meter", "Image");
            ini.put("ICON" + iconName, "ImageName", "#@#Images/" + iconName + ".png");
            ini.put("ICON" + iconName, "W", label_stat.get("W") + "*(#scale#)");
            ini.put("ICON" + iconName, "H", label_stat.get("H") + "*(#scale#)");
            ini.put("ICON" + iconName, "X", Xcoordinate);
            ini.put("ICON" + iconName, "Y", Ycoordinate);
            ini.put("ICON" + iconName, "AntiAlias", "Image");
            ini.store();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void PutFileINIinPROJECT() {
        String file_address = "src/file/" + this.namaID + "/" + this.namaFolder;
        File iniFile = new File(file_address + "/" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);

            ini.put("Rainmeter", "Update", "1000");
            ini.put("Variables", "@include", "#@#Variables.inc");
            ini.put("Variables", "ImageW", 100);
            ini.put("Variables", "ImageH", 100);
            ini.put("Variables", "Scale", 1);
            // Image File
//            ini.put("Frame", "Meter", "Image");
//            ini.put("Frame", "ImageName", "#@#Images/" + "IconA.png");
//            ini.put("Frame", "W", "(#ImageW#*#scale#)");
//            ini.put("Frame", "H", "(#ImageH#*#scale#)");
//            ini.put("Frame", "AntiAlias", "Image");
//            ini.put("Frame", "Greyscale", "Image");
//            ini.put("Frame", "ImageTint", "Image");

            ini.store();
            // To catch basically any error related to writing to the file
            // (The system cannot find the file specified)
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void PutFileImageinRes(int sum_pictures, List<Map<String, Integer>> List_label_stat) {
        String path_local = "src/images/";
        String file_address = "src/file/" + this.namaID + "/@Resources/Images/";
        int Basex = 0;
        int Basey = 0;
        try {
            for (int i = 0; i < sum_pictures; i++) {
//                String[] coordinat = icon_coordinat[i].split(",");;
                Map<String, Integer> label_stat = List_label_stat.get(i);
                FileOutputStream out = null;
                FileInputStream in = null;
                int cursor;
                String file_Name = "TempImage" + i;
                String file_Name_dest = "Icon" + i;
                in = new FileInputStream(new File(path_local + file_Name + ".png"));
                out = new FileOutputStream(file_address + file_Name_dest + ".png");
                while ((cursor = in.read()) != -1) {
                    out.write(cursor);
                }
                if (i == 0) {
                    Basex = label_stat.get("X");
                    Basey = label_stat.get("Y");
//                    Basex = Integer.parseInt(coordinat[0]);
//                    Basey = Integer.parseInt(coordinat[1]);
                    System.out.println("Base x: " + Basex);
                    PutManyIcons(file_Name_dest, 0, 0, label_stat);
                } else {
                    int Xcoor = label_stat.get("X") - Basex;
                    int Ycoor = label_stat.get("Y") - Basey;
                    PutManyIcons(file_Name_dest, Xcoor, Ycoor, label_stat);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
