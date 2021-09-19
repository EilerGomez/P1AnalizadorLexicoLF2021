/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto1lenguajesformales2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author raymond
 */
public class ArchivosLeerEscribir {
    BufferedReader bufer;
    String linea;
    
    AnalizadorTokens analizador = new AnalizadorTokens();
    //VistaGráfica vista = new VistaGráfica();

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void leerArchivoDeEntrada(String nombre,JTextArea areaEntrada, JTextArea salidaResult){
        int contadorLienas=1;
        try{
            this.bufer= new BufferedReader(new FileReader(new File(nombre)));
            while((linea=bufer.readLine())!=null){
                contadorLienas++;
                System.out.println(linea + " Liena: " + contadorLienas);
                areaEntrada.setText(areaEntrada.getText()+"\n" + linea);
                analizador.iniciarAnalizador(linea, contadorLienas);
                salidaResult.setText(analizador.getResultadoObtenido());
            }
            bufer.close();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error al intentar cargar el archivo");
        } catch (IOException ex) {
            Logger.getLogger(ArchivosLeerEscribir.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void leerArchivoAnalizando(String texto, JTextArea salidaResultado){
        try{
                String lienas[]= texto.split("\n");
                int contadorLineas=0;
            for(String linea: lienas){
                contadorLineas++;
                analizador.iniciarAnalizador(linea, contadorLineas);
                salidaResultado.setText(analizador.getResultadoObtenido());
               
            }
        }catch(ArrayIndexOutOfBoundsException w){
            
        }
    }
    public void guardarArchivo(String texto, String lugar){
        try {
            BufferedWriter escribir = new BufferedWriter(new FileWriter(new File(lugar + ".txt")));
            escribir.write(texto);
            escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivosLeerEscribir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
