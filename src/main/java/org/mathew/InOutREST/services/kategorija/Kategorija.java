package org.mathew.InOutREST.services.kategorija;

import lombok.*;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.slike.Slike;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "kategorija")
public class Kategorija {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "naziv",nullable = false)
    String naziv;

    @ManyToMany(mappedBy = "kategorije")
    Set<Slike> slikeIzKategorije;

}
