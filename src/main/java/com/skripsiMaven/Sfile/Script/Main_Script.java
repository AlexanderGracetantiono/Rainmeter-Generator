package com.skripsiMaven.Sfile.Script;

import com.skripsiMaven.Sfile.FrameList.Launcher;
import com.skripsiMaven.Sfile.FrameList.Main_Frame;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main_Script {

    public static void main(String[] args) {
//        SpringApplication.run(SfileApplication.class, args);
        Launcher screen = new Launcher();
        screen.setVisible(true);
        screen.setUndecorated(true);
        screen.setLocationRelativeTo(null);
//        screen.setBounds(200, 200, 1000, 800);
//        screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void CreateTempDirectory() {
        try {
            // create temporary folder
            Path path = Files.createTempDirectory("java-");
            // print path
            System.out.println(path.toAbsolutePath().toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
