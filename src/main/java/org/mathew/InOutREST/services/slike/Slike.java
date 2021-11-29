package org.mathew.InOutREST.services.slike;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.rating.Rating;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
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
    Integer id;

    @Column(name = "adresa",nullable = false)
    String adresa;

    @Column(name = "datum_slikanja",nullable = false)
    Date datum;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_autora")
    Accounts autor;

    @ManyToMany
    @JoinTable(name = "pripadnost",joinColumns = @JoinColumn(name = "slike_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "kategorija_id",referencedColumnName = "id"))
    List<Kategorija> kategorije;

    @Column(name = "referenca")
    String referenca;

    @Column(name = "rating")
    Double rating;

    @JsonIgnore
    @OneToMany(mappedBy = "slika")
    Set<Rating> ocene;

}
