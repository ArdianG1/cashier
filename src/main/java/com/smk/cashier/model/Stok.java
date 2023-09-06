package com.smk.cashier.model;

public class Stok extends Model {
    private int id;
    private String kodeBarang;
    private int stokBarang;

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    @Override
    public String toString() {
        return "Stok{" +
                "id=" + id +
                ", kodeBarang='" + kodeBarang + '\'' +
                ", stokBarang=" + stokBarang +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                ", createdBy='" + createdBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
