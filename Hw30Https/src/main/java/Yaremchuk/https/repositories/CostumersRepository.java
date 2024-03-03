package Yaremchuk.https.repositories;




import Yaremchuk.https.entities.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CostumersRepository extends ListCrudRepository<Costumer, Long> {
}
