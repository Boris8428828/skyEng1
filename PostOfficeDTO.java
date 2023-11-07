package com.example.skyeng.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PostOfficeDTO {

    Long id;

    @NotBlank(message = "Поле 'индекс' не должно быть пустым")
    String index;

    @NotBlank(message = "Поле 'название' не должнр быть пустым")
    String name;

    @NotBlank(message = "Поле 'адрес получателя' не должно быть пустым")
    String recipient_address;
}
