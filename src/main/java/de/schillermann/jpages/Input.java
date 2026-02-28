package de.schillermann.jpages;

import java.io.InputStream;
import java.io.IOException;

public interface Input {
  InputStream stream() throws IOException;
}
