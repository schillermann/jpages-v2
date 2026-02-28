package de.schillermann.jpages;

public final class PageWithStatus implements Page {
  private final Page origin;
  private final int code;
  private final String reason;

  public PageWithStatus(final Page page, final int status, final String msg) {
    this.origin = page;
    this.code = status;
    this.reason = msg;
  }

  @Override
  public void print(final Connection c) {
    this.origin.print(new ConnectionWithStatus(c, this.code, this.reason));
  }
}
