/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p3;

/**
 *
 * @author deusineusio
 */
public class MinhaExcecao extends Exception {

    public MinhaExcecao() {
        super();
    }

    public MinhaExcecao(String mensagem) {
        super(mensagem);
    }

    public MinhaExcecao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

