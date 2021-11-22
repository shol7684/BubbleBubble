package bubble.test.ex15;

public interface Moveable {
	
	void left();
	void right();
	void up();
	default void down() {};
	default void attack() {};

}
