package br.com.cargaparticipantesstr.step;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrepararArquivoParaLeituraStepConfig {
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private File tempFile;
    
    @Bean
    public Step prepararArquivoParaLeitura() {
	return this.stepBuilderFactory
		.get("prepararArquivoParaLeitura")
    		.tasklet((contribution, chunkContext) -> {
            	    var path = Paths.get(this.tempFile.getPath());
            	    try(var lines = Files.lines(path)){
            		var replaced = lines
            			.filter(line -> line != null && !line.isBlank())
            			.map(line -> line).collect(Collectors.toList());
            		Files.write(path, replaced);
            	    }
            	    return RepeatStatus.FINISHED;
    		})
    		.build();
    }
    
}
