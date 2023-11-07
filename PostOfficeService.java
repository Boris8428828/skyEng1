package com.example.skyeng.service;

import com.example.skyeng.dto.PostOfficeDTO;
import com.example.skyeng.entity.PostOfficeEntity;
import com.example.skyeng.exception.Exceptions;

public interface PostOfficeService {

    PostOfficeEntity registerNewPostOffice(PostOfficeDTO postOfficeDTO) throws Exceptions;

    PostOfficeEntity findPostOfficeById(Long postId);
}
