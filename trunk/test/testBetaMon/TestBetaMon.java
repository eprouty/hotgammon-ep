package testBetaMon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import baseMon.Game;
import baseMon.GameImpl;
import baseMon.Location;
import betaMon.BetaMoveStrategy;



public class TestBetaMon {
	private Game game;
	
	@Before public void setup() {
	    game = new GameImpl(new BetaMoveStrategy());
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
}
