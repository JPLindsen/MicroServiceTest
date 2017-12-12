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
@RequestMapping(value="inputs")
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
        
        double AGE_num = Double.parseDouble(AGE);
        double RACE_num = Double.parseDouble(RACE);
        double PSA_num = Double.parseDouble(PSA);
        double GLEASON_num = Double.parseDouble(GLEASON);
        
        
        RowData row = new RowData();
         row.put("AGE", AGE_num);
         row.put("RACE", RACE_num);
         row.put("PSA", PSA_num);
         row.put("GLEASON", GLEASON_num);

        BinomialModelPrediction p = model.predictBinomial(row);

        return String.format("{\"model output\":\"%4.3f\"}", p.classProbabilities[1]);
    }
}
