package com.gtalent.ICUReceiver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ICUSignalPayload {
    private int Id;
    private int NationalId;
    private int heartRate;
    private int pulse;
    private String timestamp;
    private List<Double> ecg;
}

