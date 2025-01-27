package it.unibs.ing.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	 
    private static final String FILE_DATI = "dati.json";
	
    public static void salvaDati(Dati dati) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DATI))) {
            writer.write(gson.toJson(dati));
            throw new IOException();
        }
    }
    
    public static Dati caricaDati() throws IOException {
        Gson gson = new Gson();
        File file = new File(FILE_DATI);
        if (!file.exists() || file.length() == 0) {
            return new Dati();
        } 
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return gson.fromJson(reader, Dati.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException(e);
            
        }
    }
}