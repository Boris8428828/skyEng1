package com.example.skyeng.controller;

import com.example.skyeng.dto.MailingDTO;
import com.example.skyeng.entity.MailingEntity;
import com.example.skyeng.entity.PostOfficeEntity;
import com.example.skyeng.enumeration.TypeEnum;
import com.example.skyeng.exception.Exceptions;
import com.example.skyeng.service.MailingService;
import com.example.skyeng.service.impl.PostOfficeServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Почтовое отправление")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailingController {

    MailingService mailingService;
    PostOfficeServiceImpl postOfficeService;

    public static final String CREATE_MAILING = "/api/v1/mailing/";
    public static final String GET_TYPE_ENUMS = "/api/v1/mailing/types/";
    public static final String ADD_INTERMEDIATE_LOCATION = "/api/v1/mailing/{mailId}/intermediate/{postId}/";
    public static final String LEAVE_POST_OFFICE = "/api/v1/mailing/leave/{mailId}/";
    public static final String RECEIVE_MAILING = "/api/v1/mailing/receive/{mailId}/";
    public static final String MOVEMENT_HISTORY = "/api/v1/mailing/movement-history/{mailId}/";
    public static final String GET_MAILING_BY_ID = "/api/v1/mailing/{mailId}/";

    @PostMapping(CREATE_MAILING)
    public ResponseEntity<?> createMailing(@RequestBody @Valid MailingDTO mailingDTO) throws Exceptions {
        mailingService.registerNewMailing(mailingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Почтовое отправление успешно создано");
    }

    @GetMapping(GET_TYPE_ENUMS)
    public HashMap<String, Object> getTypes() {
        HashMap<String, Object> response = new HashMap<>();

        response.put("list", TypeEnum.getTypes());

        return response;
    }

    @PostMapping(ADD_INTERMEDIATE_LOCATION)
    public ResponseEntity<MailingEntity> arriveAtIntermediateLocation(@PathVariable Long mailId,
                                                                      @PathVariable Long postId) {
        PostOfficeEntity postOffice = postOfficeService.findPostOfficeById(postId);

        MailingEntity mailingEntity = mailingService.mailingIntermediateLocation(mailId, postOffice);
        return ResponseEntity.ok(mailingEntity);
    }

    @PostMapping(LEAVE_POST_OFFICE)
    public ResponseEntity<MailingEntity> leaveFromPostOffice(@PathVariable Long mailId) {
        MailingEntity mailingEntity = mailingService.leavingPostOffice(mailId);
        return ResponseEntity.ok(mailingEntity);
    }

    @PostMapping(RECEIVE_MAILING)
    public ResponseEntity<MailingEntity> markAsReceived(@PathVariable Long mailId) {
        MailingEntity mailingEntity = mailingService.receivedMailing(mailId);
        return ResponseEntity.ok(mailingEntity);
    }

    @GetMapping(MOVEMENT_HISTORY)
    public Object getMovementHistory(@PathVariable Long mailId) {
        return mailingService.getMovementHistory(mailId);
    }

    @GetMapping(GET_MAILING_BY_ID)
    public Optional<MailingEntity> getMailingById(@PathVariable Long mailId) {
        return mailingService.getMailingById(mailId);
    }
}
