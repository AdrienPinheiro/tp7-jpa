package dal;


import bo.entities.Marque;
import bo.entities.NutritionnalValue;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NutritionnalValueImpl implements DAO<NutritionnalValue> {
    @Override
    public void insert(NutritionnalValue data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e){
            throw new DALException("problème de création des nutritionnalValue");
        }
    }

    @Override
    public void delete(NutritionnalValue data) throws DALException {

    }

    @Override
    public void update(NutritionnalValue data) throws DALException {

    }

    @Override
    public NutritionnalValue selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public List<NutritionnalValue> selectAll() throws DALException {
        ResultSet rs;
        List<NutritionnalValue> nutritionnalValuesList = new ArrayList<>();
        try{
            TypedQuery<NutritionnalValue> selectAll = Settings.getProperty().createQuery("SELECT p FROM Produit p", NutritionnalValue.class);
            nutritionnalValuesList = selectAll.getResultList();
        } catch (DALException e){
            throw new DALException("Problèmer lors de la récupération de la liste de nutritionnal values");
        }
        return nutritionnalValuesList;
    }
}
