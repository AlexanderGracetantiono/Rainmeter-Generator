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
            Path path1 = Paths.get(this.dir_Path + this.namaID + "/@Resources/Images");
            Path path2 = Paths.get(this.dir_Path + this.namaID + "/" + this.namaFolder);
            Files.createDirectories(path1);
            Files.createDirectories(path2);
            System.out.println("Directory is created!");

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
    }

    public void PutManyIcons(String iconName, int Xcoordinate, int Ycoordinate, Map<String, Object> label_stat) {
        String file_address = this.dir_Path + this.namaID + "/" + this.namaFolder;
        File iniFile = new File(file_address + "\\" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
            System.out.println("DATA LABE: EXP:"+label_stat);
            ini.put("ICON" + iconName, "Meter", "Image");
            ini.put("ICON" + iconName, "ImageName", "#@#Images/" + iconName + ".png");
            ini.put("ICON" + iconName, "W", label_stat.get("W"));
            ini.put("ICON" + iconName, "H", label_stat.get("H"));
            ini.put("ICON" + iconName, "X", Xcoordinate);
            ini.put("ICON" + iconName, "Y", Ycoordinate);
            ini.put("ICON" + iconName, "AntiAlias", "Image");
            ini.store();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void PutCPUMeasure(String MeasureName, int Xcoordinate, int Ycoordinate, Map<String, Object> label_stat) {
        String file_address = this.dir_Path + this.namaID + "/" + this.namaFolder;
        File iniFile = new File(file_address + "\\" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
            int fontSize = ConvertObjectToInt(label_stat.get("FontSize"));
            String MeasureName1 = "Measure" + MeasureName;
            String MeasureNameString = "Measure" + MeasureName + "String";

            ini.put(MeasureName1, "Measure", "CPU");

            ini.put(MeasureNameString, "Meter", "String");
            ini.put(MeasureNameString, "MeasureName", MeasureName1);
            ini.put(MeasureNameString, "Text", "%1%");
            ini.put(MeasureNameString, "FontFace", "Times New Roman");
            ini.put(MeasureNameString, "FontSize", fontSize);
            ini.put(MeasureNameString, "FontColor", "FFFFFF");
            ini.put(MeasureNameString, "X", Xcoordinate);
            ini.put(MeasureNameString, "Y", Ycoordinate);
            ini.put(MeasureNameString, "AntiAlias", 1);
            ini.put(MeasureNameString, "Autoscale", 1);
            ini.store();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void PutRAMMeasure(String MeasureName, int Xcoordinate, int Ycoordinate, Map<String, Object> label_stat) {
        String file_address = this.dir_Path + this.namaID + "/" + this.namaFolder;
        File iniFile = new File(file_address + "\\" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
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
            ini.put(MeasureNameString, "X", Xcoordinate);
            ini.put(MeasureNameString, "Y", Ycoordinate);
            ini.put(MeasureNameString, "AntiAlias", 1);
            ini.put(MeasureNameString, "Autoscale", 1);
            ini.store();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void PutDownloadMeasure(String MeasureName, int Xcoordinate, int Ycoordinate, Map<String, Object> label_stat) {
        String file_address = this.dir_Path + this.namaID + "/" + this.namaFolder;
        File iniFile = new File(file_address + "\\" + this.file_name + ".ini");
        try {
            iniFile.createNewFile();
            Wini ini = new Wini(iniFile);
            int fontSize = ConvertObjectToInt(label_stat.get("FontSize"));
            String MeasureName1 = "Measure" + MeasureName + "Download";
            String MeasureNameString = "Measure" + MeasureName + "String";

            ini.put(MeasureName1, "Measure", "NetIn");

            ini.put(MeasureNameString, "Meter", "String");
            ini.put(MeasureNameString, "MeasureName", MeasureName1);
            ini.put(MeasureNameString, "Text", "%1%");
            ini.put(MeasureNameString, "FontFace", "Times New Roman");
            ini.put(MeasureNameString, "FontSize", fontSize);
            ini.put(MeasureNameString, "FontColor", "FFFFFF");
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
            ini.store();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    private int min_x_coord = 0;
    private int min_y_coord = 0;

    public void CheckingCoordinat(
            List<Map<String, Object>> List_label_STATLABEL,
            int sum_Labels
    ) {
        Options_Script opt = new Options_Script();
        for (int i = 0; i < sum_Labels; i++) {
            Map<String, Object> label_stat_label = List_label_STATLABEL.get(i);
            int X_COOR = opt.ConvertObjectToInt(label_stat_label.get("X"));
            int Y_COOR = opt.ConvertObjectToInt(label_stat_label.get("Y"));
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
        }
    }

    public int ConvertObjectToInt(Object values) {
        int valueConvert = (Integer) values;
        return valueConvert;
    }

    public void PutFileImageinRes(
            int sum_Labels,
            List<Map<String, Object>> List_label_STATLABEL,
            List<Map<String, Object>> List_Labels
    ) {
        Options_Script opt = new Options_Script();
        CheckingCoordinat(List_label_STATLABEL, sum_Labels);
        System.out.println("Jumlah Label: " + sum_Labels + " sizestat: " + List_label_STATLABEL.size() + " sizeLab:" + List_Labels.size());
        String file_address = this.dir_Path + this.namaID + "/@Resources/Images/";
        int Basex = min_x_coord;
        int Basey = min_y_coord;
        try {
            System.out.println("BASE X:Y " + min_x_coord + " : " + min_y_coord);
            for (int i = 0; i < sum_Labels; i++) {
                Map<String, Object> labels_data = List_Labels.get(i);
                int label_type = opt.ConvertObjectToInt(labels_data.get("typeData"));
                if (label_type == 1) {
                    Map<String, Object> label_stat = List_label_STATLABEL.get(i);
                    FileOutputStream out = null;
                    FileInputStream in = null;
                    int cursor;
                    String file_Name_dest = "Icon" + i;
                    in = new FileInputStream(opt.ConvertObjectToFile(labels_data.get("filePath")));
                    out = new FileOutputStream(file_address + file_Name_dest + ".png");
                    System.out.println("IMAGE X:Y " + label_stat.get("X") + " : " + label_stat.get("Y"));
                    while ((cursor = in.read()) != -1) {
                        out.write(cursor);
                    }
                    int CoorX = ConvertObjectToInt(label_stat.get("X"));
                    int CoorY = ConvertObjectToInt(label_stat.get("Y"));
                    if ((min_x_coord == CoorX) && (min_y_coord == CoorY)) {
                        PutManyIcons(file_Name_dest, 0, 0, label_stat);
                    } else {
                        int Xcoor = CoorX - Basex;
                        int Ycoor = CoorY - Basey;
                        PutManyIcons(file_Name_dest, Xcoor, Ycoor, label_stat);
                    }
                } // Start for Labels
                else if (label_type == 2) {
                    Map<String, Object> label_stat = List_label_STATLABEL.get(i);
                    Map<String, Object> label_texts = List_Labels.get(i);
                    int CoorX = ConvertObjectToInt(label_stat.get("X"));
                    int CoorY = ConvertObjectToInt(label_stat.get("Y"));
                    String file_Name_dest;
                    switch (opt.ConvertObjectToString(label_texts.get("textType"))) {
                        case "RAM":
                            file_Name_dest = "RAM" + i;
                            if ((min_x_coord == CoorX) && (min_y_coord == CoorY)) {
                                PutRAMMeasure(file_Name_dest, 0, 0, label_stat);
                            } else {
                                int Xcoor = CoorX - Basex;
                                int Ycoor = CoorY - Basey;
                                PutRAMMeasure(file_Name_dest, Xcoor, Ycoor, label_stat);
                            }
                            break;
                        case "CPU":
                            file_Name_dest = "CPU" + i;
                            if ((min_x_coord == CoorX) && (min_y_coord == CoorY)) {
                                PutCPUMeasure(file_Name_dest, 0, 0, label_stat);
                            } else {
                                int Xcoor = CoorX - Basex;
                                int Ycoor = CoorY - Basey;
                                PutCPUMeasure(file_Name_dest, Xcoor, Ycoor, label_stat);
                            }
                            break;
                        case "DOWN":
                            file_Name_dest = "DOWN" + i;
                            if ((min_x_coord == CoorX) && (min_y_coord == CoorY)) {
                                PutDownloadMeasure(file_Name_dest, 0, 0, label_stat);
                            } else {
                                int Xcoor = CoorX - Basex;
                                int Ycoor = CoorY - Basey;
                                PutDownloadMeasure(file_Name_dest, Xcoor, Ycoor, label_stat);
                            }
                            break;
                        default:
                        // code block
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
