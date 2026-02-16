package de.schillermann.jpages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonPageTest {
  @Test
  void printsJsonContentTypeHeader() {
    final HttpMediaFake media = new HttpMediaFake();
    final Page page = new PageWithType(
        new SimplePage("{ \"name\": \"Mario\" }"), "application/json");

    page.print(media);

    assertEquals("application/json", media.header("Content-Type"));
    assertEquals("19", media.header("Content-Length"));
    assertEquals("{ \"name\": \"Mario\" }", media.body());
  }
}
