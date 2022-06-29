package dal;

import bll.BLLException;
import bo.entities.Produit;

import javax.persistence.EntityManager;
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
        return null;
    }
}
