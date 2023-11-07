package com.example.skyeng.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class MailingDTO<T> {

    Long id;

    @NotBlank(message = "Поле 'тип' не должно быть пустым")
    T typeEnum;

    @NotBlank(message = "Поле 'индекс получателя' не должно быть пустым")
    String recipientIndex;

    @NotBlank(message = "Поле 'адрес получателя' не должно быть пустым")
    String recipientAddress;

    @NotBlank(message = "Поле 'имя получателя' не должно быть пустым")
    String recipientName;

    Boolean is_received=false;
}
