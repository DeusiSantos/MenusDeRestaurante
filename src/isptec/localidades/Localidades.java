/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.localidades;

import isptec.Defs;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Aires Veloso
 */
public class Localidades
{

    
    public static Localidades localidadeStatic = new Localidades();
    public static List<Localidade> localidadesList = localidadeStatic.getLocalidades();
    public static CompareLocalidades compareLocalidades = new CompareLocalidades();

    private List<Localidade> localidades;
    private long sizeReg;


    public static List<Localidade> filhos(int codigo)
    {
        List<Localidade> lista = new ArrayList();
        for (Localidade loc : localidadesList)
        {
            if (loc.getLocalidadePai() == codigo)
                lista.add(loc);
        }
        sort(lista);
        return lista;
    }
    
    private Localidades()
    {
        localidades = Arrays.asList(
                new Localidade(1, "Africa", 0),
                new Localidade(2, "America", 0),
                new Localidade(3, "Europa", 0),
                new Localidade(4, "Asia", 0),
                new Localidade(5, "Angola", 1),
                new Localidade(6, "Moçambique", 1),
                new Localidade(7, "Africa do Sul", 1),
                new Localidade(8, "Nigeria", 1),
                new Localidade(9, "Brasil", 2),
                new Localidade(10, "Luanda", 5),
                new Localidade(11, "Cabinda", 5),
                new Localidade(12, "Zaire", 5),
                new Localidade(13, "Uige", 5),
                new Localidade(14, "Lunda-Norte", 5),
                new Localidade(15, "Lunda-Sul", 5),
                new Localidade(16, "Malange", 5),
                new Localidade(17, "Bengo", 5),
                new Localidade(18, "Kwanza-Norte", 5),
                new Localidade(19, "Kwanza-Sul", 5),
                new Localidade(20, "Huambo", 5),
                new Localidade(21, "Bie", 5),
                new Localidade(22, "Moxico", 5),
                new Localidade(23, "Kuando Kubango", 5),
                new Localidade(24, "Benguela", 5),
                new Localidade(25, "Huila", 5),
                new Localidade(26, "Cunene", 5),
                new Localidade(27, "Namibe", 5),
                /*
                Municios de Luanda:
                Belas, Cacuaco, Cazenga, Icolo e Bengo,
                Luanda, Kissama, Kilamba Kiaxi, Talatona, Viana
                */
                new Localidade(28, "Belas", 10),
                new Localidade(29, "Cacuaco", 10),
                new Localidade(30, "Cazenga", 10),
                new Localidade(31, "Icolo e Bengo", 10),
                new Localidade(32, "Luanda", 10),
                new Localidade(33, "Kissama", 10),
                new Localidade(34, "Kilamba Kiaxi", 10),
                new Localidade(35, "Talatona", 10),
                new Localidade(36, "Viana", 10)
        );
        sizeReg = 0;
        try
        {
            sizeReg = new Localidade().sizeof();
        }
        catch (IOException ex)
        {
            String msg = "falha na escrita da localidade no ficheiro " + Defs.fileLocalidades;
            JOptionPane.showMessageDialog(null, msg);
            System.exit(1);
        }
    }

    public static void sort(List<Localidade> lista)
    {
        Collections.sort(lista, new CompareLocalidades());
    }

    public static class CompareLocalidades implements Comparator
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            Localidade s1 = (Localidade) o1;
            Localidade s2 = (Localidade) o2;
            return s1.getNome().compareToIgnoreCase(s2.getNome());
        }
    }

    private long getNumeroLocalidades(RandomAccessFile stream)
    {
        long nregs = 0;
        try
        {
            nregs = stream.length() / sizeReg;
        }
        catch (IOException ex)
        {
            String msg = "falha na escrita da localidade no ficheiro " + Defs.fileLocalidades;
            JOptionPane.showMessageDialog(null, msg);
            System.exit(1);
        }
        return nregs;
    }

    private void write(Localidade loc, RandomAccessFile stream)
    {
        try
        {
            stream.seek((loc.getCodigo() - 1) * sizeReg);
            loc.write(stream);
        }
        catch (IOException ex)
        {
            String msg = "falha na escrita da localidade no ficheiro " + Defs.fileLocalidades;
            JOptionPane.showMessageDialog(null, msg);
            System.exit(1);
        }
    }

    private Localidade read(RandomAccessFile stream, long pos)
    {
        Localidade loc = new Localidade();
        try
        {
            stream.seek(pos * sizeReg);
            loc.read(stream);
        }
        catch (IOException ex)
        {
            String msg = "falha na leitura da localidade no ficheiro " + Defs.fileLocalidades;
            JOptionPane.showMessageDialog(null, msg);
            System.exit(1);
        }
        return loc;
    }

    private void write(RandomAccessFile stream)
    {
        for (Localidade loc : this.localidades)
        {
            this.write(loc, stream);
        }
    }

    private void read(RandomAccessFile stream)
    {
        long nregs = getNumeroLocalidades(stream);
        this.localidades = new ArrayList();
        Localidade loc;
        for (int i = 0; i < nregs; i++)
        {
            loc = this.read(stream, i);
            localidades.add(loc);
        }
    }

    @Override
    public String toString()
    {
        String msg = "";
        boolean first = true;

        for (Localidade loc : this.localidades)
        {
            if (!first)
            {
                msg += ", ";
            }
            else
            {
                msg += "Localidades{\n";
            }
            first = false;
            msg += loc;
        }
        msg += "\n}";
        return msg;
    }

    // Getters and Setters

    private List<Localidade> getLocalidades()
    {
        return localidades;
    }

    public static List<Localidade> getLocalidadesList()
    {
        return localidadesList;
    }
    
}
