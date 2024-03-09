/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p3;

import isptec.Defs;
import isptec.listas.Listas;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deusineusio
 */
public class Prato implements Comparable<Prato>
{

    private int opc;
    private String nome;
    private String descricao;
    private double preco;
    private MenuItemTipoEnum tipoPrato; // Tipo do prato usando MenuItemTipoEnum

    // Construtor com todos os atributos
    public Prato(String nome, String descricao, double preco, MenuItemTipoEnum tipoPrato)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoPrato = tipoPrato;
    }

    private static RandomAccessFile file = null;

    public static void menuItem() throws MinhaExcecao
    {
        String[] opcoes =
        {
            "Novo", "Remover", "Imprimir", "Consultas", "Editar", "Voltar"
        };
        int opcao;
        do
        {
            opcao = Listas.enviarLerOpcaoEscolhida(opcoes);
            switch (opcao)
            {
                case 1:
                    criarPrato();
                    break;
                case 2:
                    eliminarPrato();
                    break;
                case 3:
                    imprimirPratos();
                    break;
                case 4:
                    pesquisarPratoPorNome();
                    break;
                case 5:
                    editarPrato();
                    break;
                case 6:
                    MenuPrincipal.menuPrincipal();
                    break;
            }
        }
        while (opcao != 0);
    }

    public static Prato criarPrato()
    {
        System.out.println("Criando um novo prato...");

        System.out.print("Nome do Prato: ");
        String nome = P3.scanner.nextLine();

        if (pratoExiste(nome))
        {
            System.out.println("Um prato com esse nome já existe.");
            return null;
        }

        System.out.print("Descrição do Prato: ");
        String descricao = P3.scanner.nextLine();

        System.out.print("Preço do Prato: ");
        double preco = Double.parseDouble(P3.scanner.nextLine());

        MenuItemTipoEnum tipoPrato = MenuItemTipoEnum.enviarLerOpcaoEscolhida(); // Modificar conforme necessário

        Prato novoPrato = new Prato(nome, descricao, preco, tipoPrato);
        gravarPratoNoArquivo(novoPrato);
        return novoPrato;
    }

    public static void eliminarPrato()
    {
        System.out.print("Digite o nome do prato a ser eliminado: ");
        String nomePrato = P3.scanner.nextLine();

        if (!pratoExiste(nomePrato))
        {
            System.out.println("Prato não encontrado.");
            return;
        }

        File tempFile = new File(Defs.tmpFile);
        boolean pratoEncontrado = false;

        abrirFicheiro(); // Abre o arquivo original para leitura

        try
        {
            RandomAccessFile tempRAF = new RandomAccessFile(tempFile, "rw"); // Arquivo temporário para gravação

            // Percorre o arquivo original
            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();
                double preco = file.readDouble();
                String descricao = file.readUTF();
                String tipoPrato = file.readUTF();

                if (!nome.equals(nomePrato))
                {
                    // Escreve no arquivo temporário se não for o prato a ser removido
                    tempRAF.writeUTF(nome);
                    tempRAF.writeDouble(preco);
                    tempRAF.writeUTF(descricao);
                    tempRAF.writeUTF(tipoPrato);
                }
                else
                {
                    pratoEncontrado = true; // Marca que encontrou o prato
                }
            }

            // Fecha ambos os arquivos
            file.close();
            tempRAF.close();

            // Se encontrou o prato, substitui o arquivo original pelo temporário
            if (pratoEncontrado)
            {
                File originalFile = new File(Defs.MENU_ITEM_FILE);
                if (originalFile.delete())
                {
                    tempFile.renameTo(originalFile);
                    System.out.println("Prato removido com sucesso.");
                }
                else
                {
                    System.err.println("Erro ao remover o prato.");
                }
            }
            else
            {
                System.out.println("Prato não encontrado.");
                tempFile.delete(); // Apaga o arquivo temporário se o prato não foi encontrado
            }

        }
        catch (IOException e)
        {
            System.err.println("Erro ao eliminar o prato: " + e.getMessage());
        }
    }

    public static void pesquisarPratoPorNome()
    {
        abrirFicheiro();
        System.out.print("Digite o nome do prato que deseja pesquisar: ");
        String nomePrato = P3.scanner.nextLine();

        try
        {
            boolean pratoEncontrado = false;

            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();
                double preco = file.readDouble();
                String descricao = file.readUTF();
                MenuItemTipoEnum tipoPrato = MenuItemTipoEnum.valueOf(file.readUTF());

                if (nome.equals(nomePrato))
                {
                    pratoEncontrado = true;

                    // Exibe as informações do prato encontrado usando toString
                    Prato prato = new Prato(nome, descricao, preco, tipoPrato);
                    System.out.println("Prato encontrado:");
                    System.out.println(prato.toString());
                    break; // Encerra a busca após encontrar o prato
                }
            }

            if (!pratoEncontrado)
            {
                System.out.println("Prato não encontrado.");
            }

            file.close();
        }
        catch (IOException e)
        {
            System.err.println("Erro ao pesquisar o prato: " + e.getMessage());
        }
    }

    public static void imprimirPratos()
    {
        abrirFicheiro(); // Abre o arquivo para leitura
        try
        {
            file.seek(0); // Posiciona no início do arquivo
            while (file.getFilePointer() < file.length())
            {
                // Lê os dados de cada prato
                String nome = file.readUTF();
                double preco = file.readDouble();
                String descricao = file.readUTF();
                MenuItemTipoEnum tipoPrato = MenuItemTipoEnum.valueOf(file.readUTF());

                // Cria um objeto Prato e utiliza toString para exibir os detalhes
                Prato prato = new Prato(nome, descricao, preco, tipoPrato);
                System.out.println(prato.toString());
                System.out.println("-----------------------------------------------------------------------------");
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao ler os pratos: " + e.getMessage());
        }
        finally
        {
            fecharFicheiro(); 
        }
    }

    public static void editarPrato()
    {
        abrirFicheiro();
        System.out.print("Digite o nome do prato que deseja editar: ");
        String nomePrato = P3.scanner.nextLine();

        List<Prato> pratos = new ArrayList<>();
        boolean pratoEncontrado = false;

        try
        {
            // Lê todos os pratos do arquivo e os armazena na lista
            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();
                double preco = file.readDouble();
                String descricao = file.readUTF();
                MenuItemTipoEnum tipoPrato = MenuItemTipoEnum.valueOf(file.readUTF());

                Prato prato = new Prato(nome, descricao, preco, tipoPrato);
                pratos.add(prato);

                if (nome.equals(nomePrato))
                {
                    pratoEncontrado = true;

                    // Solicita e aplica as alterações
                    System.out.print("Novo nome do Prato: ");
                    String novoNome = P3.scanner.nextLine();
                    if (pratoExiste(novoNome))
                    {
                        System.out.println("Já existe um prato com esse nome.");
                        file.close();
                        return;
                    }

                    System.out.print("Nova descrição do Prato: ");
                    String novaDescricao = P3.scanner.nextLine();
                    System.out.print("Novo preço do Prato: ");
                    double novoPreco = Double.parseDouble(P3.scanner.nextLine());
                    MenuItemTipoEnum novoTipoPrato = MenuItemTipoEnum.enviarLerOpcaoEscolhida(); // Modificar conforme necessário

                    // Atualiza o prato na memória
                    prato.setNome(novoNome);
                    prato.setDescricao(novaDescricao);
                    prato.setPreco(novoPreco);
                    prato.setTipoPrato(novoTipoPrato);
                }
            }

            file.close();

            // Se encontrou o prato, reescreve o arquivo com os pratos atualizados
            if (pratoEncontrado)
            {
                file = new RandomAccessFile(Defs.MENU_ITEM_FILE, "rw");
                for (Prato prato : pratos)
                {
                    file.writeUTF(prato.getNome());
                    file.writeDouble(prato.getPreco());
                    file.writeUTF(prato.getDescricao());
                    file.writeUTF(prato.getTipoPrato().name());
                }
                System.out.println("Prato editado com sucesso.");
            }
            else
            {
                System.out.println("Prato não encontrado.");
            }

            file.close();
        }
        catch (IOException e)
        {
            System.err.println("Erro ao editar o prato: " + e.getMessage());
        }
    }

    public static boolean pratoExiste(String nomePrato)
    {
        try (RandomAccessFile file = new RandomAccessFile(Defs.MENU_ITEM_FILE, "r"))
        {
            while (file.getFilePointer() < file.length())
            {
                try
                {
                    String nomeExistente = file.readUTF();
                    file.readDouble(); // Pula o preço
                    file.readUTF(); // Pula a descrição
                    file.readUTF(); // Pula o tipo de prato

                    if (nomeExistente.equals(nomePrato))
                    {
                        return true;
                    }
                }
                catch (EOFException e)
                {
                    // Fim do arquivo alcançado
                    break;
                }
                catch (UTFDataFormatException e)
                {
                    System.err.println("Formato de string inválido no arquivo: " + e.getMessage());
                    // Pode ser necessário ajustar a posição do ponteiro no arquivo aqui
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao verificar o prato: " + e.getMessage());
        }
        return false;
    }

    public static Prato buscarPratoPorNome(String nomePrato)
    {
        abrirFicheiro();
        try
        {
            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();
                double preco = file.readDouble();
                String descricao = file.readUTF();
                MenuItemTipoEnum tipoPrato = MenuItemTipoEnum.valueOf(file.readUTF());

                if (nome.equals(nomePrato))
                {
                    return new Prato(nome, descricao, preco, tipoPrato);
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao buscar prato: " + e.getMessage());
        }
        finally
        {
            fecharFicheiro();
        }
        return null;
    }

    private static void gravarPratoNoArquivo(Prato prato)
    {
        try
        {
            RandomAccessFile file = new RandomAccessFile(Defs.MENU_ITEM_FILE, "rw");
            file.seek(file.length()); // Posiciona no final do arquivo
            file.writeUTF(prato.getNome());
            file.writeDouble(prato.getPreco());
            file.writeUTF(prato.getDescricao());
            file.writeUTF(prato.getTipoPrato().name());
            file.close();

            System.out.println("Prato gravado com sucesso!");
        }
        catch (IOException e)
        {
            System.err.println("Erro ao gravar o prato: " + e.getMessage());
        }
        
        fecharFicheiro();
    }

    // Método para abrir o arquivo de pratos
    private static void abrirFicheiro()
    {
        try
        {
            file = new RandomAccessFile(Defs.MENU_ITEM_FILE, "rw");
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Prato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para fechar o arquivo
    public static void fecharFicheiro()
    {
        if (file != null)
        {
            try
            {
                file.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Prato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Getters e Setters
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public MenuItemTipoEnum getTipoPrato()
    {
        return tipoPrato;
    }

    public void setTipoPrato(MenuItemTipoEnum tipoPrato)
    {
        this.tipoPrato = tipoPrato;
    }

    @Override
    public String toString()
    {
        return "Prato{"
            + "nome='" + nome + '\''
            + ", descricao='" + descricao + '\''
            + ", preco=" + preco
            + ", tipoPrato=" + tipoPrato
            + '}';
    }
    
    public static void imprimirPratos(List<Prato> pratos) {
        for (Prato prato : pratos) {
            System.out.println(prato.toString());
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    @Override
    public int compareTo(Prato outro) {
        return this.nome.compareTo(outro.nome);
    }
}
