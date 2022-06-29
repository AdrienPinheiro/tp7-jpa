package bll;

import bo.entities.Additif;
import bo.entities.Allergene;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class AllergeneManager {
    private static volatile AllergeneManager instance;
    private static DAO<Allergene> impl;
    private AllergeneManager(){
        impl = DAOFactory.getAllergeneDAO();
    }
    public final static AllergeneManager getInstance(){
        if(AllergeneManager.instance == null){
            synchronized (AllergeneManager.class){
                if(AllergeneManager.instance == null){
                    AllergeneManager.instance = new AllergeneManager();
                }
            }
        }
        return AllergeneManager.instance;
    }
    public List<Allergene> getAllergenes() throws BLLException{
        List<Allergene> allergenesList;
        try{
            allergenesList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("erreur récupération des allergenes", e.getCause());
        }
        return allergenesList;
    }
    public void deleteAllergene(Allergene allergene) throws BLLException{
        controlAllergene(allergene);
        try{
            impl.delete(allergene);
        } catch (DALException e){
            throw new BLLException("erreur lors de la suppression d'un allergene", e.getCause());
        }
    }
    public void addAllergene(Allergene allergene) throws BLLException{
        controlAllergene(allergene);
        try{
            impl.insert(allergene);
        } catch (DALException e) {
            throw new BLLException("erreur lors de l'ajout d'un allergene", e.getCause());
        }
    }
    public void updateAllergene(Allergene allergene) throws BLLException{
        controlAllergene(allergene);
        try{
            impl.update(allergene);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la mise à jour d'un allergene", e.getCause());
        }
    }
    public Allergene getOneAllergene(long id) throws BLLException{
        Allergene allergene;
        try{
            allergene = impl.selectById(id);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la récupération d'un allergene - id="+id, e.getCause());
        }
        return allergene;
    }
    public void controlAllergene(Allergene allergene) throws BLLException{
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(allergene==null){
            throw new BLLException("l'allergene est null");
        }
    }
}
