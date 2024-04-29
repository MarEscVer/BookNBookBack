package org.reader.low.booknbook.config.security.context;

import org.springframework.core.NamedThreadLocal;
import org.springframework.util.Assert;

public class ThreadLocalBookNBookContextHolderStrategy implements BookNBookStrategy{

    private static final ThreadLocal<BookNBookContext> contextHolder = new NamedThreadLocal<>("booknbook-context");

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public BookNBookContext getContext() {
        BookNBookContext context = contextHolder.get();
        if(context == null) {
            context = createEmptyContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void setContext(BookNBookContext context) {
        Assert.notNull(context, ()->"BookNBookContext no puede ser nulo");
        contextHolder.set(context);
    }
}
