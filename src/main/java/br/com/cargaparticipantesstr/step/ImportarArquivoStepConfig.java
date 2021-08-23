package br.com.cargaparticipantesstr.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cargaparticipantesstr.domain.InstituicaoFinanceira;

@Configuration
public class ImportarArquivoStepConfig {
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step importarArquivo(ItemReader<InstituicaoFinanceira> reader, 
	    			ItemProcessor<InstituicaoFinanceira, InstituicaoFinanceira> processor, 
	    			ItemWriter<InstituicaoFinanceira> writer) {
	
	return this.stepBuilderFactory
		.get("importarArquivo")
		.<InstituicaoFinanceira, InstituicaoFinanceira>chunk(50)
		.reader(reader)
		.processor(processor)
		.writer(writer)
		.build();
		
    }
}
