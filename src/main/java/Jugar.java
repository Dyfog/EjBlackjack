import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*deje 2 funciones adicionales que no se estan usando que serian leerBaraja para probar en el proceso si se estaba
haciendo bien y me parecio un desperdicio borrarla si no molesta en nada, ademas esta revisarBlackjackCPU, ya que no
estaba seguro de lo que sucedia cuando la casa saca blackjack, pero la funcion esta hecha y seria cosa de ponerla entre
la linea 27-28 y si hay empate de blackjacks la casa gana, ademas hay validaciones para los numeros.
Estan todas las funciones requeridas(algunas no tienen el mismo nombre por comodidad mia, espero no sea problema), y
hay mas de las minimas ya que me era de comodidad usarlas, por nada mas.
 */
public class Jugar {
    public static void main(String[] args) {
        jugar();
    }

    public static void jugar() {
        menuIni();
        switch (validNum(eleccion())) {
            case 0:
                ArrayList<String> baraja = crearBajara();
                Collections.shuffle(baraja);
                ArrayList<String> manoJ1 = crearMano();
                ArrayList<String> manoCPU = crearMano();
                repartir(manoJ1, baraja, 2);
                repartir(manoCPU, baraja, 2);
                leerMano(manoJ1);
                revisarBlackjack(manoJ1);
                System.out.println("calcPtos(manoJ1) = " + calcPtos(manoJ1));
                pedirCartas(manoJ1, baraja);
                pedirCartasCpu(manoCPU,baraja);
                compararPtos(manoJ1, manoCPU);

            case 1:
                break;
        }
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

    public static void menuIni() {
        System.out.println("Desea jugar?");
        System.out.println("0.-Si");
        System.out.println("1.-No");
    }

    public static int eleccion() {
        Scanner in = new Scanner(System.in);
        int eleccion = in.nextInt();
        return eleccion;
    }

    public static void leerBaraja(ArrayList lista) {
        System.out.println("Baraja:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        System.out.println("------------------------");
    }

    public static ArrayList crearMano() {
        ArrayList<String> manoFunc = new ArrayList<>();
        return manoFunc;
    }

    public static void leerMano(ArrayList mano) {
        System.out.println("mano:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(mano.get(i));
        }
        System.out.println("------------------------");

    }

    public static void repartir(ArrayList manoJ1, ArrayList baraja, int cantCartas) {
        for (int i = 0; i < cantCartas; i++) {
            manoJ1.add(baraja.get(0));
            baraja.remove(0);
        }
    }

    public static char tomarIndice(String carta) {
        return carta.charAt(0);
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

    public static void pedirCartas(ArrayList mano, ArrayList baraja) {
        if (calcPtos(mano)<=20){
            System.out.println("quieres pedir otra carta?");
            System.out.println("0.-Si");
            System.out.println("1.-No");
            int eleccion = eleccion();
            while (eleccion == 0) {
                repartir(mano, baraja, 1);
                leerMano(mano);
                System.out.println("calcPtos(mano) = " + calcPtos(mano));
                System.out.println("quieres pedir otra carta?");
                System.out.println("0.-Si");
                System.out.println("1.-No");
                if (eleccion() == 1) {
                    eleccion++;
                }else {
                    System.out.println("no puedes pedir mas cartas");
                    break;
                }
            }
        }
    }

    public static void revisarBlackjack(ArrayList mano) {
        ArrayList<Character> cartas = new ArrayList<Character>();
        for (int i = 0; i < mano.size(); i++) {
            cartas.add(tomarIndice((String) mano.get(i)));
            if (cartas.contains('A') && cartas.contains('K')) {
                System.out.println("Esta mano tiene Blackjack por lo que es el ganador");
                System.exit(1);
            } else if (cartas.contains('A') && cartas.contains('Q')) {
                System.out.println("Esta mano tiene Blackjack por lo que es el ganador");
                System.exit(1);
            } else if (cartas.contains('A') && cartas.contains('J')) {
                System.out.println("Esta mano tiene Blackjack por lo que es el ganador");
                System.exit(1);
            }
        }
    }
    public static void revisarBlackjackCPU(ArrayList mano) {
        ArrayList<Character> cartas = new ArrayList<Character>();
        for (int i = 0; i < mano.size(); i++) {
            cartas.add(tomarIndice((String) mano.get(i)));
            if (cartas.contains('A') && cartas.contains('K')) {
                System.out.println("La casa tiene es blackjack");
                leerMano(mano);
            } else if (cartas.contains('A') && cartas.contains('Q')) {
                System.out.println("La casa tiene es blackjack");
                leerMano(mano);
            } else if (cartas.contains('A') && cartas.contains('J')) {
                System.out.println("La casa tiene es blackjack");
                leerMano(mano);
            }
        }
    }
    public static void compararPtos(ArrayList manoJ, ArrayList manoCpu){
        System.out.println("calcPtos(manoCpu) = " + calcPtos(manoCpu));
        if (calcPtos(manoJ)==calcPtos(manoCpu)){
            System.out.println("La casa gana");
        }else if (calcPtos(manoJ)>21 && calcPtos(manoCpu)<=21){
            System.out.println("la casa gana");
        } else if (calcPtos(manoJ)<=21 && calcPtos(manoCpu)>21) {
            System.out.println("El jugador gana");
        } else if (calcPtos(manoJ)<=21 && calcPtos(manoCpu)<=21) {
            if (calcPtos(manoJ)>calcPtos(manoCpu)){
                System.out.println("el jugador gana");
            }else {
                System.out.println("la casa gana");
            }
        }
    }
    public static int validNum(int num){
        Scanner in = new Scanner(System.in);
        while (num>1||num<0){
            System.out.println("numero invalido, ingrese nuevamente");
            num= in.nextInt();
        }
        return num;
    }
    public static void pedirCartasCpu(ArrayList manoCpu, ArrayList baraja){
        while (calcPtos(manoCpu)<17){
            repartir(manoCpu, baraja, 1);
        }
    }
}
