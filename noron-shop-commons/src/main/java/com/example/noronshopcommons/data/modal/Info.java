package com.example.noronshopcommons.data.modal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Info {
    private String code;
    private String name;
    private String type;
    private String value;
}
