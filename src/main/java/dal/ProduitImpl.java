package dal;

import bll.BLLException;
import bo.entities.NutritionnalValue;
import bo.entities.Produit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProduitImpl implements DAO<Produit> {
    @Override
    public void insert(Produit data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e){
            throw new DALException("problème de création d'un produit");
        }
    }

    @Override
    public void delete(Produit data) throws DALException {

    }

    @Override
    public void update(Produit data) throws DALException {

    }

    @Override
    public Produit selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public List<Produit> selectAll() throws DALException {
        ResultSet rs;
        List<Produit> produitList = new ArrayList<>();
        try{
            TypedQuery<Produit> selectAll = Settings.getProperty().createQuery("SELECT p FROM Produit p", Produit.class);
            produitList = selectAll.getResultList();
        } catch (DALException e){
            throw new DALException("Problèmer lors de la récupération de la liste de produits");
        }
        return produitList;
    }
}
