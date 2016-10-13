package com.udesc.redes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rafael on 27/09/2016.
 */
public class Escalonamento {

    public int fifo(ArrayList<Integer> posicoes, Integer inicio){

        int i, distancia, total = 0;
        Integer atual = inicio;

        for(i = 0; i < posicoes.size(); i++){

            distancia = Math.abs(atual - posicoes.get(i));
            total += distancia;

            atual = posicoes.get(i);
        }

        return total;
    }

    public int sstf(ArrayList<Integer> posicoes, Integer inicio){

        int i;
        ArrayList<Integer> posicoesAux = new ArrayList<Integer>();
        Integer atual = inicio;
        Integer destino = 0;
        int distancia = 0;
        int total = 0;

        while(posicoes.size() > 0) {

            for (i = 0; i < posicoes.size(); i++) {
                //System.out.println(posicoes.get(i).toString());
                if (destino == 0) {

                    destino = posicoes.get(i);
                    distancia = Math.abs(atual - destino);

                } else if ((Math.abs(atual - posicoes.get(i)) < distancia) && atual != posicoes.get(i)) {

                    destino = posicoes.get(i);
                    distancia = Math.abs(atual - destino);
                }
            }

            //System.out.println("Atual: " + atual + ". Destino: " + destino);

            posicoesAux.add(destino);
            posicoes.remove(destino);

            total += distancia;

            atual = destino;
            destino = 0;
            distancia = 0;
        }

        for(i = 0; i < posicoesAux.size(); i++) {

            posicoes.add(posicoesAux.get(i));
        }

        return total;
    }

    public int scan(ArrayList<Integer> posicoes, Integer inicio, boolean circular){

        int i;
        int distancia = 0;
        int proximo = 0;
        Integer posicao;
        boolean sobe;
        boolean fim = false;
        ArrayList<Integer> posicoesAux = new ArrayList<Integer>();

        Collections.sort(posicoes);

        for (i = 0; i < posicoes.size(); i++){

            if (posicoes.get(i) >= inicio){
                break;
            }
        }

        if ((posicoes.size() - 1) / 2 > i) {
            sobe = false;
            proximo = i == 0 ? 0 : (i - 1);
        }else{
            sobe = true;
            proximo = i;
        }

        if(!circular){

            while (posicoes.size() > 0) {

                if (sobe && proximo >= posicoes.size()){

                    proximo = 0;
                }

                posicao = posicoes.get(proximo);
                posicoesAux.add(posicao);
                posicoes.remove(proximo);

                if(!sobe && proximo > 0){
                    proximo --;
                }
            }

        }else{

            while (posicoes.size() > 0) {

                posicao = posicoes.get(proximo);
                posicoesAux.add(posicao);
                posicoes.remove(proximo);

                if(!sobe && proximo == 0){
                    fim = true;
                }else if(sobe && proximo == (posicoes.size() - 1)){
                    fim = true;
                }

                if(!sobe && !fim){
                    proximo--;
                }else if(!sobe && posicoes.size() > 0 && fim){

                    proximo = posicoes.size() - 1;
                }

                if(sobe && posicoes.size() > 0 && fim && (proximo != (posicoes.size() - 1))){

                    proximo = 0;
                }
            }
        }

        for(i = 0; i < posicoesAux.size(); i++) {

            if (i == 0){
                distancia += Math.abs(inicio - posicoesAux.get(i));
            }else{
                distancia += Math.abs(posicoesAux.get((i - 1)) - posicoesAux.get(i));
            }

            posicoes.add(posicoesAux.get(i));
        }

        return distancia;
    }
}
