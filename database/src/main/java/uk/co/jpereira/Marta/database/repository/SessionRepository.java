package uk.co.jpereira.Marta.database.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.jpereira.Marta.database.model.Session;

import java.math.BigInteger;

/**
 * Created by joao on 4/20/16.
 */
public interface SessionRepository extends CrudRepository<Session, BigInteger> {
}
