/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto1lenguajesformales2021;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raymond
 */
public class ReporteErrores {
    AnalizadorTokens analizador = new AnalizadorTokens();
    public void insertarReportesErrores(JTable tablaReportesErrores){
       
            for(int i=1; i<=analizador.numeroDeErrores;i++){
                Object row[]={"Hola","Hola","Hola"};
                ((DefaultTableModel) tablaReportesErrores.getModel()).addRow(row);
            }
    }
}
