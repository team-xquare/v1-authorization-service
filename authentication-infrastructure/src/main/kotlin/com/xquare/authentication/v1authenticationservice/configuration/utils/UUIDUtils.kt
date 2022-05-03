package com.xquare.authentication.v1authenticationservice.configuration.utils

import java.nio.ByteBuffer
import java.util.UUID

object UUIDUtils {
    fun ByteArray.toUUID(): UUID {
        val uuidByteBuffer = ByteBuffer.wrap(this)
        return UUID(uuidByteBuffer.long, uuidByteBuffer.long)
    }

    fun UUID.toByteArray(): ByteArray {
        val uuidByteBuffer = ByteBuffer.wrap(ByteArray(16))
        uuidByteBuffer.putLong(this.mostSignificantBits)
        uuidByteBuffer.putLong(this.leastSignificantBits)
        return uuidByteBuffer.array()
    }

    fun String.toUUID(): UUID =
        UUID.fromString(this)
}
