/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.da.velha;

/**
 *
 * @author william
 */
public class Jogada {
    int linha;
    int coluna;
    int score;
    
    public Jogada(int l, int c){
        linha = l;
        coluna = c;
        score = 0;
    }    
}
