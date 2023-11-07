package com.example.skyeng.service;

import com.example.skyeng.dto.MailingDTO;
import com.example.skyeng.entity.MailingEntity;
import com.example.skyeng.entity.PostOfficeEntity;
import com.example.skyeng.exception.Exceptions;

import java.util.HashMap;
import java.util.Optional;

public interface MailingService {

    MailingEntity registerNewMailing(MailingDTO mailingDTO) throws Exceptions;

    MailingEntity mailingIntermediateLocation(Long mailId, PostOfficeEntity officeEntity);

    MailingEntity leavingPostOffice(Long mailId);

    MailingEntity receivedMailing(Long mailId);

    HashMap<String, Object> getMovementHistory(Long mailId);

    Optional<MailingEntity> getMailingById(Long mailId);

}
