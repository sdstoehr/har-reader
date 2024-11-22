package de.sstoehr.harreader;

import java.io.File;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.sstoehr.harreader.jackson.MapperFactory;
import de.sstoehr.harreader.model.Har;

public class HarReader extends AbstractHarIO {

    private static final HarReaderMode DEFAULT_READER_MODE = HarReaderMode.STRICT;

    public HarReader() {
        super();
    }
    
    public HarReader(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    public Har readFromFile(File har) throws HarReaderException {
        return this.readFromFile(har, DEFAULT_READER_MODE);
    }

    public Har readFromFile(File har, HarReaderMode mode) throws HarReaderException {
        return wrap(mode, mapper -> mapper.readValue(har, Har.class));
    }

    /**
     * Deserialize HAR from the given {@link InputStream}. {@link InputStream} is closed after reading.
     *
     * @param harInputStream {@link InputStream} to read HAR from.
     * @return HAR deserialized from {@link InputStream}
     * @throws HarReaderException if a low-level I/O problem occurs
     */
    public Har readFromInputStream(InputStream harInputStream) throws HarReaderException {
        return this.readFromInputStream(harInputStream, DEFAULT_READER_MODE);
    }

    /**
     * Deserialize HAR from the given {@link InputStream}. {@link InputStream} is closed after reading.
     *
     * @param harInputStream {@link InputStream} to read HAR from.
     * @param mode           Reading mode
     * @return HAR deserialized from {@link InputStream}
     * @throws HarReaderException if a low-level I/O problem occurs
     */
    public Har readFromInputStream(InputStream harInputStream, HarReaderMode mode) throws HarReaderException {
        return wrap(mode, mapper -> mapper.readValue(harInputStream, Har.class));
    }

    public Har readFromString(String har) throws HarReaderException {
        return this.readFromString(har, DEFAULT_READER_MODE);
    }

    public Har readFromString(String har, HarReaderMode mode) throws HarReaderException {
        return wrap(mode, mapper -> mapper.readValue(har, Har.class));
    }

    /**
     * Deserialize HAR from the given byte array.
     *
     * @param bytes Byte array to deserialize HAR from
     * @return HAR deserialized from byte array
     * @throws HarReaderException if a low-level I/O problem occurs
     */
    public Har readFromBytes(byte[] bytes) throws HarReaderException {
        return this.readFromBytes(bytes, DEFAULT_READER_MODE);
    }

    /**
     * Deserialize HAR from the given byte array.
     *
     * @param bytes Byte array to deserialize HAR from
     * @param mode  Reading mode
     * @return HAR deserialized from byte array
     * @throws HarReaderException if a low-level I/O problem occurs
     */
    public Har readFromBytes(byte[] bytes, HarReaderMode mode) throws HarReaderException {
        return wrap(mode, mapper -> mapper.readValue(bytes, Har.class));
    }

    private <T> T wrap(HarReaderMode mode, IOFunction<ObjectMapper, T> consumer) throws HarReaderException {
        return wrap(getMapperFactory().instance(mode), consumer, HarReaderException::new);
    }
}
