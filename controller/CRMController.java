package com.app.controller;


import com.app.service.SmsService;
import com.app.evaluation.Agent;
import com.app.evaluation.Area;
import com.app.evaluation.CustomerVisit;
import com.app.repository.AgentRepository;
import com.app.repository.AreaRepository;
import com.app.repository.CustomerVisitRepository;
import com.app.service.WhatsAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/crm")
public class CRMController {
    private AreaRepository areaRepository;
    private CustomerVisitRepository customerRepository;
    private AgentRepository agentRepository;
    private SmsService smsService;
    private WhatsAppService whatsAppService;
//    private EmailService emailService;


    public CRMController(AreaRepository areaRepository, CustomerVisitRepository customerVisitRepository, AgentRepository agentRepository, SmsService smsService, WhatsAppService whatsAppService) {
        this.areaRepository = areaRepository;
        this.customerRepository = customerVisitRepository;
        this.agentRepository = agentRepository;
        this.smsService = smsService;
        this.whatsAppService = whatsAppService;
//        this.emailService = emailService;
    }
    //http://localhost:8035/api/v1/crm/area
    @GetMapping("/area")
    public ResponseEntity<List<Area>> SearchAgent(@RequestParam int pinCode) {
        List<Area> area = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(area, HttpStatus.OK);
    }
    //http://localhost:8035/api/v1/crm/cus
    @PutMapping("/cus")
    public String allocateAgent(
            @RequestParam int customerId,
            @RequestParam int agentId
    ) {
        Agent agent = null;
      Optional<Agent> opAgent = agentRepository.findById(agentId);
      if(opAgent.isPresent()) {
           agent = opAgent.get();
      }
        CustomerVisit customerVisit = customerRepository.findById(customerId).get();
        customerVisit.setAgent(agent);
      customerRepository.save(customerVisit);
      //sms
      smsService.sendSms("+919368277694", "Agent is now allcated");
      //WhatsApp message
        whatsAppService.sendWhatsAppMessage("+919368277694", "you can send message on whatsapp");
        //Message via Email
//        emailService.sendEmail("banti64963@gmail.com", "Java Development", "Skills Grouth Carrier");
        return "Agent is now Allocated";
    }

}
