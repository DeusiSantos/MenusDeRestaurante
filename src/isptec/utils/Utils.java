/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isptec.utils;

import isptec.listas.Listas;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author aires
 */
public class Utils
{
    public static boolean concorda()
    {
        String opcoes[] =
        {
            "Concorda", "Nao Concorda"
        };
        return Listas.enviarLerOpcaoEscolhida(opcoes) == 1;
    }
    
    public static boolean continua()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Continua ? [s]/[S]: ");
        String resposta = scanner.next();
        return resposta.equalsIgnoreCase("s") || 
                        resposta.equalsIgnoreCase("S");
    }
    
    public static boolean continua(String msg)
    {
        System.out.println(msg);
        return continua();
    }
    
    public static void exit(String msg)
    {
        System.err.println(msg);
        System.exit(0);
    }

    public static void exit(String msg, int codigo)
    {
        System.err.println(msg);
        System.exit(codigo);
    }

    public static int compare(Integer n1, Integer n2)
    {
        return n1 > n2 ? 1 : (Objects.equals(n1, n2) ? 0 : -1);
    }

    public static double converterDoubleDoisDecimais(double precoDouble)
    {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoDouble);
        return Double.parseDouble(string);
    }

}
