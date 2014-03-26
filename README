compare_ead_files
=================

Compares two version of an EAD file

**Installation**

Clone the app in a directory

```
  $ git clone https://github.com/KepaJRodriguez/compare_ead_files
```


Build the application (maven 3.1 or higher version needed)
```
  $ cd compare_ead_files
  $ mvn compile assembly:single
```


You will find the jar file **compare_ead_files-0.0.1-SNAPSHOT-jar-with-dependencies.jar** in the 
*target* directory


**Use**

```
java -jar compare_ead_files-0.0.1-SNAPSHOT-jar-with-dependencies.jar eadfile1.xml eadfile2.xml
```

As output you will get one of the following messages:
- Node PATH has different identifiers in both EAD
- Node PATH of the first EAD is not present in the second EAD
- Node PATH of the second EAD is not present in the first EAD
- No changes found
