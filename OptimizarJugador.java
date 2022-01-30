
/**
 *
 */
public class OptimizarJugador extends Jugador {
	BusquedaMinima minimax;

	/**
	 * @param color
	 */
	public OptimizarJugador(boolean color, int maxElemento) {
		super(color);
		minimax = new BusquedaMinima(color, maxElemento);
	}

	/**
	 *  
	 * @return
	 */
	public Mover getSegMover(Tablero b) {
		Mover mover = minimax.decision(b);
		return mover;
	}

}
