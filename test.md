# EmptyObjectGenerator

## Gradle

```kotlin
dependencies {
  implementation("io.github.Yaklede:empty-object-generator:0.2.2")
}
```

## What's EmptyObjectGenerator?

EmptyObjectGenerator is a Kotlin utility class that automatically generates objects with desired default values. It
inherently supports Kotlin's primitive types and extends its support to 1-dimensional and 2-dimensional arrays.
Additionally, users have the flexibility to add custom support.

## Default Supported types

```kotlin
val string: String
val char: Char
val int: Int
val long: Long
val float: Float
val double: Double
val boolean: Boolean
val list: List<Any>
val set: Set<Any>
val map: Map<Any, Any>

val stringSingleArray: Array<String>
val charSingleArray: CharArray
val intSingleArray: IntArray
val longSingleArray: LongArray
val floatSingleArray: FloatArray
val doubleSingleArray: DoubleArray
val booleanSingleArray: BooleanArray
val listArray: Array<List<Any>>
val setArray: Array<Set<Any>>
val mapArray: Array<Map<Any, Any>>

val stringNestedArray: Array<Array<String>>
val charNestedARray: Array<CharArray>
val intNestedArray: Array<IntArray>
val longNestedArray: Array<LongArray>
val floatNestedArray: Array<FloatArray>
val doubleNestedArray: Array<DoubleArray>
val booleanNestedArray: Array<BooleanArray>
val listNestedArray: Array<Array<List<Any>>>
val setNestedArray: Array<Array<Set<Any>>>
val mapNestedArray: Array<Array<Map<Any, Any>>>
```