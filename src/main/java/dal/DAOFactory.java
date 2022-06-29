package dal;

import bo.entities.*;

public class DAOFactory {
    public static DAO<Produit> getProduitDAO(){
        return new ProduitImpl();
    }
    public static DAO<Additif> getAdditifDAO(){
            return new AdditifImpl();
    }
    public static DAO<Allergene> getAllergeneDAO(){
        return new AllergeneImpl();
    }
    public static DAO<Categorie> getCategorieDAO(){
        return new CategorieImpl();
    }
    public static DAO<Ingredient> getIngredientDAO(){
        return new IngredientImpl();
    }
    public static DAO<Marque> getMarqueDAO(){
        return new MarqueImpl();
    }
    public static DAO<NutritionnalValue> getNutritionnalValueDAO(){
        return new NutritionnalValueImpl();
    }
}
