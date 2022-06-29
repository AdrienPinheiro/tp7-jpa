package dal;

import bo.entities.Additif;
import bo.entities.Allergene;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AllergeneImpl implements DAO<Allergene> {
    @Override
    public void insert(Allergene data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e){
            throw new DALException("problème de création d'un allergene");
        }
    }

    @Override
    public void delete(Allergene data) throws DALException {

    }

    @Override
    public void update(Allergene data) throws DALException {

    }

    @Override
    public Allergene selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public List<Allergene> selectAll() throws DALException {
        ResultSet rs;
        List<Allergene> allergenesfList = new ArrayList<>();
        try{
            TypedQuery<Allergene> selectAll = Settings.getProperty().createQuery("SELECT a FROM Allergene a", Allergene.class);
            allergenesfList = selectAll.getResultList();
        } catch (DALException e){
            throw new DALException("Problèmer lors de la récupération de la liste d'allergene");
        }
        return allergenesfList;
    }
}
