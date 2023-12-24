package com.empty.generator

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

object EmptyObjectGenerator {
    private val customKotlinTypeSupports = mutableMapOf<KClass<*>, Any>()
    fun <T : Any> generate(
        clazz: KClass<T>,
        isNullable: Boolean = false,
        defaultString: String = "empty",
        defaultInt: Int = 0,
        defaultLong: Long = 0L,
        defaultFloat: Float = 0f,
        defaultDouble: Double = 0.0,
        defaultBoolean: Boolean = false,
        defaultArraySize: Int = 0,
        defaultInnerArraySize: Int = 0,
        emptyValue: EmptyValue? = null
    ): T {

        this.customKotlinTypeSupports.forEach { (customKClass, value) ->
            if (clazz == customKClass) {
                return value as T
            }
        }

        val constructor = clazz.primaryConstructor ?: error("Primary constructor not found for $clazz")

        var emptyValue = getEmptyValue(
            emptyValue = emptyValue,
            defaultString = defaultString,
            defaultInt = defaultInt,
            defaultLong = defaultLong,
            defaultFloat = defaultFloat,
            defaultDouble = defaultDouble,
            defaultBoolean = defaultBoolean,
            defaultArraySize = defaultArraySize,
            defaultInnerArraySize = defaultInnerArraySize,
        )

        val argsMap = constructor.parameters.associateWith {
            val type = it.type
            when {
                (type.isMarkedNullable && isNullable) -> null
                isPrimitiveTypeOnKotlin(type) -> generateEmptyValueKotlin(it.type, emptyValue)
                else -> generateEmptyValueJava(type.jvmErasure.java, emptyValue)
            }
        }

        return constructor.callBy(argsMap)
    }

    private fun getEmptyValue(
        emptyValue: EmptyValue?,
        defaultString: String,
        defaultInt: Int,
        defaultLong: Long,
        defaultFloat: Float,
        defaultDouble: Double,
        defaultBoolean: Boolean,
        defaultArraySize: Int,
        defaultInnerArraySize: Int
    ): EmptyValue {
        if (emptyValue == null) {
            return EmptyValue(
                string = defaultString,
                int = defaultInt,
                long = defaultLong,
                float = defaultFloat,
                double = defaultDouble,
                boolean = defaultBoolean,
                arraySize = defaultArraySize,
                innerArraySize = defaultInnerArraySize
            )
        }
        return emptyValue
    }

    private fun generateEmptyValueJava(clazz: Class<*>, emptyValue: EmptyValue): Any {
        return when {
            clazz == String::class.java -> emptyValue.string
            clazz == Int::class.java -> emptyValue.int
            clazz == Long::class.java -> emptyValue.long
            clazz == Float::class.java -> emptyValue.float
            clazz == Double::class.java -> emptyValue.double
            clazz == Boolean::class.java -> emptyValue.boolean
            List::class.java.isAssignableFrom(clazz) -> emptyList<Any>()
            clazz.isArray -> {
                generateArray(
                    clazz =  clazz,
                    emptyValue = emptyValue
                )
            }
            else -> {
                generate(clazz.kotlin, emptyValue = emptyValue)
            }
        }
    }

    private fun generateArray(
        clazz: Class<*>,
        emptyValue: EmptyValue
    ): Cloneable {
        val arraySize = emptyValue.arraySize
        val innerArraySize = emptyValue.innerArraySize

        val componentType = clazz.componentType
        return when {
            componentType.isArray -> generateNestedArray(
                componentType.componentType,
                arraySize,
                innerArraySize,
                emptyValue
            )

            else -> generateSingleArray(componentType, arraySize, emptyValue)
        }
    }

    private fun generateSingleArray(
        componentType: Class<*>,
        arraySize: Int,
        emptyValue: EmptyValue
    ): Cloneable {
        return when (componentType) {
            String::class.java -> Array(arraySize) { emptyValue.string }
            Int::class.java -> IntArray(arraySize) { emptyValue.int }
            Long::class.java -> LongArray(arraySize) { emptyValue.long }
            Float::class.java -> FloatArray(arraySize) { emptyValue.float }
            Double::class.java -> DoubleArray(arraySize) { emptyValue.double }
            Boolean::class.java -> BooleanArray(arraySize) { emptyValue.boolean }
            else -> Array(arraySize) { generate(componentType.kotlin, emptyValue = emptyValue) }
        }
    }

    private fun generateNestedArray(
        innerComponentType: Class<*>,
        arraySize: Int,
        innerArraySize: Int,
        emptyValue: EmptyValue
    ): Cloneable {
        return when (innerComponentType) {
            String::class.java -> Array(arraySize) { Array(innerArraySize) { emptyValue.string } }
            Int::class.java -> Array(arraySize) { IntArray(innerArraySize) { emptyValue.int } }
            Long::class.java -> Array(arraySize) { LongArray(innerArraySize) { emptyValue.long } }
            Float::class.java -> Array(arraySize) { FloatArray(innerArraySize) { emptyValue.float } }
            Double::class.java -> Array(arraySize) { DoubleArray(innerArraySize) { emptyValue.double } }
            Boolean::class.java -> Array(arraySize) { BooleanArray(innerArraySize) { emptyValue.boolean } }
            else -> Array(0) { generate(innerComponentType.kotlin, emptyValue = emptyValue) }
        }
    }


    private fun generateEmptyValueKotlin(type: KType, emptyValue: EmptyValue): Any {
        return when (type.classifier) {
            String::class -> emptyValue.string
            Int::class -> emptyValue.int
            Long::class -> emptyValue.long
            Float::class -> emptyValue.float
            Double::class -> emptyValue.double
            Boolean::class -> emptyValue.boolean
            else -> throw IllegalArgumentException("not supported type $type")
        }
    }

    private fun isPrimitiveTypeOnKotlin(type: KType): Boolean {
        return type.jvmErasure.javaPrimitiveType != null
    }
    data class EmptyValue(
        val string: String,
        val int: Int,
        val long: Long,
        val float: Float,
        val double: Double,
        val boolean: Boolean,
        val arraySize: Int,
        val innerArraySize: Int
    )

    fun addCustomTypeSupport(clazz: KClass<*>, defaultValue: Any) {
        this.customKotlinTypeSupports[clazz] = defaultValue
    }

    fun clearCustomTypeSupport(): Unit {
        this.customKotlinTypeSupports.clear()
    }
}