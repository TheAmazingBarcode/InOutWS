package org.mathew.InOutREST.services.slike;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.rating.Rating;
import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "slike")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Slike {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "adresa",nullable = false)
    private String adresa;

    @Column(name = "datum_slikanja",nullable = false)
    private Date datum;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_autora")
    private Accounts autor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pripadnost",joinColumns = @JoinColumn(name = "slike_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "kategorija_id",referencedColumnName = "id"))
    private Set<Kategorija> kategorije;

    @Column(name = "referenca")
    private String referenca;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @JsonIgnore
    @OneToMany(mappedBy = "slika")
    private Set<Rating> ocene;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Slike slike = (Slike) o;
        return id != null && Objects.equals(id, slike.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
