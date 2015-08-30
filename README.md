HAR reader
==========

Read [HTTP Archives](http://www.softwareishard.com/blog/har-12-spec/) with Java.

```
<dependency>
  <groupId>de.sstoehr</groupId>
  <artifactId>har-reader</artifactId>
  <version>2.0.0</version>
</dependency>
```

[![Build Status](https://travis-ci.org/sdstoehr/har-reader.png?branch=master)](https://travis-ci.org/sdstoehr/har-reader)
[![Coverage Status](https://coveralls.io/repos/sdstoehr/har-reader/badge.png?branch=master)](https://coveralls.io/r/sdstoehr/har-reader?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/de.sstoehr/har-reader.svg)](http://mvnrepository.com/artifact/de.sstoehr/har-reader)

## Usage

Reading HAR from File:

```
HarReader harReader = new HarReader();
Har har = harReader.readFromFile(new File("myhar.har"));
System.out.println(har.getLog().getCreator().getName());
```

Reading HAR from String:

```
HarReader harReader = new HarReader();
Har har = harReader.readFromString("{ ... HAR-JSON-Data ... }");
```

## Migrating from 1.* to 2.0

`HarReader` can't be called statically anymore. Please create your own `HarReader` instance:

* `HarReader.fromFile()` should be `harReader.readFromFile()`
* `HarReader.fromString()` should be  `harReader.readFromString()`

`HarReader` should be thread-safe (when using the `DefaultMapperFactory`).

In old versions `HarReader` threw `IllegalArgumentExceptions` when the HAR contained null values, although the spec
stated, that this field is not optional. This behaviour was changed. `HarReader` does not check, whether required
fields are not null.
To allow easier read access, `HarReader` will return "empty" objects and lists wherever possible.  