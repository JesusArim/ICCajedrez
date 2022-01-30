import java.util.ArrayList;
/**
 * Clase que define una pieza de ajedrez
 * @author Jesús ALberto Rivera Mora
 * @version 1.0
 */
public abstract class Pieza {
        public static final boolean BLANCA = true, NEGRA = false;
	protected String pieza;
	protected boolean color;
	protected int val;

	public boolean getColor() {
		return color;
	}

	public Pieza(boolean color) {
		this.color = color;
		val = 0;
	}

	public int getVal() {
		return val;
	}

	public abstract Pieza copiar();

	public abstract ArrayList<Mover> getMovimiento(Tablero b, int x, int y);

	/**
	 * @param b Tablero
	 * @param x x 
	 * @param y y 
	 */
	static public boolean valido(int x, int y) {
		if(x < 0 || x > 5 || y < 0 || y > 5)
			return false;
		else
			return true;
	}
}


