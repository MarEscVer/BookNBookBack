package org.reader.low.booknbook.config.security.context;

public class BookNBookHolder {

    public static final String SYSTEM_PROPERTY= "booknbook.context.strategy";

    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);

    private static BookNBookStrategy strategy;

    static {
        initialize();
    }

    private BookNBookHolder() {}

    private static void initialize() {
        strategy = new ThreadLocalBookNBookContextHolderStrategy();
    }

    public static BookNBookContext getCurrentContext() {
        return strategy.getContext();
    }

    public static void  setCurrentContext(BookNBookContext context) {
        strategy.setContext(context);
    }

    public static void clear() {
        strategy.clearContext();
    }

    public static void setStrategyName(String name) {
        BookNBookHolder.strategyName = name;
        initialize();
    }

    public static  BookNBookStrategy getBookNBookStrategy() {
        return strategy;
    }

    public static String getStrategyName() {
        return strategyName;
    }
}
