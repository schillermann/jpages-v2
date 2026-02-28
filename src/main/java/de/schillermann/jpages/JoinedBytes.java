package de.schillermann.jpages;

public final class JoinedBytes implements Bytes {
  private final byte[] head;
  private final byte[] tail;

  public JoinedBytes(final byte[] first, final byte[] second) {
    this.head = first;
    this.tail = second;
  }

  @Override
  public byte[] array() {
    final byte[] result = new byte[this.head.length + this.tail.length];
    System.arraycopy(this.head, 0, result, 0, this.head.length);
    System.arraycopy(this.tail, 0, result, this.head.length, this.tail.length);
    return result;
  }
}
