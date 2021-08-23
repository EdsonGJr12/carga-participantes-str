package br.com.cargaparticipantesstr.step.processor;

import java.util.Collection;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cargaparticipantesstr.domain.InstituicaoFinanceira;

@Configuration
public class ArquivoProcessorConfig {
    
    @Autowired
    private Collection<String> codigosCadastrado;

    @Bean
    public ItemProcessor<InstituicaoFinanceira, InstituicaoFinanceira> processor() {
	return new CompositeItemProcessorBuilder<InstituicaoFinanceira, InstituicaoFinanceira>()
		.delegates(textosEmMaiusculo(), processadorDeValidacao())
		.build();
    }
    
    private ItemProcessor<InstituicaoFinanceira, InstituicaoFinanceira> textosEmMaiusculo(){
	return instituicaoFinanceira -> {
    		instituicaoFinanceira.setAcessoPrincipal(instituicaoFinanceira.getAcessoPrincipal().trim());
    		var codigo = instituicaoFinanceira.getCodigo().trim();
    		instituicaoFinanceira.setCodigo( codigo.equals("n/a") ? null : codigo);
    		instituicaoFinanceira.setInicioOperacao(instituicaoFinanceira.getInicioOperacao().trim());
    		instituicaoFinanceira.setIspb(instituicaoFinanceira.getIspb().trim());
    		instituicaoFinanceira.setNome(instituicaoFinanceira.getNome().trim().toUpperCase());
    		instituicaoFinanceira.setNomeReduzido(instituicaoFinanceira.getNomeReduzido().trim().toUpperCase());
    		instituicaoFinanceira.setInicioOperacao(instituicaoFinanceira.getInicioOperacao().trim());
    		return instituicaoFinanceira;
	};
    }
    
    private ValidatingItemProcessor<InstituicaoFinanceira> processadorDeValidacao(){
	var processor = new ValidatingItemProcessor<InstituicaoFinanceira>();
	processor.setValidator(validator());
	processor.setFilter(true);
	return processor;
    }
    
    private Validator<InstituicaoFinanceira> validator() {
	return instituicaoFinanceira -> {
	    if(codigosCadastrado.contains(instituicaoFinanceira.getCodigo())) {
		throw new ValidationException("Banco " + instituicaoFinanceira.getCodigo() + " já cadastrado");
	    }
	};
    }

}
