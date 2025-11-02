# HAR Reader

A Java library for reading and writing [HTTP Archive (HAR)](http://www.softwareishard.com/blog/har-12-spec/) files.

[![Build Status](https://app.travis-ci.com/sdstoehr/har-reader.svg?branch=main)](https://app.travis-ci.com/sdstoehr/har-reader)
[![codecov](https://codecov.io/github/sdstoehr/har-reader/graph/badge.svg?token=TQ9XVRjg4A)](https://codecov.io/github/sdstoehr/har-reader)
[![Maven Central](https://img.shields.io/maven-central/v/de.sstoehr/har-reader.svg)](http://mvnrepository.com/artifact/de.sstoehr/har-reader)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Features

- ✅ Read HAR files from File, String, or InputStream
- ✅ Write HAR data to File, OutputStream, Writer, or byte array
- ✅ Fluent builder API for creating HAR data structures
- ✅ Support for non-standard date formats (LAX mode)
- ✅ Customizable Jackson ObjectMapper configuration
- ✅ Support for additional non-standard HAR fields
- ✅ Fully compliant with HAR 1.2 specification
- ✅ Records-based immutable model (Java 17+)

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Reading HAR Files](#reading-har-files)
  - [Writing HAR Files](#writing-har-files)
  - [Building HAR Data](#building-har-data)
  - [Customization](#customization)
- [Release Notes](#release-notes)
- [License](#license)

## Requirements

- Java 17 or higher
- Jackson 3.x (for version 4.0.0+)
- Jackson 2.x (for version 3.1.6 and earlier)

## Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>de.sstoehr</groupId>
  <artifactId>har-reader</artifactId>
  <version>4.0.0</version>
</dependency>
```

## Usage

### Reading HAR Files

#### From File

```java
HarReader harReader = new HarReader();
Har har = harReader.readFromFile(new File("myhar.har"));
System.out.println(har.log().creator().name());
```

#### From String

```java
HarReader harReader = new HarReader();
Har har = harReader.readFromString("{ ... HAR-JSON-Data ... }");
```

#### LAX Mode for Non-Standard Formats

Some HAR generators use date formats that don't comply with the specification. Use LAX mode to handle these gracefully:

```java
HarReader harReader = new HarReader();
Har har = harReader.readFromFile(new File("myhar.har"), HarReaderMode.LAX);
// or
Har har = harReader.readFromString("{ ... }", HarReaderMode.LAX);
```

For more control over date parsing, see the [Customization](#customization) section.

### Writing HAR Files

#### To File

```java
Har har = new Har();
HarWriter harWriter = new HarWriter();
harWriter.writeTo(new File("myhar.har"), har);
```

#### To OutputStream

```java
Har har = new Har();
HarWriter harWriter = new HarWriter();
ByteArrayOutputStream baos = new ByteArrayOutputStream();
harWriter.writeTo(baos, har);
```

#### To Writer

```java
Har har = new Har();
HarWriter harWriter = new HarWriter();
StringWriter sw = new StringWriter();
harWriter.writeTo(sw, har);
```

#### As Byte Array

```java
Har har = new Har();
HarWriter harWriter = new HarWriter();
byte[] harBytes = harWriter.writeAsBytes(har);
```

### Building HAR Data

Create HAR data structures using the fluent builder API:

```java
Har har = Har.builder()
    .log(HarLog.builder()
        .creator(HarCreatorBrowser.builder()
            .name("HAR reader")
            .version("1.0")
            .build())
        .entry(HarEntry.builder()
            .pageref("page_0")
            .startedDateTime(ZonedDateTime.parse("2021-01-01T00:00:00Z"))
            .time(42)
            .request(HarRequest.builder()
                .method(HttpMethod.GET.name())
                .url("https://www.example.com")
                .httpVersion("HTTP/1.1")
                .build())
            .response(HarResponse.builder()
                .status(200)
                .statusText("OK")
                .httpVersion("HTTP/1.1")
                .build())
            .build())
        .build())
    .build();
```

#### Builder API Features

The builders support both single and batch operations:

```java
// Add single entry
harLogBuilder.page(HarPage page);

// Add multiple entries (does NOT replace existing entries)
harLogBuilder.pages(List<HarPage> pages);

// Clear all entries
harLogBuilder.clearPages();
```

#### Updating Existing Objects

Use `.toBuilder()` to create a modified copy of an existing object:

```java
Har updatedHar = har.toBuilder()
    .log(har.log().toBuilder()
        .comment("Updated comment")
        .build())
    .build();
```

### Customization

Create a custom `MapperFactory` to configure Jackson's ObjectMapper behavior:

```java
public class MyMapperFactory implements MapperFactory {
    public ObjectMapper instance(HarReaderMode mode) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        // Configure Jackson object mapper as needed
        // e.g., custom date deserializers, property naming strategies, etc.

        mapper.registerModule(module);
        return mapper;
    }
}
```

Use your custom factory:

```java
HarReader harReader = new HarReader(new MyMapperFactory());
```

See [DefaultMapperFactory](src/main/java/de/sstoehr/harreader/jackson/DefaultMapperFactory.java) for reference.

## Release Notes

### 4.0.0 - 2025-10-05

**⚠️ BREAKING CHANGE:** Switched to Jackson 3

If you need Jackson 2 support, use version 3.1.6.

[Full Release Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-4.0.0)

### 3.1.6 - 2025-10-02

* Dependency updates

[Full Release Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-3.1.6)

### 3.1.0 - 2025-04-23

**⚠️ BREAKING CHANGE:** Switched timings from `int` to `long`

[Full Release Details](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-3.1.0)

### 3.0.1 - 2024-12-21

**Major modernization release:**
- Minimum Java version: 17
- Records-based model (immutable by default)
- `ZonedDateTime` instead of `Date`
- Proper `@Nullable` and `@NotNull` annotations

[Full Release Details & Breaking Changes](https://github.com/sdstoehr/har-reader/releases/tag/har-reader-3.0.1)

<details>
<summary>View Older Releases</summary>

### 2.x Series (Java 8+)

- **2.5.0** (2024-11-20): Fixed browser field nullability
- **2.4.1** (2024-11-15): Fixed duplicate fields issue
- **2.4.0** (2024-11-13): HAR serialization support, unknown HTTP methods/status codes
- **2.3.0** (2023-11-17): Dropped Java 7 support
- **2.2.1** (2022-05-26): Default values compliance
- **2.2.0** (2021-03-27): Support for additional non-standard fields
- **2.1.x** (2018-2020): Various dependency updates and minor enhancements
- **2.0.0** (2015-08-30): Customizable MapperFactory support

[View All Releases](https://github.com/sdstoehr/har-reader/releases)

</details>

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
