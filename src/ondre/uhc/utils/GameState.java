package ondre.uhc.utils;

public enum GameState {
	
	LOBBY,
	PREGAME,
	INGAME,
	DEATHMATCH,
	RESTARTING;

	public static GameState state;
	
	public GameState getGameState() {
		return state;
	}	
}