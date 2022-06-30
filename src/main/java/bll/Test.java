package bll;

import au.com.bytecode.opencsv.CSVReader;
import bo.entities.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Test {
    public static void main(String[] args) {
        ProduitManager produitManager = ProduitManager.getInstance();
        AdditifManager additifManager = AdditifManager.getInstance();
        AllergeneManager allergeneManager = AllergeneManager.getInstance();
        CategorieManager categorieManager = CategorieManager.getInstance();
        IngredientManager ingredientManager = IngredientManager.getInstance();
        MarqueManager marqueManager = MarqueManager.getInstance();
        NutritionnalValueManager nutritionnalValueManager =NutritionnalValueManager.getInstance();
        try{
            CSVReader reader = new CSVReader(new FileReader("D:\\Workplace\\IntelliJ\\traitement-fichier\\src\\main\\resources\\open-food-facts.csv"), '|');

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
            data.remove(data.get(0));
            for (String[] line : data) {
                Produit prod = new Produit();

                Categorie cat = new Categorie();
                if(!line[0].isEmpty()){
                    cat.setLibelle(line[0].toLowerCase());
                    List<Categorie> categorieList = categorieManager.getCategories();
                    if(!categorieList.isEmpty()){
                        boolean categorieExist = false;
                        int index = 0;
                        for(int i = 0; i < categorieList.size(); i++){
                            if(categorieList.get(i).getLibelle().toLowerCase().equals(cat.getLibelle())){
                                categorieExist = true;
                                index = i;
                            }
                        }
                        if(categorieExist && index != 0){
                            prod.setCategorie(categorieList.get(index));
                        } else if (!categorieExist) {
                            categorieManager.addCategorie(cat);
                            prod.setCategorie(cat);
                        }
                    } else{
                        categorieManager.addCategorie(cat);
                        prod.setCategorie(cat);
                    }
                }

                Marque mar = new Marque();
                if(!line[1].isEmpty()){
                    mar.setLibelle(line[1].toLowerCase());
                    List<Marque> marqueList = marqueManager.getMarques();
                    if(!marqueList.isEmpty()){
                        boolean marqueExist = false;
                        int index = 0;
                        for(int i = 0; i < marqueList.size(); i++){
                            if(marqueList.get(i).getLibelle().toLowerCase().equals(mar.getLibelle())){
                                marqueExist = true;
                                index = i;
                            }
                        }
                        if(marqueExist && index != 0){
                            prod.setMarque(marqueList.get(index));
                        } else if (!marqueExist) {
                            marqueManager.addMarque(mar);
                            prod.setMarque(mar);
                        }

                    } else {
                        marqueManager.addMarque(mar);
                        prod.setMarque(mar);
                    }
                }

                NutritionnalValue ntv = new NutritionnalValue();
                ntv.setEnergies(line[5]);
                ntv.setGraisses(line[6]);
                ntv.setSucres(line[7]);
                ntv.setFibres(line[8]);
                ntv.setProteines(line[9]);
                ntv.setSel(line[10]);
                ntv.setVitA(line[11]);
                ntv.setVitD(line[12]);
                ntv.setVitE(line[13]);
                ntv.setVitK(line[14]);
                ntv.setVitC(line[15]);
                ntv.setVitB1(line[16]);
                ntv.setVitB2(line[17]);
                ntv.setVitPP(line[18]);
                ntv.setVitB6(line[19]);
                ntv.setVitB9(line[20]);
                ntv.setVitB12(line[21]);
                ntv.setCalcium(line[22]);
                ntv.setMagnesium(line[23]);
                ntv.setIron(line[24]);
                ntv.setFer(line[25]);
                ntv.setBetaCarotene(line[26]);
                ntv.setHuildeDePalme(Boolean.parseBoolean(line[27]));

                Set<Ingredient> ingredientsList = new HashSet<>();
                String[] ingredients = line[4].trim().split(",.");
                if(ingredients.length >= 1){
                    Ingredient ingre = new Ingredient();
                    int max = Math.min(ingredients[0].length(), 254);
                    ingre.setLibelle(ingredients[0].substring(0, max).trim().replace("_", "").replace(".", "").replace("*", ""));
                    ingredientManager.addIngredient(ingre);
                    List<Ingredient> ingredientDBList = ingredientManager.getIngredients();
                    for(String ingredient : ingredients){
                        if(!ingredient.isEmpty()){
                            max = Math.min(ingredient.length(), 254);
                            ingre.setLibelle(ingredient.substring(0, max).trim().replace("_", "").replace(".", "").replace("*", ""));
                            if(!ingredientDBList.isEmpty()){
                                for(Ingredient ingredientDB : ingredientDBList){
                                    if(!ingredient.toLowerCase().equals(ingredientDB.getLibelle().toLowerCase())){
                                        ingredientManager.addIngredient(ingre);
                                    }
                                    ingredientsList.add(ingre);
                                }
                            } else {
                                ingredientManager.addIngredient(ingre);
                                ingredientDBList = ingredientManager.getIngredients();
                            }
                        }
                    }
                }



                Set<Allergene> allergenesList = new HashSet<>();
                String[] allergenes = line[28].trim().split(",");
                if(allergenes.length >= 1){
                    for (String allergeneStr : allergenes){
                        if(allergeneStr != null && !allergeneStr.isEmpty()){
                            Allergene all = new Allergene();
                            all.setLibelle(allergeneStr.toLowerCase());
                            List<Allergene> allergeneDBList = allergeneManager.getAllergenes();
                            if(!allergeneDBList.isEmpty()){
                                boolean allergeneExist = false;
                                int index = 0;
                                for(int i = 0; i < allergeneDBList.size(); i++){
                                    if(allergeneDBList.get(i).getLibelle().toLowerCase().equals(all.getLibelle())){
                                        allergeneExist = true;
                                        index = i;
                                    }
                                }
                                if(allergeneExist && index != 0){
                                    allergenesList.add(allergeneDBList.get(index));
                                } else if (!allergeneExist) {
                                    allergenesList.add(all);
                                    allergeneManager.addAllergene(all);
                                }
                            } else {
                                allergenesList.add(all);
                                allergeneManager.addAllergene(all);
                            }
                        }
                    }
                }


                Set<Additif> additifsList = new HashSet<>();
                String[] additifs = line[29].trim().split(",");
                if(additifs.length >= 1){
                    for (String additifStr : additifs){
                        if(additifStr != null && !additifStr.isEmpty()){
                            Additif add = new Additif();
                            add.setLibelle(additifStr.toLowerCase());
                            List<Additif> additifDBList = additifManager.getAdditifs();
                            if(!additifDBList.isEmpty()){
                                boolean additifExist = false;
                                int index = 0;
                                for(int i = 0; i < additifDBList.size(); i++){
                                    if(additifDBList.get(i).getLibelle().toLowerCase().equals(add.getLibelle())){
                                        additifExist = true;
                                        index = i;
                                    }
                                }
                                if(additifExist && index != 0){
                                    additifsList.add(additifDBList.get(index));
                                } else if (!additifExist) {
                                    additifsList.add(add);
                                    additifManager.addAdditif(add);
                                }
                            } else {
                                additifManager.addAdditif(add);
                                additifsList.add(add);
                            }
                        }
                    }
                }

                prod.setAllergens(allergenesList);
                prod.setAdditifs(additifsList);

                prod.setNameProduct(line[2]);
                prod.setScoreNutritionnel(line[3]);
                prod.setIngredients(ingredientsList);
                prod.setNutritionnalValue(ntv);

                produitManager.addProduit(prod);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BLLException e) {
            throw new RuntimeException(e);
        }
    }
}
