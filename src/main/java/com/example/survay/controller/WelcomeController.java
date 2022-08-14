package com.example.survay.controller;

import com.example.survay.model.Infor;
import com.example.survay.model.SubmissionModel;
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
        model.addAttribute("scenario",1);
        model.addAttribute("choiceList","");
        return "welcome";
    }
    @PostMapping("/scenario")
    public String submitPreferences(@ModelAttribute SubmissionModel submissionModel, Model model){
        submissionModel.setChoiceList(submissionModel.getChoiceList().replaceAll("[\\[\\]]", "")
                .replaceAll("]","")+"," + submissionModel.getChoices().replace("[","").replace("]","").replace(";",""));

        //submissionModel.setChoiceList(submissionModel.getChoices());
        model.addAttribute("scenario", Integer.parseInt(submissionModel.getScenario())+1);
        model.addAttribute("choiceList", submissionModel.getChoiceList());

        if(submissionModel.getScenario().equals("5")){
            return "infor";
        }else{
            return "welcome";
        }

    }

}