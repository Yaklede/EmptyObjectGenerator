package com.empty.generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmptyObjectGeneratorTest {
    @BeforeEach
    fun setUp() = EmptyObjectGenerator.clearCustomTypeSupport()

    @Test
    fun emptyPrimitiveTest() {
        EmptyObjectGenerator.generate(EmptyPrimitiveObject::class).run {
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(EmptyPrimitiveObject::class.java)
        }
    }

    @Test
    fun emptyPrimitiveIsNullableTest() {
        EmptyObjectGenerator.generate(EmptyPrimitiveObject::class, isNullable = true).run {
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(EmptyPrimitiveObject::class.java)
            println(this)
        }
    }

    @Test
    fun complexEmptyPrimitiveIsNullableTest() {
        EmptyObjectGenerator.generate(ComplexEmptyPrimitiveObject::class, isNullable = true).run {
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(ComplexEmptyPrimitiveObject::class.java)
        }
    }

    @Test
    fun emptyArrayTest() {
        EmptyObjectGenerator.generate(EmptyArrayObject::class, defaultArraySize = 5).run {
            println(this)
            assertThat(this.stringArray!!.size).isEqualTo(5)
            assertThat(this.intArray!!.size).isEqualTo(5)
            assertThat(this.longArray!!.size).isEqualTo(5)
            assertThat(this.doubleArray!!.size).isEqualTo(5)
            assertThat(this.floatArray!!.size).isEqualTo(5)
            assertThat(this.booleanArray!!.size).isEqualTo(5)
        }
    }

    @Test
    fun emptyArrayIsNullableTest() {
        EmptyObjectGenerator.generate(EmptyArrayObject::class, defaultArraySize = 5, isNullable = true).run {
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(EmptyArrayObject::class.java)
        }
    }

    @Test
    fun empty2DepthArrayTest() {
        EmptyObjectGenerator.generate(
            EmptyNestedArrayObject::class,
            defaultArraySize = 2,
            defaultInnerArraySize = 5
        )
            .run {
                assertThat(this.stringNestedArray!!.size).isEqualTo(2)
                stringNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
                assertThat(this.intNestedArray!!.size).isEqualTo(2)
                intNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
                assertThat(this.longNestedArray!!.size).isEqualTo(2)
                longNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
                assertThat(this.floatNestedArray!!.size).isEqualTo(2)
                floatNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
                assertThat(this.booleanNestedArray!!.size).isEqualTo(2)
                booleanNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
            }
    }

    @Test
    fun emptyNestedArrayNullableTest() {
        EmptyObjectGenerator.generate(
            EmptyNestedArrayObject::class,
            isNullable = true,
            defaultArraySize = 2,
            defaultInnerArraySize = 5
        ).run {
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(EmptyNestedArrayObject::class.java)
        }
    }

    @Test
    fun complexObjectTest() {
        EmptyObjectGenerator.generate(
            ComplexObject::class,
            defaultArraySize = 1,
            defaultInnerArraySize = 2
        ).run {
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(ComplexObject::class.java)
        }
    }

    @Test
    fun complexObjectNullableTest() {
        EmptyObjectGenerator.generate(
            ComplexObject::class,
            isNullable = true,
            defaultArraySize = 1,
            defaultInnerArraySize = 2
        ).run {
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(ComplexObject::class.java)
        }
    }

    @Test
    fun supportTest() {

        val defaultValue = EmptySupportTestObject(
            intArray = IntArray(10) { 1 },
            EmptySupportTestInnerObject(
                int = 10,
                string = "innerObject"
            )
        )

        EmptyObjectGenerator.addCustomTypeSupport(
            clazz = EmptySupportTestObject::class,
            defaultValue = defaultValue
        )

        EmptyObjectGenerator.generate(EmptySupportTestObject::class).run {
            assertThat(this.intArray!!.size).isEqualTo(10)
            assertThat(this.innerObject).isNotNull
            assertThat(this.innerObject!!.int).isEqualTo(10)
            assertThat(this.innerObject!!.string).isEqualTo("innerObject")
        }
    }

    @Test
    fun supportNullableTest() {

        val defaultValue = EmptySupportTestObject(
            intArray = IntArray(10) { 1 },
            EmptySupportTestInnerObject(
                int = 10,
                string = "innerObject"
            )
        )

        EmptyObjectGenerator.addCustomTypeSupport(
            clazz = EmptySupportTestObject::class,
            defaultValue = defaultValue
        )

        EmptyObjectGenerator.generate(EmptySupportTestObject::class, isNullable = true).run {
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

    data class EmptyPrimitiveObject(
        val string: String? = null,
        val int: Int? = null,
        val long: Long? = null,
        val float: Float? = null,
        val double: Double? = null,
        val boolean: Boolean? = null
    )

    data class ComplexEmptyPrimitiveObject(
        val nullableString: String? = null,
        val nullableInt: Int? = null,
        val nullableLong: Long? = null,
        val float: Float,
        val double: Double,
        val boolean: Boolean,
    )

    data class EmptyArrayObject(
        val stringArray: Array<String>? = null,
        val intArray: IntArray? = null,
        val longArray: LongArray? = null,
        val floatArray: FloatArray? = null,
        val doubleArray: DoubleArray? = null,
        val booleanArray: BooleanArray? = null
    )

    data class EmptyNestedArrayObject(
        val stringNestedArray: Array<Array<String>>? = null,
        val intNestedArray: Array<IntArray>? = null,
        val longNestedArray: Array<LongArray>? = null,
        val floatNestedArray: Array<FloatArray>? = null,
        val doubleNestedArray: Array<DoubleArray>? = null,
        val booleanNestedArray: Array<BooleanArray>? = null,
    )

    data class ComplexObject(
        val int: Int,
        val stringNullable: String? = null,
        val list: List<Any>,
        val listNullable: List<Any>? = null,
        val complexInnerObject: ComplexInnerObject? = null
    )

    data class ComplexInnerObject(
        val boolean: Boolean,
        val doubleArrayNullable: DoubleArray? = null,
        val floatNestedArray: Array<FloatArray>
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