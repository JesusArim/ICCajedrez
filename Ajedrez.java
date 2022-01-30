/**
 * Programa para juagar ajedrez
 * @author Jesús Alberto Rivera Mora
 * @version 1.0
 */
public class Ajedrez {

	public static void main(String[] args) {
		float jugador1Puntaje = 0;
		int tab = 0;
		int iter = 5; 
		for(int i = 0; i < iter; i++){
			Tablero tablero = new Tablero();
			System.out.println(tablero.toString());
			Jugador jugador1 = new OptimizarJugador(Pieza.BLANCA,2);
	
			Jugador jugador2 = new OptimizarJugador(Pieza.NEGRA,1);

                int ganador = jugar(jugador1, jugador2, tablero);
                
                     if(ganador == 1)
		          jugador1Puntaje++;
		    if(ganador == 0) {
		          jugador1Puntaje += 0.2f;
			  tab++;
			}
		}

		System.out.println(jugador1Puntaje);
	}

	public static int jugar(Jugador jugador1, Jugador jugador2, Tablero b) {
	    Mover mover;
	    int resultado;
		int turno = 0;
		while(true) {
		   if(turno++ > 10) 
		       return 0;

		mover = jugador1.getSegMover(b);
		    if(mover == null && b.verificar(jugador1.getColor())) 
		       return -1;
		    if(mover == null) 
			return 0;

		resultado = b.ejecMover(mover);
		System.out.println(b);
			if(resultado == -1) 
			  return (jugador2.getColor() == Pieza.NEGRA) ? -1 : 1; 
			if(resultado == 1) 
			  return (jugador1.getColor() == Pieza.BLANCA) ? 1 : -1;    


			mover = jugador2.getSegMover(b);
			if(mover == null && b.verificar(jugador2.getColor()))
				return 1;
			if(mover == null) 
				return 0;

			resultado = b.ejecMover(mover);
			System.out.println(b);
			
       }
   }       
}	
	
	

