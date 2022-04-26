package com.xquare.authentication.v1authenticationservice

import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import org.assertj.core.api.Assertions.assertThat

object EqualsTestUtil {
    fun isEqualTo(baseObject: Any, targetObject: Any) {
        val baseObjectFieldMap = baseObject::class.memberProperties
            .getAccessibleMemberProperties()
            .associateBy { it.name }

        val targetObjectFields = targetObject::class.memberProperties
            .getAccessibleMemberProperties()

        targetObjectFields
            .forEach {
                assertThat(getValueOfProperty(baseObject, baseObjectFieldMap[it.name]!!))
                    .isEqualTo(getValueOfProperty(targetObject, it))
            }
    }

    private fun Collection<KProperty<*>>.getAccessibleMemberProperties() =
        this.filter { it.isAccessible }

    private fun getValueOfProperty(obj: Any, property: KProperty<*>) =
        property.getter.call(obj)
}
