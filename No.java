class No{
    private Documento documento;
    private No proximo;

    //construtor
    public No(Documento documento){
        this.documento = documento;
    }

    //getters e setters
    public Documento getDocumento() {
        return documento;
    }
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    public No getProximo() {
        return proximo;
    }
    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return documento.toString();
    }
}
