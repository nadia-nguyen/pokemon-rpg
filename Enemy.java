public class Enemy extends Pet  {
	private int XPgiven;
	
	/**
	 * constructs enemy with a name, hp, damage and xp given
	 * @param s name 
	 * @param h total hp
	 * @param d damage that can be done by enemy
	 * @param xp how much xp is given to player if this enemy is defeated
	 */
	public Enemy (String s, int h, int d,int xp) {
		super (s,h,d);
		XPgiven = xp;
	}
	
	/**
	 * access total xp that can be given by enemy in other classes
	 * @return XPgiven how much xp earned when enemy is defeated
	 */
	public int getXPGive () {
		return XPgiven;
	}
	

}
