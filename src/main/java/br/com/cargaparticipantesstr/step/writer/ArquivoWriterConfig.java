package br.com.cargaparticipantesstr.step.writer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cargaparticipantesstr.domain.InstituicaoFinanceira;

@Configuration
public class ArquivoWriterConfig {
    
    @Bean
    public JdbcBatchItemWriter<InstituicaoFinanceira> writer(DataSource dataSource){
	return new JdbcBatchItemWriterBuilder<InstituicaoFinanceira>() 
		.dataSource(dataSource)
		.sql("INSERT INTO APP.INSTITUICAO_FINANCEIRA(ISPB, NOME_REDUZIDO, CODIGO, PARTICIPA_COMPENSACAO, ACESSO_PRINCIPAL, NOME, INICIO_OPERACAO) VALUES(?, ?, ?, ?, ?, ?, ?)")
		.itemPreparedStatementSetter(itemPreparedStatementSetter())
		.build();
	 
    }

    private ItemPreparedStatementSetter<InstituicaoFinanceira> itemPreparedStatementSetter() {
	return (instituicaoFinanceira, ps) -> {
	    ps.setString(1, instituicaoFinanceira.getIspb());
	    ps.setString(2, instituicaoFinanceira.getNomeReduzido());
	    ps.setString(3, instituicaoFinanceira.getCodigo());
	    ps.setBoolean(4, instituicaoFinanceira.getParticipaCompensacao().equalsIgnoreCase("SIM"));
	    ps.setString(5, instituicaoFinanceira.getAcessoPrincipal());
	    ps.setString(6, instituicaoFinanceira.getNome());
	    Date date;
	    try {
		date = new SimpleDateFormat("dd/MM/yyyy").parse(instituicaoFinanceira.getInicioOperacao());
	    } catch (ParseException e) {
		throw new RuntimeException(e);
	    }
	    ps.setDate(7, new java.sql.Date(date.getTime()));
	
	};
    }
}
