package testAlphaMon;

import org.junit.*;

import alphaMon.Color;
import alphaMon.Game;
import alphaMon.GameImpl;
import alphaMon.Location;
import static org.junit.Assert.*;

/** Testing skeleton for AlphaMon.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/
public class TestAlphamon {
  private Game game;
  
  @Before public void setup() {
    game = new GameImpl();
    game.newGame();
  }
  
  @Test public void shouldHaveNoPlayerInTurnAfterNewGame() {
    assertEquals( Color.NONE, game.getPlayerInTurn() );
  }
  @Test public void shouldHaveBlackPlayerInTurnAfterNewGame() {
    game.nextTurn(); // will throw [1,2] and thus black starts
    assertEquals( Color.BLACK, game.getPlayerInTurn() );
  }
  
  /**
   * Test #1. Make sure there are 2 checkers to start on R1
   */
  @Test public void thereAre2CheckersOnR1(){
	  assertEquals(2, game.getCount(Location.R1));
  }
  
  /**
   * Test #2. Ensure that the 2 checker starting on R1 are also black checkers
   */
  @Test public void the2CheckersOnR1AreBlack(){
	  assertEquals(Color.BLACK, game.getColor(Location.R1));
  }
  
  /**
   * Test #3. Moving a black checker from R1 to R2 is valid.
   * Test #7: After moving black checker from R1 to R2 there is only one checker on R1.
   * Test #4. After moving there is only one turn left.
   */
  @Test public void moveBlackCheckerFromR1toR2isValid(){
	  game.nextTurn();
	  assertEquals("Failed trying to move a piece from R1 to R2", true, game.move(Location.R1, Location.R2));
	  assertEquals("Number of checkers left on R1 != 1", 1, game.getCount(Location.R2));
	  assertEquals("There numberOfMovesLeft() != 1", 1, game.getNumberOfMovesLeft());
  }
  
  /**
   * Test #5. There are 2 red checkers on B1 to start
   */
  @Test public void thereAre2RedCheckersOnB1atStart(){
	  assertEquals(2, game.getCount(Location.B1));
	  assertEquals(Color.RED, game.getColor(Location.B1));
  }
  
  /**
  * Test #6. Cannot more from R1 to B1 as there is an opponent (red) piece in the way
  */
  @Test public void cannotMoveFromR1toB1(){
	  game.nextTurn();
	  assertEquals(false, game.move(Location.R1, Location.B1));
  }
  /**
   * Test #20. Converse of test #6
   */
  @Test public void cannotMoveFromB1toR1(){
	  game.nextTurn(); //black turn
	  game.nextTurn(); //red turn
	  assertFalse(game.move(Location.B1, Location.R1));
  }
  
  /**
   * Test #8. After moving 2 black checkers the number of moves left is 0.
   */
  @Test public void noTurnsLeftAfterMoving2BlackCheckers(){
	  game.nextTurn();
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertTrue(game.move(Location.R2, Location.R3));
	  assertEquals(0, game.getNumberOfMovesLeft());
  }
  
  /**
   * Test #9. Red players turn after nextTurn is called twice.
   */
  @Test public void redPlayersTurnSecond(){
	  game.nextTurn();
	  game.nextTurn();
	  assertEquals(Color.RED, game.getPlayerInTurn());
  }
  
  /**
   * Test #10. Die values on the second turn are 3-4
   */
  @Test public void secondTurnDieValuesAre34(){
	  game.nextTurn();
	  game.nextTurn();
	  assertArrayEquals(new int[] {3,4}, game.diceThrown());
  }
  
  /**
   * Test #11. First turn die roll is 1-2
   */
  @Test public void firstTurnDieValuesAre12(){
	  game.nextTurn();
	  assertArrayEquals(new int[] {1,2}, game.diceThrown());
  }
  
  /**
   * Test #12. Third turn die roll is 5-6
   */
  @Test public void thirdTurnDieValuesAre56(){
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  assertArrayEquals(new int[] {5,6}, game.diceThrown());
  }
  
  /**
   * Test #13. The next series of tests ensures that the board is set up properly
   */
  @Test public void fiveBlackOnB6(){
	  assertEquals(5, game.getCount(Location.B6));
	  assertEquals(Color.BLACK, game.getColor(Location.B6));
  }
  @Test public void fiveRedOnR6(){
	  assertEquals(5, game.getCount(Location.R6));
	  assertEquals(Color.RED, game.getColor(Location.R6));
  }
  @Test public void threeBlackOnB8(){
	  assertEquals(3, game.getCount(Location.B8));
	  assertEquals(Color.BLACK, game.getColor(Location.B8));
  }
  @Test public void threeRedOnR8(){
	  assertEquals(3, game.getCount(Location.R8));
	  assertEquals(Color.RED, game.getColor(Location.R8));
  }
  @Test public void fiveRedOnB12(){
	  assertEquals(5, game.getCount(Location.B12));
	  assertEquals(Color.RED, game.getColor(Location.B12));
  }
  @Test public void fiveBlackOnR12(){
	  assertEquals(5, game.getCount(Location.R12));
	  assertEquals(Color.BLACK, game.getColor(Location.R12));
  }
  @Test public void noneOnEmptySpotAtStart(){
	  assertEquals(0, game.getCount(Location.R11));
	  assertEquals(Color.NONE, game.getColor(Location.R11));
  }
  
  /**
   * Test #14. The dice roll sequence repeats
   */
  @Test public void testDiceRollRepeat(){
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  assertArrayEquals(new int[] {1,2}, game.diceThrown());
  }
  
  /**
   * Test #15. Pieces are removed from old location after movement
   */
  @Test public void testPieceRemovedFromOldLocationOnMovement(){
	  game.nextTurn();
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertEquals(1, game.getCount(Location.R1));
  }
  
  /**
   * Test #16. Cannot move from an empty space
   */
  @Test public void testCannotMoveFromEmptySpace(){
	  game.nextTurn();
	  assertFalse(game.move(Location.R2, Location.R3));
  }
  
  /**
   * Test #17. Moving a piece updates the new locations color to the correct color
   */
  @Test public void movingUpdatesTheLocationsColor(){
	  game.nextTurn();
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertEquals(Color.BLACK, game.getColor(Location.R2));
  }
  
  /**
   * Test #18. Cannot make a move if the player has no moves remaining.
   */
  @Test public void cannotMoveOnceMovesAreUsed(){
	  game.nextTurn();
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertTrue(game.move(Location.R2, Location.R4));
	  assertFalse(game.move(Location.R4, Location.R5));
  }
  
  /**
   * Test #19. Move count resets at the beginning of a new turn.
   */
  @Test public void moveCountResetsAtBegginingOfTurn(){
	  game.nextTurn();
	  assertEquals(2, game.getNumberOfMovesLeft());
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertEquals(1, game.getNumberOfMovesLeft());
	  game.nextTurn();
	  assertEquals(2, game.getNumberOfMovesLeft());
  }
  
  /**
   * Test #21. Players can only move their own pieces
   */
  @Test public void cannotMoveOpponentsPieces(){
	  game.nextTurn();
	  assertFalse(game.move(Location.B1, Location.B2));
  }
  
  /**
   * Test #22. winner() returns Color.NONE until the game ends
   */
  @Test public void winnerReturnsNoneWhileGameInProgress(){
	  assertEquals(Color.NONE, game.winner());
  }
  
  /**
   * Test #23. Game ends after 6 turns, RED wins
   */
  @Test public void gameEndsAfter6Turns(){
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  assertEquals(Color.RED, game.winner());
  }
  
  /**
   * Test #24. Moving all pieces off an area changes color to NONE
   */
  @Test public void movingAllPiecesOffLocationChangesColorToNone(){
	  game.nextTurn();
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertEquals(2, game.getCount(Location.R2));
	  assertEquals(0, game.getCount(Location.R1));
	  assertEquals(Color.NONE, game.getColor(Location.R1));
  }
  
  /**
   * Test #25. Satisfy POSTCONDITION of diceValuesLeft() that they are sorted from largest to smallest
   */
  @Test public void testDiceValuesLeftSorted(){
	  game.nextTurn();
	  assertEquals(2, game.diceValuesLeft()[0]);
	  assertEquals(1, game.diceValuesLeft()[1]);
  }
}
