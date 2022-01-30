import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 */
public class BusquedaMinima{

	boolean color;
	int maxElemento;
	Random rand;
	/**
	 * 
	 */
	public BusquedaMinima(boolean color, int maxElemento) {
		this.color = color;
		this.maxElemento = maxElemento;
		rand = new Random();
	}

	private float maxValor(Tablero b, ArrayList<Mover> state, float alpha, float beta, int elemento) {
		if(elemento > maxElemento)
			return eval1(b, state, color);

		ArrayList<Mover> movimiento = b.getMovimientoAfter(color, state);
		if(movimiento.size() == 0) 
			return Float.NEGATIVE_INFINITY;

		for(int i = 0; i < movimiento.size(); i++) {
			state.add(movimiento.get(i));
			float tmp = minimValor(b, state, alpha, beta, elemento + 1);
			state.remove(state.lastIndexOf(movimiento.get(i)));
			if(tmp > alpha) {
				alpha = tmp;
			}

			if(beta <= alpha)
				break;

		}

		return alpha;
	}

	private float minimValor(Tablero b, ArrayList<Mover> state, float alpha, float beta, int elemento) {
		if(elemento > maxElemento)
			return eval1(b, state, !color);

		ArrayList<Mover> movimiento = b.getMovimientoAfter(!color, state);
		if(movimiento.size() == 0) 
			return Float.POSITIVE_INFINITY;

		for(int i = 0; i < movimiento.size(); i++) {
			state.add(movimiento.get(i));
			float tmp = maxValor(b, state, alpha, beta, elemento + 1);
			state.remove(state.lastIndexOf(movimiento.get(i)));
			if(tmp < beta) {
				beta = tmp;
			}

			if(beta <= alpha)
				break;
		}

		return beta;
	}

	public Mover decision(final Tablero b) {

		final ArrayList<Mover> movimiento = b.getMovimiento(color);
		if(movimiento.size() == 0)
			return null;

		Vector<Future<Float>> costos = new Vector<Future<Float>>(movimiento.size());
		costos.setSize(movimiento.size());

 		ExecutorService exec = Executors.newFixedThreadPool(movimiento.size());
 		try {
 		    for (int i = 0; i < movimiento.size(); i++) {
 		    	final Mover mover = movimiento.get(i);
 		        Future<Float> resultados = exec.submit(new Callable<Float>() {

 		            
 		            public Float call() {
 		            	ArrayList<Mover> estado = new ArrayList<Mover>();
 		            	estado.add(mover);

 		            	float tmp = minimValor(b, estado, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1);
 		            	return tmp;
 		            }
 		        });
 		        costos.set(i, resultados);
 		    }
 		} finally {
 		    exec.shutdown();
 		}

 		int maxi = -1;
		float max = Float.NEGATIVE_INFINITY;
 		for(int i = 0; i < movimiento.size(); i++) {
 			float costo;
			try {
				costo = costos.get(i).get();
			} catch (Exception e) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
				}
				continue;
			}
 			if(costo >= max) {
 				if(Math.abs(costo-max) < 0.1)
 					if(rand.nextBoolean())
 						continue;

 				max = costo;
 				maxi = i;
 			}
 		}

 		return movimiento.get(maxi);
	}

	public Mover UnicThreadDecision(Tablero b) {
		ArrayList<Mover> movimiento = b.getMovimiento(color);
		ArrayList<Mover> estado = new ArrayList<Mover>();
		float[] costos = new float[movimiento.size()];
 		for(int i = 0; i < movimiento.size(); i++) {
			estado.add(movimiento.get(i));
			float tmp = minimValor(b, estado, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1);
			costos[i] = tmp;
			estado.remove(estado.lastIndexOf(movimiento.get(i)));
		}
 		int maxi = -1;
		float max = Float.NEGATIVE_INFINITY;
 		for(int i = 0; i < movimiento.size(); i++) {
 			if(costos[i] >= max) {
 				if(Math.abs(costos[i]-max) < 0.1)
 					if(rand.nextBoolean())
 						continue;

 				max = costos[i];
 				maxi = i;
 			}
 		}

 		if(maxi == -1)
 			return null;
 		else
 			return movimiento.get(maxi);
	}

	private float eval2(Tablero b, ArrayList<Mover> movimiento, boolean actualColor) {
		Casilla[][] casillas = b.getCasillasAfter(movimiento);

		boolean negraRey = false, blancaRey = false;
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++) {
				if(casillas[i][j].estaOcupada()) {
					if(casillas[i][j].getPieza().toString().equals("R")) {
						blancaRey = true;
					}
					if(casillas[i][j].getPieza().toString().equals("r")) {
						negraRey = true;
					}
				}
			}

		if(color == Pieza.BLANCA) {
			if(blancaRey == false)
				return Float.NEGATIVE_INFINITY;
			if(negraRey == false)
				return Float.POSITIVE_INFINITY;
		}
		else {
			if(blancaRey == false)
				return Float.POSITIVE_INFINITY;
			if(negraRey == false)
				return Float.NEGATIVE_INFINITY;
		}




		int blancaPuntaje = 0;
		int negraPuntaje = 0;

		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++) {
				if(casillas[i][j].estaOcupada())
					if(casillas[i][j].getPieza().getColor() == Pieza.BLANCA)
						blancaPuntaje += casillas[i][j].getPieza().getVal();
					else
						negraPuntaje += casillas[i][j].getPieza().getVal();
			}


		if(color == Pieza.BLANCA)
			return blancaPuntaje - negraPuntaje;
		else
			return negraPuntaje - blancaPuntaje;
	}


	private float eval1(Tablero b, ArrayList<Mover> movimiento, boolean actualColor) {
		Casilla[][] casillas = b.getCasillasAfter(movimiento);

		if(b.getMovimiento(actualColor).size() == 0) {
			if(b.verificar(actualColor, movimiento))
				return (actualColor == this.color) ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
			else
				return Float.NEGATIVE_INFINITY;
		}

		int blancaPuntaje = 0;
		int negraPuntaje = 0;

		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++) {
				if(casillas[i][j].estaOcupada())
					if(casillas[i][j].getPieza().getColor() == Pieza.BLANCA)
						blancaPuntaje += casillas[i][j].getPieza().getVal();
					else
						negraPuntaje += casillas[i][j].getPieza().getVal();
			}


		if(color == Pieza.BLANCA)
		    return blancaPuntaje - negraPuntaje;
		else
		     return negraPuntaje - blancaPuntaje;
	}

}
