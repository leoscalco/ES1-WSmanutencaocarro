
package unioeste.geral.bo.telefone;

import java.io.Serializable;

public class Fone implements Serializable{

	private Integer nroFone;

	private TipoFone tipoFone;

	private CodigoFone codigoFone;

    public Integer getNroFone() {
        return nroFone;
    }

    public void setNroFone(Integer nroFone) {
        this.nroFone = nroFone;
    }

    public TipoFone getTipoFone() {
        return tipoFone;
    }

    public void setTipoFone(TipoFone tipoFone) {
        this.tipoFone = tipoFone;
    }

    public CodigoFone getCodigoFone() {
        return codigoFone;
    }

    public void setCodigoFone(CodigoFone codigoFone) {
        this.codigoFone = codigoFone;
    }
        
        

}
