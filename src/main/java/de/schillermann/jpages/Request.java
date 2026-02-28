package de.schillermann.jpages;

public interface Request {
  RequestLine line();

  /**
   * Get a specific header by name.
   * 
   * @param name Name of the header
   * @return The value
   */
  Header header(String name);

  /**
   * Get the request body as a stream.
   * 
   * @return The body
   */
  Input body();
}
