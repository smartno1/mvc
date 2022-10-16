package com.spring.mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

// DB 관련 설정 클래스
@Configuration  // 설정 빈 등록
@PropertySource("classpath:db_info.properties") // 경로의 파일을 프로퍼티소스로 => 파일 안의 값을 가져올수 있음.
public class DataBaseConfig {

    @Value("${local.db.username}")  // 프로퍼티소스로 등록한 파일의 값을 가져와서 다음에 오는 필드에 대입해라.
    private String username;    // 데이터베이스 접속 계정명

    @Value("${local.db.password}")
    private String password;    // 접속 비밀번호

    @Value("${local.db.url}")
    private String url;         // 테이버베이스 URL

    // DB 접속 정보 설정 (DataSource 설정)
    @Bean // 빈 등록
    public DataSource dataSource() {
        // 커넥션 풀 설정 (HikariCP를 사용 - 기본라이브러리)
        HikariConfig config =  new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        return new HikariDataSource(config);
    }
}
