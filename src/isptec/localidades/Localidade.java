/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.localidades;

import isptec.io.ObjectSerializableGeneric;
import isptec.io.StringBufferModelo;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author Aires Veloso
 */
public class Localidade extends ObjectSerializableGeneric
{

    private static final int SIZE_NOME = 80;

    private int codigo, localidadePai;
    private StringBufferModelo nome;

    public Localidade()
    {
        this.codigo = 0;
        this.nome = new StringBufferModelo(" ", SIZE_NOME);
        this.localidadePai = 0;
    }
    public Localidade(int codigo, String nome, int localidadePai)
    {
        this.codigo = codigo;
        this.nome = new StringBufferModelo(nome, SIZE_NOME);
        this.localidadePai = localidadePai;
    }

    @Override
    public void write(RandomAccessFile stream)
    {
        try
        {
            stream.write(codigo);
            stream.write(localidadePai);
        }
        catch (IOException ex)
        {
            String msg = "falha na escrita da localidade no ficheiro ";
            JOptionPane.showMessageDialog(null, msg);
            System.exit(1);
        }
        nome.write(stream);
    }

    @Override
    public void read(RandomAccessFile stream)
    {
        try
        {
            codigo = stream.readInt();
            localidadePai = stream.readInt();
        }
        catch (IOException ex)
        {
            String msg = "falha na leitura da localidade no ficheiro ";
            JOptionPane.showMessageDialog(null, msg);
            System.exit(1);
        }
        nome.read(stream);
    }

    @Override
    public String toString()
    {
        return "Localidade{" + "codigo=" + codigo + ", nome=" + nome + ", localidadePai=" + localidadePai + '}';
    }

    // Getters and Setters

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public int getLocalidadePai()
    {
        return localidadePai;
    }

    public void setLocalidadePai(int localidadePai)
    {
        this.localidadePai = localidadePai;
    }

    public StringBufferModelo getNome()
    {
        return nome;
    }

    public void setNome(StringBufferModelo nome)
    {
        this.nome = nome;
    }
    

}
