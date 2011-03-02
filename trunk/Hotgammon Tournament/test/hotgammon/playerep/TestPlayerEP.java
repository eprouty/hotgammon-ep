package hotgammon.playerep;

import static org.junit.Assert.*;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.Game;
import hotgammon.playerep.MyBoard.GameImpl;
import hotgammon.playerep.MyBoard.MockDice;
import hotgammon.playerep.MyBoard.TestingFactory;
import hotgammon.tournament.TournamentMove;

import org.junit.Before;
import org.junit.Test;


public class TestPlayerEP {
	private TournamentPlayerImpl tp;
	  
	@Before public void setup() {
		tp = new TournamentPlayerImpl(null, Color.BLACK);
	}
	
	@Test public void testDiceAreSetWhenRecievedFromDirector(){
		tp.move(1, 2, null);
		Game g = tp.accessMyBoard();
		assertArrayEquals(new int[] {1,2}, g.diceThrown());
	}
	
	@Test public void movesAreRepresentedOnMyGameBoardWhenMade(){
		tp.move(1, 2, null);
		Game g = tp.accessMyBoard();
		assertEquals(Color.BLACK, g.getColor(Location.B3));
	}
	
	@Test public void doesNotAttemptToBearOffWithCheckersNotInInnerTable(){
		tp.move(2, 4, null);
		Game g = tp.accessMyBoard();
		assertEquals(0, g.getCount(Location.B_BEAR_OFF));
	}
	
	@Test public void opponentsMovesAreSuccesfullyCompleted(){
		tp.move(1, 4, new TournamentMove[] {new TournamentMove(Location.B1, Location.B5), new TournamentMove(Location.B1, Location.B2)});
		Game g = tp.accessMyBoard();
		assertEquals(0, g.getCount(Location.B5));
		assertEquals(1, g.getCount(Location.B2));
	}
	
	@Test public void movesMatchDiceValues(){
		tp = new TournamentPlayerImpl(null, Color.RED);
		tp.move(1, 6, null);
		Game g = tp.accessMyBoard();
		assertEquals(1, g.getCount(Location.B7));
	}
	
	@Test public void playerMovesFromBarBeforeMakingOtherMoves(){
		tp = new TournamentPlayerImpl(null, Color.RED);
		tp.move(2, 1, null);
		tp.move(4, 2, new TournamentMove[] {new TournamentMove(Location.B6, Location.B2), new TournamentMove(Location.B8, Location.B3)});
		Game g = tp.accessMyBoard();
		assertEquals(0, g.getCount(Location.R_BAR));
	}
	
	@Test public void blackPlayerMovesFromBarBeforeMakingOtherMoves(){
		tp.move(5, 3, new TournamentMove[] {new TournamentMove(Location.B1, Location.B5), new TournamentMove(Location.B5, Location.B10)});
		tp.move(4, 2, new TournamentMove[] {new TournamentMove(Location.R_BAR, Location.B1), new TournamentMove(Location.B1, Location.B7)});
		Game g = tp.accessMyBoard();
		assertEquals(0, g.getCount(Location.B_BAR));
	}
	
	@Test public void playerDoesNotMakeNullMoves(){
		tp = new TournamentPlayerImpl(null, Color.RED);
		tp.move(6, 3, null);
		Game g = tp.accessMyBoard();
		assertEquals(1, g.getCount(Location.B4));
		assertEquals(1, g.getCount(Location.B7));
	}
	
	@Test public void doesNotMakeInvalidMoves(){
		tp = new TournamentPlayerImpl(null, Color.RED);
		tp.move(3, 1, new TournamentMove[] {new TournamentMove(Location.B6, Location.B4), new TournamentMove(Location.B8, Location.B3)});
		Game g = tp.accessMyBoard();
		assertEquals(1, g.getCount(Location.B4));
		assertEquals(Color.RED, g.getColor(Location.B4));
		assertEquals(1, g.getCount(Location.B3));
		assertEquals(1, g.getCount(Location.B2));
		assertEquals(1, g.getCount(Location.B_BAR));
	}
	
	@Test public void brokenScenario(){
		tp = new TournamentPlayerImpl(null, Color.BLACK);
		tp.move(1, 5, null);
		tp.move(5, 6, new TournamentMove[] {new TournamentMove(Location.B1, Location.B7), new TournamentMove(Location.B7, Location.B12)});
		tp.move(2, 2, new TournamentMove[] {new TournamentMove(Location.R_BAR, Location.B5), new TournamentMove(Location.B5, Location.B11)});
	}
	
	@Test public void ensureThatBlackMakesValidMovesAfterRedStarts(){
		tp = new TournamentPlayerImpl(null, Color.BLACK);
		tp.move(6, 3, new TournamentMove[] {new TournamentMove(Location.B1, Location.B3), new TournamentMove(Location.B12, Location.R8)});
	}
	
	@Test public void redBearOff(){
		MockDice md = new MockDice();
		Game g = new GameImpl(new TestingFactory(md, Color.RED));
		g.newGame();
		md.setDice(2, 1);
		g.nextTurn();
		assertTrue(g.move(Location.R1, Location.R_BEAR_OFF));
		assertTrue(g.move(Location.R1, Location.R_BEAR_OFF));
	}
}
