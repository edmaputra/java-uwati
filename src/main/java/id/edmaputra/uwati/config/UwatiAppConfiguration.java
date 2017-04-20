package id.edmaputra.uwati.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

@Configuration
@EnableJpaRepositories(basePackages = "id.edmaputra.uwati.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "id.edmaputra.uwati", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class) })
@ImportResource(locations = { "classpath:/id/edmaputra/uwati/config/security-context.xml"})
@PropertySource("classpath:/id/edmaputra/uwati/config/uwati.properties")
public class UwatiAppConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(new String[] { "id.edmaputra.uwati.entity" });
		entityManagerFactory.setJpaProperties(hibernateProperties());
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		return entityManagerFactory;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager(DataSource dataSource) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	@Bean
	@Qualifier("strukPembelianPdf")
	public JasperReportsPdfView getHelloWorldReport() {
		JasperReportsPdfView v = new JasperReportsPdfView();
		v.setUrl("classpath:reports/struk_pembelian.jasper");
		v.setReportDataKey("datasource");
		return v;
	}

}
