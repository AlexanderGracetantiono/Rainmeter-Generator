package com.skripsiMaven.Sfile.Script;

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
        Main_Frame screen = new Main_Frame();
        screen.setVisible(true);
        screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screen.setUndecorated(true);
//        screen.setLocationRelativeTo(null);
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
