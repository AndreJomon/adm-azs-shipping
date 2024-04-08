package azship.fretecrud.repository;

import azship.fretecrud.model.Frete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends MongoRepository<Frete, String> {


}
