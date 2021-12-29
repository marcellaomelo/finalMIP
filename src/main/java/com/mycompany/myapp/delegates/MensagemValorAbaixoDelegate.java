package com.mycompany.myapp.delegates;

import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.PedidoInterpreteDTO;
import com.mycompany.myapp.service.dto.PedidoInterpreteProcessDTO;
import java.util.Locale;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class MensagemValorAbaixoDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PedidoInterpreteProcessDTO pedidoInterpreteProcess = (PedidoInterpreteProcessDTO) delegateExecution.getVariable("processInstance");
        PedidoInterpreteDTO mensagemValorAbaixoDelegate = pedidoInterpreteProcess.getPedidoInterprete();
        String to = mensagemValorAbaixoDelegate.getEmailCliente();
        String subject = "[AgileKip] Valor maior que o estipulado pelo cliente" + mensagemValorAbaixoDelegate.getNomeCliente();
        Context context = new Context(Locale.getDefault());
        context.setVariable("mensagemValorAbaixoDelegate", mensagemValorAbaixoDelegate);
        String content = templateEngine.process("pedidoInterpreteProcess/mensagemValorAbaixoSummaryEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
