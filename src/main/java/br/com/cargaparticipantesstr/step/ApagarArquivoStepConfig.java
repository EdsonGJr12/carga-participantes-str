package br.com.cargaparticipantesstr.step;

import java.io.File;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApagarArquivoStepConfig {
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private File tempFile;
    
    @Bean
    public Step apagarArquivo() {
	return stepBuilderFactory
		.get("apagarArquivo")
		.tasklet((contribution, chunkContext) -> {
    			tempFile.delete();
    			return RepeatStatus.FINISHED;
		})
		.build();
    }
}
