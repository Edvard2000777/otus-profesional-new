package Yaremchuk.https.sevices.impl;

import Yaremchuk.https.entities.Costumer;
import Yaremchuk.https.sevices.CostumersService;

import Yaremchuk.repositories.CostumersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class CostumersServiceImpl implements CostumersService {

    private final CostumersRepository costumersRepository;

    @Autowired
    public CostumersServiceImpl(CostumersRepository costumersRepository, RestTemplateBuilder templateBuilder) {
        this.costumersRepository = costumersRepository;
    }

    @Override
    public Optional<Costumer> findById(Long id) {
        return costumersRepository.findById(id);
    }
}
