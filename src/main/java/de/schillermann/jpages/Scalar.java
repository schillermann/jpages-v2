package de.schillermann.jpages;

public interface Scalar<T> {
  /**
   * Get the value.
   * 
   * @return The value
   * @throws Exception If fails
   */
  T value() throws Exception;
}
