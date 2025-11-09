package com.gtalent.ICUReceiver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ICUSignal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id ;

    private int nationalId;
    private int heartRate;
    private int pulse;
    private LocalDateTime timestamp;

    @ElementCollection
    private List<Double> ecg;


    public ICUSignal(ICUSignalPayload dto){
        Id = dto.getId();
        nationalId = dto.getNationalId ();
        heartRate = dto.getHeartRate();
        pulse = dto.getPulse();
        timestamp = LocalDateTime.parse(dto.getTimestamp());
        ecg = dto.getEcg();

    }
}


