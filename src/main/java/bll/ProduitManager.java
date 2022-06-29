package bll;

import bo.entities.Produit;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class ProduitManager {
    private static volatile ProduitManager instance;
    private static DAO<Produit> impl;
    private ProduitManager(){
        impl = DAOFactory.getProduitDAO();
    }
    public final static ProduitManager getInstance(){
        if(ProduitManager.instance == null){
            synchronized (ProduitManager.class){
                if(ProduitManager.instance == null){
                    ProduitManager.instance = new ProduitManager();
                }
            }
        }
        return ProduitManager.instance;
    }
    public List<Produit> getProduits() throws BLLException{
        List<Produit> lesProduits;
        try{
            lesProduits = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("erreur récupération des produits", e.getCause());
        }
        return lesProduits;
    }
    public void deleteProduit(Produit produit) throws BLLException{
        controlProduit(produit);
        try{
            impl.delete(produit);
        } catch (DALException e){
            throw new BLLException("erreur lors de la suppression d'un produit", e.getCause());
        }
    }
    public void addProduit(Produit produit) throws BLLException{
        controlProduit(produit);
        try{
            impl.insert(produit);
        } catch (DALException e) {
            throw new BLLException("erreur lors de l'ajout d'un produit", e.getCause());
        }
    }
    public void updateProduit(Produit produit) throws BLLException{
        controlProduit(produit);
        try{
            impl.update(produit);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la mise à jour d'un produit", e.getCause());
        }
    }
    public Produit getOneProduit(long id) throws BLLException{
        Produit produit;
        try{
            produit = impl.selectById(id);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la récupération d'un produit - id="+id, e.getCause());
        }
        return produit;
    }
    public void controlProduit(Produit produit) throws BLLException{
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(produit==null){
            throw new BLLException("le produit est null");
        }
    }

}
