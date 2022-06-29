package bo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinTable(name = "product-ingredients",
    joinColumns = @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ID_INGREDIENT", referencedColumnName = "id"))
    private Set<Ingredient> ingredients;

    @Embedded
    private NutritionnalValue NutritionnalValue;

    @ManyToMany
    @JoinTable(name = "product-allergens",
    joinColumns = @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ID_ALLERGEN", referencedColumnName = "id"))
    private Set<Allergene> allergens;

    @ManyToMany
    @JoinTable(name = "product-additifs",
    joinColumns = @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ID_ADDITIF", referencedColumnName = "id"))
    private Set<Additif> additifs;

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
                ", allergens=" + allergens +
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
        return allergens;
    }

    public void setAllergens(Set<Allergene> allergens) {
        this.allergens = allergens;
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }
}
