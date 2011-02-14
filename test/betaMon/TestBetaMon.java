package betaMon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import baseMon.Game;
import baseMon.GameImpl;
import baseMon.Location;



public class TestBetaMon {
	private Game game;
	
	@Before public void setup() {
	    game = new GameImpl(new BetaFactory());
	    game.newGame();
	}
	
	@Test public void testBlackCheckerMayMoveInDirectionOfOwnInnerTable(){
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
	}
	@Test public void testRedCheckerMayMoveInDirectionOfOwnInnerTable(){
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.R6, Location.R3));
	}
	
	@Test public void testBlackCheckerMayNotMoveAwayFromOwnInnerTable(){
		game.nextTurn();
		assertFalse(game.move(Location.R12, Location.R11));
	}
	@Test public void testRedCheckerMayNotMoveAwayFromOwnInnerTable(){
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R9));
	}
	
	@Test public void testCanOnlyUseAvailableDice(){
		game.nextTurn();
		//check that the first turn can only move a 1 and a 2
		assertTrue(game.move(Location.R1, Location.R2));
		assertFalse(game.move(Location.R1, Location.R2));
		assertFalse(game.move(Location.R2, Location.R5));
		assertTrue(game.move(Location.R2, Location.R4));
	}
	
	@Test public void testCannotMoveZeroDistance(){
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R1));
	}
	
	@Test public void testBlackCannotMoveToALocationWith2OrMoreRedCheckers(){
		game.nextTurn();
		game.nextTurn();
		game.nextTurn(); //gets to black with a dice roll of 5 and 6
		assertFalse(game.move(Location.R1, Location.R6));
	}
	@Test public void testRedCannotMoveToALocationWith2OrMoreBlackCheckers(){
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn(); //get to red with a dice roll of 1 and 2
		assertFalse(game.move(Location.B12, Location.R12));
	}
	
	@Test public void testSendPieceToTheBar(){
		game.nextTurn();
		game.move(Location.R1, Location.R3);
		game.nextTurn();
		assertTrue(game.move(Location.R6, Location.R3));
		assertEquals(1, game.getCount(Location.B_BAR));
	}
	@Test public void testSendRedPieceToTheBar(){
		game.nextTurn();
		game.nextTurn();
		game.move(Location.B1, Location.B4);
		game.nextTurn();
		assertTrue(game.move(Location.B6, Location.B1));
		assertEquals(1, game.getCount(Location.R_BAR));
	}
	
	@Test public void testCannotMoveIfPiecesAreOnTheBar(){
		testSendPieceToTheBar();
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R7));
	}
	@Test public void testRedCannotMoveIfPiecesAreOnTheBar(){
		testSendRedPieceToTheBar();
		game.nextTurn();
		assertFalse(game.move(Location.B4, Location.B5));
	}
	
	@Test public void testMovingOutOfBar(){
		testSendPieceToTheBar();
		game.nextTurn(); //roll of 5 and 6 for black
		assertTrue(game.move(Location.B_BAR, Location.R5));
	}
	@Test public void testRedMovingOutOfBar(){
		testSendRedPieceToTheBar();
		game.nextTurn();
		assertTrue(game.move(Location.R_BAR, Location.B2));
	}
	
	@Test public void testBearOff(){
		game.nextTurn();
		game.move(Location.B6, Location.B5);
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.B5, Location.B_BEAR_OFF));
	}
}
