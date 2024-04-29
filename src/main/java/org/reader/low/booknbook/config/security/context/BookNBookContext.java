package org.reader.low.booknbook.config.security.context;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Builder(toBuilder = true)
@JsonDeserialize(builder = BookNBookContext.BookNBookContextBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class BookNBookContext implements Serializable {

    private transient static final String HEADER_KEY = "headers";

    private transient static final String CONTACT_POINT_KEY = "booknbookPoint";

    private transient static final String BOOKNBOOK_INFO_KEY = "booknbookInfo";

    private transient static final String CUSTOM_ACTIVITY_KEY = "customActivityLog";

    private static final long serialVersionUID = -1;

    private Map<String, Object> booknbookDict;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BookNBookContextBuilder {

        private Map<String, Object> booknbookDict = new ConcurrentHashMap<>();

        public BookNBookContextBuilder() {
            booknbookDict.put(CUSTOM_ACTIVITY_KEY, new ConcurrentHashMap<>());
        }

        public BookNBookContextBuilder contactPoint(BooknBookPoint contactPoint) {
            Assert.notNull(contactPoint, ()->"BooknBookPoint no puede ser nulo");
            addValue(CONTACT_POINT_KEY, contactPoint);
            return this;
        }

        public BookNBookContextBuilder info(BooknBookInfo info) {
            Assert.notNull(info, ()->"BooknBookInfo no puede ser nulo");
            addValue(BOOKNBOOK_INFO_KEY, info);
            return this;
        }

        public BookNBookContextBuilder headers(Map<String, String> headers) {
            Assert.notNull(headers, ()->"headers no puede ser nulo");
            addValue(HEADER_KEY, new ConcurrentHashMap<>(headers));
            return this;
        }

        public BookNBookContextBuilder header(String key, String value) {
            Assert.notNull(key, ()->"key no puede ser nulo");
            Assert.notNull(value, ()->"value no puede ser nulo");
            if(!booknbookDict.containsKey(HEADER_KEY)) {
                addValue(HEADER_KEY, new ConcurrentHashMap<String, String>());
            }
            ((Map<String, String>)booknbookDict.get(HEADER_KEY)).put(key, value);
            return this;
        }

        public BookNBookContextBuilder customizeActivityLog(String key, String log) {
            Assert.notNull(key, ()->"key no puede ser nulo");
            Assert.notNull(log, ()->"log no puede ser nulo");
            ((Map<String, String>)booknbookDict.get(CUSTOM_ACTIVITY_KEY)).put(key, log);
            return this;
        }


        private void addValue(String key, Object value) {
            booknbookDict.put(key, value);
        }
    }

    public BooknBookPoint getBooknBookPoint() {
        return (BooknBookPoint) get(CONTACT_POINT_KEY);
    }

    public BooknBookInfo getBooknBookInfo() {
        return (BooknBookInfo) get(BOOKNBOOK_INFO_KEY);
    }

    public Map<String, String> getBooknBookHeaders() {
        return (Map<String, String>) get(HEADER_KEY);
    }

    public Map<String, String> getBooknBookActivityLog() {
        return (Map<String, String>) get(CUSTOM_ACTIVITY_KEY);
    }

    public Object get(String key) {
        return booknbookDict.get(key);
    }

    public void put(String key, Object value) {
        booknbookDict.put(key, value);
    }

    public boolean containsKey(String key) {
        return booknbookDict.containsKey(key);
    }
}
