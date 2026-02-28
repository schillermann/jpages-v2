package de.schillermann.jpages;

public final class ConnectionWithHeader implements Connection {
  private final Connection origin;
  private final String name;
  private final String value;

  public ConnectionWithHeader(Connection cnct, String key, String val) {
    this.origin = cnct;
    this.name = key;
    this.value = val;
  }

  @Override
  public Request request() {
    return this.origin.request();
  }

  @Override
  public Media media() {
    return new WithHeader(
        this.origin.media(),
        this.name,
        this.value);
  }
}
