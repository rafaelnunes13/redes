package com.udesc.redes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by rafae on 27/09/2016.
 */
public class Execucao {

    public static void main(String[] args) {

        String arquivo = "C:/temp/entradas.txt";
        Integer nrLinha = 0;
        String tipo = "";
        ArrayList<Integer> posicoes = new ArrayList<Integer>();
        Integer inicio = 0;
        int i, distanciaTotal = 0;
        Escalonamento escalonamento = new Escalonamento();

        try {
            FileReader arq = new FileReader(arquivo);

            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            nrLinha++;

            while (linha != null) {

                if(nrLinha == 1){
                    tipo = linha.trim();
                }else if(nrLinha == 2){

                    String pos[] = linha.split(",");

                    for (i = 0; i < pos.length; i++){
                        posicoes.add(Integer.parseInt(pos[i]));
                    }
                }else if(nrLinha == 3){
                    inicio = Integer.parseInt(linha);
                }

                linha = lerArq.readLine(); // lê da segunda até a última linha
                nrLinha++;
            }

        }catch(Exception e){
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        };

        switch(tipo){
            case "SSTF":
                distanciaTotal = escalonamento.sstf(posicoes, inicio);
                break;
            case "FIFO":
                distanciaTotal = escalonamento.fifo(posicoes, inicio);
                break;
            case "SCAN":
                distanciaTotal = escalonamento.scan(posicoes, inicio, false);
                break;
            case "CSCAN":
                distanciaTotal = escalonamento.scan(posicoes, inicio, true);
                break;
        }

        for(i = 0; i < posicoes.size(); i++){
            System.out.println(posicoes.get(i).toString());
        }

        System.out.println("Distância total: " + distanciaTotal);
    }
}
