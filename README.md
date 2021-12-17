# PG220
********
This project consists of making an application for a carpentry company that cuts big panels into smaller planks.

The application reads XML files containing informations about the Clients, their needs, the suppliers and their supply.

After filtering the files, the application runs different algorithms (the user can choose which one) to cut the planks, the application then generates XML files that contains the cuts, and generate SVG files containing the cut planks.
********

Functionalities:

-> XMLReader: Reads XML files to string
-> Solution: Writes Lists to XML and SVG Files
-> CutFactory: Executes 2nd algorithm
-> CutFactory2: Executes 3rd algorithm
-> GUI: Graphical User Interface

How to use:
********IMPORTANT********
The user must have 2 valid XML files named "clients.xml" and  "fournisseurs.xml" or the application will throw exceptions.
*************************

Etape_1:
Compile: javac *.java
Run: java Test
-> During this step, the application reads XML Files "clients.xml" and "fournisseurs.xml", checks if the data is valid, then prints the results to the standard output. 

Etape_2:
Compile: javac */*.java
Run: java Logic.Main
-> During this step, the application reads XML Files "clients.xml" and "fournisseurs.xml", filters the data and generates SVG Files following this cutting method:
---- Each SVG File contain one cut of one pannel into one plank.

Etape_3:
Compile: javac */*.java
Run: java Logic.Main
-> During this last step, the application reads XML Files "clients.xml" and "fournisseurs.xml", filters the data, and pops up a GUI that helps the user choose which algorithm to use.
After choosing the algorithm, the application proceeds to filter the data, and generate ONE XML file and multiple SVG files (if possible) following the chosen algorithm.
---- If the chosen algorithm is "First Algorithm", the application procceds to generate SVG Files following the first algorithm implemented in "CutFactory.java"
---- If the chosen algorithm is "Second Algorithm", the application prooceds to generate SVG Files following the second algorithm implemented in "CutFactory2.java"

NB: if the user decides to run the GUI multiple times without closing, the application deletes previous SVG files and generates new ones.