package com.thoughtmechanix.simpleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.*;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;
//import model_test.GLM_model_R_1511970560428_1;
import com.thoughtmechanix.simpleservice.GLM_model_R_1511970560428_1;

@SpringBootApplication
@RestController
@RequestMapping(value="hello")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value="/{AGE}/{RACE}/{PSA}/{GLEASON}",method = RequestMethod.GET)
    public String hello( @PathVariable("AGE") String AGE,
                         @PathVariable("RACE") String RACE,
                         @PathVariable("PSA") String PSA,
                         @PathVariable("GLEASON") String GLEASON) throws Exception {
    	
    	GLM_model_R_1511970560428_1 glmModel = new GLM_model_R_1511970560428_1(); // POJO model
        EasyPredictModelWrapper model = new EasyPredictModelWrapper(glmModel);
        
        RowData row = new RowData();
         row.put("AGE", (Float.valueOf(AGE)).floatValue());
         row.put("RACE", (Float.valueOf(RACE)).floatValue());
         row.put("PSA", (Float.valueOf(PSA)).floatValue());
         row.put("GLEASON", (Float.valueOf(GLEASON)).floatValue());

        BinomialModelPrediction p = model.predictBinomial(row);

        return String.format("{\"model output\":\"%4.3f\"}", p.classProbabilities[1]);
    }
}
