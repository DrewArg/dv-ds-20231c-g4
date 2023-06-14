package ar.edu.davinci.dvds20231cg4.mapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ar.edu.davinci.dvds20231cg4.Constantes;
public class DateMapper {
    public String asString(Date date) {
        return date != null ? new SimpleDateFormat( Constantes.FORMATO_FECHA )
                .format( date ) : null;
    }
    public Date asDate(String date) {
        try {
            return date != null ? new SimpleDateFormat( Constantes.FORMATO_FECHA )
                    .parse( date ) : null;
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
    }
}
