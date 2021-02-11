package co.com.jsierra.api;

import co.com.jsierra.model.commons.ErrorHeader;
import co.com.jsierra.model.commons.Header;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;


public class CommonTest {
    @Autowired
    Common common;

    @Test(expected = IllegalStateException.class)
   public void CommonConstructor() {
        Common common = new Common();
    }

    @Test
    @DisplayName("Validate object Header")
    public void validateObject() {
        assertNotNull(Common.validateObject(new Header()));
    }

    @Test
    public void responseErrorMessage() {
        ErrorHeader errorHeader = ErrorHeader.builder().message("Error").code("123").build();

        StepVerifier.create(common.responseErrorMessage(errorHeader))
                .assertNext(Assert::assertNotNull)
                .expectComplete();
    }
}