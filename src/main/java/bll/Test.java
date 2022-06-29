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
                if(line[0] != null){
                    cat.setLibelle(line[0]);
                    List<Categorie> categorieList = categorieManager.getCategories();
                    if(!categorieList.isEmpty()){
                        for(Categorie categorie : categorieList){
                            if(!categorie.getLibelle().toLowerCase().equals(line[0].toLowerCase())){
                                categorieManager.addCategorie(cat);
                            }
                        }
                    } else {
                        categorieManager.addCategorie(cat);
                        prod.setCategorie(cat);
                    }
                }

                Marque mar = new Marque();
                if(line[1] != null){
                    mar.setLibelle(line[1]);
                    List<Marque> marqueList = marqueManager.getMarques();
                    if(!marqueList.isEmpty()){
                        for(Marque marque : marqueList){
                            if(!marque.getLibelle().toLowerCase().equals(line[1].toLowerCase())){
                                marqueManager.addMarque(mar);
                            }
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
                    Allergene all = new Allergene();
                    all.setLibelle(allergenes[0]);
                    allergeneManager.addAllergene(all);
                    List<Allergene> allergeneDBList = allergeneManager.getAllergenes();
                    for(String allergene : allergenes){
                        if(!allergene.isEmpty()){
                            all.setLibelle(allergene);
                            allergenesList.add(all);
                            if(!allergeneDBList.isEmpty()){
                                for(Allergene allergeneDB : allergeneDBList){
                                    if(!allergene.toLowerCase().equals(allergeneDB.getLibelle().toLowerCase())){
                                        allergeneManager.addAllergene(all);
                                    }
                                }
                            } else{
                                allergeneManager.addAllergene(all);
                                allergeneDBList = allergeneManager.getAllergenes();
                            }
                        }
                    }
                }


                Set<Additif> additifsList = new HashSet<>();
                String[] additifs = line[29].trim().split(",");
                if(additifs.length >= 1){
                    Additif add = new Additif();
                    add.setLibelle(additifs[0]);
                    additifManager.addAdditif(add);
                    additifsList.add(add);
                    List<Additif> additifDBList = additifManager.getAdditifs();
                    for(String additif : additifs){
                        if(!additif.isEmpty()){
                            add.setLibelle(additif);
                            additifsList.add(add);
                            if(!additifDBList.isEmpty()){
                                for(Additif additifDB : additifDBList){
                                    if(!additif.toLowerCase().equals(additifDB.getLibelle().toLowerCase())){
                                        additifManager.addAdditif(add);
                                    }
                                }
                            } else{
                                additifManager.addAdditif(add);
                                additifDBList = additifManager.getAdditifs();
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


                categorieManager.addCategorie(cat);
                marqueManager.addMarque(mar);
                produitManager.addProduit(prod);
                System.out.println(allergenesList);
                System.out.println(additifsList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BLLException e) {
            throw new RuntimeException(e);
        }
    }
}
