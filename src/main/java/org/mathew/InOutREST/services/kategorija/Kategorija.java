package org.mathew.InOutREST.services.kategorija;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.mathew.InOutREST.services.slike.Slike;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "kategorija")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Kategorija {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "naziv",nullable = false)
    String naziv;

    @ManyToMany(mappedBy = "kategorije")
    @JsonIgnore
    List<Slike> slikeIzKategorije;

}
