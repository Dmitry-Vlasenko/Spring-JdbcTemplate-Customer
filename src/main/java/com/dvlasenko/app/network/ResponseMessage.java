package com.dvlasenko.app.network;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    DELETED("Deleted."),
    SMTH_WRONG("Something wrong.");

    private final String responseMsg;
}
