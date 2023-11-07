package com.example.skyeng.controller;

import com.example.skyeng.dto.PostOfficeDTO;
import com.example.skyeng.entity.PostOfficeEntity;
import com.example.skyeng.exception.Exceptions;
import com.example.skyeng.service.PostOfficeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Tag(name = "Почтовое отделение")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostOfficeController {

    PostOfficeService postOfficeService;

    public static final String CREATE_POST_OFFICE = "/api/v1/post_office/";
    public static final String GET_POST_OFFICE_BY_ID = "/api/v1/post_office/{postId}/";


    @PostMapping(CREATE_POST_OFFICE)
    public ResponseEntity<?> createPostOffice(@RequestBody @Valid PostOfficeDTO postOfficeDTO) throws Exceptions {
        postOfficeService.registerNewPostOffice(postOfficeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Почтовое отделение успешно создано");
    }

    @GetMapping(GET_POST_OFFICE_BY_ID)
    public PostOfficeEntity getPostOfficeById(@PathVariable Long postId) {
        return postOfficeService.findPostOfficeById(postId);
    }
}
