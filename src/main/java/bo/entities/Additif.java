package bo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Additif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle", length = 255)
    private String libelle;

    @ManyToMany(mappedBy = "additifs")
    private Set<Produit> products;

    public Additif() {}

    @Override
    public String toString() {
        return "Additif{" +
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
