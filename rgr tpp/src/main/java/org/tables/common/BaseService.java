package org.tables.common;

import java.util.List;

public abstract class BaseService<T> {

    public abstract T get(List<String> columnNames, List<String> columnValues);

    public abstract void insert(List<String> columnNames, List<String> columnValues);

    public abstract void update(List<String> columnNames, List<String> columnValues);

    public abstract void delete(List<String> columnNames, List<String> columnValues);

    public abstract String show();
}