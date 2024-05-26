package assets;

public class TradutorCase {

    public String tradutorSneakCase ( String camelCase ) throws StringIndexOutOfBoundsException {
        String saida = "";
        char c;
        for ( int i = 0; i < camelCase.length(); i++ ) {
            c = camelCase.charAt( i );
            if ( c >= 'A' && c <= 'Z' ) {
                saida += "_" + c;
            }
            else {
                saida += c;
            }
        }
        return saida.toLowerCase();
    }

    public String tradutorCamelCase ( String sneakCase ) throws StringIndexOutOfBoundsException {
        String saida = "";
        sneakCase = sneakCase.toLowerCase();
        char c;
        while ( true ) {
            int indice = sneakCase.indexOf( "_" );
            if ( indice != -1 ) {
                char proximo = sneakCase.charAt( indice + 1 );
                proximo += 'A' - 'a';
                saida += sneakCase.substring( 0, indice ) + proximo;
                sneakCase = sneakCase.substring( indice + 2 );
            }
            else {
                break;
            }
        }
        return saida + sneakCase;
    }

}
