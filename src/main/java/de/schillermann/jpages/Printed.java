package de.schillermann.jpages;

public final class Printed implements Text {
  private final Page page;
  private final Connection connection;

  public Printed(Page pg, Connection c) {
    this.page = pg;
    this.connection = c;
  }

  @Override
  public String string() {
    // Here, the 'Noun' becomes an 'Action'
    this.page.print(this.connection);
    return "Success"; // Or a more meaningful status
  }
}
