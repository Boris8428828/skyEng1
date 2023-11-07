package com.example.skyeng.service.impl;

import com.example.skyeng.dto.PostOfficeDTO;
import com.example.skyeng.entity.PostOfficeEntity;
import com.example.skyeng.exception.Exceptions;
import com.example.skyeng.repository.PostOfficeRepository;
import com.example.skyeng.service.PostOfficeService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostOfficeServiceImpl implements PostOfficeService {

    ModelMapper modelMapper;
    PostOfficeRepository postOfficeRepository;

    public PostOfficeServiceImpl(ModelMapper modelMapper, PostOfficeRepository postOfficeRepository) {
        this.modelMapper = modelMapper;
        this.postOfficeRepository = postOfficeRepository;
    }


    @Override
    public PostOfficeEntity registerNewPostOffice(PostOfficeDTO postOfficeDTO) throws Exceptions {

        PostOfficeEntity postOfficeEntity = convertToEntity(postOfficeDTO);

        return postOfficeRepository.save(postOfficeEntity);

    }

    @Override
    public PostOfficeEntity findPostOfficeById(Long postId) {
        return postOfficeRepository.findById(postId).orElse(null);
    }

    private PostOfficeEntity convertToEntity(PostOfficeDTO postOfficeDTO) {
        return modelMapper.map(postOfficeDTO, PostOfficeEntity.class);
    }
}
