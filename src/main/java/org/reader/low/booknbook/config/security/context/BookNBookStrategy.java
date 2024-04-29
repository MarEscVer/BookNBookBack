package org.reader.low.booknbook.config.security.context;

public interface BookNBookStrategy {

    void clearContext();

    BookNBookContext getContext();

    void setContext(BookNBookContext context);

    default BookNBookContext createEmptyContext() {
        return BookNBookContext.builder().build();
    }
}
