package id.edmaputra.uwati;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Provider;
import javax.persistence.EntityManager;
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
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.mysema.query.jpa.impl.JPAQueryFactory;

import id.edmaputra.uwati.builder.LaporanPenjualanExcelBuilder;

@Configuration
@EnableJpaRepositories(basePackages = "id.edmaputra.uwati.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "id.edmaputra.uwati", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class) })
@ImportResource(locations = { "classpath:security-context.xml"})
//@ImportResource(locations = { "classpath:/id/edmaputra/uwati/config/security-context.xml"})
//@PropertySource("classpath:/id/edmaputra/uwati/config/uwati.properties")
@PropertySource("classpath:uwati.properties")
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
		dataSource.setMaxWait(40000);
		dataSource.setMaxActive(80);
		dataSource.setMaxIdle(20);
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
	
//	@Bean
//	public EntityManager entityManager() {
//		return entityManagerFactory().getObject().createEntityManager();
//	}
//	
//	@Bean
//	public JPAQueryFactory JpaQueryFactory() {
//		JPAQueryFactory queryFactory = new JPAQueryFactory((Provider<EntityManager>) entityManager());
//		return queryFactory;
//	}

	@Bean
	@Qualifier("strukPembelianPdf")
	public JasperReportsPdfView getStrukPembelianReport() {
		JasperReportsPdfView v = new JasperReportsPdfView();
		v.setUrl("classpath:reports/struk_pembelian.jasper");
		v.setReportDataKey("datasource");
		return v;
	}
	
	@Bean
	@Qualifier("strukPenjualanPdf")
	public JasperReportsPdfView getStrukPenjualanReport() {
		JasperReportsPdfView v = new JasperReportsPdfView();
		v.setUrl("classpath:reports/struk_penjualan.jasper");
		v.setReportDataKey("datasource");
		return v;
	}
	
//	@Bean
//	@Qualifier("laporanPenjualanPdf")
//	public JasperReportsPdfView getLaporanPenjualan() {
//		JasperReportsPdfView v = new JasperReportsPdfView();
//		v.setUrl("classpath:reports/LaporanPenjualan1.jasper");
//		v.setReportDataKey("datasource");
//		v.setSubReportDataKeys("");
//		v.setSubReportDataKeys("dataSourceSubReportObat");		
//		return v;
//	}
	
	@Bean
	@Qualifier("datasourceSubReport")
	public List<String> subReportDataSourceList(){
		List<String> l = new ArrayList<>();
		l.add("dataSourceSubReportObat");
		return l;
	};
	
//	@Bean
//	@Qualifier("dataSourceSubReportLaporanPenjualan")
//	public JasperReportsPdfView getLaporanPenjualanSubReportObat() {
//		JasperReportsPdfView v = new JasperReportsPdfView();
//		v.setUrl("classpath:reports/LaporanPenjualanObat.jasper");
//		v.setReportDataKey("dataSourceSubReportObat");
//		v.setSubReportDataKeys("datasourceSubReport");
//		v.setSubReportUrls((Properties) new Properties().setProperty("SubReportKantor", "classpath:reports/LaporanPenjualanObat.jasper"));
//		return v;
//	}
	
	@Bean
	@Qualifier("penjualanExcel")
	public AbstractXlsView laporanPenjualanExcel(){
		return new LaporanPenjualanExcelBuilder();
	}

}
