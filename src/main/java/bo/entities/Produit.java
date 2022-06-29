package bo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "id_marque")
    private Marque marque;

    @Column(name = "nameProduct", nullable = false)
    private String nameProduct;

    @Column(name = "scoreNutritionnel")
    private String scoreNutritionnel;

    @ManyToMany
    @JoinTable(name = "produit_ingredients", joinColumns = @JoinColumn(name = "produit_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(name = "produit_additifs", joinColumns = @JoinColumn(name = "produit_id"), inverseJoinColumns = @JoinColumn(name = "additif_id"))
    private Set<Additif> additifs;

    @ManyToMany
    @JoinTable(name = "produit_allergenes", joinColumns = @JoinColumn(name = "produit_id"), inverseJoinColumns = @JoinColumn(name = "allergene_id"))
    private Set<Allergene> allergenes;

    @Embedded
    private NutritionnalValue NutritionnalValue;



    public Produit() {}

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", categorie=" + categorie +
                ", marque=" + marque +
                ", nameProduct='" + nameProduct + '\'' +
                ", scoreNutritionnel='" + scoreNutritionnel + '\'' +
                ", ingredients=" + ingredients +
                ", NutritionnalValue=" + NutritionnalValue +
                ", allergens=" + allergenes +
                ", additifs=" + additifs +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getScoreNutritionnel() {
        return scoreNutritionnel;
    }

    public void setScoreNutritionnel(String scoreNutritionnel) {
        this.scoreNutritionnel = scoreNutritionnel;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public bo.entities.NutritionnalValue getNutritionnalValue() {
        return NutritionnalValue;
    }

    public void setNutritionnalValue(bo.entities.NutritionnalValue nutritionnalValue) {
        NutritionnalValue = nutritionnalValue;
    }

    public Set<Allergene> getAllergens() {
        return allergenes;
    }

    public void setAllergens(Set<Allergene> allergens) {
        this.allergenes = allergens;
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }
}
