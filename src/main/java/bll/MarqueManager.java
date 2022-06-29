package bll;

import bo.entities.Marque;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class MarqueManager {
    private static volatile MarqueManager instance;
    private static DAO<Marque> impl;
    private MarqueManager(){
        impl = DAOFactory.getMarqueDAO();
    }
    public final static MarqueManager getInstance(){
        if(MarqueManager.instance == null){
            synchronized (MarqueManager.class){
                if(MarqueManager.instance == null){
                    MarqueManager.instance = new MarqueManager();
                }
            }
        }
        return MarqueManager.instance;
    }
    public List<Marque> getMarques() throws BLLException{
        List<Marque> marquesList;
        try{
            marquesList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("erreur récupération des marques", e.getCause());
        }
        return marquesList;
    }
    public void deleteMarque(Marque marque) throws BLLException{
        controlMarque(marque);
        try{
            impl.delete(marque);
        } catch (DALException e){
            throw new BLLException("erreur lors de la suppression d'une marque", e.getCause());
        }
    }
    public void addMarque(Marque marque) throws BLLException{
        controlMarque(marque);
        try{
            impl.insert(marque);
        } catch (DALException e) {
            throw new BLLException("erreur lors de l'ajout d'une marque", e.getCause());
        }
    }
    public void updateMarque(Marque marque) throws BLLException{
        controlMarque(marque);
        try{
            impl.update(marque);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la mise à jour d'une marque", e.getCause());
        }
    }
    public Marque getOneMarque(long id) throws BLLException{
        Marque marque;
        try{
            marque = impl.selectById(id);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la récupération d'une marque - id="+id, e.getCause());
        }
        return marque;
    }
    public void controlMarque(Marque marque) throws BLLException{
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(marque==null){
            throw new BLLException("Marque est null");
        }
    }
}
