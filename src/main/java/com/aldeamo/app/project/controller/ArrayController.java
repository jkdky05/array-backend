package com.aldeamo.app.project.controller;

import com.aldeamo.app.project.dto.AnswerRS;
import com.aldeamo.app.project.dto.ErrorRS;
import com.aldeamo.app.project.service.ArrayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArrayController {

    ArrayService arrayService;

    public ArrayController(ArrayService arrayService){
        this.arrayService = arrayService;
    }

    @GetMapping(value = "/answer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> answer(@RequestParam Integer iterationAmount, @RequestParam Long arrayID){
        if(iterationAmount < 1){
            ErrorRS errorRs = new ErrorRS("Iterations cannot be fewer than 1", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorRs, HttpStatus.BAD_REQUEST);
        }

        if(arrayID < 1 || arrayID > 5){
            ErrorRS errorRs = new ErrorRS("ID must be between 1 and 5", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorRs, HttpStatus.BAD_REQUEST);
        }

        AnswerRS answerRS = new AnswerRS(arrayService.getAnswerArray(iterationAmount, arrayID));
        return new ResponseEntity<>(answerRS, HttpStatus.OK);
    }
}
