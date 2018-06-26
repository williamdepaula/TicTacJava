/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.da.velha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author william
 */
public class JogoDaVelhaView extends JFrame {
    JPanel PainelJogo; // Painel dos componentes de interface
    JComboBox SelectDif; // Combo Box de dificuldade
    JButton BotaoStart; // Botão de começar o jogo
    String Dificuldade; // Variavel de dificuldade
    JButton [] Botoes; // Botões com o jogo
    
    Jogo jogo; // Tabuleiro do jogo
    
    public JogoDaVelhaView(){
        super("Jogo da Velha");
        // Determina uma borda ao redor dos componentes
        ((JComponent)getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
        
        PainelJogo = new JPanel();//Cria um novo painel
        PainelJogo.setLayout(new GridLayout(4, 3, 10, 10)); 
        
        // Criando seleção de dificuldade  
        JLabel label1 = new JLabel("Modo");
        PainelJogo.add(label1);
        
        String[] Dif = {"Fácil", "Médio", "Difícil"};
        SelectDif = new JComboBox(Dif);
        SelectDif.setSelectedIndex(0);
        PainelJogo.add(SelectDif);
        
        BotaoStart = new JButton("Iniciar");
        BotaoStart.setFont(new Font("Calibri", Font.BOLD, 15));        
        BotaoStart.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(SelectDif.getSelectedIndex() == 0){
                    Dificuldade = "facil";
                } else if(SelectDif.getSelectedIndex() == 1){
                    Dificuldade = "medio";
                } else {
                    Dificuldade = "dificil";
                }
                // Inicializaçã do jogo em sí
                jogo = new Jogo(Dificuldade, "X");
                SelectDif.setEnabled(false);
                BotaoStart.setEnabled(false);
                for (int i = 0; i < 9; i++) {
                    Botoes[i].setEnabled(true);
                }
            }
        });
        PainelJogo.add(BotaoStart);
        
        // Inicializando Botões do jogo
        Botoes = new JButton[9];
        for(int i=0;i<9;i++){
            Botoes[i] = new JButton("");
            Botoes[i].setEnabled(false);
            Botoes[i].setFont(new Font("Calibri", Font.BOLD, 70));
            PainelJogo.add(Botoes[i]);
        }
        
        Botoes[0].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(0, 0);
                Botoes[0].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[1].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(0, 1);
                Botoes[1].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[2].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(0, 2);
                Botoes[2].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[3].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(1, 0);
                Botoes[3].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[4].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(1, 1);
                Botoes[4].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[5].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(1, 2);
                Botoes[5].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[6].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(2, 0);
                Botoes[6].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[7].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(2, 1);
                Botoes[7].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        Botoes[8].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Chamar Função de jogo
                jogo.Joga(2, 2);
                Botoes[8].setEnabled(false);
                atualizaTabuleiro();
            }
        });
        
        // Fim da Formatação dos botões
        
        add(PainelJogo);
        setLocation(470, 200);
        setSize(400, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);               
        
    }
    
    public void atualizaTabuleiro(){
        for(int i=0,l=0,c=0;i<9;i++){
            Botoes[i].setText(jogo.getTabuleiro(l, c));
            c++;
            if(c==3){
                c=0;
                l++;
            }            
        }
        if(jogo.Winner()){
            
            // Bloqueando tabuleiros
            for(int i=0;i<9;i++){
                Botoes[i].setEnabled(false);
            }            
            JOptionPane.showMessageDialog(null, jogo.ganhador);
        }
        
            
    }
    
}
