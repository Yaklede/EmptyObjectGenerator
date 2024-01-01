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

## Supported Method

| Method                   | Description                                                       |
|--------------------------|-------------------------------------------------------------------|
| `generate`               | Used to create an empty object.                                   |
| `addCustomSupportType`   | Allows specifying the return type for the generated empty object. |
| `clearCustomTypeSupport` | Resets all registered custom types.                               |

### Generate Method Supported Parameter
| Parameter             | Type        | Description                                                                   | Default Value  | Required |
|-----------------------|-------------|-------------------------------------------------------------------------------|----------------|----------|
| clazz                 | KClass<T>   | The Kotlin class for which an object needs to be generated.                   | -              | Yes      |
| isNullable            | Boolean     | Internal data can be null. This can only be used if there is a nullable mark. | false          | No       |
| defaultString         | String      | The default value for String types.                                           | "empty"        | No       |
| defaultChar           | Char        | The default value for Char types.                                             | Char.MIN_VALUE | No       |
| defaultInt            | Int         | The default value for Int types.                                              | 0              | No       |
| defaultLong           | Long        | The default value for Long types.                                             | 0L             | No       |
| defaultFloat          | Float       | The default value for Float types.                                            | 0.0f           | No       |
| defaultDouble         | Double      | The default value for Double types.                                           | 0.0            | No       |
| defaultBoolean        | Boolean     | The default value for Boolean types.                                          | false          | No       |
| defaultArraySize      | Int         | The default size for 1-dimensional arrays.                                    | 0              | No       |
| defaultInnerArraySize | Int         | The default size for 2-dimensional arrays.                                    | 0              | No       |
| emptyValue            | EmptyValue? | Customizable empty value for additional support beyond primitive types.       | null           | No       |

### addCustomSupportType Supported Parameter

| Parameter      | Description                                                 | Type        | Default Value | Required |
|----------------|-------------------------------------------------------------|-------------|---------------|----------|
| `clazz`        | Represents the class of the supported custom Kotlin type.   | `KClass<*>` | -             | Yes      |
| `defaultValue` | Represents the default value for the specified Kotlin type. | `Any`       | -             | Yes      |

### clearCustomTypeSupport

| Parameter       | Description                     | Type | Default Value | Required |
|-----------------|---------------------------------|------|---------------|----------|
| (No parameters) | (No parameters for this method) | -    | -             | No       |