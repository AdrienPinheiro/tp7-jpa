package bll;

import au.com.bytecode.opencsv.CSVReader;
import bo.entities.Categorie;
import bo.entities.Ingredient;
import bo.entities.Marque;
import bo.entities.Produit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ProduitManager test = ProduitManager.getInstance();
        try{
            CSVReader reader = new CSVReader(new FileReader("D:\\Workspace\\IntelliJ\\traitement-fichier\\src\\main\\resources\\open-food-facts.csv"), '|');

            List<String[]> data = new ArrayList<>();

            String[] nextLine = null;
            while ((nextLine = reader.readNext()) != null) {
                int size = nextLine.length;
                // ligne vide
                if (size == 0) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if (debut.length() == 0 && size == 1) {
                    continue;
                }
                // ligne de commentaire
                if (debut.startsWith("#")) {
                    continue;
                }
                data.add(nextLine);
            }
            for (String[] line : data) {
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
