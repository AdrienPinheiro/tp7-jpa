package dal;

import bo.entities.Additif;
import bo.entities.Allergene;
import bo.entities.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategorieImpl implements DAO<Categorie> {
    @Override
    public void insert(Categorie data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e){
            throw new DALException("problème de création d'une categorie");
        }
    }

    @Override
    public void delete(Categorie data) throws DALException {

    }

    @Override
    public void update(Categorie data) throws DALException {

    }

    @Override
    public Categorie selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public List<Categorie> selectAll() throws DALException {
        ResultSet rs;
        List<Categorie> categorieList = new ArrayList<>();
        try{
            TypedQuery<Categorie> selectAll = Settings.getProperty().createQuery("SELECT c FROM Categorie c", Categorie.class);
            categorieList = selectAll.getResultList();
        } catch (DALException e){
            throw new DALException("Problèmer lors de la récupération de la liste d'additif");
        }
        return categorieList;
    }
}
