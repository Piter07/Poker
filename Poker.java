public class Poker {
    Carta[] cartas =  new Carta[5];

    public Poker(String[] cartas) {
        for (int i = 0; i <  cartas.length ; i++) {
            this.cartas[i] = new Carta(cartas[i]);
        }
    }

    private void setMaso(String[] cartas) {
        for (int i = 0; i <  cartas.length ; i++) {
            this.cartas[i] = new Carta(cartas[i]);
        }
    }


    private String identificarJugada() {

        boolean escalera = this.escalera();
        boolean color = this.color();
        if(escalera && color){
            return"Escalera Color";
        }else if(escalera){
            return"Escalera";
        }else if(color){
            return"Color";
        }

        return this.otrosCasos();
    }

    private String otrosCasos() {
        int par = 0, iguales = 1;
        boolean trio = false;
        for(int i = 0; i < cartas.length - 1 ; i++){
            if(cartas[i].valor.equals(cartas[i+1].valor))
                iguales++;
            else{
                if(iguales == 4)
                    return "Poker";
                else if(iguales == 3) {
                    trio = true;
                    iguales = 1;
                }else if(iguales == 2){
                    par++;
                    iguales = 1;
                }
            }
        }
        if(iguales == 2)
            par++;

        if(trio){
            if(par == 1)
                return "Full";
            else
                return "Trio";

        }else if(par == 2)
            return "Par Doble";
        else if(par == 1)
            return "Par";


        return "Carta Alta";
    }

    private boolean color() {
        boolean color = false;
        int contColor = 0;
        for(int i = 0 ; i < this.cartas.length - 1 ; i++){
            if(cartas[i].palo.equals(cartas[i + 1].palo))
                contColor++;
            else
                break;
        }
        if(contColor == 4)
            color = true;

        return color;
    }

    public boolean escalera(){
        boolean escalera = false;
        Carta aux;
        for(int i = 0 ; i < cartas.length - 1 ; i++){
            for(int j = 0 ; j < cartas.length - 1 ; j++)
            if(cartas[j].valorNum >= cartas[j+1].valorNum ){
                aux = this.cartas[j + 1];
                this.cartas[j+1] = this.cartas[j];
                this.cartas[j] = aux;
            }
        }
        int esc = 0 ,siguiente;
        for(int i = 0 ; i < cartas.length - 1 ; i++){

            siguiente = cartas[i].valorNum + 1;
            if(cartas[i+1].valorNum != siguiente && ( !cartas[i].valor.equals("A") && !cartas[i+1].valor.equals("T"))){
                escalera = false;
                break;
            }else if(cartas[i+1].valorNum == siguiente){
                esc++;
            }

        }

        if(cartas[0].valorNum == 1 && cartas[4].valorNum == 13 ){
            esc++;
        }

        if(esc == 4){
            escalera = true;
        }

        return escalera;
    }

    public void imprimirCartas(){
        for(int i = 0; i < 4 ; i++)
            System.out.print(this.cartas[i].valorPalo() + " - ");
        System.out.print(this.cartas[4].valorPalo());
        System.out.println();
    }
    private String obtenerPalo() {
        int num = (int) (Math.random()*(4)) + 1;
        if(num == 1)
            return "S";
        if(num == 2)
            return "C";
        if(num == 3)
            return "H";
        if(num == 4)
            return "D";

        return "";
    }

    private String obtenerValor() {
       int num = (int) (Math.random()*(13)+1);
       String numero = "";
        if(num >= 2 && num <= 9) {
            numero = String.valueOf(num);
        }else if(num == 1) {
            numero = "A";
        }else if(num == 10) {
            numero = "T";
        }else if(num == 11) {
            numero = "J";
        }else if(num == 12) {
            numero = "K";
        }else if(num == 13) {
            numero = "Q";
        }
        return numero;
    }
    private void generarMasos(int cantidad) {
        String[] maso =  new String[5];
        String palo,valor;
        Carta carta;
        for(int i = 0 ; i < cantidad ; i++ ){
            for(int j = 0; j < 5 ; j++){
                do {
                    palo = this.obtenerPalo();
                    valor = this.obtenerValor();
                    palo = valor + palo;
                    for(int k = 0 ; k < j ; k++){
                        if(maso[k].equals(palo)){
                            valor = "No";
                            break;
                        }
                    }
                }while(valor.equals("No"));
                maso[j] = palo;
            }
            this.setMaso(maso);
            System.out.println(this.identificarJugada());
            this.imprimirCartas();
        }

    }



    public static void main(String[] args) {
        //Escalera Color
        //"2S", "3S", "4S", "5S", "6S"
        //"3S", "5S", "AS", "2S", "4S"
        //"AS", "JS", "KS", "QS", "TS"
        //Poker
        //"AS", "AC", "AH", "AD", "TS"
        //Full
        //"AS", "AC", "AH", "TC", "TS"
        //Color
        //"3S", "5S", "AS", "2S", "7S"
        //Escalera
        //"3S", "5S", "AS", "2S", "4C"
        //Trio
        //"AS", "AC", "AH", "1C","TS"
        //Par Doble
        //"AS", "AC", "TH", "1C","TS"
        //Par
        //"AS", "AC", "TH", "1C","2S"

        String[] maso = {"2S", "3S", "4S", "5S", "6S"};
        Poker pok1 = new Poker(maso);
        pok1.imprimirCartas();
        System.out.println(pok1.identificarJugada());
        pok1.generarMasos(20);




    }
}
