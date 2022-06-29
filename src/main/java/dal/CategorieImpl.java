package dal;

import bo.entities.Allergene;
import bo.entities.Categorie;

import javax.persistence.EntityManager;
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
        return null;
    }
}
