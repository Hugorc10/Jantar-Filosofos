package util;

public class Semaphore {
    
    // criacao de um contador protegido para esta classe
    protected int contador;
    
    // metodo construtor da classe que nao recebe nenhum valor
    public Semaphore() {
        this.contador = 0;
    }
    
    // metodo construtor da classe que recebe um valor para setar no contador
    public Semaphore(int valor) {
        this.contador = valor;
    }
    
    // metodo de sincronizacao da classe onde sera decrescido o contador
    public synchronized void down() {
        
        // enquanto o contador for igual a 0, ele aguarda e trata a excecao
        while (this.contador == 0) {
            try {
                // espera uma nova solicitacao
                wait();
            } catch (InterruptedException ex) {
                // exibe uma mensagem de controle de erro
                System.out.println("ERROR>" + ex.getMessage());
            }
        }
        
        // Caso tenha saido do while acima, entao decrementa o contador da classe
        this.contador--;
        
    }
    
    // metodo de sincronizacao da classe onde sera incrementado o contador
    public synchronized void up() {
        // incrementa o contador da classe
        this.contador++;
        // notifica que a solicitacao ja foi executada
        notify();
    }
}