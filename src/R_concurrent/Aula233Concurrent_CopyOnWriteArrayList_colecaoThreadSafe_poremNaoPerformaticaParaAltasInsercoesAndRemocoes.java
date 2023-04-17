package R_concurrent;

// Coleção Thread-Safe que utiliza array nativo em LowLevel, porém toda vez que
// modifica ela com remoções e inserções é re-alocado e re-criado o array
// porisso a baixa performance com altas operações de insert e remove!

/* Definições sobre CopyOnWriteArrayList:

É uma implementação de uma lista segura para uso em ambientes multithread, onde
a maioria das operações de escrita (adicionar, modificar e remover elementos)
são feitas em cópias da lista original, mantendo a lista original intocada.
Essa classe é especialmente útil em situações em que há muitas leituras e poucas
gravações.

A principal vantagem da CopyOnWriteArrayList é que ela é thread-safe (segura para
uso em ambientes de múltiplos threads) sem a necessidade de sincronização explícita.
A lista pode ser lida por vários threads simultaneamente, sem a preocupação de que
os dados sejam modificados por outros threads.

---> Algumas das principais aplicações e utilidades da classe incluem:

    - Listeners: em situações em que há muitos listeners registrados para receber
    notificações de eventos, a CopyOnWriteArrayList pode ser usada para armazenar
    esses listeners e permitir que vários threads os acessem simultaneamente, sem
    a preocupação de que um thread esteja adicionando ou removendo listeners
    enquanto outro thread está percorrendo a lista.

    - Cache: em sistemas de cache, a CopyOnWriteArrayList pode ser usada para
    armazenar cache de dados e permitir que vários threads leiam dados de cache
    simultaneamente. O cache pode ser atualizado periodicamente em uma thread
    separada, sem a preocupação de que os dados estejam sendo acessados por outros
    threads durante a atualização.

    - Listas de tarefas: em sistemas que usam listas de tarefas compartilhadas, a
    CopyOnWriteArrayList pode ser usada para armazenar tarefas a serem executadas.
    Vários threads podem acessar a lista simultaneamente para obter a próxima tarefa
    a ser executada, sem a preocupação de que outros threads estejam adicionando ou
    removendo tarefas.

    - Sistemas de registro: em sistemas que precisam registrar eventos e informações,
    a CopyOnWriteArrayList pode ser usada para armazenar essas informações e permitir
    que vários threads acessem a lista simultaneamente, sem a preocupação de que um
    thread esteja adicionando ou removendo informações enquanto outro thread está
    lendo a lista.

Em resumo, a CopyOnWriteArrayList é útil para situações em que há muitas leituras e
poucas gravações em uma lista compartilhada, pois ela fornece uma lista segura e
eficiente para uso em ambientes multithread. A lista pode ser lida por vários threads
simultaneamente, sem a necessidade de sincronização explícita, e permite que os dados
sejam acessados de forma segura e consistente.

*/

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Aula233Concurrent_CopyOnWriteArrayList_colecaoThreadSafe_poremNaoPerformaticaParaAltasInsercoesAndRemocoes {
    public static void main(String[] args) {

        // Vale uma observação:
        // A CopyOnWriteArrayList é imutável pois sempre re-cria o novo array,
        // Porém deve-se atentar também com os valores da coleção pois os mesmos
        // podem sofrer alterações e essas podem conflitar em concorrência!
        // Então devemos também garantir a imutabilidade dos elementos referenciados
        // nesta coleção!
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 2000; i++) {
            list.add(i);
        }
        Runnable runnableIterator = () -> {
            // Obs: O Iterator mantém uma cópia do array original, porisso
            // mesmo removendo durante os 2 segundos de sleep dele com
            // outra thread, ele ainda printa corretamente de 0 a 1999
            // Comportamento vai ser o mesmo, mesmo trocando o
            // CopyOnWriteArrayList por ArrayList comum!
            Iterator<Integer> iterator = list.iterator();
            try {
                TimeUnit.SECONDS.sleep(2);
                iterator.forEachRemaining(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable runnableRemover = () -> {
            for (int i = 0; i < 500; i++) {
                System.out.printf("%s removed %d%n", Thread.currentThread().getName(), i);
                list.remove(i);
            }
        };
        new Thread(runnableIterator).start();
        new Thread(runnableIterator).start();
        new Thread(runnableRemover).start();
    }
}
