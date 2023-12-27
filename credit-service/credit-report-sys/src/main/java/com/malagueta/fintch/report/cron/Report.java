package com.malagueta.fintch.report.cron;

import com.malagueta.fintch.adapter.CreditRepositoryImpl;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import java.time.LocalDate;
import java.util.List;

@Component
public class Report {
    private CreditRepository creditRepository;
    private CreditRepositoryImpl creditRepositoryimpl;

    public Report(CreditRepository creditRepository){
      this.creditRepository=creditRepository;
    }

    public List<CreditEntity> creditosEmDelinquencia(){

        List<CreditEntity> deliquentes= creditRepository.findByDodateLessThan(LocalDate.now());
        GmailEmailSender gmailEmailSender=new GmailEmailSender();
        Message message= gmailEmailSender.prepereMessage("arsenio.malagueta@mozabanco.co.mz",
                "subject","corpo") ;
        //GmailEmailSender.addAttach();
        return deliquentes;
    }
}
