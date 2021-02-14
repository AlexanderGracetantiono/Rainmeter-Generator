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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.commons.io.FilenameUtils;
import org.ini4j.Profile;
import org.ini4j.Wini;

/**
 *
 * @author alexa
 */
public class import_Script {

    private String folder_address;
    private String file_name;
    private String local_res_path;
    private String namaID;
    private String namaFolder;
    private String dir_Path;
    private File import_folder;

    public import_Script(File dirPath) {
        this.import_folder = dirPath;
        this.file_name = file_name;
        this.namaID = namaID;
        this.namaFolder = namaFolder;
        this.dir_Path = dirPath.getPath() + "/";
        this.folder_address = this.dir_Path + namaID + "/" + namaFolder;
    }
    List<Map<String, Object>> List_IMG_W_Directory = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> List_Label_W_Directory = new ArrayList<Map<String, Object>>();
    Map<String, Object> List_INI_W_Directory = new HashMap<>();
    int once = 0;

    public void listFilesForFolder(File folder) {
        int a = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                a++;
                listFilesForFolder(fileEntry);
            } else {
                if (getExtensionFile(fileEntry).equals("png") || getExtensionFile(fileEntry).equals("jpg")) {
                    Map<String, Object> jlabels_stat = new HashMap<>();
                    jlabels_stat.put("Name", fileEntry.getName());
                    jlabels_stat.put("Directory", fileEntry);
                    List_IMG_W_Directory.add(a, jlabels_stat);
                } else {
                    if (once == 0) {
                        Map<String, Object> jlabels_stat = new HashMap<>();
                        jlabels_stat.put("Name", fileEntry.getName());
                        jlabels_stat.put("Directory", fileEntry);
                        List_INI_W_Directory = jlabels_stat;
                        once += 1;
                    }
                }
            }
        }
    }

    public List<Map<String, Object>> getListDirectory() {
        return List_IMG_W_Directory;

    }

    public List<Map<String, Object>> getListLabel() {
        return List_Label_W_Directory;
    }

    public String getExtensionFile(File fileEntry) {
        File aa = fileEntry;
        String file_extension = aa.getName();
        int lastIndex = file_extension.lastIndexOf('.');
        return file_extension.substring(lastIndex + 1);
    }

    public void ReadFileIni() {
        Map<String, Object> directory_file = List_INI_W_Directory;
        try {
            Wini ini = new Wini((File) directory_file.get("Directory"));
            for (String sectionName : ini.keySet()) {
                if (sectionName.contains("RAM")) {
                    if (sectionName.contains("String")) {
                        String[] arr = sectionName.split("String");
                        String[] arr2 = arr[0].split("RAM");
                        String Measure_name = "MeasureRAM" + arr2[1] + "String";
                        int label_fontsize = ini.get(Measure_name, "FontSize", int.class);
                        int label_x = ini.get(Measure_name, "X", int.class);
                        int label_y = ini.get(Measure_name, "Y", int.class);
                        Map<String, Object> jlabels_stat = new HashMap<>();
                        jlabels_stat.put("textType", "RAM");
                        jlabels_stat.put("fontsize", label_fontsize);
                        jlabels_stat.put("X", label_x);
                        jlabels_stat.put("Y", label_y);
                        List_Label_W_Directory.add(Integer.parseInt(arr2[1]), jlabels_stat);
                    }
                } else if (sectionName.contains("CPU")) {
                    if (sectionName.contains("String")) {
                        String[] arr = sectionName.split("String");
                        String[] arr2 = arr[0].split("CPU");
                        String Measure_name = "MeasureCPU" + arr2[1] + "String";
                        int label_fontsize = ini.get(Measure_name, "FontSize", int.class);
                        int label_x = ini.get(Measure_name, "X", int.class);
                        int label_y = ini.get(Measure_name, "Y", int.class);
                        Map<String, Object> jlabels_stat = new HashMap<>();
                        jlabels_stat.put("textType", "CPU");
                        jlabels_stat.put("fontsize", label_fontsize);
                        jlabels_stat.put("X", label_x);
                        jlabels_stat.put("Y", label_y);
                        List_Label_W_Directory.add(Integer.parseInt(arr2[1]), jlabels_stat);
                    }
                }
            }
            for (int i = 0; i < List_IMG_W_Directory.size(); i++) {
                Map<String, Object> directory_IMG = List_IMG_W_Directory.get(i);
                String file_name = (String) directory_IMG.get("Name");
                String ICON_Name = file_name.substring(0, file_name.length() - 4);
                int img_width = ini.get("ICON" + ICON_Name, "W", int.class);
                int img_height = ini.get("ICON" + ICON_Name, "H", int.class);
                int img_x = ini.get("ICON" + ICON_Name, "X", int.class);
                int img_y = ini.get("ICON" + ICON_Name, "Y", int.class);
                Map<String, Object> jlabels_stat = new HashMap<>();
                jlabels_stat.put("Name", directory_IMG.get("Name"));
                jlabels_stat.put("Directory", directory_IMG.get("Directory"));
                jlabels_stat.put("W", img_width);
                jlabels_stat.put("H", img_height);
                jlabels_stat.put("X", img_x);
                jlabels_stat.put("Y", img_y);
                List_IMG_W_Directory.set(i, jlabels_stat);
            }
        } catch (Exception e) {
            System.err.println("Error Message:"+e.getMessage());
        }
    }
}
