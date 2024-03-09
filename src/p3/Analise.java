/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p3;

/**
 *
 * @author deusineusio
 */
public class Analise
{

}

/*
1. Objectivo: desenvolver um sistema gestor de menus com as seguintes funcionalidades:
criar menus, consultar, actualizar e remover

2. Identificar as principais entidades ligadas ao Interface com o Utilizador:

Entidade MenuRestaurante
    atributo nome : String;
    atributo itens : Lista de Pratos

    operacao criarMenu()
    operacao consultarMenu()
    operacao ImprimirMenu()
    operacao actualizartarMenu()
    operacao removerMenu()
    operacao adicionarPrato()

    operacao adicionarMenuItem()
    operacao removerMenuItem()
FimMenuRestaurante

Entidade Prato
    atributo nome, descricao : String;
    atributo preco          : double
    atributo menuItemTipo   : MenuItemTipo { CARNE, PEIXE, MARISCO, LEGUME, FRUTA, BEBIDA, SOBREMESA }

    operacao criarPrato()
    operacao consultarPrato()
    operacao ImprimirPrato();
    operacao actualizartarPrato()
    operacao removerPrato()
FimItemMenuItem

Entidade MenuData extend MenuRestaurante 
    this.menusPorData: Lista de Menus;

    operacao ....

    adicionarMenuPorDia(Date data, MenuRestaurante menu)
    imprimirMenusPorDia()
FimMenuCalendario

3. Menu Principal

    MenuRestaurante
    MenuItem    
    MenuData
    Sair


    MenuRestaurante
        Novo
        Remover
        Imprimir
        Consultas
        Editar
        Adicionar Prato
        Voltar
    
    MenuItem
        Novo
        Remover
        Imprimir
        Consultas
        Editar
        Voltar

    MenuData
        Adicionar Menu Por Dia
        Imprimir Menus Por Dia
        Sair

    Sair
 */
