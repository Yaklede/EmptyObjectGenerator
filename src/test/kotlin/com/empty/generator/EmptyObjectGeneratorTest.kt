package com.empty.generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmptyObjectGeneratorTest {
    @BeforeEach
    fun setUp() = EmptyObjectGenerator.clearCustomTypeSupport()

    @Test
    fun emptyArrayTest() {
        EmptyObjectGenerator.generate(EmptyArrayObject::class, defaultArraySize = 5).run {
            assertThat(this.stringArray!!.size).isEqualTo(5)
            assertThat(this.intArray!!.size).isEqualTo(5)
            assertThat(this.longArray!!.size).isEqualTo(5)
            assertThat(this.doubleArray!!.size).isEqualTo(5)
            assertThat(this.floatArray!!.size).isEqualTo(5)
            assertThat(this.booleanArray!!.size).isEqualTo(5)
        }
    }

    @Test
    fun empty2DepthArrayTest() {
        EmptyObjectGenerator.generate(
            Empty2DepthArrayObject::class,
            defaultArraySize = 5,
            defaultInnerArraySize = 10
        )
            .run {
                assertThat(this.stringArray!!.size).isEqualTo(5)
                stringArray.forEach {
                    assertThat(it.size).isEqualTo(10)
                }
                assertThat(this.intArray!!.size).isEqualTo(5)
                intArray.forEach {
                    assertThat(it.size).isEqualTo(10)
                }
                println("long")
                assertThat(this.longArray!!.size).isEqualTo(5)
                longArray.forEach {
                    assertThat(it.size).isEqualTo(10)
                }
                assertThat(this.floatArray!!.size).isEqualTo(5)
                floatArray.forEach {
                    assertThat(it.size).isEqualTo(10)
                }
                assertThat(this.booleanArray!!.size).isEqualTo(5)
                booleanArray.forEach {
                    assertThat(it.size).isEqualTo(10)
                }
            }
    }

    @Test
    fun supportTest() {
        EmptyObjectGenerator.addCustomTypeSupport(
            clazz = EmptySupportTestObject::class,
            defaultValue = EmptySupportTestObject(
                intArray = IntArray(10) { 1 },
                EmptySupportTestInnerObject(
                    int = 10,
                    string = "innerObject"
                )
            )
        )

        EmptyObjectGenerator.generate(EmptySupportTestObject::class).run {
            assertThat(this.intArray!!.size).isEqualTo(10)
            assertThat(this.innerObject).isNotNull
            assertThat(this.innerObject!!.int).isEqualTo(10)
            assertThat(this.innerObject!!.string).isEqualTo("innerObject")
        }
    }

    @Test
    fun generateNull() {
        EmptyObjectGenerator.generate(EmptySupportTestObject::class, isNullable = true).run {
            assertThat(this.innerObject).isNull()
            assertThat(this.intArray).isNull()
        }
    }

    data class EmptyArrayObject(
        val stringArray: Array<String>? = null,
        val intArray: IntArray? = null,
        val longArray: LongArray? = null,
        val floatArray: FloatArray? = null,
        val doubleArray: DoubleArray? = null,
        val booleanArray: BooleanArray? = null
    )

    data class Empty2DepthArrayObject(
        val stringArray: Array<Array<String>>? = null,
        val intArray: Array<IntArray>? = null,
        val longArray: Array<LongArray>? = null,
        val floatArray: Array<FloatArray>? = null,
        val doubleArray: Array<DoubleArray>? = null,
        val booleanArray: Array<BooleanArray>? = null,
    )

    data class EmptySupportTestObject(
        val intArray: IntArray? = null,
        val innerObject: EmptySupportTestInnerObject? = null
    )

    data class EmptySupportTestInnerObject(
        val int: Int? = null,
        val string: String? = null
    )
}