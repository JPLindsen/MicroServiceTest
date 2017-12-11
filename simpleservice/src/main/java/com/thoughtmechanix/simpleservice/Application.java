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
    public String hello( @PathVariable("AGE") Numeric AGE,
                         @PathVariable("RACE") Numeric RACE,
                         @PathVariable("PSA") Numeric PSA,
                         @PathVariable("GLEASON") Numeric GLEASON) throws Exception {
    	
    	GLM_model_R_1511970560428_1 glmModel = new GLM_model_R_1511970560428_1(); // POJO model
        EasyPredictModelWrapper model = new EasyPredictModelWrapper(glmModel);
        
        RowData row = new RowData();
         row.put("AGE", AGE);
         row.put("RACE", RACE);
         row.put("PSA", PSA);
         row.put("GLEASON", GLEASON);

        BinomialModelPrediction p = model.predictBinomial(row);

        return String.format("{\"model output\":\"%4.3f\"}", p.classProbabilities[1]);
    }
}
