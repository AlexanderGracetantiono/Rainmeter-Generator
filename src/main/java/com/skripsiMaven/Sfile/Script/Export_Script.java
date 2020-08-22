/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsiMaven.Sfile.Script;

import java.awt.Color;
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

    public Export_Script(String file_name, String namaID, String namaFolder, File dirPath) {
        this.file_name = file_name;
        this.namaID = namaID;
        this.namaFolder = namaFolder;
        this.dir_Path = dirPath.getPath() + "/";
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
        File iniFile = new File(file_address + "\\" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
            // Image File
//            System.out.println("PRINT WH: " + label_stat.get("W") + " H: " + label_stat.get("H"));
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

    public void PutRAMMeasure(String MeasureName, int Xcoordinate, int Ycoordinate, Map<String, Object> label_stat) {
        String file_address = this.dir_Path + this.namaID + "/" + this.namaFolder;
        File iniFile = new File(file_address + "\\" + this.file_name + ".ini");
        System.out.println("ERR16");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
            System.out.println("ERR17");
            int fontSize = ConvertObjectToInt(label_stat.get("FontSize"));
            String MeasureName1 = "Measure" + MeasureName + "Used";
            String MeasureName2 = "Measure" + MeasureName + "Total";
            String MeasureNameCalc = "Measure" + MeasureName + "Calc";
            String MeasureNameString = "Measure" + MeasureName + "String";

            ini.put(MeasureName1, "Measure", "PhysicalMemory");
            ini.put(MeasureName1, "MinValue", 0);

            ini.put(MeasureName2, "Measure", "PhysicalMemory");
            ini.put(MeasureName2, "Total", 1);

            ini.put(MeasureNameCalc, "Measure", "Calc");
            ini.put(MeasureNameCalc, "Formula", MeasureName1 + "/" + MeasureName2 + "*100");

            ini.put(MeasureNameString, "Meter", "String");
            ini.put(MeasureNameString, "MeasureName", MeasureNameCalc);
            ini.put(MeasureNameString, "Text", "%1%");
            ini.put(MeasureNameString, "FontFace", "Times New Roman");
            ini.put(MeasureNameString, "FontSize", fontSize);
            ini.put(MeasureNameString, "FontColor", "FFFFFF");
//            ini.put(MeasureNameString, "StringAlign", "CenterCenter");
            ini.put(MeasureNameString, "X", Xcoordinate);
            ini.put(MeasureNameString, "Y", Ycoordinate);
            ini.put(MeasureNameString, "AntiAlias", 1);
            ini.put(MeasureNameString, "Autoscale", 1);

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

    public void CheckingCoordinat(
            List<Map<String, Integer>> List_label_STATIMG,
            int sum_Labels,
            List<Map<String, Object>> List_label_STATTEXT,
            List<Map<String, Integer>> List_LabelsType
    ) {
        for (int i = 0; i < sum_Labels; i++) {
            Map<String, Integer> label_types = List_LabelsType.get(i);
            int label_type = label_types.get("type");
            if (label_type == 1) {

                Map<String, Integer> label_stat_img = List_label_STATIMG.get(i);
                int X_COOR = label_stat_img.get("X");
                int Y_COOR = label_stat_img.get("Y");
                if (i == 0) {
                    min_x_coord = X_COOR;
                    min_y_coord = Y_COOR;
                } else {
                    if (X_COOR < min_x_coord) {
                        min_x_coord = X_COOR;
                    }
                    if (Y_COOR < min_y_coord) {
                        min_y_coord = Y_COOR;
                    }
                }
            } else if (label_type == 2) {
                Map<String, Object> label_stat_text = List_label_STATTEXT.get(i);
                int X_COOR = ConvertObjectToInt(label_stat_text.get("X"));
                int Y_COOR = ConvertObjectToInt(label_stat_text.get("Y"));
                if (X_COOR < min_x_coord) {
                    min_x_coord = X_COOR;
                }
                if (Y_COOR < min_y_coord) {
                    min_y_coord = Y_COOR;
                }
            }
        }
    }

    public int ConvertObjectToInt(Object values) {
        int valueConvert = (Integer) values;
        return valueConvert;
    }

    public void PutFileImageinRes(
            int sum_Labels,
            List<Map<String, Integer>> List_label_STATIMG,
            List<Map<String, File>> List_image,
            List<Map<String, Object>> List_label_STATTEXT,
            List<Map<String, String>> List_Texts,
            List<Map<String, Integer>> List_LabelsType
    ) {
        CheckingCoordinat(List_label_STATIMG, sum_Labels, List_label_STATTEXT, List_LabelsType);
//        String path_local = "src/images/";
        String file_address = this.dir_Path + this.namaID + "/@Resources/Images/";
        int Basex = min_x_coord;
        int Basey = min_y_coord;
        try {
            System.out.println("BASE X:Y " + min_x_coord + " : " + min_y_coord);
            for (int i = 0; i < sum_Labels; i++) {
                Map<String, Integer> label_types = List_LabelsType.get(i);
                int label_type = label_types.get("type");
                // 1 for image
                // 2 for label
                // Start for Image
                if (label_type == 1) {
                    Map<String, Integer> label_stat = List_label_STATIMG.get(i);
                    Map<String, File> label_image = List_image.get(i);
                    File f = label_image.get("filePath");
                    FileOutputStream out = null;
                    FileInputStream in = null;
                    int cursor;
                    String file_Name_dest = "Icon" + i;
                    in = new FileInputStream(label_image.get("filePath"));
//                System.out.println("ERR1");
                    out = new FileOutputStream(file_address + file_Name_dest + ".png");
//                System.out.println("ERR2");
                    System.out.println("IMAGE X:Y " + label_stat.get("X") + " : " + label_stat.get("Y"));
                    while ((cursor = in.read()) != -1) {
                        out.write(cursor);
                    }
                    if ((min_x_coord == label_stat.get("X")) && (min_y_coord == label_stat.get("Y"))) {
                        PutManyIcons(file_Name_dest, 0, 0, label_stat);
//                    System.out.println("ERR3");
                    } else {
                        int Xcoor = label_stat.get("X") - Basex;
                        int Ycoor = label_stat.get("Y") - Basey;
                        PutManyIcons(file_Name_dest, Xcoor, Ycoor, label_stat);
//                    System.out.println("ERR3");

                    }
                } // Start for Labels
                else if (label_type == 2) {
                    Map<String, Object> label_stat = List_label_STATTEXT.get(i);
                    Map<String, String> label_texts = List_Texts.get(i);
                    switch (label_texts.get("textType")) {
                        case "RAM":

                            break;
                        default:
                        // code block
                    }
//                System.out.println("ERR12");
                    int CoorX = ConvertObjectToInt(label_stat.get("X"));
                    int CoorY = ConvertObjectToInt(label_stat.get("Y"));
                    System.out.println("TEXT X:Y " + CoorX + " : " + CoorY);
                    String file_Name_dest = "RAM" + i;
                    if ((min_x_coord == CoorX) && (min_y_coord == CoorY)) {
//                    System.out.println("ERR14");
                        PutRAMMeasure(file_Name_dest, 0, 0, label_stat);
//                    System.out.println("ERR15");
                    } else {
//                    System.out.println("ERR16");
                        int Xcoor = CoorX - Basex;
                        int Ycoor = CoorY - Basey;
                        PutRAMMeasure(file_Name_dest, Xcoor, Ycoor, label_stat);
//                    System.out.println("ERR3");

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
