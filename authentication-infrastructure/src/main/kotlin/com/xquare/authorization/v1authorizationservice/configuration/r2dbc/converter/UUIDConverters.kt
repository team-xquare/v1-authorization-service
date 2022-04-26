package com.xquare.authorization.v1authorizationservice.configuration.r2dbc.converter

import java.nio.ByteBuffer
import java.util.UUID
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter


@WritingConverter
class UUIDToByteArrayConverter: Converter<UUID, ByteArray> {

    override fun convert(source: UUID): ByteArray {
        val uuidByteBuffer = ByteBuffer.wrap(ByteArray(16))
        uuidByteBuffer.putLong(source.mostSignificantBits)
        uuidByteBuffer.putLong(source.leastSignificantBits)
        return uuidByteBuffer.array()
    }
}

@ReadingConverter
class ByteArrayToUUIDConverter: Converter<ByteArray, UUID> {
    override fun convert(source: ByteArray): UUID {
        val uuidByteBuffer = ByteBuffer.wrap(source)
        return UUID(uuidByteBuffer.long, uuidByteBuffer.long)
    }
}
