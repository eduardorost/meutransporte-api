package br.com.meutransporte.enums;

public enum ModuloType {
    EMPRESA("ROLE_EMPRESA"), USUARIO("ROLE_USUARIO"), ADMIN("ROLE_ADMIN");
    private String modulo;

    ModuloType(String modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return this.modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
}
