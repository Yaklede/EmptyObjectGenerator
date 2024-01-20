# EmptyObjectGenerator

## Gradle

```kotlin
dependencies {
  implementation("io.github.Yaklede:empty-object-generator:0.2.3")
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

## How use

- The usual method involves receiving the return value and then utilizing it.

```kotlin
val result: YourClass = EmptyObjectGenerator.generate(YourClass::class)
```

- You can use it directly within the method block without receiving the return value.

```kotlin
EmptyObjectGenerator.generate(YourClass::class) {
    something(it)
}
```

## Example

### Primitive type
```kotlin
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

val emptyPrimitiveObject = EmptyObjectGenerator.generate(EmptyPrimitiveObject::class)

//return
EmptyPrimitiveObject(
    string = "empty",
    char = Char.MIN_VALUE,
    int = 0,
    long = 0,
    float = 0.0f,
    double = 0.0,
    boolean = false,
    list = [],
    set = [],
    map = {}
)

//if you use parameter nullable = true
val emptyPrimitiveObjectIsNullable = EmptyObjectGenerator.generate(EmptyPrimitiveObject::class, isNullable = true)

//return
EmptyPrimitiveObject(
    string = null,
    char = null,
    int = null,
    long = null,
    float = null,
    double = null,
    boolean = null,
    list = null,
    set = null,
    map = null
)

//complex
data class ComplexEmptyPrimitiveObject(
    val nullableString: String? = null,
    val nullableChar: Char? = null,
    val nullableInt: Int? = null,
    val nullableLong: Long? = null,
    val float: Float,
    val double: Double,
    val boolean: Boolean,
    val nullableList: List<Any>? = null,
    val set: Set<Any>,
    val nullableMap: Map<Any, Any>? = null,
)

val complexEmptyPrimitiveObject = EmptyObjectGenerator.generate(ComplexEmptyPrimitiveObject::class, isNullable = true)

//return
ComplexEmptyPrimitiveObject(
    nullableString = null,
    nullableChar = null,
    nullableInt = null,
    nullableLong = null,
    float = 0.0f,
    double = 0.0,
    boolean = false,
    nullableList = null,
    set = [],
    nullableMap = null
)
```

### Primitive single array type

```kotlin
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

val emptyArrayObject = EmptyObjectGenerator.generate(EmptyArrayObject::class, defaultArraySize = 5)

//returns
EmptyArrayObject(
    stringArray = ["empty", "empty", "empty", "empty", "empty"],
    charArray = [Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE],
    intArray = [0, 0, 0, 0, 0],
    longArray = [0, 0, 0, 0, 0],
    floatArray = [0.0f, 0.0f, 0.0f, 0.0f, 0.0f],
    doubleArray = [0.0, 0.0, 0.0, 0.0, 0.0],
    booleanArray = [false, false, false, false, false],
    listArray = [[], [], [], [], []],
    setArray = [[], [], [], [], []],
    mapArray = [{}, {}, {}, {}, {}]
)

//if you use parameter nullable = true
val emptyArrayObjectIsNullable =
    EmptyObjectGenerator.generate(EmptyArrayObject::class, defaultArraySize = 5, isNullable = true)

//return
EmptyArrayObject(
    stringArray = null,
    charArray = null,
    intArray = null,
    longArray = null,
    floatArray = null,
    doubleArray = null,
    booleanArray = null,
    listArray = null,
    setArray = null,
    mapArray = null
)
```

### Primitive nested array type

```kotlin
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
    val mapNestedArray: Array<Array<Map<Any, Any>>>? = null
)

val emptyNestedArrayObject = EmptyObjectGenerator.generate(
    EmptyNestedArrayObject::class,
    defaultArraySize = 2,
    defaultInnerArraySize = 5
)

//return
EmptyNestedArrayObject(
    stringNestedArray = [["empty", "empty", "empty", "empty", "empty"], ["empty", "empty", "empty", "empty", "empty"]],
    charNestedArray = [[Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE],[Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE,Char.MIN_VALUE]],  
    intNestedArray = [[0, 0, 0, 0, 0], [0, 0, 0, 0, 0]],
    longNestedArray = [[0, 0, 0, 0, 0], [0, 0, 0, 0, 0]],
    floatNestedArray = [[0.0f, 0.0f, 0.0f, 0.0f, 0.0f], [0.0f, 0.0f, 0.0f, 0.0f, 0.0f]],
    doubleNestedArray = [[0.0, 0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.0, 0.0, 0.0]],
    booleanNestedArray = [[false, false, false, false, false], [false, false, false, false, false]],
    listNestedArray = [[[], [], [], [], []]],
    setNestedArray = [[[], [], [], [], []]],
    mapNestedArray = [[{}, {}, {}, {}, {}]]
)
//if you use parameter nullable = true
val emptyNestedArrayObjectIsNullable = EmptyObjectGenerator.generate(
    EmptyNestedArrayObject::class,
    isNullable = true,
    defaultArraySize = 2,
    defaultInnerArraySize = 5
)

//return
EmptyNestedArrayObject(
    stringNestedArray = null,
    charNestedArray = null,
    intNestedArray = null,
    longNestedArray = null,
    floatNestedArray = null,
    doubleNestedArray = null,
    booleanNestedArray = null,
    listNestedArray = null,
    setNestedArray = null,
    mapNestedArray = null
)
```

### Complex type

```kotlin
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

val complexObject = EmptyObjectGenerator.generate(
    ComplexObject::class,
    defaultArraySize = 1,
    defaultInnerArraySize = 2
)
//return
ComplexObject(
    int = 0,
    stringNullable = "empty",
    list = [],
    listNullable = [],
    complexInnerObject = ComplexInnerObject(
        boolean = false,
        doubleArrayNullable = [0.0],
        floatNestedArray = [[0.0, 0.0]]
    )
)


//if you use parameter nullable = true
val complexObjectNullable = EmptyObjectGenerator.generate(
    ComplexObject::class,
    isNullable = true,
    defaultArraySize = 1,
    defaultInnerArraySize = 2
)

//return
ComplexObject(
    int = 0,
    stringNullable = null,
    list = [],
    listNullable = null,
    complexInnerObject = null
)
```

## Custom Support

### Caution

- If you use a custom support it returns only default value
- Objects registered with custom support are globally accessible, so use them with care to avoid unintended side effects
  throughout your application.

```kotlin
data class EmptySupportTestObject(
    val intArray: IntArray? = null,
    val innerObject: EmptySupportTestInnerObject? = null
)
data class EmptySupportTestInnerObject(
    val int: Int? = null,
    val string: String? = null
)

//add custom type support
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

val supportTypeObject = EmptyObjectGenerator.generate(EmptySupportTestObject::class)

//return
EmptySupportTestObject(
    intArray = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    innerObject = EmptySupportTestInnerObject(
        int = 10,
        string = "innerObject"
    )
)

//if you use parameter nullable = true
val emptySupportTestObjectNullable = EmptyObjectGenerator.generate(EmptySupportTestObject::class, isNullable = true)

//return
EmptySupportTestObject(
    intArray = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    innerObject = EmptySupportTestInnerObject(
        int = 10,
        string = "innerObject"
    )
)
```