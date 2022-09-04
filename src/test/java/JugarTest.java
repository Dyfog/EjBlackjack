import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class JugarTest {

    @Test
    void calcPtos() {
        ArrayList<String> baraja = crearBajara();
        ArrayList<String> mano = crearMano();

        int x = puntoxIndice(tomarIndice(baraja.get(0)));
        int y = puntoxIndice(tomarIndice(baraja.get(1)));
        int sumaPuntosEsperada = x + y;

        if (sumaPuntosEsperada == 22) {
            sumaPuntosEsperada = 12;
        }

        repartir(mano, baraja, 2);
        assertEquals(sumaPuntosEsperada, calcPtos(mano));
    }

    @Test
    void pedirCartas() {
        ArrayList<String> baraja = crearBajara();
        ArrayList<String> mano = crearMano();

        pedirCartas(mano, baraja);

        assertEquals(1, mano.size());
    }

    @Test
    void compararPtos() {
        ArrayList<String> baraja = crearBajara();
        ArrayList<String> mano = crearMano();
        ArrayList<String> manoCPU = crearMano();
        repartir(mano, baraja, 2);
        repartir(manoCPU, baraja, 2);
        String ganadorEsperado = "casa";
        String ganador = "";

        System.out.println("calcPtos(manoCpu) = " + calcPtos(manoCPU));
        if (calcPtos(mano) == calcPtos(manoCPU)) {
            ganador = "casa";
            System.out.println("La casa gana");
        } else if (calcPtos(mano) > 21 && calcPtos(manoCPU) > 21) {
            if (calcPtos(mano) > calcPtos(manoCPU)) {
                ganador = "jugador";
                System.out.println("el jugador gana");
            } else {
                ganador = "casa";
                System.out.println("la casa gana");
            }
        } else if (calcPtos(mano) > 21 && calcPtos(manoCPU) <= 21) {
            ganador = "casa";
            System.out.println("la casa gana");
        } else if (calcPtos(mano) <= 21 && calcPtos(manoCPU) > 21) {
            ganador = "jugador";
            System.out.println("El jugador gana");
        } else if (calcPtos(mano) <= 21 && calcPtos(manoCPU) <= 21) {
            if (calcPtos(mano) > calcPtos(manoCPU)) {
                ganador = "jugador";
                System.out.println("el jugador gana");
            } else {
                ganador = "casa";
                System.out.println("la casa gana");
            }
        }
        assertEquals(ganador, ganadorEsperado);
    }





    public static void pedirCartas(ArrayList mano, ArrayList baraja) {
        if (calcPtos(mano) <= 20) {
            System.out.println("quieres pedir otra carta?");
            System.out.println("0.-Si");
            System.out.println("1.-No");
            int eleccion = 0;
            while (eleccion == 0) {
                repartir(mano, baraja, 1);
                leerMano(mano);
                System.out.println("calcPtos(mano) = " + calcPtos(mano));
                System.out.println("quieres pedir otra carta?");
                System.out.println("0.-Si");
                System.out.println("1.-No");
                eleccion = 1;
                if (calcPtos(mano) >= 21) {
                    System.out.println("no puedes pedir mas cartas");
                    eleccion++;
                }
            }
        } else {
            System.out.println("no puedes pedir mas cartas");
        }
    }
    public static int calcPtos(ArrayList mano) {
        int ptos = 0;
        int auxA = cantAs(mano);
        for (int i = 0; i < mano.size(); i++) {
            ptos = ptos + puntoxIndice(tomarIndice((String) mano.get(i)));
        }
        if (ptos > 21) {
            if (auxA > 0) {
                ptos -= 10;
                auxA--;
            }
        }
        return ptos;
    }
    public static void repartir(ArrayList manoJ1, ArrayList baraja, int cantCartas) {
        for (int i = 0; i < cantCartas; i++) {
            manoJ1.add(baraja.get(0));
            baraja.remove(0);
        }
    }
    public static void leerMano(ArrayList mano) {
        System.out.println("mano:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(mano.get(i));
        }
        System.out.println("------------------------");

    }
    public static ArrayList crearMano() {
        ArrayList<String> manoFunc = new ArrayList<>();
        return manoFunc;
    }
    public static ArrayList<String> crearBajara() {
        ArrayList<String> barajafunc = new ArrayList<>();
        String[] indice = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "D", "J", "Q", "K"};
        String[] pinta = new String[]{"Diamante", "Corazon", "Trebol", "Pica"};
        for (var i = 0; i < indice.length; i++) {
            for (var j = 0; j < pinta.length; j++) {
                barajafunc.add(indice[i] + ";" + pinta[j]);
            }
        }
        return barajafunc;
    }
    public static int puntoxIndice(char indice) {
        if (indice == 'K') {
            return 10;
        } else if (indice == ('Q')) {
            return 10;
        } else if (indice == 'J') {
            return 10;
        } else if (indice == 'D') {
            return 10;
        } else if (indice == '9') {
            return 9;
        } else if (indice == '8') {
            return 8;
        } else if (indice == '7') {
            return 7;
        } else if (indice == '6') {
            return 6;
        } else if (indice == '5') {
            return 5;
        } else if (indice == '4') {
            return 4;
        } else if (indice == '3') {
            return 3;
        } else if (indice == '2') {
            return 2;
        } else if (indice == 'A') {
            return 11;
        }
        return 0;
    }
    public static int cantAs(ArrayList mano) {
        int cantA = 0;
        for (int i = 0; i < mano.size(); i++) {
            if (tomarIndice((String) mano.get(i)) == 'A') {
                cantA++;
            }
        }
        return cantA;
    }
    public static char tomarIndice(String carta) {
        return carta.charAt(0);
    }
}
