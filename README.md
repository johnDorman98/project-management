<a id="toc"></a>
# Table of contents
 1. [Introduction](#introduction)
 2. [Features](#features)
 3. [Installation](#installation)
 4. [Usage](#usage)
 5. [License](#license)
 6. [Author](#author)

<a id="introduction"></a>
## Welcome to my Java Project Management Program. 👋 

 This program is a project management system created for an example small structural engineering firm which will allow them to use the program use to keep track of the many projects on which they work.

 The project will implement Object Oriented Programming (OOP) to show my understanding of the topic and will be updated and improved with time.
 
<a name="features"></a>
## Features 
 
 * Allows the user to capture the details that will be used to create a new project object.
 * Allows the user to add the people associated with the project.
 * The user is able to update or change the details of the project using the project number or name to find a specific project.
 * The user is able to view uncompleted or overdue tasks.
 * The user is able to update the completion status for a project by using the project number or project name.
 * The project is implemented with MySQL Database connectivity.
   * It is able to read details about existing projects including people assigned to each project.
   * It can add new projects and people to the database.
   * It allows the user to edit existing projects within the database.

<a name="installation"></a>
## Installation 

 * In order to run this program you will need to have the Java Development Kit (JDK) and a IDE in this case we will be using IntelliJ.
 * Below I will include instructions for installing JDK and IntelliJ to get it setup and allow you to run the program.
   * Firstly for IntelliJ to work we will need to install JDK. Below will be instructions for that.
     * Click [here](https://www.oracle.com/java/technologies/downloads/) to go to the JDK download page then download the installer for your Operating System (OS).
     * Once it has been downloaded run the installer and follow the installers instructions, After the installation is finished you can click close.
     * You can find a full guide on how to install JDK [here](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A__INSTALLINGTHEJDKANDJREONMICROSOFTWI-E04E8B17)
     * Next lets confirm that JDK has been installed correctly, to do so type the following into the command-line/terminal.
     * *java --version* and you should see something similar to whats below
     
       ```
       java 17.0.2 2022-01-18 LTS
       Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
       Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)
       ```
     * Do the same for *javac --version*
     
       ```
       javac 17.0.2
       ```
       
  * Now that JDK has been installed correctly we can now install IntelliJ clicking [here](https://www.jetbrains.com/idea/download/?fromIDE=#section=windows) for the download page then download the community version for your OS.
  
    ![Download the community version](https://user-images.githubusercontent.com/98963869/152643639-396bad1c-0995-43c9-98e0-45c2363f30b7.png)
    
    * Once it has been downloaded run the installer to download IntelliJ.
  
  * IntelliJ and JDK should both now be installed on your computer and you can now download or clone the program from github saving it somewhere you can find later.

  * With the latest changes to work you will also need to have MySQL installed for the project to interact with the database.
    * In the following steps I will help walk you through the setup for MySQL installation.
      * Clicking [here](https://dev.mysql.com/doc/refman/8.0/en/installing.html) will take you to MySQL documentation on installation then you can select the option that corresponds to you operating system.
      * In my case I will select the option for installing on windows and follow along with the instructions there.
      * After downloading the installer from [here](https://dev.mysql.com/downloads/mysql/)
        * Use the installer and complete the installation.
        * Once you are done installing MySQL I would recommend to watch a youtube video on how to setup and use it so that you have a better understanding of how it works.
          * Following that there will also be a .sql file called table_creation. If you import this into your MySQL workbench it will give you the needed sql statements to setup all the required tables.
    
<a name="usage"></a>
## Usage  
 * To begin using the program for the first time, if you have downloaded the program as a .zip firstly extract it in order to use it within IntelliJ.
   * With the program now downloaded and extracted open IntelliJ and the following steps will help walk you through how to get the program running.
     * Click on open and navigate to where you have stored the extracted the program folder then click open and the program should now be opened in IntelliJ.
       * You should have a screen open similar to the image below.
       
       ![Setup Project](https://user-images.githubusercontent.com/98963869/168250440-97dcc4be-a917-4dc4-8360-9678b278b36a.png)
       
       * If its still blank for you but you can see the file names then double click on Main in order to open it as shown above.
       * After the Main file is open you should have a banner at the top of the screen, promting you to setup your JDK version click on setup JDK and selecting your   JDK Version
       * Before you can run the program you will need to make minor changes for the program to connect to your database.
         * In order to make these changes you will need to navigate to DatabaseManagement.java file.
           * once there, Edit the variables user and pass, Placing your username and password for your MySQL login.
       * With the JDK setup you can now right click on the code and click on Run 'Main.main()'.
       * The program should now be running and will present you with a menu giving you options for you to select from.

<a name="license"></a>
## License 

Distributed under the GNU General Public License v3.0. See `LICENSE.txt` for more information.

<a name="author"></a>
## Author 

🖥️ **John Dorman**

* Github: [@johnDorman98](https://github.com/johnDorman98)

## Show your support

Give a ⭐️ if this project helped you!

[Back to the top of the page](#table-of-contents)
