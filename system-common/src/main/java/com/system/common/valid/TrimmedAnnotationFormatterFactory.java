package com.system.common.valid;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;


/**
 * @Description
 * @Author 陈葳
 * @Date 2018/11/26 17:30
 * @Version 1.0
 */
public class TrimmedAnnotationFormatterFactory implements AnnotationFormatterFactory<Trimmed> {

    private static final Map<Trimmed.TrimmerType, TrimmerFormatter> TRIMMER_FORMATTER_MAP;

    static {
        Trimmed.TrimmerType[] values = Trimmed.TrimmerType.values();
        Map<Trimmed.TrimmerType, TrimmerFormatter> map = new HashMap<Trimmed.TrimmerType, TrimmerFormatter>(values.length);
        for (Trimmed.TrimmerType type : values) {
            map.put(type, new TrimmerFormatter(type));
        }
        TRIMMER_FORMATTER_MAP = Collections.unmodifiableMap(map);
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1, 1);
        fieldTypes.add(String.class);
        return fieldTypes;
    }

    @Override
    public Parser<?> getParser(Trimmed annotation, Class<?> fieldType) {
        return TRIMMER_FORMATTER_MAP.get(annotation.value());
    }

    @Override
    public Printer<?> getPrinter(Trimmed annotation, Class<?> fieldType) {
        return TRIMMER_FORMATTER_MAP.get(annotation.value());
    }

    private static class TrimmerFormatter implements Formatter<String> {

        private static final Pattern PATTERN_WHITESPACES = Pattern.compile("\\s+");
        private static final Pattern PATTERN_WHITESPACES_WITH_LINE_BREAK = Pattern.compile("\\s*\\n\\s*");
        private static final Pattern PATTERN_WHITESPACES_EXCEPT_LINE_BREAK = Pattern.compile("[\\s&&[^\\n]]+");

        private final Trimmed.TrimmerType type;

        public TrimmerFormatter(Trimmed.TrimmerType type) {
            if (type == null)
                throw new NullPointerException();
            this.type = type;
        }

        @Override
        public String print(String object, Locale locale) {
            return object;
        }

        @Override
        public String parse(String text, Locale locale) throws ParseException {
            text = text.trim();
            switch (type) {
                case ALL_WHITESPACES:
                    return PATTERN_WHITESPACES.matcher(text).replaceAll(" ");
                case EXCEPT_LINE_BREAK:
                    return PATTERN_WHITESPACES_EXCEPT_LINE_BREAK.matcher(
                            PATTERN_WHITESPACES_WITH_LINE_BREAK.matcher(text).replaceAll("\n"))
                            .replaceAll(" ");
                case SIMPLE:
                    return text;
                default:
                    //not possible
                    throw new AssertionError();
            }
        }

    }
}