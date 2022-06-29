package dal;

import bo.entities.Allergene;
import bo.entities.Marque;

import javax.persistence.EntityManager;
import java.util.List;

public class MarqueImpl implements DAO<Marque> {
    @Override
    public void insert(Marque data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e){
            throw new DALException("problème de création d'une marque");
        }
    }

    @Override
    public void delete(Marque data) throws DALException {

    }

    @Override
    public void update(Marque data) throws DALException {

    }

    @Override
    public Marque selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public List<Marque> selectAll() throws DALException {
        return null;
    }
}
