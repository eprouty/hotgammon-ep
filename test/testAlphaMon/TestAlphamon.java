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
   * Test TODO:Add this test later, too early to attempt this. After moving black checker from R1 to R2 there is only one checker on R1.
   * Test #4. After moving there is only one turn left.
   */
  @Test public void moveBlackCheckerFromR1toR2isValid(){
	  assertEquals("Failed trying to move a piece from R1 to R2", true, game.move(Location.R1, Location.R2));
	  //assertEquals("Number of checkers left on R1 != 1", 1, game.getCount(Location.R1));
	  assertEquals("There numberOfMovesLeft() != 1", 1, game.getNumberOfMovesLeft());
  }
  
  /**
   * Test #5. There are 2 red checkers on B1 to start
   */
  @Test public void thereAre2RedCheckersOnB1atStart(){
	  assertEquals(2, game.getCount(Location.B1));
	  assertEquals(Color.RED, game.getColor(Location.B1));
  }
}
