package com.example.survay.controller;

import com.example.survay.model.Infor;
import com.example.survay.model.SubmissionModel;
import com.example.survay.Attributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @org.springframework.beans.factory.annotation.Autowired
    private com.example.survay.repository.AttributeRepository attributeRepository;


    @GetMapping("/")
    public String main(Model model) {
        return "description"; //view
    }

    @PostMapping ("/infor")
    public String collectInfor(@ModelAttribute Infor infor,Model model) {
        return "success";
    }

    @GetMapping ("/description")
    public String description(Model model) {
        return "description";
    }

    @GetMapping("/scenario")
    public String startQuestions(Model model){
        Attributes optionA = getOption1();
        Attributes optionB = getOption2();
        optionA.setScenario("1");
        optionB.setScenario("1");
        optionB.setUsername(optionA.getUsername());
        attributeRepository.save(optionA);
        attributeRepository.save(optionB);

        model.addAttribute("scenario",1);
        model.addAttribute("username",optionA.getUsername());
        model.addAttribute("optionA",optionA);
        model.addAttribute("optionB",optionB);
        return "welcome";
    }
    @PostMapping("/scenario")
    public String submitPreferences(@ModelAttribute SubmissionModel submissionModel, Model model){

        Attributes attributes = attributeRepository.findByUsernameAndOptionAndScenario(submissionModel.getUsername(),submissionModel.getOption(),String.valueOf(submissionModel.getScenario()));
        attributes.setSelectedOption(submissionModel.getOption());
        attributeRepository.save(attributes);
        Attributes optionA = getOption1();
        Attributes optionB = getOption2();
        optionB.setUsername(attributes.getUsername());
        optionA.setUsername(attributes.getUsername());
        optionA.setScenario(String.valueOf(submissionModel.getScenario()+1));
        optionB.setScenario(String.valueOf(submissionModel.getScenario()+1));
        attributeRepository.save(optionA);
        attributeRepository.save(optionB);

        model.addAttribute("scenario",submissionModel.getScenario()+1);
        model.addAttribute("username",optionA.getUsername());
        model.addAttribute("optionA",optionA);
        model.addAttribute("optionB",optionB);
        model.addAttribute("scenario", submissionModel.getScenario()+1);


        if(submissionModel.getScenario()==5){
            return "infor";
        }else{
            return "welcome";
        }

    }

    private Attributes getOption1() {
        java.util.Random rand = new java.util.Random();
        java.util.List<String> values = java.util.Arrays.asList("G","M","P");
        java.util.List<String> options = new java.util.ArrayList<>();
        for(int i=0;i<8;i++ ){
            options.add(values.get(rand.nextInt(10000)%3));
        }
        return Attributes.builder()
                .connectivity(options.get(0))
                .automobile_interaction(options.get(1))
                .cycle_renting(options.get(2))
                .Rentability(options.get(3))
                .road_signs(options.get(4))
                .secure_parking(options.get(5))
                .street_lightning(options.get(6))
                .tour_guide(options.get(7))
                .username(String.valueOf(System.currentTimeMillis()))
                .option("A")
                .build();

    }

    private Attributes getOption2() {


        java.util.Random rand = new java.util.Random();
        java.util.List<String> values = java.util.Arrays.asList("M","P","G");
        java.util.List<String> options = new java.util.ArrayList<>();
        for(int i=0;i<8;i++ ){
            options.add(values.get(rand.nextInt(1000000)%3));
        }
        return Attributes.builder()
                .connectivity(options.get(0))
                .automobile_interaction(options.get(1))
                .cycle_renting(options.get(2))
                .Rentability(options.get(3))
                .road_signs(options.get(4))
                .secure_parking(options.get(5))
                .street_lightning(options.get(6))
                .tour_guide(options.get(7))
                //.username(String.valueOf(System.currentTimeMillis()))
                .option("B")
                .build();
    }

}