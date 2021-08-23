package br.com.cargaparticipantesstr.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CargaParticipantesStrJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job cargaParticipanteStr(Step baixarAquivo, 
	    			    Step prepararArquivoParaLeitura, 
	    			    Step importarArquivo,
	    			    Step apagarArquivo) {
	return this.jobBuilderFactory
		.get("cargaParticipanteStr")
		.start(baixarAquivo)
//		.next(prepararArquivoParaLeitura)
		.next(importarArquivo)
		.next(apagarArquivo)
		.incrementer(new RunIdIncrementer())
		.build();
    }
    
}
