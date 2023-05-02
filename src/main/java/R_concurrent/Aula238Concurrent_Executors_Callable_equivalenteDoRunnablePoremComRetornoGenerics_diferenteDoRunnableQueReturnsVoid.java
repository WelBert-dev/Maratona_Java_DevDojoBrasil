package R_concurrent;

// Interface com mesmo contexto do Runnable, porém com retorno genérics <T>
// Diferente do Runnable que retona void.
// Utilizamos ele em conjunto dos Executors.

// Obs: Após executado, o retorno será um Future<T> ou seja, a resposta só será
// reculperada no futuro, pois estamos executando em paralelo, o método .get()
// desse future faz a current thread (main) ficar wait travada até que finalize
// a task Callable em paralelo e finalmente retorne algo.
// Também é possível passar um tempo para o get() em que a current thread ficará
// esperando (wait) e casó ultrapasse ele, ela destrava e segue a execução.

import java.util.concurrent.*;

public class Aula238Concurrent_Executors_Callable_equivalenteDoRunnablePoremComRetornoGenerics_diferenteDoRunnableQueReturnsVoid {
    public static void main(String[] args) {
        RandomNumberCallable randomNumberCallable = new RandomNumberCallable();
        // definimos 2 threads porém só executamos 1 submit, então apenas 1 será criada
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> future = executorService.submit(randomNumberCallable);
        try {
            // A Thread current (main) vai ficar wait até finalizar a execução do Callable em pararelo:
            // Ou seja, o programa vai ficar travado aqui até finalizar a task Callable em pararelo.
            String resultOfCallable = future.get();
            System.out.println(resultOfCallable);

            // Obs: Lança duas exceptions
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

class RandomNumberCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        // Math.random() -> é sincronizado, ou seja block quando a thread acessa
        int num = ThreadLocalRandom.current().nextInt(); // Não é sincronizado, não block quando a thread acessa!
        if (num > 20) num = 20;
        for (int i = 0; i < num; i++) {
            System.out.printf("%s executando uma tarefa Callable<T> que returns valor, diferente do Runnable que returns void%n", Thread.currentThread().getName());
        }

        return String.format("%n%s finished and the random number generated is %d", Thread.currentThread().getName(), num);
    }
}
