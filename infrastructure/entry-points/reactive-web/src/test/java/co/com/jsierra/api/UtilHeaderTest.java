package co.com.jsierra.api;

import co.com.jsierra.model.commons.Header;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.lang.reflect.InvocationTargetException;

@RunWith(MockitoJUnitRunner.class)
public class UtilHeaderTest {

    @Mock
    ServerRequest serverRequest;

    MockServerHttpRequest request;
    MockServerWebExchange exchange;

    //Headers
    static final String CHANNEL = "Channel";
    static final String DATE = "Date";
    static final String DATE_TIME = "DateTime";
    static final String IP = "Ip";
    static final String CLIENTID = "ClientId";
    static final String CLIENTTYPE = "ClientType";

    static final String VALUE_CHANNEL = "APP";
    static final String VALUE_DATE = "2019/12/12";
    static final String VALUE_DATE_TIME = "19:00:00";
    static final String VALUE_IP = "1.1.1.1";
    static final String VALUE_CLIENTID = "1234556788";
    static final String VALUE_CLIENTTYPE = "CC";

    @Before
    public void init(){
        request = MockServerHttpRequest.post("http://demo.com")
                .header("Content-Type", "application/json")
                .header(CHANNEL, VALUE_CHANNEL)
                .header(DATE, VALUE_DATE)
                .header(DATE_TIME, VALUE_DATE_TIME)
                .header(IP, VALUE_IP)
                .header(CLIENTID, VALUE_CLIENTID)
                .header(CLIENTTYPE, VALUE_CLIENTTYPE)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        exchange = MockServerWebExchange.from(request);
    }
    @Test(expected = IllegalStateException.class)
    public void UtilHeaderConstructor() {
        UtilHeader utilHeader = new UtilHeader();
    }

    @Test
    public void getHeaders() {
        Header headerExpected = Header.builder()
                .channel(VALUE_CHANNEL)
                .date(VALUE_DATE)
                .dateTime(VALUE_DATE_TIME)
                .ip(VALUE_IP)
                .clientId(VALUE_CLIENTID)
                .clientType(VALUE_CLIENTTYPE)
                .build();

        ServerRequest serverRequest =
                ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());

        Header headers = (Header) UtilHeader.getHeaders(serverRequest, Header.class);
        Assert.assertNotNull(headers);
        Assert.assertEquals(headers, headerExpected);
    }

    @Test(expected = NullPointerException.class)
    public void getHeadersNull() {
        Header headers = (Header) UtilHeader.getHeaders(serverRequest, Header.class);
    }

    @Test
    public void getHeadersErrors() {

        MockServerWebExchange exchangeLocal;

        MockServerHttpRequest requestLocal = MockServerHttpRequest.post("http://demo.com")
                .header("Content-Type", "application/json")
                .build();

        exchangeLocal = MockServerWebExchange.from(requestLocal);
        ServerRequest serverRequest =
                ServerRequest.create(exchangeLocal, HandlerStrategies.withDefaults().messageReaders());

        Header headers = (Header) UtilHeader.getHeaders(serverRequest, Header.class);
    }


    @Test(expected = NullPointerException.class)
    public void buildHeaderNullPointer() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        UtilHeader.buildHeader(serverRequest, Header.class);
    }

    @Test
    public void buildHeaderSuccess() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ServerRequest serverRequest =
                ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());

        UtilHeader.buildHeader(serverRequest, Header.class);
    }
}