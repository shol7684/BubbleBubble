package bubble.test.ex14;

public interface Moveable {
	
	void left();
	void right();
	void up();
	default void down() {};
	default void attack() {};

}
