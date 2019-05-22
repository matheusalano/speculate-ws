/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Speculatews;

import java.util.ArrayList;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author matheusalano
 */
@WebService(serviceName = "SpeculateWS")
public class SpeculateWS {
    
    private class SpeculateTest {
        public int id1;
        public String nome1;
        public int id2;
        public String nome2;
        public int jogoIdx;
        
        SpeculateTest(int id1, String nome1, int id2, String nome2, int jogoIdx) {
            this.id1 = id1;
            this.nome1 = nome1;
            this.id2 = id2;
            this.nome2 = nome2;
            this.jogoIdx = jogoIdx;
        }
    }

    private Speculate[] jogos;
    private Random gerador = new Random();
    private ArrayList<SpeculateTest> preRegistro;
    
    public SpeculateWS() {
        jogos = new Speculate[500];
        preRegistro = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            jogos[i] = new Speculate();
        }
}
    
    /**
     * Web service operation
     * @param nome1
     * @param id1
     * @param nome2
     * @param id2
     * @return 
     */
    @WebMethod(operationName = "preRegistro")
    synchronized public int preRegistro(@WebParam(name = "nome1") String nome1, @WebParam(name = "id1") int id1, @WebParam(name = "nome2") String nome2, @WebParam(name = "id2") int id2) {
        int jogoIdx = temPartidaDispParaTeste();
        if (jogoIdx == -2) { return -1; }
        jogos[jogoIdx].preparaParaTeste();
        
        SpeculateTest pre = new SpeculateTest(id1, nome1, id2, nome2, jogoIdx);
        preRegistro.add(pre);
        return 0;
    }

    /**
     * Web service operation
     * @param nome
     * @return 
     */
    @WebMethod(operationName = "registraJogador")
    synchronized public int registraJogador(@WebParam(name = "nome") String nome) {
        int ehTeste = jogadorEhTeste(nome);
        if (ehTeste != -1) { return ehTeste; }
        
        int partida = this.temPartidaDisp(nome);
        if (partida == -1) { return -1; }
        if (partida == -2) { return -2; }

        String id = Integer.toString(gerador.nextInt(999) + 1000);
        id += String.format("%03d" , partida + 1);

        Jogador jog = new Jogador(Integer.parseInt(id), nome);
        jogos[partida].adicionaJogador(jog);

        return jog.getIdentifier();
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "encerraPartida")
    public int encerraPartida(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return idx;

        return jogos[idx].encerraPartida(id);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "temPartida")
    public int temPartida(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return idx;

        return jogos[idx].temPartida(id);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemOponente")
    public String obtemOponente(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return "";

        return jogos[idx].obtemOponente(id);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "ehMinhaVez")
    public int ehMinhaVez(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return idx;

        return jogos[idx].ehMinhaVez(id);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemNumBolas")
    public int obtemNumBolas(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return idx;

        return jogos[idx].obtemNumBolas(id);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemNumBolasOponente")
    public int obtemNumBolasOponente(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return idx;

        return jogos[idx].obtemNumBolasOponente(id);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemTabuleiro")
    public String obtemTabuleiro(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return "";

        return jogos[idx].obtemTabuleiro();
    }

    /**
     * Web service operation
     * @param id
     * @param numLancamentos
     * @return 
     */
    @WebMethod(operationName = "defineJogadas")
    public int defineJogadas(@WebParam(name = "id") int id, @WebParam(name = "numLancamentos") int numLancamentos) {
        int idx = getPartida(id);
        if (idx == -1) return idx;

        return jogos[idx].defineJogadas(id, numLancamentos);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "jogaDado")
    synchronized public int jogaDado(@WebParam(name = "id") int id) {
        int idx = getPartida(id);
        if (idx == -1) return idx;

        return jogos[idx].jogaDado(id);
    }
    
    // Helpers

    private int temPartidaDispParaTeste() {
        for (int i = 0; i < 500; i++) {
            if (jogos[i].temVaga() == 2) { return i; }
        }
        
        return -2;
    }
    
    private int temPartidaDisp(String nome) {

        for (int i = 0; i < 500; i++) {
            if (jogos[i].temJogador(nome)) { return -1; }
        }

        for (int i = 0; i < 500; i++) {
            if (jogos[i].temVaga() == 1) { return i; }
        }

        for (int i = 0; i < 500; i++) {
            if (jogos[i].temVaga() == 2) { return i; }
        }
        
        return -2;
    }

    private int getPartida(int idUsuario) {
        for (int i = 0; i < 500; i++) {
            if (jogos[i].temJogador(idUsuario)) { return i; }
        }

        return -1;
    }
    
    private int jogadorEhTeste(String nome) {
        for(int i = 0; i < preRegistro.size(); i++) {
            SpeculateTest pre = preRegistro.get(i);
            if (pre.nome1.equals(nome)) {
                Jogador jog = new Jogador(pre.id1, pre.nome1);
                jogos[pre.jogoIdx].adicionaJogador(jog);
                return pre.id1;
            } else if ((pre.nome2.equals(nome))) {
                Jogador jog = new Jogador(pre.id2, pre.nome2);
                jogos[pre.jogoIdx].adicionaJogador(jog);
                preRegistro.remove(i);
                return pre.id2;
            }
        }
        
        return -1;
    }
}
