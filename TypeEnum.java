package com.example.skyeng.enumeration;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public enum TypeEnum {
    LTR("Письмо"),
    PKG("Посылка"),
    PRP("Бандероль"),
    PCD("Открытка");

    private final String value;

    private static final List<HashMap<String, Object>> TYPE_ENUMS;

    static {
        TYPE_ENUMS = new ArrayList<>();

        for (TypeEnum typeEnum : TypeEnum.values()){
            HashMap<String, Object> response = new HashMap<>();

            response.put("id", typeEnum.ordinal());
            response.put("code", typeEnum.name());
            response.put("name", typeEnum.value);

            TYPE_ENUMS.add(response);
        }
    }

    public static List<HashMap<String, Object>> getTypes(){
        return Collections.unmodifiableList(TYPE_ENUMS);
    }

    public static String getName (int typeId) {return String.valueOf(TYPE_ENUMS.get(typeId).get("name"));
    }

}
