package com.example.demo;


import java.io.OutputStream;

import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApi {

    Model model = JenaEngine.readModel("data/transport.owl");


    @GetMapping("/avion")
    @CrossOrigin(origins = "http://localhost:3000")
    public String afficherAliment() {
        String NS = "";
        if (model != null) {

            NS = model.getNsPrefixURI("");


            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            OutputStream res =  JenaEngine.executeQueryFile(inferedModel, "data/query_Avion.txt");

            
            System.out.println(res);
            return res.toString();


        } else {
            return ("Error when reading model from ontology");
        }
    }
    
    
    
    
    
}





