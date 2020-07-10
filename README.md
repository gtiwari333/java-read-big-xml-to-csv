## Sample project for  
 - https://ganeshtiwaridotcomdotnp.blogspot.com/2011/08/xml-parsing-using-saxparser-with.html
 - https://ganeshtiwaridotcomdotnp.blogspot.com/2020/07/java-read-huge-xml-file-and-convert-to.html
 - https://ganeshtiwaridotcomdotnp.blogspot.com/2020/07/graalvm-setup-and-generate-native-image.html

## This uses Java SAX parser to parse a big xml file efficiently and convert to csv file

First, import into Intellij as Maven project. Make sure you have maven

## Requirement: make sure you have java installed (run `java -version`)

## Generate Jar file - it will be generated in target folder

`mvnw clean package`

## Run 

`java -jar target\xmltocsv-FINAL.jar  C:\folder\input.xml  C:\folder\output.csv`

## Time/Memory Consumption

RAM Used: less than 190MB

Time Taken:

File: 118MB big2.xml @ 16GB Ram, Core i5, 6MB L3 cache, SSD
- JDK8 - 8-9 sec
- JDK 11 - 6-7 sec 
- JDK 14 - 5 sec     

# Included xml files - unzip before use
- small.xml - 1.44KB file
- big1.zip - 2MB file
- big2.zip - 118MB file
- big3.7z - 6.58GB file

# Native Image with GraalVM
Refer to this https://ganeshtiwaridotcomdotnp.blogspot.com/2020/07/graalvm-setup-and-generate-native-image.html blog for how to setup GraalVM and native-image utility

Run the following to generate native image

```mvn clean package native-image:native-image```

- Run native executable

```
$ ./target/xmltocsv ../big2.xml ../big2.csv
```