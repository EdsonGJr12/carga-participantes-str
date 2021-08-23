package br.com.cargaparticipantesstr.step;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaixarArquivoStepConfig {
    
    @Value("${url-arquivo-csv}")
    private String urlArquivoCsv;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private File tempFile;
    
    @Bean
    public Step baixarAquivo() {
	return this.stepBuilderFactory
		.get("baixarArquivo")
		.tasklet((contribution, chunkContext) -> {
        	    FileUtils.copyURLToFile(new URL(urlArquivoCsv), this.tempFile);
        	    return RepeatStatus.FINISHED;
        	})
		.build();
    }
}
