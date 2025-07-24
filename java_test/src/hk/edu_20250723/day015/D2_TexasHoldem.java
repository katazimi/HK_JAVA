package hk.edu_20250723.day015;

import java.util.ArrayList;
import java.util.List;

public class D2_TexasHoldem {
	
	public List<D2_Player> players;
	public D2_Player table;
		
	public D2_TexasHoldem() {
		players = new ArrayList<D2_Player>();
		table = new D2_Player();
		for (int i=0; i<2; i++)
			players.add(new D2_Player());
	}
	
	public void dealingCards() {
		
	}
	
	public void flop() {
		
	}
	
	public void turn() {
		
	}
	
	public void river() {
		
	}
	
	public void match() {
		
	}
}
