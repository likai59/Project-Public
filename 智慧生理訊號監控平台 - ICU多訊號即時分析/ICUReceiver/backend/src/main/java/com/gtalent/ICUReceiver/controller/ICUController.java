package com.gtalent.ICUReceiver.controller;

import com.gtalent.ICUReceiver.model.ICUSignal;
import com.gtalent.ICUReceiver.model.ICUSignalPayload;
import com.gtalent.ICUReceiver.repository.ICURepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ICUController {

    private static final Logger log = LoggerFactory.getLogger(ICUController.class);

    @Autowired
    private ICURepository icuRepository;


    // http://localhost:8080/api/upload
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody ICUSignalPayload dto){
        try{
            //DTO = Data Transfer Object
            ICUSignal entity = new ICUSignal(dto);
            //entity.setTimestamp(LocalDateTime.now());
            icuRepository.save(entity);
            log.info("接收到我的資料:", dto);
            return ResponseEntity.ok("資料已接收");
        }catch (Exception e){
            log.error("上傳失敗", e.getMessage());
            return ResponseEntity.status(500).body("伺服器失敗");
        }
    }

    // http://localhost:8080/api/QueryAll
    @GetMapping("/QueryAll")
    public ResponseEntity<List<ICUSignal>> findAll(){
        List<ICUSignal> list = icuRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("lastest")
    public List<ICUSignalPayload> getLastest(@RequestParam int id){
        List<ICUSignal> entity = icuRepository.findTop50ByIdOrderByTimestampDesc(id);
        return entity.stream()
                .map(e -> new ICUSignalPayload(
                        e.getId(),
                        e.getNationalId(),
                        e.getHeartRate(),
                        e.getPulse(),
                        e.getTimestamp().toString(),
                        e.getEcg())).toList();
    }
}
