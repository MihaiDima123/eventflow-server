package com.eventflow.eventflow.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource
import org.flywaydb.core.Flyway

@Configuration
class DatasourceConfig(
    @Value("\${spring.datasource.url}") val datasourceUrl: String,
    @Value("\${spring.datasource.driver-class-name}") val driverClassName: String,
    @Value("\${spring.datasource.username}") val username: String,
    @Value("\${spring.datasource.password}") val password: String,

    @Value("\${spring.flyway.locations}") val flywayLocations: String
){
    @Bean
    fun getDatasource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl =datasourceUrl
        config.driverClassName = driverClassName
        config.username = username
        config.password = password

        config.maximumPoolSize = 2
        config.connectionTimeout = 30_000
        config.idleTimeout = 600_000
        config.maximumPoolSize = 180_000

        return HikariDataSource(config)
    }

    @Bean
    fun flyway(dataSource: DataSource): Flyway {
        val flyway = Flyway
            .configure()
            .dataSource(dataSource)
            .locations(flywayLocations).load()

        flyway.migrate()

        return flyway
    }
}
