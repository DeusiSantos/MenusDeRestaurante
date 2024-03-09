/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isptec.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aires
 */
public class ListUtils
{

    public static <T> List<T> reverse(List<T> lista)
    {        
        List<T> listaTmp = new ArrayList<>();
        int sz = lista.size();
        for (int i = sz - 1; i >= 0; i--)
        {
            listaTmp.add(lista.get(i));
        }
        return listaTmp;
    }

    // sendo T uma classe envolvente: Integer, Double, Long, Character, String
    public static <T> String toString(List<T> lista)
    {
        String msg = "{ ";
        boolean first = true;

        for (T item : lista)
        {
            if (!first)
            msg += ", ";
            msg += item;
            first = false;
        }
        msg += " }";
        return msg;
    }

    // sendo T uma classe não envolvente
    public static <T> String toString(List<T> lista, ToString<T> type)
    {
        String msg = "{ ";
        boolean first = true;

        for (T obj : lista)
        {
            msg += (first ? "" : ", ");
            msg += type.toString(obj);
            first = false;
        }
        msg += " }";
        return msg;
    }

    public static boolean includes(List<Integer> list, int value)
    {
        for (Integer item : list)
        {
            if (value == item)
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean includes(List<String> list, String value)
    {
        for (String item : list)
        {
            if (value.equalsIgnoreCase(item))
            {
                return true;
            }
        }
        return false;
    }

    public static List<String> remove(List<String> lista, String item)
    {
        List<String> lista2 = new ArrayList();

        for (String palavra : lista)
        {
            if (!palavra.equals(item))
            {
                lista2.add(palavra);
            }
        }
        return lista2;
    }

    // 10, 20, 33
    public static List<Integer> parseInts(String st)
    {
        List<Integer> list = new ArrayList();
        String numerosSt[] = st.trim().split(",");
        int nl = numerosSt.length;

        for (int i = 0; i < nl; i++)
        {
            try
            {
                list.add(Integer.valueOf(numerosSt[i].trim()));
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        return list;
    }

}
