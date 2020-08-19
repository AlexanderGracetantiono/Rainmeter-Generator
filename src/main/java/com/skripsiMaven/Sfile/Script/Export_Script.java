/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsiMaven.Sfile.Script;

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
public class Export_Script {

    private String folder_address;
    private String file_name;
    private String local_res_path;
    private String namaID;
    private String namaFolder;
    private String dir_Path;

    public Export_Script(String file_name, String namaID, String namaFolder,File dirPath) {
        this.file_name = file_name;
        this.namaID = namaID;
        this.namaFolder = namaFolder;
        this.dir_Path = dirPath.getPath()+"/";
        this.folder_address = this.dir_Path + namaID + "/" + namaFolder;
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
//            String file_address = "C:/file/" + this.namaID + "/@Resources/Images/";
            Path path1 = Paths.get(this.dir_Path + this.namaID + "/@Resources/Images");
            Path path2 = Paths.get(this.dir_Path + this.namaID + "/" + this.namaFolder);
            Files.createDirectories(path1);
            Files.createDirectories(path2);
            System.out.println("Directory is created!");

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
    }

    public void PutManyIcons(String iconName, int Xcoordinate, int Ycoordinate, Map<String, Integer> label_stat) {
        String file_address = this.dir_Path + this.namaID + "/" + this.namaFolder;
//          String file_address = "C:\\file\\" + this.namaID + "\\@Resources\\Images\\";
        File iniFile = new File(file_address + "\\" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
            // Image File
            System.out.println("PRINT WH: " + label_stat.get("W") + " H: " + label_stat.get("H"));
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
        String file_address = this.dir_Path + this.namaID + "/" + this.namaFolder;
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
    private int min_x_coord = 0;
    private int min_y_coord = 0;

    public void CheckingCoordinat(List<Map<String, Integer>> List_label_stat, int sum_pictures) {
        for (int i = 0; i < sum_pictures; i++) {
            Map<String, Integer> label_stat = List_label_stat.get(i);
            int X_COOR = label_stat.get("X");
            int Y_COOR = label_stat.get("Y");
            if (i == 0) {
                min_x_coord = X_COOR;
                min_y_coord = Y_COOR;
            } else {
                if (X_COOR < min_x_coord) {
                    min_x_coord = X_COOR;
                    min_y_coord = Y_COOR;
                }
            }
        }
    }

    public void PutFileImageinRes(int sum_pictures, List<Map<String, Integer>> List_label_stat, List<Map<String, File>> List_image) {
        CheckingCoordinat(List_label_stat, sum_pictures);
        String path_local = "src/images/";
        String file_address = this.dir_Path + this.namaID + "/@Resources/Images/";
        int Basex = min_x_coord;
        int Basey = min_y_coord;
        try {
            for (int i = 0; i < sum_pictures; i++) {
//                String[] coordinat = icon_coordinat[i].split(",");;
                Map<String, Integer> label_stat = List_label_stat.get(i);
                Map<String, File> label_image = List_image.get(i);
                File f = label_image.get("filePath");
                FileOutputStream out = null;
                FileInputStream in = null;
                int cursor;
                String file_Name_dest = "Icon" + i;
                in = new FileInputStream(label_image.get("filePath"));
                System.out.println("ERR1");
                out = new FileOutputStream(file_address + file_Name_dest + ".png");
                System.out.println("ERR2");
                while ((cursor = in.read()) != -1) {
                    out.write(cursor);
                }
                if ((min_x_coord == label_stat.get("X")) && (min_y_coord == label_stat.get("Y"))) {
//                    System.out.println(f.getName());
                    PutManyIcons(file_Name_dest, 0, 0, label_stat);
                    System.out.println("ERR3");
                } else {
                    int Xcoor = label_stat.get("X") - Basex;
                    int Ycoor = label_stat.get("Y") - Basey;
                    PutManyIcons(file_Name_dest, Xcoor, Ycoor, label_stat);
                    System.out.println("ERR3");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
