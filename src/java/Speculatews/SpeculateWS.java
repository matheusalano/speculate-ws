/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Speculatews;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author matheusalano
 */
@WebService(serviceName = "SpeculateWS")
public class SpeculateWS {

    /**
     * Web service operation
     * @param nome1
     * @param id1
     * @param nome2
     * @param id2
     * @return 
     */
    @WebMethod(operationName = "preRegistro")
    public int preRegistro(@WebParam(name = "nome1") String nome1, @WebParam(name = "id1") int id1, @WebParam(name = "nome2") String nome2, @WebParam(name = "id2") int id2) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param nome
     * @return 
     */
    @WebMethod(operationName = "registraJogador")
    public int registraJogador(@WebParam(name = "nome") String nome) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "encerraPartida")
    public int encerraPartida(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "temPartida")
    public int temPartida(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemOponente")
    public String obtemOponente(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "ehMinhaVez")
    public int ehMinhaVez(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemNumBolas")
    public int obtemNumBolas(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemNumBolasOponente")
    public int obtemNumBolasOponente(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtemTabuleiro")
    public String obtemTabuleiro(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param id
     * @param numLancamentos
     * @return 
     */
    @WebMethod(operationName = "defineJogadas")
    public int defineJogadas(@WebParam(name = "id") int id, @WebParam(name = "numLancamentos") int numLancamentos) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "jogaDado")
    public int jogaDado(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return 0;
    }
}
