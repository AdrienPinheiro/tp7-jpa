package bll;

import bo.entities.Categorie;
import bo.entities.Ingredient;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class IngredientManager {
    private static volatile IngredientManager instance;
    private static DAO<Ingredient> impl;
    private IngredientManager(){
        impl = DAOFactory.getIngredientDAO();
    }
    public final static IngredientManager getInstance(){
        if(IngredientManager.instance == null){
            synchronized (IngredientManager.class){
                if(IngredientManager.instance == null){
                    IngredientManager.instance = new IngredientManager();
                }
            }
        }
        return IngredientManager.instance;
    }
    public List<Ingredient> getIngredients() throws BLLException{
        List<Ingredient> ingredientsList;
        try{
            ingredientsList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("erreur récupération des ingredients", e.getCause());
        }
        return ingredientsList;
    }
    public void deleteIngredient(Ingredient ingredient) throws BLLException{
        controlIngredient(ingredient);
        try{
            impl.delete(ingredient);
        } catch (DALException e){
            throw new BLLException("erreur lors de la suppression d'un ingredient", e.getCause());
        }
    }
    public void addIngredient(Ingredient ingredient) throws BLLException{
        controlIngredient(ingredient);
        try{
            impl.insert(ingredient);
        } catch (DALException e) {
            throw new BLLException("erreur lors de l'ajout d'un ingredient", e.getCause());
        }
    }
    public void updateIngredient(Ingredient ingredient) throws BLLException{
        controlIngredient(ingredient);
        try{
            impl.update(ingredient);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la mise à jour d'un ingredient", e.getCause());
        }
    }
    public Ingredient getOneIngredient(long id) throws BLLException{
        Ingredient ingredient;
        try{
            ingredient = impl.selectById(id);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la récupération d'un ingredient - id="+id, e.getCause());
        }
        return ingredient;
    }
    public void controlIngredient(Ingredient ingredient) throws BLLException{
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(ingredient==null){
            throw new BLLException("Ingredient est null");
        }
    }
}
