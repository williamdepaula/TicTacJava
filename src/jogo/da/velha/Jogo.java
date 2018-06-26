/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.da.velha;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author william
 */



public class Jogo {
    String tabuleiro[][];
    String dificuldade;
    String jogador;
    String ganhador;
    int    qtdJogadas;
    ArrayList<Jogada> jogadas;
    
    
    public Jogo(String dif, String jogIni){
        dificuldade = dif;
        jogador = jogIni;
        tabuleiro = new String[3][3];
        jogadas = new ArrayList<>();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tabuleiro[i][j]="";
            }
        }        
        qtdJogadas = 0;
        
        
    }
    
    public boolean Winner(){
        // Verificando linhas
        for(int l=0;l<3;l++){
            if(tabuleiro[l][0].equals(tabuleiro[l][1]) && tabuleiro[l][0].equals(tabuleiro[l][2]) && !tabuleiro[l][0].equals("")){
                ganhador = tabuleiro[l][0];
                return true;
            }            
        }
        // Verificando colunas
        for(int c=0;c<3;c++){
            if(tabuleiro[0][c].equals(tabuleiro[1][c]) && tabuleiro[0][c].equals(tabuleiro[2][c]) && !tabuleiro[0][c].equals("")){
                ganhador = tabuleiro[0][c];
                return true;
            }            
        }
        // Verificando diagonal principal
        if(tabuleiro[0][0].equals(tabuleiro[1][1]) && tabuleiro[0][0].equals(tabuleiro[2][2]) && !tabuleiro[0][0].equals("")){
            ganhador = tabuleiro[0][0];
            return true;
        }            
        // Verificando diagonal secundaria
        if(tabuleiro[0][2].equals(tabuleiro[1][1]) && tabuleiro[0][2].equals(tabuleiro[2][0]) && !tabuleiro[0][2].equals("")){
            ganhador = tabuleiro[0][2];
            return true;
        }            
        
        if(qtdJogadas == 9){
            ganhador = "Empatou";
            return true;
        }
        
        return false;
    }
    
    public void mudaJogador(){
        if(jogador.equals("X")){
            jogador = "O";
        } else {
            jogador = "X";
        }
    }
    
    public Jogada Smith(){
        // Agente Smith, sistem de jogo randomico.
        Jogada melhorJogada;

        ArrayList<Jogada> jogadas = jogadasValidas();
        Random gerador = new Random();
        int jogada = gerador.nextInt(jogadas.size());
        melhorJogada = new Jogada(jogadas.get(jogada).linha,jogadas.get(jogada).coluna);
        
        return melhorJogada;        
    }
    
    public Jogada Minimax(){
        
        // Retorna uma jogada com o minimax
        Jogada melhorJogada = new Jogada(0,0);
        ArrayList<Jogada> scores;
        scores = new ArrayList<>();        
        
        if(Winner()){
            if(ganhador.equals(jogador)){
                
                melhorJogada.score = 10;
                return melhorJogada;
            } 
            mudaJogador();
            if(ganhador.equals(jogador)){
                melhorJogada.score = -10;
                return melhorJogada;
            }
            
            melhorJogada.score = 0;
            return melhorJogada;
        }
        

        ArrayList<Jogada> jogadas = jogadasValidas();
        for(Jogada jg: jogadas){
            
            Jogo aux = new Jogo(dificuldade, jogador);
            for(int l=0;l<3;l++)
                for(int c=0;c<3;c++)
                    aux.tabuleiro[l][c]= tabuleiro[l][c];
            aux.Joga(jg.linha, jg.coluna);
            aux.mudaJogador();
            scores.add(aux.Minimax());
        }
        
        return scores.get(0);
        
    }
    
    
    public void Joga(int x, int y){
        
        if(!Winner()){
            tabuleiro[x][y] = jogador;
            mudaJogador();
            qtdJogadas ++;
        }     
        
        if(!Winner()){
            Jogada jogo;
            if(dificuldade.equals("facil"))
                jogo = Smith();
            else if(dificuldade.equals("medio"))
                jogo = Smith();
            else {
                jogo = Minimax();
            }
                
            
            tabuleiro[jogo.linha][jogo.coluna] = jogador;
            mudaJogador();        
            qtdJogadas ++;
        }
        
        
        
    }
    
    public String getTabuleiro(int x, int y){
        if(x>=0 && y>=0 && x<3 && y<3){
            return tabuleiro[x][y];
        }
        return "";
        
    }
    
    public ArrayList<Jogada> jogadasValidas(){
        jogadas = new ArrayList<>();
        
        for(int l=0;l<3;l++){
            for(int c=0;c<3;c++){
                if(tabuleiro[l][c].equals("")){
                    jogadas.add(new Jogada(l,c));
                }
            }            
        }
        
        return jogadas;
    }
}
