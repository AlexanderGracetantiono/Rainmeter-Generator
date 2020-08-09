package com.skripsiMaven.Sfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfileApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SfileApplication.class, args);
        TestLayout screen = new TestLayout();
        screen.setVisible(true);
        screen.setLocationRelativeTo(null);
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
