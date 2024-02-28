package Yaremchuk.repositories;




import Yaremchuk.https.entities.Costumer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CostumersRepository extends ListCrudRepository<Costumer, Long> {
}
