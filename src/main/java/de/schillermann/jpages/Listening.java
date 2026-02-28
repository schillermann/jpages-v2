package de.schillermann.jpages;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

final class Listening implements Scalar<Boolean> {
  private final ServerSocket server;
  private final Page page;

  Listening(ServerSocket skt, Page manifest) {
    this.server = skt;
    this.page = manifest;
  }

  @Override
  public Boolean value() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        // We create the 'Printed' promise for every visitor
        try (Socket client = this.server.accept()) {
          new Printed(
              this.page,
              new ConnectionFromSocket(client)).string();
        }
      } catch (IOException ex) {
        throw new IllegalStateException("Network failure", ex);
      }
    }
    return true;
  }
}
