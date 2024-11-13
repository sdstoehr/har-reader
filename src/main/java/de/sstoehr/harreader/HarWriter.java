package de.sstoehr.harreader;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.sstoehr.harreader.jackson.MapperFactory;
import de.sstoehr.harreader.model.Har;

public final class HarWriter extends AbstractHarIO {

    public HarWriter() {
        super();
    }

    public HarWriter(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    /**
     * Serialize HAR as a byte array. It's functionally equivalent to calling {@link #writeTo(OutputStream)} with
     * {@link java.io.ByteArrayOutputStream} and getting bytes, but more efficient. Encoding used will be UTF-8.
     * @return Serialized HAR as a byte array
     * @throws IOException if a low-level I/O problem occurs
     */
    public byte[] writeAsBytes(Har har) throws HarWriterException {
        return wrap(m -> m.writeValueAsBytes(har));
    }

    public void writeTo(Writer writer, Har har) throws HarWriterException {
        wrap(m -> {
            m.writeValue(writer, har);
            return null;
        });
    }

    public void writeTo(OutputStream os, Har har) throws HarWriterException {
        wrap(m -> {
            m.writeValue(os, har);
            return null;
        });
    }

    public void writeTo(File file, Har har) throws HarWriterException {
        wrap(m -> {
            m.writeValue(file, har);
            return null;
        });
    }

    private <T> T wrap(IOFunction<ObjectMapper, T> consumer) throws HarWriterException {
        ObjectMapper mapper = getMapperFactory().instance();
        try {
            return consumer.apply(mapper);
        } catch(IOException thrown) {
            throw new HarWriterException(thrown);
        }
    }

    @FunctionalInterface
    private interface IOFunction<T, R> {

        R apply(T object) throws IOException;

    }

}
