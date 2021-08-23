package br.com.cargaparticipantesstr;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CargaParticipantesStrApplication {

    public static void main(String[] args) {
	SpringApplication.run(CargaParticipantesStrApplication.class, args);
    }
    
    @Bean
    public File tempFile() throws IOException {
	return File.createTempFile("carga-participantes", null);
    }
    
    @Bean
    public Collection<String> codigosCadastrados(DataSource dataSource) throws SQLException{
	var codigos = new HashSet<String>();
	
	try(Connection connection = dataSource.getConnection()){
	    var prepareStatment = connection.prepareStatement("select codigo from app.instituicao_financeira");
	    var resultSet = prepareStatment.executeQuery();
	    
	    while(resultSet.next()) {
		codigos.add(resultSet.getString(1));
	    }
	}
	return codigos;
    }

}
