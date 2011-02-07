package testAlphaMonRPRD;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alphaMon.AlphaMoveStrategy;
import alphaMon.AlphaTurnStrategy;
import baseMon.Color;
import baseMon.Game;
import baseMon.GameImpl;
import baseMon.Location;


/**
 * Tests for AlphaMon
 * @author Richard DiCroce
 * @author Ryan Price
 *
 */
public class TestGame {

	private Game game;
	
	@Before
	public void setUp() {
		game = new GameImpl(new AlphaMoveStrategy(), new AlphaTurnStrategy());
		game.newGame();
	}
	
	/**
	 * Tests that no player is currently in turn when the game starts, as specified in the requirements.
	 */
	@Test
	public void testNoPlayerInTurnAtGameStart() {
		assertEquals("No player should be in turn when the game starts", game.getPlayerInTurn(), Color.NONE);
	}
	
	/**
	 * Tests that black is in turn after the first call to nextTurn(), as specified in the requirements.
	 */
	@Test
	public void testBlackInTurnAfterFirstTurn() {
		game.nextTurn();
		
		assertEquals("Black should be in turn after the first call to nextTurn()", game.getPlayerInTurn(), Color.BLACK);
	}
	
	/**
	 * Tests that red is in turn after the second call to nextTurn(), as specified in the requirements.
	 */
	@Test
	public void testRedInTurnAfterSecondTurn() {
		game.nextTurn();
		game.nextTurn();
		
		assertEquals("Red should be in turn after the second call to nextTurn()", game.getPlayerInTurn(), Color.RED);
	}
	
	/**
	 * Tests that the player in turn has two moves left at the beginning of a turn.
	 */
	@Test
	public void testPlayerInTurnStartsWithTwoMoves() {
		game.nextTurn();
		
		assertEquals("The player should have two moves left at the beginning of a turn", game.getNumberOfMovesLeft(), 2);
	}
	
	/**
	 * Tests that moves get decremented properly: after two moves, the player should have zero moves left.
	 */
	@Test
	public void testPlayerInTurnGetsTwoMoves() {
		game.nextTurn();
		
		game.move(Location.R12, Location.R11);
		game.move(Location.R12, Location.R11);
		
		assertEquals("The player should have no moves left after making two moves", game.getNumberOfMovesLeft(), 0);
	}
	
	/**
	 * Tests that die rolls are sequential, as specified in the requirements.
	 */
	@Test
	public void testSequentialDieRolls() {
		game.nextTurn();
		assertArrayEquals("First turn die roll should be 1-2", game.diceThrown(), new int[]{1, 2});
		
		game.nextTurn();
		assertArrayEquals("Second turn die roll should be 3-4", game.diceThrown(), new int[]{3, 4});
		
		game.nextTurn();
		assertArrayEquals("Third turn die roll should be 5-6", game.diceThrown(), new int[]{5, 6});
		
		game.nextTurn();
		assertArrayEquals("Fourth turn die roll should be 1-2 again", game.diceThrown(), new int[]{1, 2});
	}
	
	/**
	 * Tests that diceValuesLeft() returns an array sorted in descending order, as specified in the requirements.
	 */
	@Test
	public void testDiceValuesLeftIsSorted() {
		game.nextTurn();
		int[] dice = game.diceValuesLeft();
		
		assertTrue("The first dice value in the array should be larger than the second",
				dice[0] > dice[1]);
	}
	
	/**
	 * Tests that dice values get used up as moves are made. That is, after a move, the length of the array
	 * returned by diceValuesLeft() should be one less than it was before the move. 
	
	@Test
	public void testDiceValuesLeftGetsDecrementedOnMove() {
		game.nextTurn();
		
		game.move(Location.R12, Location.R11);
		assertEquals("The number of dice values left should be 1", game.diceValuesLeft().length, 1);
		
		game.move(Location.R12, Location.R11);
		assertEquals("The number of dice values left should be 0", game.diceValuesLeft().length, 0);
	} */
	
