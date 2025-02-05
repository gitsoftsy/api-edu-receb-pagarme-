package br.com.softsy.pagarme.response;

public class EmailResponse {
    private boolean encontrado;
    private Long id;
    private String tabela;
    private String mensagem;

    public EmailResponse(boolean encontrado, Long id, String tabela, String mensagem) {
        this.encontrado = encontrado;
        this.id = id;
        this.tabela = tabela;
        this.mensagem = mensagem;
    }

    // Getters e Setters
    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabela() {
        return tabela; 
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
