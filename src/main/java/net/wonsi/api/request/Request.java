package net.wonsi.api.request;

import java.util.concurrent.CompletableFuture;

public interface Request {

    CompletableFuture<Void> async();
    void sync();

}
