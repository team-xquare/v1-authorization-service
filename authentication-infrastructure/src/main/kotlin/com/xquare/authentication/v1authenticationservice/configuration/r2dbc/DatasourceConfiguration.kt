package com.xquare.authentication.v1authenticationservice.configuration.r2dbc

import com.xquare.authentication.v1authenticationservice.configuration.r2dbc.converter.ByteArrayToUUIDConverter
import com.xquare.authentication.v1authenticationservice.configuration.r2dbc.converter.UUIDToByteArrayConverter
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.MySqlDialect
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.transaction.ReactiveTransactionManager

@Configuration
class DatasourceConfiguration(
    private val connectionFactory: ConnectionFactory
) : AbstractR2dbcConfiguration() {
    @Bean
    fun initializer(): ConnectionFactoryInitializer {
        val populator = ResourceDatabasePopulator(ClassPathResource("schema.sql"))
        val initializer = ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(populator)
        }

        return initializer
    }

    @Bean
    fun customConversions(): R2dbcCustomConversions {
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE)
    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager =
        R2dbcTransactionManager(connectionFactory)

    override fun connectionFactory(): ConnectionFactory =
        connectionFactory

    override fun getCustomConverters(): List<Any> {
        return listOf(UUIDToByteArrayConverter(), ByteArrayToUUIDConverter())
    }
}
