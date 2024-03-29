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
            println(this)
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
            println(this)
            assertThat(this).isNotNull
            assertThat(this).isInstanceOf(ComplexEmptyPrimitiveObject::class.java)
        }
    }

    @Test
    fun emptyArrayTest() {
        EmptyObjectGenerator.generate(EmptyArrayObject::class, defaultArraySize = 5).run {
            println(this)
            assertThat(this.stringArray!!.size).isEqualTo(5)
            assertThat(this.charArray!!.size).isEqualTo(5)
            assertThat(this.intArray!!.size).isEqualTo(5)
            assertThat(this.longArray!!.size).isEqualTo(5)
            assertThat(this.doubleArray!!.size).isEqualTo(5)
            assertThat(this.floatArray!!.size).isEqualTo(5)
            assertThat(this.booleanArray!!.size).isEqualTo(5)
            assertThat(this.listArray!!.size).isEqualTo(5)
            assertThat(this.setArray!!.size).isEqualTo(5)
            assertThat(this.mapArray!!.size).isEqualTo(5)
        }
    }

    @Test
    fun emptyArrayIsNullableTest() {
        EmptyObjectGenerator.generate(EmptyArrayObject::class, defaultArraySize = 5, isNullable = true).run {
            println(this)
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
                println(this)
                assertThat(this.stringNestedArray!!.size).isEqualTo(2)
                stringNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
                assertThat(this.charNestedArray!!.size).isEqualTo(2)
                charNestedArray.forEach {
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
                assertThat(this.listNestedArray!!.size).isEqualTo(2)
                listNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
                assertThat(this.setNestedArray!!.size).isEqualTo(2)
                setNestedArray.forEach {
                    assertThat(it.size).isEqualTo(5)
                }
                assertThat(this.mapNestedArray!!.size).isEqualTo(2)
                mapNestedArray.forEach {
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

    @Test
    fun providerTest() {
        EmptyObjectGenerator.generate(EmptyPrimitiveObject::class) {
            println(it)
            assertThat(it).isNotNull
            assertThat(it).isInstanceOf(EmptyPrimitiveObject::class.java)
        }
    }

    @Test
    fun selfReferenceTest() {
        EmptyObjectGenerator.generate(SelfReferenceObject::class) {
            println(it)
        }
    }

    data class EmptyPrimitiveObject(
        val string: String? = null,
        val char: Char? = null,
        val int: Int? = null,
        val long: Long? = null,
        val float: Float? = null,
        val double: Double? = null,
        val boolean: Boolean? = null,
        val list: List<Any>? = null,
        val set: Set<Any>? = null,
        val map: Map<Any, Any>? = null
    )

    data class ComplexEmptyPrimitiveObject(
        val nullableString: String? = null,
        val char: Char,
        val nullableInt: Int? = null,
        val nullableLong: Long? = null,
        val float: Float,
        val double: Double,
        val boolean: Boolean,
        val nullableList: List<Any>? = null,
        val set: Set<Any>,
        val nullableMap: Map<Any, Any>? = null,
    )

    data class EmptyArrayObject(
        val stringArray: Array<String>? = null,
        val charArray: CharArray? = null,
        val intArray: IntArray? = null,
        val longArray: LongArray? = null,
        val floatArray: FloatArray? = null,
        val doubleArray: DoubleArray? = null,
        val booleanArray: BooleanArray? = null,
        val listArray: Array<List<Any>>? = null,
        val setArray: Array<Set<Any>>? = null,
        val mapArray: Array<Map<Any, Any>>? = null
    )

    data class EmptyNestedArrayObject(
        val stringNestedArray: Array<Array<String>>? = null,
        val charNestedArray: Array<CharArray>? = null,
        val intNestedArray: Array<IntArray>? = null,
        val longNestedArray: Array<LongArray>? = null,
        val floatNestedArray: Array<FloatArray>? = null,
        val doubleNestedArray: Array<DoubleArray>? = null,
        val booleanNestedArray: Array<BooleanArray>? = null,
        val listNestedArray: Array<Array<List<Any>>>? = null,
        val setNestedArray: Array<Array<Set<Any>>>? = null,
        val mapNestedArray: Array<Array<Map<Any,Any>>>? = null
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

    data class SelfReferenceObject(
        val data: Any,
        val self: SelfReferenceObject?
    )
}