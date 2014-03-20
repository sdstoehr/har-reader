HAR reader
==========

Read [HTTP Archives](http://www.softwareishard.com/blog/har-12-spec/) with Java.

```
<dependency>
  <groupId>de.sstoehr</groupId>
  <artifactId>har-reader</artifactId>
  <version>1.0.0</version>
</dependency>
```

[![Build Status](https://travis-ci.org/sdstoehr/har-reader.png?branch=master)](https://travis-ci.org/sdstoehr/har-reader)
[![Coverage Status](https://coveralls.io/repos/sdstoehr/har-reader/badge.png?branch=master)](https://coveralls.io/r/sdstoehr/har-reader?branch=master)

## Usage

Reading HAR from File:

```
Har har = HarReader.fromFile(new File("myhar.har"));
System.out.println(har.getLog().getCreator().getName());
```

Reading HAR from String:

```
Har har = HarReader.fromString("{ ... HAR-JSON-Data ... }");
```

