package bubble.test.ex18;

public interface Moveable {
	
	void left();
	void right();
	void up();
	default void down() {};
	default void attack() {};

}
