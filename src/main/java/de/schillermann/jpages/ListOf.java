package de.schillermann.jpages;

import java.util.AbstractList;

public final class ListOf<T> extends AbstractList<T> {
  private final T[] array;

  @SafeVarargs
  public ListOf(T... items) {
    super();
    this.array = items;
  }

  @Override
  public T get(int index) {
    return this.array[index];
  }

  @Override
  public int size() {
    return this.array.length;
  }
}
