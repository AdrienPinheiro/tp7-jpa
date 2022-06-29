package dal;

import bo.entities.Categorie;
import bo.entities.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IngredientImpl implements DAO<Ingredient> {
    @Override
    public void insert(Ingredient data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e){
            throw new DALException("problème de création d'un ingredient");
        }
    }

    @Override
    public void delete(Ingredient data) throws DALException {

    }

    @Override
    public void update(Ingredient data) throws DALException {

    }

    @Override
    public Ingredient selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public List<Ingredient> selectAll() throws DALException {
        ResultSet rs;
        List<Ingredient> ingredientList = new ArrayList<>();
        try{
            TypedQuery<Ingredient> selectAll = Settings.getProperty().createQuery("SELECT i FROM Ingredient i", Ingredient.class);
            ingredientList = selectAll.getResultList();
        } catch (DALException e){
            throw new DALException("Problèmer lors de la récupération de la liste d'ingredient");
        }
        return ingredientList;
    }
}
