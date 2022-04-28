package com.xquare.authentication.v1authenticationservice.configuration.r2dbc.converter

import com.xquare.authentication.v1authenticationservice.configuration.utils.UUIDUtils.toByteArray
import com.xquare.authentication.v1authenticationservice.configuration.utils.UUIDUtils.toUUID
import java.util.UUID
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

@WritingConverter
class UUIDToByteArrayConverter : Converter<UUID, ByteArray> {

    override fun convert(source: UUID): ByteArray {
        return source.toByteArray()
    }
}

@ReadingConverter
class ByteArrayToUUIDConverter : Converter<ByteArray, UUID> {
    override fun convert(source: ByteArray): UUID {
        return source.toUUID()
    }
}
