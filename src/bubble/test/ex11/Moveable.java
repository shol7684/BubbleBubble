package bubble.test.ex11;

public interface Moveable {
	
	void left();
	void right();
	void up();
	default void down() {};

}
