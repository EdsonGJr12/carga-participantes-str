package br.com.cargaparticipantesstr.step.reader;

import java.io.File;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import br.com.cargaparticipantesstr.domain.InstituicaoFinanceira;

@Configuration
public class ArquivoReaderConfig {
    
    @Autowired
    private File tempFile;
    
    @Bean
    public FlatFileItemReader<InstituicaoFinanceira> reader(){
	var resource = new FileSystemResource(tempFile);
	return new FlatFileItemReaderBuilder<InstituicaoFinanceira>()
		.name("arquivoReader")
		.resource(resource)
		.linesToSkip(1)
		.delimited()
		.delimiter(",")
		.names(new String[] {"ispb", "nomeReduzido", "codigo", "participaCompensacao", "acessoPrincipal", "nome", "inicioOperacao"})
		.recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
		.targetType(InstituicaoFinanceira.class)
		.build();
    }
}
