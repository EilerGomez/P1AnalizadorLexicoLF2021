package com.mycompany.proyecto1lenguajesformales2021;

import java.util.Scanner;
import javax.swing.JTextArea;

public final class AnalizadorTokens {

    public JTextArea areaEntrada;
    String palabra;
    int posicion = 0;
    int matrizTransiciones[][] = new int[8][6];
    int estadosFinalizacion[] = new int[35];
    String descripcionFinalizacion[] = new String[35];
    //int estadoActual = 0;
    boolean seguirLeyendo = true;

    String resultadoObtenido = "";

    AnalizadorTokens(JTextArea entrada) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.areaEntrada = entrada;

        //iniciarVariables();
        //Scanner teclado = new Scanner(System.in);
        //System.out.println("Ingrese la palabra: ");
        //iniciarAnalizador();
    }

    public void iniciarAnalizador(String entrada) {
        //this.areaEntrada=entrada;
        palabra = "";
        iniciarVariables();
        this.palabra = entrada;
        while (posicion < palabra.length()) {
            getToken();
        }
    }

    public String getResultadoObtenido() {
        return resultadoObtenido;
    }

    public void setResultadoObtenido(String resultadoObtenido) {
        this.resultadoObtenido = resultadoObtenido;
    }

    public static void main(String args[]) {
        AnalizadorTokens analizadorTokens = new AnalizadorTokens();
    }

    //ANÁLISIS DE LOS TOKENS; 
    public void iniciarVariables() {
        /*
            0-->letra; 1--> número; 2-->punto
            3--> operador; 4-->puntuación 
            5--> agrupacion
            matrizTransiciones[NumEstado][caracter]
            resultado=1 --> aceptación;       
         */
        // analisis del token identificador;
        posicion=0;
        matrizTransiciones[0][0] = 1;
        matrizTransiciones[1][0] = 1;
        matrizTransiciones[1][1] = 1;
        //analisis token numeros enteros y decimales
        matrizTransiciones[0][1] = 3;
        matrizTransiciones[3][1] = 3;
        matrizTransiciones[3][2] = 4;
        matrizTransiciones[4][1] = 5;
        matrizTransiciones[5][1] = 5;
        //análisis token operadores
        matrizTransiciones[0][3] = 2;
        matrizTransiciones[2][3] = 2;
        //análisis token puntuación
        matrizTransiciones[0][4] = 6;
        matrizTransiciones[6][4] = 6;
        matrizTransiciones[0][2] = 6;
        matrizTransiciones[6][2] = 6;

        //análisis token agrupación
        matrizTransiciones[0][5] = 7;
        matrizTransiciones[7][5] = 7;

        //analisis de errores:
        matrizTransiciones[1][2] = -2;
        matrizTransiciones[2][4] = -3;
        matrizTransiciones[2][2] = -3;
        matrizTransiciones[2][5] = -4;
        matrizTransiciones[1][3] = -5;
        matrizTransiciones[1][4] = -6;
        matrizTransiciones[1][5] = -7;

        matrizTransiciones[3][0] = -8;
        matrizTransiciones[3][3] = -9;
        matrizTransiciones[3][4] = -10;
        matrizTransiciones[3][5] = -11;

        matrizTransiciones[4][0] = -12;
        matrizTransiciones[4][2] = -13;
        matrizTransiciones[4][3] = -14;
        matrizTransiciones[4][4] = -15;
        matrizTransiciones[4][5] = -16;

        matrizTransiciones[5][0] = -17;
        matrizTransiciones[5][2] = -18;
        matrizTransiciones[5][3] = -19;
        matrizTransiciones[5][4] = -20;
        matrizTransiciones[5][5] = -21;

        matrizTransiciones[6][0] = -22;
        matrizTransiciones[6][1] = -23;
        matrizTransiciones[6][3] = -24;
        matrizTransiciones[6][5] = -25;

        matrizTransiciones[7][0] = -26;
        matrizTransiciones[7][1] = -27;
        matrizTransiciones[7][2] = -28;
        matrizTransiciones[7][3] = -29;
        matrizTransiciones[7][4] = -30;

        //identificador
        estadosFinalizacion[0] = 1;
        descripcionFinalizacion[0] = "identificador(id)";
        //numero entero:
        estadosFinalizacion[1] = 3;
        descripcionFinalizacion[1] = "Numero entero";
        //numero decimal:
        estadosFinalizacion[2] = 5;
        descripcionFinalizacion[2] = "Numero decimal";
        //operadores
        estadosFinalizacion[3] = 2;
        descripcionFinalizacion[3] = "Signos de Operación";
        //puntuación
        estadosFinalizacion[4] = 6;
        descripcionFinalizacion[4] = "Signos de puntuación";
        //agrupacion
        estadosFinalizacion[5] = 7;
        descripcionFinalizacion[5] = "Signos de agrupación";
        //errores:
        estadosFinalizacion[6] = -2;
        descripcionFinalizacion[6] = "Error, el punto no es válido";

        estadosFinalizacion[7] = -3;
        descripcionFinalizacion[7] = "Error, los grupos de operación y puntuación están juntos.";

        estadosFinalizacion[8] = -4;
        descripcionFinalizacion[8] = "Error, los grupos de operación y agrupación están juntos.";

        estadosFinalizacion[9] = -5;
        descripcionFinalizacion[9] = "Error, numero-letra está agrupada con un signo de operación";

        estadosFinalizacion[10] = -6;
        descripcionFinalizacion[10] = "Error, numero-letra está agrupada con signo de puntuación";

        estadosFinalizacion[11] = -7;
        descripcionFinalizacion[11] = "Error, numero-letra está agrupada con signo de agrupación";

        estadosFinalizacion[12] = -8;
        descripcionFinalizacion[12] = "Error, La letras están combinadas con números";

        estadosFinalizacion[13] = -9;
        descripcionFinalizacion[13] = "Error, numeros y operador estan juntos";

        estadosFinalizacion[14] = -10;
        descripcionFinalizacion[14] = "Error, numeros y signos de puntuacion están juntos";

        estadosFinalizacion[15] = -11;
        descripcionFinalizacion[15] = "Error, numeros y signos de agrupación están juntos";

        estadosFinalizacion[16] = -12;
        descripcionFinalizacion[16] = "Error, letra después del punto('.')";

        estadosFinalizacion[17] = -13;
        descripcionFinalizacion[17] = "Error, dos puntos juntos.";

        estadosFinalizacion[18] = -14;
        descripcionFinalizacion[18] = "Error, Operador después del punto";

        estadosFinalizacion[19] = -15;
        descripcionFinalizacion[19] = "Error, signo de puntuación después del punto";

        estadosFinalizacion[20] = -16;
        descripcionFinalizacion[20] = "Error, signo de agrupación después del punto";

        estadosFinalizacion[21] = -17;
        descripcionFinalizacion[21] = "Error, Letra después del número";

        estadosFinalizacion[22] = -18;
        descripcionFinalizacion[22] = "Error, dos puntos en un mismo token de números";

        estadosFinalizacion[23] = -19;
        descripcionFinalizacion[23] = "Error, operador después del decimal";

        estadosFinalizacion[24] = -20;
        descripcionFinalizacion[24] = "Error, signo de puntuación después del decimal";

        estadosFinalizacion[25] = -21;
        descripcionFinalizacion[25] = "Error, signo de agrupación después del decimal";

        estadosFinalizacion[26] = -22;
        descripcionFinalizacion[26] = "Error, letra después del signo de puntuación";

        estadosFinalizacion[27] = -23;
        descripcionFinalizacion[27] = "Error, número después del signo de puntuación";

        estadosFinalizacion[28] = -24;
        descripcionFinalizacion[28] = "Error, operador despúes del signo de puntuación";

        estadosFinalizacion[29] = -25;
        descripcionFinalizacion[29] = "Error, signo de agrupación después del signo de puntuación";

        estadosFinalizacion[30] = -26;
        descripcionFinalizacion[30] = "Error, letra después del signo de agrupación";

        estadosFinalizacion[31] = -27;
        descripcionFinalizacion[31] = "Error, número después del signo de agrupación";

        estadosFinalizacion[32] = -28;
        descripcionFinalizacion[32] = "Error, punto después del signo de agrupación";

        estadosFinalizacion[33] = -29;
        descripcionFinalizacion[33] = "Error, operador después del signo de agrupación";

        estadosFinalizacion[34] = -30;
        descripcionFinalizacion[34] = "Error, signo de puntuación después de signo de agrupación";
    }

    AnalizadorTokens() {
        //entrada=this.areaEntrada;
        Scanner teclado = new Scanner(System.in);
        iniciarVariables();
        
            
            System.out.println("Ingrese la palabra: ");
            palabra = teclado.nextLine();
            while (posicion < palabra.length()) {
                getToken();
            }
    }
    //moverme en los estados.

    public int getSiguienteEstado(int estadoActual, int caracter) {
        int resultado = -1;
        if (estadoActual >= 0 && caracter <= 5) {
            try {
                resultado = matrizTransiciones[estadoActual][caracter];
            } catch (ArrayIndexOutOfBoundsException e) {
                resultado = -1;
            }
        }
        return resultado;
    }

    public int getIntCaracter(char caracter) {
        int resultado = -1;
        if (Character.isDigit(caracter)) {
            resultado = 1;
        } else if ((caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z')) {
            resultado = 0;
        } else if (caracter == '.') {
            resultado = 2;
        } else if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {
            resultado = 3;
        } else if (caracter == '.' || caracter == ',' || caracter == ';' || caracter == ':') {
            resultado = 4;
        } else if (caracter == '(' || caracter == ')' || caracter == '[' || caracter == ']' || caracter == '{' || caracter == '}') {
            resultado = 5;
        }

        return resultado;
    }

    public String getEstadoAceptacion(int i) {
        String res = "Error Character not exist";
        int indice = 0;
        for (int estadoAceptacion : estadosFinalizacion) {
            if (estadoAceptacion == i) {
                res = descripcionFinalizacion[indice];
            }
            indice++;
        }
        return res;
    }

    public void getToken() {
        int estadoActual = 0;
        seguirLeyendo = true;
        char tmp;
        String token = "";

        while ((seguirLeyendo == true) && (posicion < palabra.length())) {
            if (Character.isSpaceChar(tmp = palabra.charAt(posicion))||Character.isWhitespace (tmp=palabra.charAt(posicion))) {
                seguirLeyendo = false;
            }else if("\n".equals(Character.toString(palabra.charAt(posicion)))){
                seguirLeyendo=false;
            } 
            else {
                int estadoTemporal = getSiguienteEstado(estadoActual, getIntCaracter(tmp));
                String movimiento = ("Estado actual: " + estadoActual + " caracter: " + tmp + " transición a: " + estadoTemporal);
                System.out.println(movimiento);
                setResultadoObtenido(getResultadoObtenido() + "\n" + movimiento);
                token += tmp;
                estadoActual = estadoTemporal;
                System.out.println(tmp);
            }
            posicion++;
        }
        String mensaje = ("------[Terminó en el estado: " + getEstadoAceptacion(estadoActual) + " token actual: " + token + "]------");
        
        setResultadoObtenido(getResultadoObtenido() + "\n"+lineas(mensaje)+"\n|" + mensaje+
                                                            "|\n"+lineas(mensaje)+"\n" );
        System.out.println(mensaje + "\n");
    }
    public String lineas(String mensaje){
        String lineas="+";
        for(int i=1; i<=mensaje.length();i++){
            lineas+="-";
            if(i==mensaje.length()){
                lineas+="+";
            }
        }
        return lineas;
    }

}
