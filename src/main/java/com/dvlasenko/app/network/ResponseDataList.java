package com.dvlasenko.app.network;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDataList<T> {
    private String status;
    private boolean success;
    private List<T> data;
}
