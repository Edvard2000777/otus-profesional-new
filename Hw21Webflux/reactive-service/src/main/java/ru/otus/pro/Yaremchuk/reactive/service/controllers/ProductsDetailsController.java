package ru.otus.pro.Yaremchuk.reactive.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.pro.Yaremchuk.reactive.service.dtos.ProductDetailsDto;
import ru.otus.pro.Yaremchuk.reactive.service.dtos.ProductDetailsExtendDto;
import ru.otus.pro.Yaremchuk.reactive.service.entities.Product;
import ru.otus.pro.Yaremchuk.reactive.service.services.ProductDetailsService;
import ru.otus.pro.Yaremchuk.reactive.service.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detailed")
@RequiredArgsConstructor
public class ProductsDetailsController {
    private final ProductDetailsService productDetailsService;
    private final ProductsService productsService;
    @GetMapping("/{id}")
    public Mono<ProductDetailsExtendDto> getProductDetailsById(@PathVariable Long id) {
        Mono<Product> productMono = productsService.findById(id);
        Mono<ProductDetailsDto> productDetailsDtoMono = productDetailsService.getProductDetailsById(id);
        ProductDetailsExtendDto productWithDetailDto = new ProductDetailsExtendDto();
        return Mono.zip(productMono, productDetailsDtoMono).map(tuple -> {
            Product product = tuple.getT1();
            ProductDetailsDto details = tuple.getT2();
            productWithDetailDto.setId(product.getId());
            productWithDetailDto.setName(product.getName());
            productWithDetailDto.setDescription(details.getDescription());
            return productWithDetailDto;
        });
    }
    @GetMapping("/list")
    public Flux<ProductDetailsExtendDto> getProductDetailsByIds(@RequestParam List<Long> ids) {
        return Flux.fromIterable(ids)
                .flatMap(id -> getProductDetailsById(id))
                .collectList()
                .flatMapMany(Flux::fromIterable);
    }
    @GetMapping
    public Flux<ProductDetailsExtendDto> getAllProductsDetails() {
        Flux<Product> productsFlux = productsService.findAll();

        return productsFlux.flatMap(product -> {
            Mono<ProductDetailsDto> productDetailsDtoMono = productDetailsService.getProductDetailsById(product.getId());

            return productDetailsDtoMono.map(productDetailsDto -> {
                ProductDetailsExtendDto productDetailsExtendDto = new ProductDetailsExtendDto();
                productDetailsExtendDto.setId(product.getId());
                productDetailsExtendDto.setName(product.getName());
                productDetailsExtendDto.setDescription(productDetailsDto.getDescription());

                return productDetailsExtendDto;
            });
        });
    }

    @GetMapping("/demo")
    public Flux<ProductDetailsDto> getManySlowProducts() {
        Mono<ProductDetailsDto> p1 = productDetailsService.getProductDetailsById(1L);
        Mono<ProductDetailsDto> p2 = productDetailsService.getProductDetailsById(2L);
        Mono<ProductDetailsDto> p3 = productDetailsService.getProductDetailsById(3L);
        return p1.mergeWith(p2).mergeWith(p3);
    }
}
