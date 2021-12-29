package com.mycompany.myapp.delegates;

import com.mycompany.myapp.service.dto.PedidoInterpreteProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class VerificaStatusTrajetoDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PedidoInterpreteProcessDTO pedidoInterpreteProcess = (PedidoInterpreteProcessDTO) delegateExecution.getVariable("processInstance");
        Boolean tipoAuxilio = false;
        if (pedidoInterpreteProcess.getPedidoInterprete().getTipoAuxilio().contains("Interprete Gestual")) {
            tipoAuxilio = true;
        }
        delegateExecution.setVariable("tipoAuxilio", tipoAuxilio);
    }
}
