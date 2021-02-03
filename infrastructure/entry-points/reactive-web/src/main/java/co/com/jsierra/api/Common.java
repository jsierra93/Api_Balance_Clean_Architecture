package co.com.jsierra.api;

import co.com.jsierra.model.commons.ErrorHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
public class Common {


    public Common(){
        throw new IllegalStateException("Utility Class");
    }

    public static ErrorHeader validateObject(Object object){
        ValidatorFactory factory;
        factory = Validation.buildDefaultValidatorFactory();
        Validator validator;
        validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            String message = "";
            for (ConstraintViolation<Object> violation : constraintViolations) {
                message = violation.getMessage();
                log.info("Headers Error => {}", message);
            }
            return ErrorHeader.builder().code("Error").message(message).build();
        }else{
            return null;
        }
    }


    public static <T> Mono<ServerResponse> responseErrorMessage(ErrorHeader error) {
        return ServerResponse
                .status(400)
                .body(Mono.just(error), ErrorHeader.class);
    }
}
