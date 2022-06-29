package dal;

import bo.entities.Additif;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdditifImpl implements DAO<Additif> {
    @Override
    public void insert(Additif data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e){
            throw new DALException("problème de création d'un additif");
        }
    }

    @Override
    public void delete(Additif data) throws DALException {

    }

    @Override
    public void update(Additif data) throws DALException {

    }

    @Override
    public Additif selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public List<Additif> selectAll() throws DALException {
        ResultSet rs;
        List<Additif> additifList = new ArrayList<>();
        try{
            TypedQuery<Additif> selectAll = Settings.getProperty().createQuery("SELECT a FROM Additif a", Additif.class);
            additifList = selectAll.getResultList();
        } catch (DALException e){
            throw new DALException("Problèmer lors de la récupération de la liste d'additif");
        }
        return additifList;
    }
}
