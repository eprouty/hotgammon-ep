package testAlphaMonMHTF;

import org.junit.*;

import alphaMon.AlphaMoveStrategy;
import baseMon.Color;
import baseMon.Game;
import baseMon.GameImpl;
import baseMon.Location;


import static org.junit.Assert.*;

/**
 * Test cases for the AlphaMon version of Backgammon.
 * 
 * @author Henrik B. Christensen
 * @author Timothy Flynn
 * @author Mark Hawthorne
 * @version January 26, 2011
 */
public class TestAlphamon {
	
	private Game game;

	@Before public void setup() {
		game = new GameImpl(new AlphaMoveStrategy());
	}

	@Test 
	public void shouldHaveNoPlayerInTurnAfterNewGame() {
		game.newGame();
		assertEquals( Color.NONE, game.getPlayerInTurn() );
	}
	
	@Test 
	public void shouldHaveBlackPlayerInTurnAfterNewGame() {
		game.newGame();
		game.nextTurn(); // will throw [1,2] and thus black starts
		assertEquals( Color.BLACK, game.getPlayerInTurn() );
	}
	
	@Test
	public void shouldBeTwoBlackCheckersOnR1() {
		game.newGame();
		assertEquals(2, game.getCount(Location.R1));
		assertEquals(Color.BLACK, game.getColor(Location.R1));
	}
	
	@Test
	public void shouldBeFiveRedCheckersOnR6() {
		game.newGame();
		assertEquals(5, game.getCount(Location.R6));
		assertEquals(Color.RED, game.getColor(Location.R6));
	}
	
	@Test
	public void shouldBeThreeRedCheckersOnR8() {
		game.newGame();
		assertEquals(3, game.getCount(Location.R8));
		assertEquals(Color.RED, game.getColor(Location.R8));
	}
	
	@Test
	public void shouldBeFiveBlackCheckersOnR12() {
		game.newGame();
		assertEquals(5, game.getCount(Location.R12));
		assertEquals(Color.BLACK, game.getColor(Location.R12));
	}
	
	@Test
	public void shouldBeFiveRedCheckersOnB12() {
		game.newGame();
		assertEquals(5, game.getCount(Location.B12));
		assertEquals(Color.RED, game.getColor(Location.B12));
	}
	
	@Test
	public void shouldBeThreeBlackCheckersOnB8() {
		game.newGame();
		assertEquals(3, game.getCount(Location.B8));
		assertEquals(Color.BLACK, game.getColor(Location.B8));
	}
	
	@Test
	public void shouldBeFiveBlackCheckersOnB6() {
		game.newGame();
		assertEquals(5, game.getCount(Location.B6));
		assertEquals(Color.BLACK, game.getColor(Location.B6));
	}
	
	@Test
	public void shouldBeTwoRedCheckersOnB1() {
		game.newGame();
		assertEquals(2, game.getCount(Location.B1));
		assertEquals(Color.RED, game.getColor(Location.B1));
	}
	
	@Test
	public void shouldBeNoCheckersOnOtherLocations() {
		game.newGame();
		
		assertEquals(0, game.getCount(Location.B2));
		assertEquals(Color.NONE, game.getColor(Location.B2));
		
		assertEquals(0, game.getCount(Location.B10));
		assertEquals(Color.NONE, game.getColor(Location.B10));
		
		assertEquals(0, game.getCount(Location.R7));
		assertEquals(Color.NONE, game.getColor(Location.R7));
		
		assertEquals(0, game.getCount(Location.R5));
		assertEquals(Color.NONE, game.getColor(Location.R5));
	}
	
	@Test
	public void shouldBeAbleToMoveBlackFromR1ToR2AfterGameStarts() {
		game.newGame();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertEquals(1, game.getCount(Location.R1));
		assertEquals(1, game.getCount(Location.R2));
		assertEquals(1, game.getNumberOfMovesLeft());
	}
	
	@Test
	public void shouldBeAbleToMoveBlackFromR1ToR2ThenR1ToR3() {
		game.newGame();
		game.nextTurn();
		
		// R1 to R2
		assertTrue(game.move(Location.R1, Location.R2));
		assertEquals(1, game.getCount(Location.R1));
		assertEquals(1, game.getCount(Location.R2));
		assertEquals(1, game.getNumberOfMovesLeft());
		
		// R1 to R3
		assertTrue(game.move(Location.R1, Location.R3));
		assertEquals(0, game.getCount(Location.R1));
		assertEquals(1, game.getCount(Location.R3));
		assertEquals(0, game.getNumberOfMovesLeft());

		assertEquals(Color.NONE, game.getColor(Location.R1));
		assertEquals(Color.BLACK, game.getColor(Location.R2));
		assertEquals(Color.BLACK, game.getColor(Location.R3));
	}
	
