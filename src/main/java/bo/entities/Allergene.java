package bo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Allergene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @ManyToMany(mappedBy = "allergens")
    private Set<Produit> products;

    public Allergene() {}

    @Override
    public String toString() {
        return "Allergene{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Produit> getProducts() {
        return products;
    }

    public void setProducts(Set<Produit> products) {
        this.products = products;
    }
}
