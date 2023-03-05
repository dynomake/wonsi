package net.wonsi.api.request;

import net.wonsi.api.result.ExecutedReturningAction;

import java.util.concurrent.CompletableFuture;

public interface ReturningRequest<T> {

    CompletableFuture<ExecutedReturningAction<T>> async();
    ExecutedReturningAction<T> sync();
}
