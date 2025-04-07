package com.github.gyari03.LootRandomizer.chestLootRandomizer.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SerializeJson {

    public static <T> void saveListToFile(List<T> list, File file){
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(file);
            gson.toJson(list, writer);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Something wrong happened when saving with gson: " + e.getMessage());
        }
    }

    public static <T> List<T> loadListFromFile(File file, Type type){
        List<T> list;
        try{

            Gson gson = new Gson();
            FileReader reader = new FileReader(file);
            Type listType = new TypeToken<List<T>>(){}.getType();
            list = gson.fromJson(reader, type);
            reader.close();
        } catch(IOException e){
            throw new RuntimeException("Something wrong happened when loading with gson: " + e.getMessage());
        }
        return list;
    }

}
