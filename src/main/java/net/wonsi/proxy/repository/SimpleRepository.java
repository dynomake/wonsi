package net.wonsi.proxy.repository;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.wonsi.api.mapping.WonsiColumn;
import net.wonsi.api.mapping.WonsiPrimary;
import net.wonsi.api.repository.BaseRepository;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.column.ColumnUtil;
import net.wonsi.util.Condition;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Getter
@RequiredArgsConstructor
public class SimpleRepository<MappingType, Identifier>
        implements BaseRepository<MappingType, Identifier> {


    private final WonsiTable<MappingType> wrappedTable;
    private final String idColumnName;

    @Override
    public Collection<MappingType> findAll() {
        return wrappedTable.select().where(Condition.alwaysTrue()).sync().getAll();
    }

    @Override
    public Optional<MappingType> findByIdentifier(@NonNull Identifier identifier) {
        return wrappedTable.select().where(Condition.is(idColumnName, identifier)).sync().findFirst();
    }

    @Override
    public Collection<MappingType> findByCondition(@NonNull Condition condition) {
        return wrappedTable.select().where(condition).sync().getAll();
    }

    @Override
    public void deleteByIdentifier(@NonNull Identifier identifier) {
        wrappedTable.delete()
                .where(Condition.is(idColumnName, identifier))
                .limit(1)
                .sync();
    }


    @Override
    public void save(@NonNull MappingType mappingType) {
        wrappedTable.insert()
                .updateOnDuplicate()
                .data(map -> {
                    for (Field field : mappingType.getClass().getDeclaredFields()) {
                        WonsiColumn column = field.getAnnotation(WonsiColumn.class);
                        val name = column.name().equals("null_wonsidv") ? field.getName() : column.name();
                        field.trySetAccessible();
                        try {
                            map.put(name, field.get(mappingType));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .sync();
    }
}
