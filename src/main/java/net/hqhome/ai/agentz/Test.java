package net.hqhome.ai.agentz;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class Test {
    public static void main(String[] args) {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            log.info("-----> called async");
            return null;
        });
        int i = 0;
        while (i < 1000) {
            log.info("called sync");
            i++;
        }

        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            log.info("in executor" + Thread.currentThread().getName());
        });
        log.info("out executor" + Thread.currentThread().getName());
    }
    class Ta extends Thread {

    }

    class Tb extends Thread {

    }
}
