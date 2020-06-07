package com.example.generic;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.*;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

public class GenericRecordExamples {
    public static void main(String[] args) {
        // step 1: create a generic record
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse("{\n" +
                "     \"type\": \"record\",\n" +
                "     \"namespace\": \"com.example\",\n" +
                "     \"name\": \"Customer\",\n" +
                "     \"doc\": \"Avro Schema for our Customer\",     \n" +
                "     \"fields\": [\n" +
                "       { \"name\": \"first_name\", \"type\": \"string\", \"doc\": \"First Name of Customer\" },\n" +
                "       { \"name\": \"last_name\", \"type\": \"string\", \"doc\": \"Last Name of Customer\" },\n" +
                "       { \"name\": \"age\", \"type\": \"int\", \"doc\": \"Age at the time of registration\" },\n" +
                "       { \"name\": \"height\", \"type\": \"float\", \"doc\": \"Height at the time of registration in cm\" },\n" +
                "       { \"name\": \"weight\", \"type\": \"float\", \"doc\": \"Weight at the time of registration in kg\" },\n" +
                "       { \"name\": \"automated_email\", \"type\": \"boolean\", \"default\": true, \"doc\": \"Field indicating if the user is enrolled in marketing emails\" }\n" +
                "     ]\n" +
                "}");

        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
        customerBuilder.set("first_name", "Sindbad");
        customerBuilder.set("last_name", "the Sailor");
        customerBuilder.set("age", 25);
        customerBuilder.set("height", 172f);
        customerBuilder.set("weight", 75f);
        GenericData.Record customer = customerBuilder.build();

        // step 2: write the generic record to a file
        File file = new File("customers-generic.avro");
        try {
            DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
            DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
            dataFileWriter.create(customer.getSchema(), file);
            dataFileWriter.append(customer);
            dataFileWriter.close();
            System.out.println("written to file customer-generic.avro");
        } catch (IOException ex) {
            System.out.println("couldn't write to a file");
        }

        // step 3: read a generic record from a file
        try {
            DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>();
            DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
            GenericRecord record = dataFileReader.next();
            System.out.println(record);
            System.out.println("successfully read the data from customers-generic.avro");
        } catch(IOException ex) {
            System.out.println("failed in reading customers-generic.avro");
        }

    }
}
