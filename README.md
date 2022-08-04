# csc413-tankgame


| Student Information |                       |
|:-------------------:|-----------------------|
|  Student Name       | Ethan Lunderville     |
|  Student Email      | elunderville@sfsu.edu |

## src Folder Purpose 
src folder is to be used to store source code only.

## resources Folder Purpose 
resources folder is to be used to store the resources for your project only. This includes images, sounds, map text files, etc.

`The src and resources folders can be deleted if you want a different file structure`

## jar Folder Purpose 
The jar folder is to be used to store the built jar of your term-project.

`NO SOURCE CODE SHOULD BE IN THIS FOLDER. DOING SO WILL CAUSE POINTS TO BE DEDUCTED`

`THIS FOLDER CAN NOT BE DELETED OR MOVED`

# Required Information when Submitting Tank Game

## Version of Java Used: Java 17

## IDE used: Intellij

## Steps to Import project into IDE:

Select File->New->Project From Existing Sources

Select the csc413-tankgame-ethanlunderville folder and press ”ok”

## Steps to Build and run your Project:

Once the project has been imported. Create an application run configuration using the Main.Launcher class as the Main class.
Ensure that you are using at least java 17 or above.

After that press apply and ok on the bottom right of the GUI.
Following this you will need to tell the ide where the resource folder is. To do this you press :

File -> Project Settings -> Project Structure -> Modules
Press the csc413-tankgame-ethanlunderville module and you will see this:

On the “mark as” menu click resources and then click resources from the files in the project.
After this apply the changes and the game should be ready to run.
Press the green play button.

Building the Jar

Go to
File -> Project Settings -> Artifacts and press the plus sign

Then choose Jar and “From modules with dependencies…”
When it asks for the main class type: Main.Launcher.
After this, to build the jar, go to

Build -> Build Artifacts.


Now the jar should be in the csc413-tankgame-ethanlunderville\out\artifacts\csc413_tankgame_ethanlunderville_jar directory.
To run the jar, open a terminal (Command Prompt, Powershell etc.) and navigate into this directory.
When you are in the directory type this command to run the game :  

java -jar csc413-tankgame-ethanlunderville.jar

## Controls to play your Game:

|               | Player 1 | Player 2  |
|---------------|----------|-----------|
|  Forward      | W        | Up Key    |
|  Backward     | S        | Down Key  |
|  Rotate left  | A        | Left Key  |
|  Rotate Right | D        | Right Key |
|  Shoot        | Space    | K         |

<!-- you may add more controls if you need to. -->