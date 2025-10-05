package de.sstoehr.harreader.jackson;

import de.sstoehr.harreader.HarReaderMode;
import tools.jackson.databind.ObjectMapper;

public interface MapperFactory {

    ObjectMapper instance(HarReaderMode mode);

    ObjectMapper instance();

}
