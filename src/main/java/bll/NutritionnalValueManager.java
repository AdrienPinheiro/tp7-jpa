package bll;

import bo.entities.NutritionnalValue;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class NutritionnalValueManager {
    private static volatile NutritionnalValueManager instance;
    private static DAO<NutritionnalValue> impl;
    private NutritionnalValueManager(){
        impl = DAOFactory.getNutritionnalValueDAO();
    }
    public final static NutritionnalValueManager getInstance(){
        if(NutritionnalValueManager.instance == null){
            synchronized (NutritionnalValueManager.class){
                if(NutritionnalValueManager.instance == null){
                    NutritionnalValueManager.instance = new NutritionnalValueManager();
                }
            }
        }
        return NutritionnalValueManager.instance;
    }
    public List<NutritionnalValue> getNutritionnalsValues() throws BLLException{
        List<NutritionnalValue> nutritionnalsValuesList;
        try{
            nutritionnalsValuesList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("erreur récupération des nutritionnals values", e.getCause());
        }
        return nutritionnalsValuesList;
    }
    public void deleteNutritionnalValue(NutritionnalValue nutritionnalValue) throws BLLException{
        controlNutritionnalValue(nutritionnalValue);
        try{
            impl.delete(nutritionnalValue);
        } catch (DALException e){
            throw new BLLException("erreur lors de la suppression d'une nutritionnal value", e.getCause());
        }
    }
    public void addNutritionnalValue(NutritionnalValue nutritionnalValue) throws BLLException{
        controlNutritionnalValue(nutritionnalValue);
        try{
            impl.insert(nutritionnalValue);
        } catch (DALException e) {
            throw new BLLException("erreur lors de l'ajout d'une nutritionnal value", e.getCause());
        }
    }
    public void updateNutritionnalValue(NutritionnalValue nutritionnalValue) throws BLLException{
        controlNutritionnalValue(nutritionnalValue);
        try{
            impl.update(nutritionnalValue);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la mise à jour d'une nutritionnal value", e.getCause());
        }
    }
    public NutritionnalValue getOneNutritionnalValue(long id) throws BLLException{
        NutritionnalValue nutritionnalValue;
        try{
            nutritionnalValue = impl.selectById(id);
        } catch (DALException e) {
            throw new BLLException("erreur lors de la récupération d'une nutritionnal value - id="+id, e.getCause());
        }
        return nutritionnalValue;
    }
    public void controlNutritionnalValue(NutritionnalValue nutritionnalValue) throws BLLException{
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(nutritionnalValue==null){
            throw new BLLException("Nutritionnal value est null");
        }
    }
}
