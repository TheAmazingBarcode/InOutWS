package org.mathew.InOutREST.services.rating;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.slike.Slike;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "korisnici_id")
    private Accounts korisnik;


    @ManyToOne
    @JoinColumn(name = "slike_id")
    private Slike slika;

    @Column(name = "ocena")
    private Integer ocena;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rating rating = (Rating) o;
        return id != null && Objects.equals(id, rating.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
