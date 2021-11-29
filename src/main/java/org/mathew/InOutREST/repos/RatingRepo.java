package org.mathew.InOutREST.repos;

import org.mathew.InOutREST.services.rating.Rating;
import org.mathew.InOutREST.services.slike.Slike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepo extends CrudRepository<Rating,Integer> {
    List<Rating> findBySlika(Slike slika);
}
