package co.com.jsierra.model.commons;


public class Constant {

    //Nombres Headers
    public static final String CHANNEL= "Channel";
    public static final String DATE= "Date";
    public static final String DATETIME= "DateTime";
    public static final String IP= "Ip";
    public static final String CLIENT_ID= "ClientID";
    public static final String CLIENT_TYPE= "ClientType";


    //Mensajes para la cabecera Channel
    public static final String MESSAGE_CHANNEL_NOT_BLANK = "La cabecera 'Channel' Es requerida.";
    public static final String MESSAGE_CHANNEL_ALPHABETICAL_REQUIRED
            = "La cabecera 'Channel' Solo acepta letras mayusculas.";
    public static final String MESSAGE_CHANNEL_LENGTH_REQUIRED
            = "La cabecera 'Channel' Debe cumplir con la longitud requerida entre 1 y 3 caracteres.";

    //Mensajes para la cabecera Date
    public static final String MESSAGE_DATE_NOT_BLANK= "La cabecera 'Date' Es requerida.";
    public static final String MESSAGE_DATE_FORMAT_REQUIRED
            = "La cabecera 'Date' Debe cumplir con el formato 'yyyy/MM/dd'";
    public static final String MESSAGE_DATE_RANGE_REQUIRED
            = "La cabecera 'Date' Debe cumplir con un rango valido para las fechas.";

    //Mensajes para la cabecera DateTime
    public static final String MESSAGE_DATETIME_NOT_BLANK= "La cabecera 'DateTime' Es requerida.";
    public static final String MESSAGE_DATETIME_FORMAT_REQUIRED
            = "La cabecera 'DateTime' Debe cumplir con el formato HH:mm:ss";
    public static final String MESSAGE_DATETIME_RANGE_REQUIRED
            = "La cabecera 'DateTime' Debe cumplir con un rango valido para las horas, minutos o segundos.";

    //Mensajes para la cabecera Ip
    public static final String MESSAGE_IP_NOT_BLANK= "La cabecera 'Ip' Es requerida.";
    public static final String MESSAGE_IP_FORMAT_REQUIRED
            = "La cabecera 'Ip' Debe cumplir con un valor en el siguiente rango 1.1.1.1 - 255.255.255.255";

    //Mensajes para la cabecera ClientId
    public static final String MESSAGE_CLIENT_ID_NOT_BLANK = "La cabecera 'ClientID' Es requerida.";

    //Mensajes para la cabecera ClientType
    public static final String MESSAGE_CLIENT_TYPE_NOT_BLANK = "La cabecera 'ClientType' Es requerida.";


    public Constant() {
        throw new IllegalStateException("Utility class");
    }

}