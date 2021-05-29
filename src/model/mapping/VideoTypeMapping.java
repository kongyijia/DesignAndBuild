package model.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import control.MainFrame;
import model.Administrator;
import model.Client;
import model.Coach;
import model.User;
import util.Util;

import java.io.*;
import java.util.ArrayList;

public class VideoTypeMapping {
    public static final String DATA_PATH = "data/videoType.txt";
    public static final int SUCCESS = 1;
    private static final String ERROR = "System error! Please try again.";

    public static void add(String type) throws IOException {
        if(!find(type)){
            ArrayList<String> types = readAllVideoTypes();
            types.add(type);
            writeAllVideoTypes(types);
        }
    }

    public static boolean find(String type) throws IOException {
        ArrayList<String> types = readAllVideoTypes();
        return types.contains(type);
    }

    public static ArrayList<String> search (String name){
        ArrayList<String> types = new ArrayList<>();
        try {
            for (String item : readAllVideoTypes()){
                if (item.contains(name))
                    types.add(item);
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        return types;
    }

    public static ArrayList<String> readAllVideoTypes() throws IOException {
        ArrayList<String> types = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(DATA_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while (oneLine != null){
                types.add(oneLine);
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            Util.showDialog(MainFrame.getInstance(), ERROR);
        }
        return types;
    }

    public static void writeAllVideoTypes(ArrayList<String> arrayList) throws IOException{
        try {
            FileWriter fileWriter = new FileWriter(DATA_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String oneLine : arrayList){
                bufferedWriter.write(oneLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e){
            Util.showDialog(MainFrame.getInstance(), ERROR);
        }
    }

}
