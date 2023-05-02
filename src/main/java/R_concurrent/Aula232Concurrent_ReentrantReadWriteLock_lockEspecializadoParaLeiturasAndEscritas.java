package R_concurrent;

/* Definições sobre ReentrantReadWriteLock:

É uma implementação de um bloqueio de leitura/gravação (read-write lock) que
permite o acesso simultâneo a um recurso para leitura por vários threads, mas
permite que apenas um thread acesse o recurso para escrita.

A classe é chamada de "reentrante" porque um thread que já possui o bloqueio de
leitura/gravação pode reentrar no modo de leitura/gravação novamente sem ficar
bloqueado.

Essa classe é útil em situações em que vários threads precisam ler dados de um
recurso compartilhado, mas apenas um thread deve escrever dados nesse recurso.
Com o uso do ReentrantReadWriteLock, é possível garantir que a integridade dos
dados seja mantida e que a escrita e a leitura ocorram de forma segura.

---> Algumas das principais aplicações e utilidades da classe incluem:

    - Caching: em sistemas de cache de memória ou disco, vários threads podem
    ler dados ao mesmo tempo, enquanto apenas um thread pode escrever dados no
    cache. Isso pode melhorar o desempenho e evitar a leitura redundante de dados.

    - Sistemas de banco de dados: quando vários threads acessam uma base de dados,
    é importante garantir que apenas um thread possa escrever dados para evitar
    conflitos de escrita.

    - Coleções compartilhadas: ao usar coleções compartilhadas como List ou Map,
    várias threads podem ler dados simultaneamente, mas apenas uma thread deve
    escrever dados para evitar erros de concorrência.

    - Threads de background: em aplicativos com várias threads de background que
    precisam compartilhar dados, o ReentrantReadWriteLock pode ser usado para
    controlar o acesso aos dados compartilhados e evitar conflitos.

Em resumo, ReentrantReadWriteLock é útil para controlar o acesso simultâneo a
recursos compartilhados em ambientes de programação multithread. Ela é uma maneira
segura e eficiente de garantir que os dados sejam lidos e gravados de forma segura
e consistente.

*/


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Aula232Concurrent_ReentrantReadWriteLock_lockEspecializadoParaLeiturasAndEscritas {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        MapReadWrite mapReadWrite = new MapReadWrite(rwl);

        Runnable writer = () -> {
            for (int i = 0; i < 10; i++) {
                mapReadWrite.put(String.valueOf(i), String.valueOf(i));
            }
        };

        Runnable reader = () -> {
            if(rwl.isWriteLocked()) {
                System.out.println("WRITE LOCKED!");
            }

            rwl.readLock().lock();
            System.out.println("Finalmente eu consegui o READ Lock!");
            try {
                System.out.println(Thread.currentThread().getName()+" -> "+mapReadWrite.allKeys());
            } finally {
                rwl.readLock().unlock();
            }
        };

        Thread t0 = new Thread(writer);
        Thread t1 = new Thread(reader);
        Thread t2 = new Thread(reader);

        t0.setPriority(Thread.MAX_PRIORITY);
        t0.start();
        t1.start();
        t2.start();

        // Thread-0 obteve o WRITE Lock -> Write começa pois setei Thread.MAX_PRIORITY
        // WRITE LOCKED! -> t1-reader tenta ler porém está WRITE LOCKED
        // WRITE LOCKED! -> t2-reader tenta ler porém está WRITE LOCKED
        // Thread-0 obteve o WRITE Lock -> Se mantém WRITE LOCKED até finalizar o for i < 10
        // Thread-0 obteve o WRITE Lock
        // Thread-0 obteve o WRITE Lock
        // Thread-0 obteve o WRITE Lock
        // Thread-0 obteve o WRITE Lock
        // Thread-0 obteve o WRITE Lock
        // Thread-0 obteve o WRITE Lock
        // Thread-0 obteve o WRITE Lock
        // Thread-0 obteve o WRITE Lock
        // Finalmente eu consegui o READ Lock! -> Finalizado o for i < 10 do WRITE, libera o LOCK para reader:
        // Finalmente eu consegui o READ Lock! -> 2 threads reader então as duas acessa o recurso
        // Thread-1 -> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] -> finalmente elas podem ler e printar as keys do map
        // Thread-2 -> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}

class MapReadWrite {
    private final Map<String, String> map = new LinkedHashMap<>();
    private final ReentrantReadWriteLock rwl;
    MapReadWrite(ReentrantReadWriteLock rwl) {
        this.rwl = rwl;
    }

    // Apenas uma thread pode escrever por vez, ou seja o processo de ESCRITA
    // bloqueia qualquer outra operação de outra thread, ao contrário de LEITURA
    // que libera várias leituras simultâneas.
    public void put(String key, String value) {
        rwl.writeLock().lock();
        try {
            if(rwl.isWriteLocked()) {
                System.out.printf("%s obteve o WRITE Lock%n", Thread.currentThread().getName());
            }
            Thread.sleep(2000);

            map.put(key, value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwl.writeLock().unlock();
        }
    }
    public Set<String> allKeys() {
        rwl.readLock().lock();
        try {
            return map.keySet();
        } finally {
            rwl.readLock().unlock();
        }
    }

}
