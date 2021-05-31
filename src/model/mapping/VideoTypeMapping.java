package model.mapping;

import control.MainFrame;
import util.Util;

import java.io.*;
import java.util.ArrayList;

/**
 * Map VideoType to JSON database.
 * This "JSON database" is a self-defined database and it should only be used in this software.
 * Offering {@code add}, {@code find}, {@code search} methods for video type data
 *
 * @author Zai Song
 * @version 1.0
 * @since 23 April 2021
 */
public class VideoTypeMapping {
    public static final String DATA_PATH = "data/videoType.txt";
    public static final int SUCCESS = 1;
    private static final String ERROR = "System error! Please try again.";

    /**
     * Add videoType to JSON database
     * @throws IOException when IO issue occur
     */
    public static void add(String type) throws IOException {
        if(!find(type)){
            ArrayList<String> types = readAllVideoTypes();
            types.add(type);
            writeAllVideoTypes(types);
        }
    }

    /**
     * find existing type
     * @param type String
     * @return boolean, which stands for containing or not
     * @throws IOException
     */
    public static boolean find(String type) throws IOException {
        ArrayList<String> types = readAllVideoTypes();
        return types.contains(type);
    }

    /**
     * show all type name
     * @param name
     * @return all the types
     */
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

    /**
     * get all video type name from data base
     * @return all type name
     * @throws IOException
     */
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

    /**
     * write all value into database
     * @param arrayList
     * @throws IOException
     */
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
