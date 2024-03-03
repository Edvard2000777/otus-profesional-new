package Yaremchuk.https.sevices;



import Yaremchuk.https.entities.Costumer;

import java.util.Optional;

public interface CostumersService {
    Optional<Costumer> findById(Long id);
}
