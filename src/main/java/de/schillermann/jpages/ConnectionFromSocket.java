package de.schillermann.jpages;

import java.net.Socket;
import java.io.IOException;

public final class ConnectionFromSocket implements Connection {
  private final Socket socket;

  public ConnectionFromSocket(Socket s) {
    this.socket = s;
  }

  @Override
  public Request request() {
    try {
      return new RequestFromInput(this.socket.getInputStream());
    } catch (IOException ex) {
      throw new IllegalStateException(
          "Can't reach the input stream of the socket",
          ex);
    }
  }

  @Override
  public Media media() {
    try {
      return new OutputStreamMedia(this.socket.getOutputStream());
    } catch (IOException ex) {
      throw new IllegalStateException(
          "Can't reach the output stream of the socket",
          ex);
    }
  }
}
