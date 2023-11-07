package com.example.skyeng.service.impl;

import com.example.skyeng.dto.MailingDTO;
import com.example.skyeng.entity.MailingEntity;
import com.example.skyeng.entity.PostOfficeEntity;
import com.example.skyeng.enumeration.TypeEnum;
import com.example.skyeng.exception.Exceptions;
import com.example.skyeng.repository.MailingRepository;
import com.example.skyeng.service.MailingService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MailingServiceImpl implements MailingService {

    ModelMapper modelMapper;
    MailingRepository mailingRepository;

    public MailingServiceImpl(ModelMapper modelMapper, MailingRepository mailingRepository) {
        this.modelMapper = modelMapper;
        this.mailingRepository = mailingRepository;
    }


    @Override
    public MailingEntity registerNewMailing(MailingDTO mailingDTO) {

        MailingEntity mailingEntity = convertToEntity(mailingDTO);

        int typeId = Integer.parseInt(mailingDTO.getTypeEnum().toString());
        String type = TypeEnum.getName(typeId);

        mailingEntity.setTypeEnum(type);

        return mailingRepository.save(mailingEntity);
    }

    @Override
    public MailingEntity mailingIntermediateLocation(Long mailId, PostOfficeEntity officeEntity) {
        MailingEntity mailingEntity = mailingRepository.findById(mailId).orElse(null);

        mailingEntity.setPostOfficeEntity(officeEntity);
        mailingEntity.getMovementHistory().add(officeEntity);
        return mailingRepository.save(mailingEntity);
    }

    @Override
    public MailingEntity leavingPostOffice(Long mailId) {

        MailingEntity mailingEntity = mailingRepository.findById(mailId).orElse(null);

        mailingEntity.setPostOfficeEntity(null);

        return mailingRepository.save(mailingEntity);
    }

    @Override
    public MailingEntity receivedMailing(Long mailId) {

        MailingEntity mailingEntity = mailingRepository.findById(mailId).orElse(null);

        mailingEntity.setIs_received(true);
        return mailingRepository.save(mailingEntity);
    }

    @Override
    public HashMap<String, Object> getMovementHistory(Long mailId) {
        MailingEntity mailingEntity = mailingRepository.findById(mailId).orElse(null);

        HashMap<String, Object> response = new HashMap<>();
        response.put("is_received", mailingEntity.getIs_received());
        response.put("movement_history", mailingEntity.getMovementHistory());
        return response;

    }

    @Override
    public Optional<MailingEntity> getMailingById(Long mailId) {
        return mailingRepository.findById(mailId);
    }

    private MailingEntity convertToEntity(MailingDTO mailingDTO) {
        return modelMapper.map(mailingDTO, MailingEntity.class);
    }
}
