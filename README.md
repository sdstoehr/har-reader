HAR reader
==========

Read [HTTP Archives](http://www.softwareishard.com/blog/har-12-spec/) with Java.

```
<dependency>
  <groupId>de.sstoehr</groupId>
  <artifactId>har-reader</artifactId>
  <version>2.2.1</version>
</dependency>
```

[![Build Status](https://app.travis-ci.com/sdstoehr/har-reader.svg?branch=main)](https://app.travis-ci.com/sdstoehr/har-reader)
[![codecov](https://codecov.io/gh/sdstoehr/har-reader/branch/master/graph/badge.svg)](https://codecov.io/gh/sdstoehr/har-reader)
[![Maven Central](https://img.shields.io/maven-central/v/de.sstoehr/har-reader.svg)](http://mvnrepository.com/artifact/de.sstoehr/har-reader)

## Usage

Reading HAR from File:

```java
HarReader harReader = new HarReader();
Har har = harReader.readFromFile(new File("myhar.har"));
System.out.println(har.getLog().getCreator().getName());
```

Reading HAR from String:

```java
HarReader harReader = new HarReader();
Har har = harReader.readFromString("{ ... HAR-JSON-Data ... }");
```

Some HAR generators use date formats, which are not according to the specification.
You can tell HAR reader to ignore those fields instead of throwing an exception:

```java
HarReader harReader = new HarReader();   
Har har = harReader.readFromFile(new File("myhar.har"), HarReaderMode.LAX);
Har har = harReader.readFromString("{ ... HAR-JSON-Data ... }", HarReaderMode.LAX);
```

You can also follow the next section and configure your own mapping configuration to deal with these fields.

### Customizing HAR reader

As of version 2.0.0 you can create your own `MapperFactory` [(DefaultMapperFactory)](src/main/java/de/sstoehr/harreader/jackson/DefaultMapperFactory.java)

 
```java
public class MyMapperFactory implements MapperFactory {
    public ObjectMapper instance(HarReaderMode mode) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        
        // configure Jackson object mapper as needed

        mapper.registerModule(module);
        return mapper;
    }
}
```

You can now use your configuration by instantiating the `HarReader` with your `MapperFactory`:

```java
HarReader harReader = new HarReader(new MyMapperFactory());
```

## Latest Releases

### 2.2.1 - 2022-05-26

* Updated dependencies
* #82: Make sure default values from HAR entities satisfies specification

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.2.1)

### 2.2.0 - 2021-03-27

* Updated dependencies
* Added support for fields, which are not supported in official spec. You can access these fields using `Map<String, Object> getAdditional()`

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.2.0)

### 2.1.10 - 2020-10-05

* Updated dependencies

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.10)

### 2.1.9 - 2020-06-30

* Updated dependencies

_This is the first release, which is provided both on GitHub and Maven Central repository._

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.9)

### 2.1.8 - 2020-05-24

* Updated dependencies

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.8)

### 2.1.7 - 2019-11-05

* Updated dependencies

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.7)

### 2.1.6 - 2019-10-04

* Updated dependencies

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.6)

### 2.1.5 - 2019-09-06

* Updated dependencies

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.5)

### 2.1.4 - 2019-05-24

* Updated dependencies

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.4)

### 2.1.3 - 2018-10-18

* Updated dependencies ([CVE-2018-7489](https://nvd.nist.gov/vuln/detail/CVE-2018-7489))

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.3)

### 2.1.2 - 2018-08-02

* Added support for several HTTP status codes, e.g. (308, 422 - 451, 505 - 511)

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.2)

### 2.1.1 - 2018-07-26

* Added support for HTTP method: ```PATCH```

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.1)

### 2.1.0 - 2018-03-11

* You can now access additional fields, which are not part of the HAR spec:

```java
response.getAdditional().get("_transferSize");
```

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.1.0)

### 2.0.3 - 2017-04-14

* Added equals and hashCode methods

### 2.0.2 - 2016-11-21

* Added CCM_POST HttpMethod to enum

### 2.0.1 - 2016-04-16 

* Ignore invalid integers in lax mode

[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.0.1)

### 2.0.0 - 2015-08-30

* HAR reader is now easier customizable. Use your own `MapperFactory` to adjust HAR reader for your project!
* HAR reader threw exceptions, when required fields were empty. This behaviour was changed, so that you can now read non-standard-compliant HAR files
  
[Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-2.0.0)  
