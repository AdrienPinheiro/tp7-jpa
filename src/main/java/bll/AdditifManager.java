package bll;

import bo.entities.Additif;
import bo.entities.Produit;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class AdditifManager {
    private static volatile AdditifManager instance;
    private static DAO<Additif> impl;
    private AdditifManager(){
        impl = DAOFactory.getAdditifDAO();
    }
    public final static AdditifManager getInstance(){
        if(AdditifManager.instance == null){
            synchronized (AdditifManager.class){
                if(AdditifManager.instance == null){
                    AdditifManager.instance = new AdditifManager();
                }
            }
        }
        return AdditifManager.instance;
    }
    public List<Additif> getAdditifs() throws BLLException{
        List<Additif> additifsList;
        try{
            additifsList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("erreur récupération des produits", e.getCause());
        }
        return additifsList;
    }
    public void deleteAdditif(Additif additif) throws BLLException{
        controlProduit(additif);
        try{
            impl.delete(additif);
        } catch (DALException e){
            throw new BLLException("erreur lors de la suppression d'un additif", e.getCause());
        }
    }
    public void addAdditif(Additif additif) throws BLLException{
        controlProduit(additif);
        try{
            impl.insert(additif);
        } catch (DALException e) {
            throw new BLLException("erreur lors de l'ajout d'un additif", e.getCause());
        }
    }
    public void updateAdditif(Additif additif) throws BLLException{
        controlProduit(additif);
        try{
            impl.update(additif);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la mise à jour d'un additif", e.getCause());
        }
    }
    public Additif getOneAdditif(long id) throws BLLException{
        Additif additif;
        try{
            additif = impl.selectById(id);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la récupération d'un additif - id="+id, e.getCause());
        }
        return additif;
    }
    public void controlProduit(Additif additif) throws BLLException{
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(additif==null){
            throw new BLLException("l'additif est null");
        }
    }
}
