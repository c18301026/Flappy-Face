# Flappy-Face
A clone of the well-known Flappy Bird mobile game for Android

This was made using Java and Android Studio.

# Classes used
1. The DatabaseHelper class deals with all the database operations, i.e., INSERT, UPDATE, SELECT and DELETE. It makes use of the SQLiteOpenHelper and the SQLiteDatabase classes. In this class, there is also a method called getAllEntries() to return a list of all the leaderboard entries, i.e., player names and their corresponding scores.

2. The GameActivity class is the activity where the actual game is played. It sets the view to the game and makes use of a handler and a timer to control the speed of the game.

3. The GameOverActivity class is the activity when the player hits one of the two obstacles or the ground. There are two text views for displaying "Game Over" and the player's score. The edit text view is a field for the player to enter their name. The button will insert this data into the database if the player's name is unique. If the name is already used, then the score is updated instead.

4. The GameView class contains the code for the game itself. This is where the player's character, obstacles and score are defined and displayed onto the canvas/screen. It is also where the code for the game loop, collision detection and character control can be found.

5. The GenericObject class is a parent class for the Player and Obstacle class. This is where the x, y, width, height and speed attributes of those two child classes can be found.

6. The LeaderboardActivity class is the activity for displaying a list of player names and their associated scores. It lists the players in order of their score with the highest being at the top and the lowest at the bottom. It also has a button for going back to the menu (main) activity.

7. The LeaderboardEntry class simply defines the table columns of the database used by the app. It stores the player name and the score.

8. The MainActivity class acts as the app's main menu. It has a text view that displays the title of the app and three buttons to: 
	1. play the game
	2. display the leaderboard
	3. go to the options

9. The MusicActivity class has image buttons with the faces of different musicians (Johann Sebastian Bach, Sir Roland Hanna, Ralf Scheepers) and the default player character. Clicking on any of them plays a piece associated with them:
	1. Johann Sebastian Bach plays Fugue in E-flat major, BWV 998
	2. Sir Roland Hanna plays Century Rag
	3. Ralf Scheepers (Gamma Ray) plays Mr. Outlaw
	4. Default character mutes any music already playing

10. The Obstacle class is a child of the GenericObject class. It has a Paint object to set its colour, i.e., black, and a method move() to constantly move towards the left of the screen.

11. The OptionsActivity class has buttons to let you go to the MusicActivity, clear (delete) the leaderboard or go back to the MainActivity.

12. The Player class is a child of the GenericObject class. It has a Bitmap object that holds the player character's current sprite and a Boolean attribute that describes the current state of the player, i.e., dead or alive. It also has a gravity() method to pull the player down towards the ground.

# How the application works
This app is essentially a clone of Dong Nguyen's (in)famous Flappy Bird game. The first activity/screen you see is the main menu which has three buttons to play the game, display the leaderboard and show options. When you play the game, the goal is to make your character, i.e., a face, fly in the air as long as possible without touching the columns or the ground. You get a score when you successfully pass though the columns. When you die, you are greeted with a game over screen that asks for your name. After entering your name, you are listed in the leaderboard with the score you got when you died. You can always view the leaderboard with the leaderboard button in the main menu. In the options section, you can listen to some music or clear the leaderboard.

# Video Demonstration
[![Vimeo Video Demonstration]](https://vimeo.com/488595115)

# Music used
- Johann Sebastian Bach - Fugue in E-flat major, BWV 998
- Sir Roland Hanna - Century Rag
- Ralf Scheepers (Gamma Ray) - Mr. Outlaw (instrumental)
