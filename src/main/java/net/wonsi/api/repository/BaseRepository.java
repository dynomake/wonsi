package net.wonsi.api.repository;

import lombok.NonNull;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.util.Condition;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface BaseRepository<MappingType, Identifier> {

    Collection<MappingType> findAll();
    Optional<MappingType> findByIdentifier(@NonNull Identifier identifier);
    Collection<MappingType> findByCondition(@NonNull Condition condition);
    CompletableFuture<Void> deleteByIdentifier(@NonNull Identifier identifier);
    CompletableFuture<Void> save(@NonNull MappingType mappingType);
    WonsiTable<MappingType> getWrappedTable();

}
