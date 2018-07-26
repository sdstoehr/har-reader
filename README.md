HAR reader
==========

Read [HTTP Archives](http://www.softwareishard.com/blog/har-12-spec/) with Java.

```
<dependency>
  <groupId>de.sstoehr</groupId>
  <artifactId>har-reader</artifactId>
  <version>2.1.1</version>
</dependency>
```

[![Build Status](https://travis-ci.org/sdstoehr/har-reader.png?branch=master)](https://travis-ci.org/sdstoehr/har-reader)
[![Coverage Status](https://coveralls.io/repos/sdstoehr/har-reader/badge.png?branch=master)](https://coveralls.io/r/sdstoehr/har-reader?branch=master)
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
