package de.schillermann.jpages;

public final class Trimmed implements Text {
  private final Object origin;

  public Trimmed(final Object text) {
    this.origin = text;
  }

  @Override
  public String string() {
    return this.origin.toString().trim();
  }
}
