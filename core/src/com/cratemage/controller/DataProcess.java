package com.cratemage.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataProcess {
    File file = new File("data.txt");
    public void writeResult(int levelCurrent, String name, int time) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(levelCurrent + "\n");
            writer.write(name + "\n");
            writer.write(String.format("%03d", time) + "\n");

            //System.out.println(levelCurrent + " " + name + " " + time);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readTimePassLevel(int levelCurrent) {
        List<String> timePass = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.ready()){
                String level = reader.readLine();
                String name = reader.readLine();
                String time = reader.readLine();

                if(Objects.equals(level, Integer.toString(levelCurrent))){
                    timePass.add(time + " " + name);
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return timePass;
    }
}
