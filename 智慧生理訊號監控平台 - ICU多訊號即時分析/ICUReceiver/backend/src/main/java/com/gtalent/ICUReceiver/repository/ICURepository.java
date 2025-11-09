package com.gtalent.ICUReceiver.repository;

import com.gtalent.ICUReceiver.model.ICUSignal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICURepository extends JpaRepository<ICUSignal, Integer> {
    List<ICUSignal> findTop50ByIdOrderByTimestampDesc(int id);
    /*
    SELECT * FROM ICUsignal
    WHERE id = ?
    ORDER BY timestamp DESC
    LIMIT 50;
     */
}