	@Test
	public void shouldBeInvalidToMoveFromR12toB12() {
		game.newGame();
		game.nextTurn();
		assertFalse(game.move(Location.R12, Location.B12));
		assertEquals(5, game.getCount(Location.R12));
		assertEquals(5, game.getCount(Location.B12));
		assertEquals(2, game.getNumberOfMovesLeft());
		assertEquals(Color.BLACK, game.getColor(Location.R12));
		assertEquals(Color.RED, game.getColor(Location.B12));
	}
	
	@Test
	public void shouldBeInvalidToMoveAnotherPlayersPiece() {
		game.newGame();
		game.nextTurn();
		assertFalse(game.move(Location.B12, Location.R12));
		assertEquals(5, game.getCount(Location.R12));
		assertEquals(5, game.getCount(Location.B12));
		assertEquals(2, game.getNumberOfMovesLeft());
		assertEquals(Color.BLACK, game.getColor(Location.R12));
		assertEquals(Color.RED, game.getColor(Location.B12));
	}
	
	@Test
	public void shouldBeInvalidToMoveFromAnEmptyLocation() {
		game.newGame();
		game.nextTurn();
		assertFalse(game.move(Location.B2, Location.B3));
		assertEquals(0, game.getCount(Location.B2));
		assertEquals(0, game.getCount(Location.B3));
		assertEquals(2, game.getNumberOfMovesLeft());
		assertEquals(Color.NONE, game.getColor(Location.B2));
		assertEquals(Color.NONE, game.getColor(Location.B3));
	}
	
	@Test
	public void shouldBeInvalidToMakeMoreThanTwoMovesPerTurn() {
		game.newGame();
		game.nextTurn();
		
		// R1 to R3 - move 1
		assertTrue(game.move(Location.R1, Location.R3));
		assertEquals(1, game.getCount(Location.R1));
		assertEquals(1, game.getCount(Location.R3));
		assertEquals(1, game.getNumberOfMovesLeft());
		
		// R1 to R2 - move 2
		assertTrue(game.move(Location.R1, Location.R2));
		assertEquals(0, game.getCount(Location.R1));
		assertEquals(1, game.getCount(Location.R2));
		assertEquals(0, game.getNumberOfMovesLeft());
		
		// R2 to R3 - move 3
		assertFalse(game.move(Location.R2, Location.R3));

		assertEquals(Color.NONE, game.getColor(Location.R1));
		assertEquals(Color.BLACK, game.getColor(Location.R2));
		assertEquals(Color.BLACK, game.getColor(Location.R3));
	}
	
	@Test
	public void shouldHaveRedPlayerInTurnAfterBlackTurn() {
		game.newGame();
		game.nextTurn(); // will throw [1,2] and thus black starts
		assertEquals( Color.BLACK, game.getPlayerInTurn() );
		assertEquals(2, game.diceValuesLeft()[0]);
		assertEquals(1, game.diceValuesLeft()[1]);
		
		game.nextTurn(); // will throw [3,4]
		assertEquals( Color.RED, game.getPlayerInTurn() );
		assertEquals(4, game.diceValuesLeft()[0]);
		assertEquals(3, game.diceValuesLeft()[1]);
	}
	
	@Test
	public void shouldBeNoWinnerAfterFirstRoll() {
		game.newGame();
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
	}
	
	@Test
	public void shouldBeWinnerAfterSixTurns() {
		game.newGame();
		
		// Turn 1
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
		assertEquals(Color.BLACK, game.getPlayerInTurn());
		
		// Turn 2
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
		assertEquals(Color.RED, game.getPlayerInTurn());
		
		// Turn 3
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
		assertEquals(Color.BLACK, game.getPlayerInTurn());
		
		// Turn 4
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
		assertEquals(Color.RED, game.getPlayerInTurn());
		
		// Turn 5
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
		assertEquals(Color.BLACK, game.getPlayerInTurn());
		
		// Turn 6
		game.nextTurn();
		assertEquals(Color.RED, game.winner());
		assertEquals(Color.RED, game.getPlayerInTurn());
	}
}
