package bubble.test.ex16;

public interface Moveable {
	
	void left();
	void right();
	void up();
	default void down() {};
	default void attack() {};

}
