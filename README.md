# Utilities Pro

Utilities Pro is a Java Runtime/ProcessBuilder tapper. It is to serve as a terminal in restricted enviornments, such as schools or universities. Tapping Java's ProcessBuilder or Runtime command system, its possible to bypass MCX, and most other controls on effective computer work.

Our release streams are as follows:
  * Master: Full Release, with binaries on GitHub. The master stream is the least updated of them, as it requires a passing of inspection, and is usually resulting from a merge from Beta into Master.
  * Beta: This is for development work. You will likely need to compile this yourself, and it will likely not have all functions completed, unless it is declared as Major.Minor_dev# (although there should be no versions which throw outright errors in Eclipse). Due to the change of the development cycle to Agile, there will be many, more frequent updates. The majority of them will likely be dev# versions, with Major versions released more infrequently.
  
  * WinUtilities: This is an independent branch, not supported by the main team. Another team of programmers is working on porting Utilities Pro (or a significant part of it), into a Windows XP, Vista, 7 environment. This allows for cross-platform support for the programme. We do not expect that Linux users will necessarily require a programme like Utilities Pro, and thus, there is no port for Linux.
  
  All other branches are not qualified for official release.

## Classes
All methods should be stored in their GUI Menu placement. If it is not a GUI tool, then it should be stored in TextCommands.
### CommandCommands
This class contains all the methods used in the GUI's command Menu. Thus, it shows the methods for terminating processes, terminating by PID, terminating by GUI, and bombarding commands.
### EditCommands
This class carries the contents of the edit menu. Cut, Copy, and Paste, however, are not a part of the class, as they are interfacing with OSX, and thus use Apple's EAWT, which is handled in MacHandler.
### ExecEngine
The Execution Engine is the core of the program. It contains all the methods necessary to create a process from Runtime and Process Builder. It also contains methods necessary for the program to maintain interoperability and rely on the privacy of Java's Library.
### FileCommands
This manages all the functions which are categorised in the File Menu. Since most of the file menu relates to files, it is the class which handles the exportation and configuration of Utilities Pro.
### HelpCommands
This handles all the things which are done in the help menu. 
### ScriptCommands
This manages all the functions in the script menu. It is where most (should be all) of the scripted commands which the programme contains should be located.
### TextCommands
TextCommands handles every part of the command-line interface's operation. It handles all of the text commands. It holds the commands and the logic for our implementation of 'cd', path, and tabComplete, as they are TextCommands. It also processes all of the functions which relate to the internal command-line commands, and all methods which have externalised them.
### UtilitiesPro.class
This is the main class. It constructs the GUI and contains all methods which relate to the operation and implentation of the GUI, and base data. It is somewhat bloated.
