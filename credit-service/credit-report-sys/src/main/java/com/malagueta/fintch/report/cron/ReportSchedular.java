package com.malagueta.fintch.report.cron;


import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class ReportSchedular {

    @Autowired
    private CreditRepository creditRepository;

    public void sendTODelinquent(){
        // Replace with the recipient's email address
        String toEmail = "arsenio.malagueta@mozabanco.co.mz,arsenio.malagueta@gmail.com";//

        // Email details
        String subject = "Test Email";
        String body = "<h1>1</h1> <h2></h2>This is a test email sent from Java.";

        body="<style>div {color: #D52B1E;font-family: 'Concord-Regular', 'Century Gothic';font-size: 15px;}table.blueTable {width: 100%;text-align: left;border-collapse: collapse;}table.blueTable td, table.blueTable th {border: 1px solid #3D3D3D;padding: 3px 5px;}table.blueTable tbody td {font-size: 11px;font-family: 'Concord-Light', 'Century Gothic'}table.blueTable tr:nth-child(even) {background: #EEEEEE;}table.blueTable thead {background: #D52B1E;}table.blueTable thead th {font-size: 11px;font-weight: bold;color: #FFFFFF;border-left: 1px solid #3D3D3D;font-family: 'Concord-Regular','Century Gothic'}table.blueTable thead th:first-child{border-left: none;}}</style>"
                +"<div>Investment that will expire in 2 days</div><br/><table class='blueTable'><thead><tr>" +
                "<th>Nome do Cliente</th><th>ID_Credito</th><th>numero de telefone</th><th>Morada</th><th>E-mail</th><th>MONTANTE</th><th>Do Date</th><th>Saldo</th></tr></thead><tbody>";

        StringBuilder bodyBuilder = new StringBuilder();

        List<CreditEntity> creditos= creditRepository.findByDodateLessThan(LocalDate.now().plusDays(2));

        for (CreditEntity credito : creditos) {

            bodyBuilder.append("<tr>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getCliente().getNome());
            bodyBuilder.append("</td>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getId());
            bodyBuilder.append("</td>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getCliente().getTelefone());
            bodyBuilder.append("</td>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getCliente().getMorada());
            bodyBuilder.append("</td>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getCliente().getEmail());
            bodyBuilder.append("</td>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getValor());
            bodyBuilder.append("</td>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getDoDate());
            bodyBuilder.append("</td>");
            bodyBuilder.append("<td>");
            bodyBuilder.append(credito.getValor());//saldo resultado da subtracoes dos pagamento e das taxas
            bodyBuilder.append("</td>");

        }
        bodyBuilder.append("</table>");
        bodyBuilder.append("<br/><br/><br/>");
        body=body+bodyBuilder.toString();

        // Send the email
        GmailEmailSender gmailEmailSender=new GmailEmailSender();
        gmailEmailSender.sendEmailSMTP_HTML(toEmail,toEmail,subject,body);
    }


}
