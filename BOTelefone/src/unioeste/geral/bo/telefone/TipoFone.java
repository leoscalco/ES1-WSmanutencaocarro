
package unioeste.geral.bo.telefone;

import java.io.Serializable;

public class TipoFone implements Serializable{

	private Integer idTipoFone;

	private String nomeTipoFone;

    public Integer getIdTipoFone() {
        return idTipoFone;
    }

    public void setIdTipoFone(Integer idTipoFone) {
        this.idTipoFone = idTipoFone;
    }

    public String getNomeTipoFone() {
        return nomeTipoFone;
    }

    public void setNomeTipoFone(String nomeTipoFone) {
        this.nomeTipoFone = nomeTipoFone;
    }
        
        

}
