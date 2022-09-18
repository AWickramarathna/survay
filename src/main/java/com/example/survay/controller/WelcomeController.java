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

    @org.springframework.beans.factory.annotation.Autowired
    private com.example.survay.repository.InforRepository inforRepository;


    @GetMapping("/")
    public String main(Model model) {
        return "description"; //view
    }

    @PostMapping ("/infor")
    public String collectInfor(@ModelAttribute Infor infor,Model model) {
       inforRepository.save(infor);
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
        attributes.setSelectedOption(submissionModel.getChoice1());
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
            model.addAttribute("username",attributes.getUsername());
            return "infor";
        }else{
            return "welcome";
        }

    }

    private Attributes getOption1() {
        java.util.Random rand = new java.util.Random();
        java.util.List<String> gmp = java.util.Arrays.asList("Good","Moderate","Poor");
        java.util.List<String> lmh = java.util.Arrays.asList("Low","Moderate","High");
        java.util.List<String> hl = java.util.Arrays.asList("Low","High");
        java.util.List<String> yn = java.util.Arrays.asList("Yes","No");
        java.util.List<String> fee = java.util.Arrays.asList("Rs 500","Rs 1000","Rs 1500","Rs 2000");


        return Attributes.builder()
                .connectivity(gmp.get(rand.nextInt(10000)%3))
                .automobile_interaction(lmh.get(rand.nextInt(10000)%3))
                .cycle_renting(fee.get(rand.nextInt(10000)%4))
                .Rentability(hl.get(rand.nextInt(10000)%2))
                .road_signs(lmh.get(rand.nextInt(10000)%3))
                .secure_parking(yn.get(rand.nextInt(10000)%2))
                .street_lightning(yn.get(rand.nextInt(10000)%2))
                .tour_guide(yn.get(rand.nextInt(10000)%2))
                .username(String.valueOf(System.currentTimeMillis()))
                .option("A")
                .build();

    }

    private Attributes getOption2() {


        java.util.Random rand = new java.util.Random();
        java.util.List<String> gmp = java.util.Arrays.asList("Poor","Good","Moderate");
        java.util.List<String> lmh = java.util.Arrays.asList("High","Low","Moderate");
        java.util.List<String> hl = java.util.Arrays.asList("High","Low");
        java.util.List<String> yn = java.util.Arrays.asList("No","Yes");
        java.util.List<String> fee = java.util.Arrays.asList("Rs 2000","Rs 1000","Rs 500","Rs 1500");
        return Attributes.builder()
                .connectivity(gmp.get(rand.nextInt(10000)%3))
                .automobile_interaction(lmh.get(rand.nextInt(10000)%3))
                .cycle_renting(fee.get(rand.nextInt(10000)%4))
                .Rentability(hl.get(rand.nextInt(10000)%2))
                .road_signs(lmh.get(rand.nextInt(10000)%3))
                .secure_parking(yn.get(rand.nextInt(10000)%2))
                .street_lightning(yn.get(rand.nextInt(10000)%2))
                .tour_guide(yn.get(rand.nextInt(10000)%2))
                .option("B")
                .build();
    }

}