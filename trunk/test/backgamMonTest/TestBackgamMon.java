package backgamMonTest;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import baseMon.Color;
import baseMon.Game;
import baseMon.GameImpl;
import baseMon.Location;


public class TestBackgamMon {
	private Game game;
	  
	@Before public void setup() {
		game = new GameImpl(new TestBackgamFactory());
		game.newGame();
	}
	
	@Test public void testTurnSelection(){
		game.nextTurn();
		Random rg = new Random(2);
		Color c;
		if (rg.nextInt(6) + 1 < rg.nextInt(6) + 1){
			c = Color.BLACK;
		} else {
			c = Color.RED;
		}
		assertEquals(c, game.getPlayerInTurn());
	}
	
	@Test public void testDoubleDiceRoll(){
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals(4, game.getNumberOfMovesLeft());
	}
	
	@Test public void testCorrectNumberedBearOff(){
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B_BEAR_OFF));
		assertEquals(1, game.getCount(Location.B_BEAR_OFF));
	}
	
	@Test public void testBearOffWithNoLargerNumbers(){
		game.nextTurn();
		assertTrue(game.move(Location.B4, Location.B_BEAR_OFF));
		assertEquals(1, game.getCount(Location.B_BEAR_OFF));
	}
	
	@Test public void testRedCorrectNumberedBearOff(){
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.R2, Location.R_BEAR_OFF));
		assertEquals(game.getCount(Location.R_BEAR_OFF), 1);
	}
	
	@Test public void testNoBearOffWithLargerDieWhenLargerNumberedPiecesAreOnBoard(){
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R_BEAR_OFF));
		assertTrue(game.move(Location.R2, Location.R_BEAR_OFF));
		assertFalse(game.move(Location.R1, Location.R_BEAR_OFF));
		assertTrue(game.move(Location.R4, Location.R1));
	}
	
	@Test public void testBlackWinning(){
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B_BEAR_OFF));
		assertTrue(game.move(Location.B4, Location.B_BEAR_OFF));
		assertEquals(Color.BLACK, game.winner());
	}
	
	@Test public void testNoWinner(){
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
	}
}
