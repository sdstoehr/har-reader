package de.sstoehr.harreader.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sstoehr.harreader.HarReaderMode;

public interface MapperFactory {

    ObjectMapper instance(HarReaderMode mode);

}
