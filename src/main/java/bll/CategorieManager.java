package bll;

import bo.entities.Allergene;
import bo.entities.Categorie;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class CategorieManager {
    private static volatile CategorieManager instance;
    private static DAO<Categorie> impl;
    private CategorieManager(){
        impl = DAOFactory.getCategorieDAO();
    }
    public final static CategorieManager getInstance(){
        if(CategorieManager.instance == null){
            synchronized (CategorieManager.class){
                if(CategorieManager.instance == null){
                    CategorieManager.instance = new CategorieManager();
                }
            }
        }
        return CategorieManager.instance;
    }
    public List<Categorie> getCategories() throws BLLException{
        List<Categorie> categoriesList;
        try{
            categoriesList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("erreur récupération des categories", e.getCause());
        }
        return categoriesList;
    }
    public void deleteCategorie(Categorie categorie) throws BLLException{
        controlCategorie(categorie);
        try{
            impl.delete(categorie);
        } catch (DALException e){
            throw new BLLException("erreur lors de la suppression d'une categorie", e.getCause());
        }
    }
    public void addCategorie(Categorie categorie) throws BLLException{
        controlCategorie(categorie);
        try{
            impl.insert(categorie);
        } catch (DALException e) {
            throw new BLLException("erreur lors de l'ajout d'une categorie", e.getCause());
        }
    }
    public void updateCategorie(Categorie categorie) throws BLLException{
        controlCategorie(categorie);
        try{
            impl.update(categorie);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la mise à jour d'une categorie", e.getCause());
        }
    }
    public Categorie getOneCategorie(long id) throws BLLException{
        Categorie categorie;
        try{
            categorie = impl.selectById(id);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la récupération d'une categorie - id="+id, e.getCause());
        }
        return categorie;
    }
    public void controlCategorie(Categorie categorie) throws BLLException{
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(categorie==null){
            throw new BLLException("Categorie est null");
        }
    }
}
