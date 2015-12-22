package unioeste.manutencao.bo.cliente;

import java.io.Serializable;
import unioeste.geral.bo.pessoa.Pessoa;

public class Cliente extends Pessoa implements Serializable{

	private int idCliente;

	private char tipoPessoa;

	private Pessoa pessoa;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public char getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(char tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

        

}
