package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskiesFilterByYear(
            @RequestParam(name ="year", required = false) Integer year,
            @RequestParam(name = "distillery", required = false) String distillery,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region", required = false) String region){
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        if (distillery != null && age != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryNameAndAge(distillery, age), HttpStatus.OK);
        }
        if (distillery != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryName(distillery), HttpStatus.OK);
        }
        if (region != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }
//
//    @GetMapping(value = "/whiskies/distilleries")
//    public ResponseEntity<List<Whisky>> getAllWhiskiesFromDistilleriesFilterByAge(@PathVariable Distillery distillery, @RequestParam(name = "age", required = false) Integer age){
//        if (age != null){
//            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryAndAge(distillery, age));
//        }
//        return new ResponseEntity<>(whiskyRepository.findWhiskyByDistillery(distillery));
//    }


}
