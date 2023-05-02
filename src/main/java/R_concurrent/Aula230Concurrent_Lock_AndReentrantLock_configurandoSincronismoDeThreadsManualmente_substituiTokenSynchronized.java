package R_concurrent;

// Configurando manualmente o lock de blocos/escopos para evitar concorrência.

// Desvantagem: Mais verboso e complexo diferente do equivalente `synchronized`,
// ou seja, se possível utilizar `synchronized` ao invés de `ReentrantLock` melhor!

/* Definições sobre a interface `Lock`:

A interface Lock é uma das principais formas de controlar a concorrência em um
ambiente multithread. É um mecanismo mais poderoso e flexível do que a primitiva
`synchronized` do Java, pois permite que você crie locks (travas) que podem ser
adquiridos e liberados em diferentes momentos.

- A interface Lock oferece vários métodos, incluindo:

    - lock(): adquire o lock. Se o lock já estiver sendo usado por outro thread,
    o thread atual aguardará até que o lock esteja disponível.

    - unlock(): libera o lock. Se outro thread estiver esperando pelo lock, ele
    será notificado de que o lock está disponível.

    - tryLock(): tenta adquirir o lock. Se o lock já estiver sendo usado por outro
    thread, o método retornará imediatamente sem adquirir o lock.

---> Algumas aplicações e utilidades da interface Lock incluem:

- Controlar o acesso a recursos compartilhados, como variáveis globais ou arquivos.

- Evitar condições de corrida (race conditions) em que dois ou mais threads tentam
acessar ou modificar o mesmo recurso simultaneamente.

- Melhorar a eficiência de programas que utilizam múltiplos threads, pois permite
que diferentes threads executem diferentes partes do código simultaneamente sem
interferir uns com os outros.

- Possibilitar a implementação de algoritmos de sincronização mais complexos, como
monitores condicionais e semáforos.

Em resumo, a interface Lock é uma ferramenta poderosa para controlar a concorrência
em ambientes multithread, oferecendo maior flexibilidade e recursos do que a
primitiva `synchronized`.


---> Algumas implementações comuns da interface Lock incluem:

    * ReentrantLock: É a implementação mais básica e utilizada da interface Lock.
    Ela permite que um mesmo thread adquira o lock várias vezes seguidas sem causar
    um deadlock, desde que libere o lock a mesma quantidade de vezes que o adquiriu.
    É uma opção mais flexível do que a primitiva `synchronized`.

    * ReentrantReadWriteLock: Essa implementação é mais adequada para cenários em
    que há uma grande quantidade de leituras e poucas escritas em um recurso
    compartilhado.
    Ela permite que múltiplos threads realizem leituras simultaneamente, mas
    bloqueia o acesso para escritas enquanto uma leitura ou outra escrita está
    em andamento.

    * StampedLock: È uma implementação mais otimizada para cenários em que há uma
    alta quantidade de leituras e poucas escritas, como o ReentrantReadWriteLock.
    Ele usa um mecanismo de carimbos (stamps) para controlar o acesso ao recurso
    compartilhado, permitindo que múltiplos threads realizem leituras simultâneas
    e uma única escrita.

    * FairLock: Essa implementação é semelhante ao ReentrantLock, mas usa uma fila
    FIFO para determinar a ordem de aquisição do lock.
    Isso significa que o thread que estiver esperando pelo lock por mais tempo terá
    prioridade na aquisição do lock, o que pode ajudar a evitar starvation.

As diferenças entre essas implementações estão principalmente na forma como elas
controlam o acesso concorrente aos recursos compartilhados. Cada uma delas tem
suas próprias vantagens e desvantagens, e a escolha da implementação mais adequada
deve ser baseada nas necessidades específicas do sistema em que está sendo utilizado.
Por exemplo, se o recurso compartilhado for frequentemente acessado para leitura, o
ReentrantReadWriteLock pode ser mais adequado do que o ReentrantLock ou o StampedLock.


---> Pontos importantes e atenções a se tomar:

- Certifique-se de liberar o lock: Se você adquirir um lock em um recurso
compartilhado, é importante garantir que você o libere após o uso. Caso
contrário, outros threads não poderão acessar o recurso, o que pode levar
a problemas como deadlocks.

- Use try-finally: Para garantir que o lock seja sempre liberado, mesmo se
ocorrer uma exceção no código que estiver dentro do bloco protegido pelo lock,
é uma boa prática utilizar um bloco try-finally para garantir a liberação do lock.

- Evite deadlock: Deadlock ocorre quando dois ou mais threads aguardam indefinidamente
um recurso que nunca será liberado. Para evitar isso, certifique-se de que todos
os threads que adquirem locks também os liberem e evite a aquisição de vários locks
em ordem diferente.

- Utilize a implementação correta para a situação: Como mencionado anteriormente,
existem várias implementações de Lock disponíveis, cada uma com suas próprias
características. Certifique-se de escolher a implementação correta para a situação,
com base no tipo de acesso concorrente que está ocorrendo.

- Considere o impacto na performance: Embora a interface Lock seja útil para
controlar a concorrência em programas multithread, o uso excessivo pode levar
a um impacto negativo na performance. Portanto, é importante avaliar cuidadosamente
o número de locks que são usados em um programa e garantir que eles sejam aplicados
apenas quando necessário.

- Esteja ciente de condições de corrida: Embora o uso de locks possa ajudar a evitar
condições de corrida, é importante lembrar que a aplicação incorreta de locks pode
levar a essas condições. Certifique-se de que o recurso compartilhado esteja sendo
acessado corretamente e que todos os threads estejam utilizando o mesmo mecanismo
de lock.


---> Principais diferenças entre as implementações dela:

    * ReentrantLock: Permite que um mesmo thread adquira o lock várias vezes
    seguidas, sem causar um deadlock, desde que libere o lock a mesma quantidade
    de vezes que o adquiriu. Ela também oferece opções de personalização da ordem
    de aquisição do lock e controle de interrupções.

    * ReadWriteLock: Permite que vários threads leiam o recurso compartilhado ao
    mesmo tempo, mas apenas um thread pode gravar no recurso compartilhado por vez.
    Isso é útil em situações em que a maioria das operações envolve leitura, mas as
    operações de escrita são menos frequentes.

    * StampedLock: Oferece um mecanismo de lock otimista, que permite que um thread
    tente acessar o recurso compartilhado sem bloquear outros threads. Se a operação
    for bem-sucedida, o thread pode continuar sem problemas. Caso contrário, ele
    precisa adquirir um lock para garantir a consistência dos dados.

    * ReentrantReadWriteLock: Combina as características de ReentrantLock e
    ReadWriteLock, permitindo que vários threads leiam o recurso compartilhado ao
    mesmo tempo e um único thread grave no recurso compartilhado por vez.
    Isso é útil em situações em que há um número significativo de operações de
    leitura e de escrita.

    * Condition: A interface Condition é uma extensão da interface Lock que oferece
    mecanismos de sinalização entre threads. Ela permite que um thread espere até
    que uma condição específica seja atendida antes de continuar sua execução.

*/


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aula230Concurrent_Lock_AndReentrantLock_configurandoSincronismoDeThreadsManualmente_substituiTokenSynchronized {
    public static void main(String[] args) {
        // Testa Worker para entender os métodos do Lock mais básicos:
        ReentrantLock lock = new ReentrantLock();

        new Thread(new Worker("A", lock)).start();
        new Thread(new Worker("B", lock)).start();
        new Thread(new Worker("C", lock)).start();
        new Thread(new Worker("D", lock)).start();
        new Thread(new Worker("E", lock)).start();
        new Thread(new Worker("F", lock)).start();
        new Thread(new Worker("G", lock)).start();
    }
}


class Worker implements Runnable {
    private String name;
    private ReentrantLock lock;

    public Worker(String name, ReentrantLock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
            // lock.tryLock(3, TimeUnit.SECONDS);
            lock.lock();
            // Apenas a nível de exemplo, NUNCA devemos sincronizar o bloco run por completo
            // pois assim estamos perderndo todas as vantagens do paralelismo.
            System.out.printf("A Thread %s entrou em uma sessão crítica%n", name);
            System.out.printf("%d Threads esperando na fila para executar esse bloco%n", lock.getQueueLength());
            System.out.printf("A Thread %s vai esperar 2s%n", name);
            Thread.sleep(2000);
            System.out.printf("A Thread %s finalizou espera e liberou o lock.%n", name);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // verifica se a thread corrente é quem está segurando o lock
            // pois se tentar unlock() em threads que não estão lock lançara:
            // java.lang.IllegalMonitorStateException
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }


    }
}