	/**
	 * Tests that there is no winner in the first five turns. The requirements say that the game ends
	 * "after six rolls of the dice", which we took to mean six turns.
	 */
	@Test
	public void testNoWinnerBeforeSixTurns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		
		assertEquals("There should not be a winner until the 6th turn", game.winner(), Color.NONE);
	}
	
	/**
	 * Tests that red wins the game, as specified in the requirements.
	 */
	@Test
	public void testRedWins() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		
		assertEquals("Red should win the game", game.winner(), Color.RED);
	}
	
	/**
	 * Tests that the board is initialized with the appropriate number and color pieces in the
	 * correct locations.
	 */
	@Test
	public void testBoardSetupIdenticalToNormalBackgammon() {
		assertEquals("Count of R1 should be 2",game.getCount(Location.R1),2);
		assertEquals("Color of R1 should be black",game.getColor(Location.R1),Color.BLACK);
		assertEquals("Count of R6 should be 5",game.getCount(Location.R6),5);
		assertEquals("Color of R6 should be red",game.getColor(Location.R6),Color.RED);
		assertEquals("Count of R8 should be 3",game.getCount(Location.R8),3);
		assertEquals("Color of R8 should be red",game.getColor(Location.R8),Color.RED);
		assertEquals("Count of R12 should be 5",game.getCount(Location.R12),5);
		assertEquals("Color of R12 should be black",game.getColor(Location.R12),Color.BLACK);
		assertEquals("Count of B12 should be 5",game.getCount(Location.B12),5);
		assertEquals("Color of B12 should be red",game.getColor(Location.B12),Color.RED);
		assertEquals("Count of B8 should be 3",game.getCount(Location.B8),3);
		assertEquals("Color of B8 should be black",game.getColor(Location.B8),Color.BLACK);
		assertEquals("Count of B6 should be 5",game.getCount(Location.B6),5);
		assertEquals("Color of B6 should be black",game.getColor(Location.B6),Color.BLACK);
		assertEquals("Count of B1 should be 2",game.getCount(Location.B1),2);
		assertEquals("Color of B1 should be red",game.getColor(Location.B1),Color.RED);
		
		// Test some empty spots
		assertEquals("Count of R4 should be 0",game.getCount(Location.R4),0);
		assertEquals("Color of R4 should be none",game.getColor(Location.R4),Color.NONE);
	}
	
	/**
	 * Tests that a player is not allowed to move the opponent's checkers.
	 */
	@Test
	public void testCannotMoveOpponentsChecker() {
		game.nextTurn();
		assertFalse("Black should not be able to move a red checker",
				game.move(Location.B12, Location.B11));
		
		game.nextTurn();
		assertFalse("Red should not be able to move a black checker",
				game.move(Location.R12, Location.R11));
	}
	
	/**
	 * Tests that a player is not allowed to move one of their checkers to a location
	 * occupied by at least one opponent checker.
	 */
	@Test
	public void testCannotMoveToOpponentLocation() {
		game.nextTurn();
		assertFalse("Black should not be able to move to a location occupied by red checkers",
				game.move(Location.R12, Location.B12));
	}
	
	/**
	 * Tests that a location becomes properly empty after the last checker occupying it is moved.
	 */
	@Test
	public void testMoveCheckerThatEmptiesSourceLocation() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);
		
		assertEquals("R1 should be Color.NONE after moving 2 checkers from it",
				game.getColor(Location.R1), Color.NONE);
		assertEquals("R1 should be 0 after moving 2 checkers from it",
				game.getCount(Location.R1), 0);
		
		assertEquals("R2 should be Color.BLACK after moving 2 checkers to it",
				game.getColor(Location.R2), Color.BLACK);
		assertEquals("R2 should be 2 after moving 2 checkers to it",
				game.getCount(Location.R2), 2);
	}
	
	/**
	 * Tests that the number of checkers in a location is decremented properly after a move.
	 */
	@Test
	public void testMoveCheckerNotEmptyingSourceLocation() {
		game.nextTurn();
		game.move(Location.R12, Location.R11);
		
		assertEquals("R12 should still be Color.BLACK after moving 1 checkers from it",
				game.getColor(Location.R12), Color.BLACK);
		assertEquals("R12 should be 4 after moving 1 checkers from it",
				game.getCount(Location.R12), 4);
		
		assertEquals("R11 should be Color.BLACK after moving 1 checkers to it",
				game.getColor(Location.R11), Color.BLACK);
		assertEquals("R11 should be 1 after moving 1 checkers to it",
				game.getCount(Location.R11), 1);
	}

	/**
	 * Tests that a player is not allowed to move a checker if they have no moves left.
	 */
	@Test
	public void testCannotMoveCheckerIfMovesExhausted() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);
		
		assertFalse("Player should not be able to move after exhausting moves",
				game.move(Location.R12, Location.R2));
	}
	
	/**
	 * Tests that newGame() reinitializes the game state properly.
	 */
	@Test
	public void testNewGameResetsGameState() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.nextTurn();
		game.move(Location.B12, Location.B11);
		game.newGame();
		
		// Run some previous tests to check that the game and board were
		// re-initialized correctly
		testNoPlayerInTurnAtGameStart();
		testBoardSetupIdenticalToNormalBackgammon();
		testSequentialDieRolls();
	}
	
	/**
	 * Tests that no moves can be made before the first turn starts.
	 * Thanks to John Vilk and his tests for the catch.
	 */
	@Test
	public void testCannotMoveBeforeFirstTurn() {
		assertFalse("No move should be valid before the first turn", game.move(Location.R3, Location.R4));
	}
}
