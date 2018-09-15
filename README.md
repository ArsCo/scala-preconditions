# scala-preconditions

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-preconditions_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-preconditions_2.11) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-preconditions_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-preconditions_2.12)

[![Coverage Status](https://coveralls.io/repos/github/ArsCo/scala-preconditions/badge.svg?branch=master)](https://coveralls.io/github/ArsCo/scala-preconditions?branch=master)
[![Licence](https://img.shields.io/badge/license-Apache_2.0-blue.svg)](https://tldrlegal.com/license/apache-license-2.0-(apache-2.0))
[![Latest release](https://img.shields.io/github/release/ArsCo/scala-preconditions/all.svg)](https://github.com/ArsCo/scala-preconditions/releases/latest)
[![Code quality](https://img.shields.io/codacy/40b219086c594dc7b67c0698363f56ed.svg)](https://www.codacy.com/app/ArsCo/scala-preconditions)
[![Gitterchat](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/ars-co/scala-preconditions)

Scala library that provides extendable API to check method preconditions.

## Features:
- predicates and method precondition checkers for most common cases
- fully tested (code coverage is more than 97%)
- well-formed failure messages
- easy customisation (single place)
- easy extensibility (traits)
- Scala 2.11 and 2.12

# Usage

Add dependency to `build.sbt`:
```scala
libraryDependencies +=  "ru.ars-co" %% "scala-preconditions" % currentVersion
```

To use default implementation:
1. add imports 
    ```scala
    import ars.precondition.require.Require.Default._  // requireXXX methods
    import ars.precondition.implicits._                // implicit conversions
    ``` 
1. add preconditions to your methods, for example:
    ```scala
    def foo(name: String, age: Int, email: Option[String], code: String, custom: MyValue) = {
      requireNotBlank(name, "name")
      requireNonNegative(age, "age")
      optional(email, "email")(requireEmail)
      requirePattern(code, "[A-Z]{4,7}".r, "code")
      require(myCustomCheck(custom), "The argument `custom` is not valid.")
        . . .
    }
    ```

## Full list of method precondition checkers

The library provides predicates and precondition checkers to test that
- all elements of `Iterable[T]` meet the requirements (trait `RequireAll`)
    - `requireAll()`
    - `requireAllPredicate()`
- all elements of `Iterable[T]` is not `null` or blank (trait `RequireAllSpecific`)
    - `requireAllNotNull()`
    - `requireAllNotBlank()` (for `Iterable[String]` values only)
- `Any` value is not `null` (trait `RequireAny`)  
    - `requireNotNull`
- `Iterable[T]` is not `null` and empty (trait `RequireIterable`)
    - `requireNotBlank()`
- numeric value is positive or negative (trait `RequireNumeric`)
    - `requirePositive()`
    - `requireNegative()`
    - `requireNonPositive()`
    - `requireNonNegative()`
- numeric value in range (trait `RequireNumericRange`)
    - `requireNumber()`
    - `requireNumberFrom()`
    - `requireNumberUntil()`
    - `requireNumberInRange()`
- `Option[T]` value meets the requirements (trait `RequireOptional`)
    - `optional()`
    - `optionalPredicate()`
- size of `Iterable[T]` in range (trait `RequireSize`)
    - `requireSize()`
    - `requireSizeFrom()`
    - `requireSizeUntil()`
    - `requireSizeInRange()`
- `String` is not blank or satisfies the regular expression (trait `RequireString`)
    - `requireNotBlank()`
    - `requirePattern()`
- `String` is correct `UUID` or email (trait `RequireStringFormat`)
    - `requireEmail()`
    - `requireUuid()`
    - `requireUrl()`
- `String` contains correct numeric value (trait `RequireStringNumeric`)
    - `requireByte()`
    - `requireShort()`
    - `requireInt()`
    - `requireLong()`
    - `requireFloat()`
    - `requireDouble()`

# Commom concepts

The library is based on concepts of `Predef.require()` method from Scala standard library. It reimplements
similar method with enhancements.

The core trait is `RequireCore`. It contains 4 methods which is used to implement all other precondition checkers:
- `exception()` - encapsulates exception creation
- `failureMessage()` - encapsulates failure message generation
- `fail()` - creates exception and throws it
- `require()` - tests requirement and throws exception if it's fail

All other `RequireXXX` traits extend `RequireCore` and their `requireXXX` are based on these 4 methods 
and specific predicates.

`Require` class is the default implementation of all `RequireXXX` traits. Its companion object
has default instance `Require.Default`. The best practice is to import all methods of `Require.Default`:
```scala
import ars.precondition.require.Require.Default._
```

# Customization
## Prefixes and postfixes

If you want to use custom prefixes or postfixes for all generated messages, create
new instance of `Require` with `PrefixPostfixMessage` and override `prefix` and/or `postfix` methods.

__Example:__
```scala
val PrefixedRequire = new Require extends PrefixPostfixMessage {
  override def prefix: String = "PREFIX "
  override def postfix: String = " POSTFIX"
}
```

## Custom require traits

To implement custom `RequireXXX` trait 
1. create new trait that extends `RequireCore` and implement new
`requireXXX` methods.
1. create instance of `Require` with new trait

__Example:__
```scala
trait RequireNew extends RequireCore {
  def requireNew(value: String, name: String = NoParameterName): Unit = {
    require(value == "new", s"Parameter `$new` is not new.")
  }
}

final val MyRequire = new RequireNew

import MyRequire._

def foo(newValue: String) = {
  requireNew(newValue, "newValue")
}

```

## Exception type customization

To customize exception type 
1. create new class that extends `Require` and override `exception()` method.
1. create instance of new class

__Example:__
```scala
class RequireWithMyException extends Require {
  override def exception(message: String, cause: Option[RuntimeException] = None): RuntimeException = {
      //...
  }
}

final val MyRequire = new RequireWithMyException

import MyRequire._

```

# Copyright

Copyright 2018 Arsen Ibragimov (ars)
