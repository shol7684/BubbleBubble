package bubble.test.ex12;

public interface Moveable {
	
	void left();
	void right();
	void up();
	default void down() {};

}
