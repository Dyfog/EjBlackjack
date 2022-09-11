import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class JugarTest {

    @Test
    void calcPtosTest() {
        ArrayList<String> baraja = Jugar.crearBajara();
        ArrayList<String> mano = Jugar.crearMano();

        int x = Jugar.puntoxIndice(Jugar.tomarIndice(baraja.get(0)));
        int y = Jugar.puntoxIndice(Jugar.tomarIndice(baraja.get(1)));
        int sumaPuntosEsperada = x + y;

        if (sumaPuntosEsperada == 22) {
            sumaPuntosEsperada = 12;
        }

        Jugar.repartir(mano, baraja, 2);
        assertEquals(sumaPuntosEsperada, Jugar.calcPtos(mano));
    }

    @Test
    void pedirCartas() {
        ArrayList<String> baraja = Jugar.crearBajara();
        ArrayList<String> mano = Jugar.crearMano();

        Jugar.pedirCartas(mano, baraja);

        assertEquals(1, mano.size());
    }

    @Test
    void compararPtos() {
        ArrayList<String> baraja = Jugar.crearBajara();
        ArrayList<String> mano = Jugar.crearMano();
        ArrayList<String> manoCPU = Jugar.crearMano();
        Jugar.repartir(mano, baraja, 2);
        Jugar.repartir(manoCPU, baraja, 2);
        String ganadorEsperado = "casa";
        String ganador = "";

        System.out.println("calcPtos(manoCpu) = " + Jugar.calcPtos(manoCPU));
        if (Jugar.calcPtos(mano) == Jugar.calcPtos(manoCPU)) {
            ganador = "casa";
            System.out.println("La casa gana");

        } else if (Jugar.calcPtos(mano) > 21 && Jugar.calcPtos(manoCPU) > 21) {
            if (Jugar.calcPtos(mano) > Jugar.calcPtos(manoCPU)) {
                ganador = "jugador";
                System.out.println("el jugador gana");
            } else {
                ganador = "casa";
                System.out.println("la casa gana");
            }
        } else if (Jugar.calcPtos(mano) > 21 && Jugar.calcPtos(manoCPU) <= 21) {
            ganador = "casa";
            System.out.println("la casa gana");
        } else if (Jugar.calcPtos(mano) <= 21 && Jugar.calcPtos(manoCPU) > 21) {
            ganador = "jugador";
            System.out.println("El jugador gana");
        } else if (Jugar.calcPtos(mano) <= 21 && Jugar.calcPtos(manoCPU) <= 21) {
            if (Jugar.calcPtos(mano) > Jugar.calcPtos(manoCPU)) {
                ganador = "jugador";
                System.out.println("el jugador gana");
            } else {
                ganador = "casa";
                System.out.println("la casa gana");
            }
        }
        assertEquals(ganador, ganadorEsperado);
    }

    @Test
    @DisplayName("caso en que la baraja este vacia")
    void verificarRepartirCartasSinBaraja() {
        Logger logger = Logger.getLogger("Blackjack.class");
        ArrayList<String> mano = Jugar.crearMano();
        assertThrows(NullPointerException.class,
                     ( ) -> Jugar.repartir(mano, null,2),"se ingreso una baraja nula");
        logger.info("se lanzo la excepcion NullPointerExeption dado que la baraja estaba nula");
    }
}


