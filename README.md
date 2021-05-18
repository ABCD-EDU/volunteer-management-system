## Starting Information
**First, make sure to set the project's SDK and Language level to both java version "11.0.9" and 11 - Local variable syntax for lambda parameters**

## Project Setup
1. Create a JavaFX Project inside Intellij.
2. After successfully creating the project, go into VCS and create a Git repository. (Same directory as project)
3. Go ahead and click Git and select Manage Remotes.
4. Get the HTTPS clone link of our repository and add it as one of our remotes.
5. Go to Git again and click Fetch then Pull.

### How to add JavaFX and JDBC Drivers lib
#### JDBC

To download JDBC, use the link and choose Platform Independent for the Operating System. Select the
ZIP File to download.
[Download link here](https://dev.mysql.com/downloads/connector/j/)

#### JavaFX

To download JavaFX, select the LTS for Java 11.0.9.
[Download link here](https://gluonhq.com/products/javafx/)

#### Installation
Step 1. Extract the zip file into a safe location in your PC or "C:\Program Files\***Folder Name***".<br/>
Change **Folder Name** to MySQL Connector or Java FX<br/>
Step 2. Go to IDEA and select File > Project Structure > Modules<br/>
Step 3. Click the + button on the right side window and click add JARs or Directories<br/>
Step 4. Here select all the files in your javafx/lib folder including the zip file<br/>
Step 5. Now, go to Run > Edit Configurations and add this into your VM options --module-path "path to javafx/lib" --add-modules=javafx.controls,javafx.fxml<br/>

### For JDBC:
For the MySQL Connector, choose mysql-connector-java-<'version'>-bin.jar to import as an external library.
Double check in modules if the library is imported.

### For Java FX:
For Java FX, we must import all of the jar files found in the lib folder. Navigate to javafx-sdk-<'version'>
then move to lib and import everything.

## Import SQL File
Step 1. Go to http://localhost/phpmyadmin and login with the root account.<br/>
Step 2. Create a new database on the left-side of the interface and name it as "221-events". (leave everything as default)<br/>
Step 3. Select your newly created database in the list on the left side of the interface.<br/>
Step 4. Go to the Import tab and select browse file. Locate the SQL file included in the project and select that file.<br/>
Step 5. Leave everything as default and click the "Go" button to import the dump.
