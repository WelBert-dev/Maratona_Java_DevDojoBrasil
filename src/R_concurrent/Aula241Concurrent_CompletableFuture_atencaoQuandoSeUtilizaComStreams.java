package R_concurrent;

// Quando se utiliza paralelismo com CompletableFuture em Streams podemos ter os
// mesmos problemas sobre sincronismo se não separar as operações de obtenção do
// CompletableFuture em referência e da execução do método `get()` ou `join()`;
// Obs: Exemplo presente aqui é o mesmo da aula anterior 240.

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aula241Concurrent_CompletableFuture_atencaoQuandoSeUtilizaComStreams {
    public static void main(String[] args) {
        List<String> storesList = List.of("Store 1", "Store 2", "Store 3", "Store 4");
        long start; // time para calcular ms da execução
        long end;

        // ------------------------- [JEITO ERRADO] ---------------------------
        // Jeito errado: Aqui executamos tudo de uma só vez, ai está o erro!

        start = System.currentTimeMillis();
        List<Double> notAsyncResult = storesList.stream()
                .map(StoreService::getPricesAsyncWithCompletableFuture)
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.printf("Time passed to searchPricesSync with Not Async: %dms%n", end - start);
        // Time passed to searchPricesSync with Not Async: 8075ms

        // ------------------------- [JEITO CERTO] ----------------------------
        // Jeito correto: Quebrar a execução em 2 processos, um para transformar o fluxo
        // stream em um fluxo de stream CompletableFuture (Equivalente a primeiro referênciar
        // em memória para depois executar o `get()` ou `join()` em outro stream.
        start = System.currentTimeMillis();
        List<CompletableFuture<Double>> collect = storesList.stream()
                .map(StoreService::getPricesAsyncWithCompletableFuture)
                .collect(Collectors.toList());

        List<Double> pricesWithAsyncList = collect.stream()
                .map(CompletableFuture::join).collect(Collectors.toList());

        end = System.currentTimeMillis();
        System.out.printf("Time passed to searchPricesSync with Not Async: %dms%n", end - start);
        // Time passed to searchPricesSync with Not Async: 4036ms

    }
}
