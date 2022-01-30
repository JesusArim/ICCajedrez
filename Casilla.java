/**
 * Metodo donde se define las casillas
 * 
 */
public class Casilla {
	private boolean ocupada;
	private Pieza pieza;

	/**
	 * 
	 */
	public Casilla() {
		ocupada = false;
	}

	public Casilla(Casilla casilla) {
		this.ocupada = casilla.estaOcupada();
		this.pieza = casilla.estaOcupada() ? casilla.getPieza().copiar() : null;
	}

	public Casilla(Pieza pieza) {
		ocupada = true;
		this.pieza = pieza;
	}

	public String toString() {
		if(ocupada)
			return pieza.toString();
		else
			return ".";
	}

	public Pieza getPieza() {
		return pieza;
	}

	public boolean estaOcupada() {
		return ocupada;
	}

}
