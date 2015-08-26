package de.sstoehr.harreader.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import de.sstoehr.harreader.HarReaderMode;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Jackson date parser is poor at parsing dates, especially given the date formats used in HTTP is very
 * forgiving. This class contains few more parsing formats to handle that. It may still need some work
 * before it can parse all dates though.
 */
public class HttpDateDeserializer extends DateDeserializers.DateDeserializer {

  private HarReaderMode harReaderMode;

  protected final static String[] ALL_DATE_FORMATS = {
      "EEE, dd MMM yyyy HH:mm:ss 'GMT'", // Tue, 25 Aug 2015 16:01:35 GMT
      "EEEEEE, dd-MMM-yy HH:mm:ss 'GMT'", // Tuesday, 25-Aug-15 16:01:35 GMT
      "EEE MMMM dd HH:mm:ss yyyy", // Tue August 25 16:01:35 2015
      "EEE',' dd-MMM-YYYY HH:mm:ss 'GMT" // Tue, 25-Aug-2015 16:01:35 GMT
  };

  protected final static DateTimeFormatter[] DATE_FORMATTERS;

  static {
    DATE_FORMATTERS = new DateTimeFormatter[ALL_DATE_FORMATS.length];
    for (int i = 0; i < ALL_DATE_FORMATS.length; i++) {
      DATE_FORMATTERS[i] = withLocalTZ(ALL_DATE_FORMATS[i]);
    }
  }

  public HttpDateDeserializer(HarReaderMode harReaderMode) {
    this.harReaderMode = harReaderMode;
  }

  protected static DateTimeFormatter withLocalTZ(String dateFormat) {
    return DateTimeFormat
        .forPattern(dateFormat)
        .withLocale(Locale.US)
        .withZone(DateTimeZone.UTC);
  }

  @Override
  public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws java.io.IOException {
    if (jp.getCurrentToken().equals(JsonToken.VALUE_STRING)) {
      String dateStr = jp.getText().trim();
      try {
        return super.deserialize(jp, ctxt);
      } catch (IOException defaultTimeParserException) {
        for (DateTimeFormatter dateFormat : DATE_FORMATTERS) {
          try {
            return dateFormat.parseDateTime(dateStr).toDate();
          } catch (Exception e) {
            // ignore, as we are iterating through various date formats,
            // throw exception once we are done trying with all known formats
          }
        }

        if (harReaderMode == HarReaderMode.LAX) {
          return getEmptyValue();
        } else {
          throw ctxt.weirdStringException(dateStr, Date.class, "Tried default date formats of jackson " +
              "library, as well as formats: " + Arrays.asList(ALL_DATE_FORMATS).toString());
        }
      }
    }
    throw ctxt.wrongTokenException(jp, JsonToken.VALUE_STRING, "Failed to parse Date value");
  }
}
