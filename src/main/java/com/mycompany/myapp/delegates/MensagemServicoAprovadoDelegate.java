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
public class MensagemServicoAprovadoDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PedidoInterpreteProcessDTO pedidoInterpreteProcess = (PedidoInterpreteProcessDTO) delegateExecution.getVariable("processInstance");
        PedidoInterpreteDTO mensagemServicoAprovadoDelegate = pedidoInterpreteProcess.getPedidoInterprete();
        String to = mensagemServicoAprovadoDelegate.getEmailCliente();
        String subject = "[AgileKip] Pedido aprovado" + mensagemServicoAprovadoDelegate.getNomeCliente();
        Context context = new Context(Locale.getDefault());
        context.setVariable("mensagemServicoAprovadoDelegate", mensagemServicoAprovadoDelegate);
        String content = templateEngine.process("pedidoInterpreteProcess/mensagemServicoAprovadoSummary", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
