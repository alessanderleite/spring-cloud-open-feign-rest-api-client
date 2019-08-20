package br.com.alessanderleite.valueobject;

public class RetornoVO {
	
	private ClienteVO cliente;
	private HistoricoVO historico;
	
	public RetornoVO() {}

	public RetornoVO(ClienteVO cliente, HistoricoVO historico) {
		this.cliente = cliente;
		this.historico = historico;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public HistoricoVO getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoVO historico) {
		this.historico = historico;
	}	
}
