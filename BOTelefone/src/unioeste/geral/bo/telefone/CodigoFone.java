
package unioeste.geral.bo.telefone;

import java.io.Serializable;

public class CodigoFone implements Serializable{

	private int idCodigoFone;

	private int ddd;

	private int codigoInternacional;

    public int getIdCodigoFone() {
        return idCodigoFone;
    }

    public void setIdCodigoFone(int idCodigoFone) {
        this.idCodigoFone = idCodigoFone;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getCodigoInternacional() {
        return codigoInternacional;
    }

    public void setCodigoInternacional(int codigoInternacional) {
        this.codigoInternacional = codigoInternacional;
    }
        
        

}
