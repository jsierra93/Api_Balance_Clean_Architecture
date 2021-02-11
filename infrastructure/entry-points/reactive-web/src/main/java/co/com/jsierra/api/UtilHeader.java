package co.com.jsierra.api;


import co.com.jsierra.model.commons.HeaderName;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class UtilHeader {

    public UtilHeader() {
        throw new IllegalStateException("Utility Class");
    }

    public static Object getHeaders(ServerRequest request, Class<?> spec) {
        try {
            return buildHeader(request, spec);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            return null;
        }
    }

    /**
     * Metodo para construir objeto Header
     *
     * @param
     * @return
     */
    public static Object buildHeader(ServerRequest request, Class<?> spec) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        HttpHeaders httpHeaders;
        httpHeaders = request.headers().asHttpHeaders();

        Object object = spec.getDeclaredConstructor().newInstance();

        for (Field f : spec.getFields()) {

            if (f.get(object) == null) {

                String name = httpHeaders.getFirst(f.isAnnotationPresent(HeaderName.class) ?
                        f.getAnnotation(HeaderName.class).value() : f.getName());
                f.set(object, name);
            }
        }

        return object;

    }
}
