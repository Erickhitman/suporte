package com.senai.suporte.suporte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import javax.sql.DataSource;
import java.sql.*;

//Constantes para a conexão com o banco de dados
@Configuration
public class DataConfiguration {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/suporte"
            + "?createDatabaseIfnotExists=true&useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String USUARIO = "root";
    private static final String SENHA =
            System.getenv().getOrDefault("DB_PASSWORD", "senai@126");


    //Metodo que retorna a fonte de dados
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(USUARIO);
        dataSource.setPassword(SENHA);
        return dataSource;
    }


    //Metodo que retorna o adaptador JPA
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform(String.valueOf(Database.MYSQL));
        adapter.setShowSql(true);
        boolean bancoJaCriado = bancoJaCriado();
        adapter.setGenerateDdl(!bancoJaCriado);

        if(bancoJaCriado) {
            System.out.println("[DataConfiguration] Banco de dados já criado. -> as Tabelas não serão criadas");
        }else {
            System.out.println("[DataConfiguration] Primeira execução. -> as Tabelas serão criadas");
        }
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }

    // Lógica para verificar se o banco de dados já foi criado
    public boolean bancoJaCriado() {
        String[] tabelasEsperadas = {"solicitcoes", "tecnicos", "painelTecnico"};
        try (Connection conexao = DriverManager.getConnection(DATABASE_URL, USUARIO, SENHA)) {
            DatabaseMetaData metaDados = conexao.getMetaData();
            for (String tabela : tabelasEsperadas) {
                try (ResultSet resultado = metaDados.getTables(conexao.getCatalog(), null, tabela, new String[] {"TABLE"})){
                    if(!resultado.next()) {
                        return false;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("[DataConfiguration] Erro ao verificar se o banco de dados foi criado: " + e.getMessage());
            return false;
        }
    }
}