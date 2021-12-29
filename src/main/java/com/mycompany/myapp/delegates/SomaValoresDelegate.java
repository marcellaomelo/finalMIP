package com.mycompany.myapp.delegates;

import com.mycompany.myapp.service.dto.PedidoInterpreteProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class SomaValoresDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PedidoInterpreteProcessDTO pedidoInterpreteProcess = (PedidoInterpreteProcessDTO) delegateExecution.getVariable("processInstance");
        Boolean valorEstipulado = false;
        if (
            pedidoInterpreteProcess.getPedidoInterprete().getValorMaximoCliente() >=
            pedidoInterpreteProcess.getPedidoInterprete().getValorTotalCobrado()
        ) {
            valorEstipulado = true;
        }
        delegateExecution.setVariable("valorEstipulado", valorEstipulado);
    }
}
