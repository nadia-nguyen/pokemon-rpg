import java.io.Serializable;
public class Pet implements Serializable {
	private String name;
	private int hp;
	private int damage;
	
	/**
	 * construct a pet object with a name, hp, and damage value
	 * @param s name of pet
	 * @param h hp of pet
	 * @param d damage that can be done by pet
	 */
	public Pet (String s, int h, int d) {
		name = s;
		hp = h;
		damage = d;
	}
	
	/**
	 * access name of pet from other classes
	 * @return name name of pet
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * access HP from other class
	 * @return hp total health of pet
	 */
	public int getHP() {
		return this.hp;
	}
	
	/**
	 * method to attack another pet
	 * @param target pet to be attacked
	 */
	public void attack (Pet target) {
		target.hp = target.hp- this.damage;
		System.out.println(this.getName() +" has attacked "+target.getName() );
	}
	/*
	public int addHP(int i) {
		hp = hp + i;
		return hp;
	}
	*/
	
	public String toString() {
		return "Name: "+name+"\nHP: "+hp+"\nDamage: "+damage;
	}

}
