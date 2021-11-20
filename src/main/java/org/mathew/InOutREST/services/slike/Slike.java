package org.mathew.InOutREST.services.slike;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.kategorija.Kategorija;

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
    Set<Kategorija> kategorije;

}
