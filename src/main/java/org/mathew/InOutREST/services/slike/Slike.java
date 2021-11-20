package org.mathew.InOutREST.services.slike;

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
public class Slike {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "adresa",nullable = false)
    String adresa;

    @Column(name = "datum",nullable = false)
    Date datum;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "korisnici_id")
    Accounts account;

    @ManyToMany
    @JoinTable(name = "pripadnost",joinColumns = @JoinColumn(name = "slika_id"),
            inverseJoinColumns = @JoinColumn(name = "kategorija_id"))
    List<Kategorija> kategorije;

}
