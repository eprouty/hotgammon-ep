Instructions for preparing your Backgammon player for the final tournament.
Follow the steps below so you can submit your project in a form that can be
used for the final project deliverable.

1. Do not move or rename any of the files and packages in this project. You must 
   rename the hotgammon.player package to hotgammon.playerxx, where "xx" is your initials. 
   If there are two of you working on this, you can rename the package to 
   hotgammon.playerxxyy. This is important since the tournament manager will create
   an instance of your player through the reflection API. It will instantiate the
   class hotgammon.playerxxyy.tournamentPlayerImpl.
   
2. All of the classes that you add to the project must go into the package containing
   your TournamentPlayerImpl class or in packages beneath this package.
   
3. When your TournamentPlayerImpl constructor is called, the board will be in the 
   initial position. When your player's move() method is called, the board will reflect 
   the state of the game before your player makes the move.
   
4. When you finish your implementation, you will create a JAR file that contains all of the 
   classes required for your player to function. All of these should be in the 
   hotgammon.playerxx package and its subpackages. You should NOT include any files
   in the test source folder or modify the Color, Location, TournamentBoard, TournamentMove,
   and TournamentPlayer source files. If you just put the hotgammon.playerxx package and
   subpackages in the JAR file, you should have no problem.
   
You will submit this project, exported to a zipped archive AND your JAR file that will
have the name hotgammonxx.jar or hotgammonxxyy.jar, depending upon whether there are 
two of you working on the project or not. If your JAR file is named hotgammonxx.jar, the
tournament manager will try to instantiate the class hotgammon.playerxx.TournamentPlayerImpl.