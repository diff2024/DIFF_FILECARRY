package com.diff._file_carry.Config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.diff._file_carry.Mapper", sqlSessionFactoryRef="SqlSessionFactory", basePackages = "com.diff._file_carry.Mapper")
@EnableTransactionManagement
public class DBConfig {
	
	@Bean(name="DataSource")
	@ConfigurationProperties(prefix="spring.datasource.filecarry")
	public DataSource masterDataSource() {
		//application.properties에서 정의한 DB 연결 정보를 빌드
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="SqlSessionFactory")
	public SqlSessionFactory SqlSessionFactory(@Qualifier("DataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception{
		//세션 생성 시, 빌드된 DataSource를 세팅하고 SQL문을 관리할 mapper.xml의 경로를 알려준다.
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(DataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:Mapper/Main/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="SqlSessionTemplate")
	public SqlSessionTemplate SqlSessionTemplate(SqlSessionFactory SqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(SqlSessionFactory);
	}
}