package ru.otus.pro.Yaremchuk.reactive.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailsExtendDto {
    private Long id;
    private String name;
    private String description;
}
