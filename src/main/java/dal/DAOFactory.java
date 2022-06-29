package dal;

import bo.entities.Produit;

public class DAOFactory {
    public static DAO<Produit> getProduitDAO(){
        return new ProduitImpl();
    }
}
