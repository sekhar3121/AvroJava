# AvroJava

This is an introduction to work with Avro data in Java.

# What is an Avro ?
Avro is a binary data format with its own data structures. It is language agnostic. To serialize/deserialize the data, it needs a Schema. This schema is usually expressed in JSON.

# Getting started with Avro in Java

Add the following dependecies in pom.xml

    <dependencies>
        <dependency>
            <groupId>org.apache.avro</groupId>
            <artifactId>avro</artifactId>
            <version>1.9.2</version>
        </dependency>
    </dependencies>




An Object model is an in-memory representation of data read into the program. 

Avro exposes three object models in java
1) Generic
2) Specific
3) Reflect

# Generic:
A set of classes that can be used with any runtime schema. 
GenericRecord class works with getters and setters by using field names.
Since it is a generic one, it takes/returns Object type and doesn't provide type safety.
Provided a schema, GenericRecord class creates an object. We can then set the field values using setters.
