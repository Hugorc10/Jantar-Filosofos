package util;

import view.ScreenView;

public class Philosopher extends Thread {
    
    // cria um codigo privado para o filosofo
    private int id;
    
    // cria padroes de comportamento do filosofo
    final int THINKING = 0;
    final int HUNGRY = 1;
    final int EATING = 2;
    
    // meotodo construtor que recebe um nome para a classe e um codigo de
    // identificacao do filosofo
    public Philosopher(String nome, int id) {
        super(nome);
        this.id = id;
    }
    
    // metodo para definir que o filosofo esta com fome
    public void hungry() {
        // seta o state deste filosofo na classe Table para 'HUNGRY'
        Table.state[this.id] = this.HUNGRY;
        // exibe uma mensagem de controle na tela
        System.out.println("O filosofo " + getName() + " esta com fome!");
    }
    
    // metodo para definir que o filosofo esta comendo
    public void eat() {
        // seta o estado deste filosofo na classe Table para 'EATING'
        Table.state[this.id] = this.EATING;
        // exibe uma mensagem de controle na tela
        System.out.println("O filosofo " + getName() + " esta comendo!");
        
        // sera criado um controle para o filosofo permanecer comendo
        // durante certo periodo de tempo
        try {
            // fica parado neste estado por um tempo
            Thread.sleep((int) (1000 - ScreenView.philosophersSld.getValue()));
//            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            // exibe uma mensagem de controle de erro
            System.out.println("ERROR>" + ex.getMessage());
        }
    }
    
    // metodo para definir que o filosofo esta pensando
    public void think() {
        // seta o state deste filosofo na classe Grade para 'THINKING'
        Table.state[this.id] = this.THINKING;
        // Exibe uma mensagem de controle na tela
        System.out.println("O flosofo " + getName() + " esta pensando!");
        
        // sera criado um controle para o filosofo permanecer pensando
        // durante certo periodo de tempo
        try {
            // fica parado neste estado por um tempo
            Thread.sleep((int) (1000 - ScreenView.philosophersSld.getValue()));
//            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            // exibe uma mensagem de controle de erro
            System.out.println("ERROR>" + ex.getMessage());
        }
    }
    
    // metodo para o filosofo soltar um garfo que ele pegou
    public void LargarGarfo() {
        // decrementa o semaforo mutex principal da classe, isso permite
        // informar que o atual metodo esta operando na mesa dos filosofo
        Table.mutex.down();
        
        // coloca o filosofo para pensar determinado tempo
        think();
        
        // apos o filosofo pensar, ele vai informar para os seus vizinhos
        // que podem tentar pegar os garfos que ja est?o dispon?veis
        Table.philosophers[VizinhoEsquerda()].TentarObterGarfos();
        Table.philosophers[VizinhoDireita()].TentarObterGarfos();
        
        // apos operar, volta o semaforo mutex para o estado normal
        // indicando que ja realizou todos procedimentos na mesa
        Table.mutex.up();
    }
    
    // metodo para o filosofo pegar um garfo na mesa
    public void PegarGarfo() {
        // decrementa o semaforo mutex principal da classe, isso permite
        // informar que o atual metodo esta operando na mesa dos fil?sofos
        Table.mutex.down();
        
        // Deixa o filosofo faminto por determinado tempo
        hungry();
        
        // apos o filosofo o periodo de fome, ele vai verificar com seus
        // vizinhos se ele pode pegar os garfos
        TentarObterGarfos();
        
        // apos operar, volta o semaforo mutex para o estado normal
        // indicando que ja realizou todos procedimentos na mesa
        Table.mutex.up();
        // decrementa seu semaforo
        Table.semaphores[this.id].down();
    }
    
    // metodo para verificar se o filosofo pode pegar um garfo disposto na mesa
    public void TentarObterGarfos() {
        
        // se o filosofo estiver faminto e o vizinho esquerdo e direito nao estiver comendo, chama o metodo 'eat()'
        if (Table.state[this.id] == this.HUNGRY &&
                Table.state[VizinhoEsquerda()] != this.EATING &&
                Table.state[VizinhoDireita()] != this.EATING) {
            eat();
            Table.semaphores[this.id].up();
        } else {
            System.out.println(getName() + " nao conseguiu comer!");
        }
        
    }
    
    // metodo de execucao da classe, onde o ambiente do filosofo sera rodado
    @Override
    public void run() {
        
        try {
            // coloca o filosofo para pensar
            think();
            
            // entao realiza uma vida infinita para o filosofo onde inicialmente
            // ele executa os procedimentos de pergar os garfos da mesa, posteriormente
            // ele descansa um pouco, e por fim, ele largar os garfos que ele pegou
            do {
                PegarGarfo();
                Thread.sleep((int) (1000 - ScreenView.philosophersSld.getValue()));
//                Thread.sleep(1000);
                LargarGarfo();
            } while (true);
        } catch (InterruptedException ex) {
            // exibe uma mensagem de controle de erro
            System.out.println("ERROR>" + ex.getMessage());
            // da um retorno de cancelamento
            return;
        }
    }
    
    // metodo para obter o filosofo vizinho da direita
    public int VizinhoDireita() {
        // Rationa o valor em 5 posicoes, ou seja, se o id deste filosofo acrescentado
        // de um for maior que quatro, passa a ser zero
        return (this.id + 1) % 5;
    }
    
    // metodo para obter o filosofo vizinho da esquerda
    public int VizinhoEsquerda() {
        if (this.id == 0) {
            // Retorna a ultima posicao
            return 4;
        } else {
            // Rationa o valor em 5 posicoes, ou seja, se o id deste filosofo decrescido
            // de um for menor que zero, passa a ser quatro
            return (this.id - 1) % 5;
        }
    }
}
