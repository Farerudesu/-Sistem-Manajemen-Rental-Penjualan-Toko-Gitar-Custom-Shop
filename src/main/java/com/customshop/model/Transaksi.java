package com.customshop.model;


public abstract class Transaksi {
    protected String kodeTransaksi;
    protected String namaPelanggan;
    protected Gitar gitar;
    protected String tanggalTransaksi;
    protected double totalBiaya;

    public Transaksi(String kodeTransaksi, String namaPelanggan, Gitar gitar, String tanggalTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaPelanggan = namaPelanggan;
        this.gitar = gitar;
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public abstract double hitungTotalBiaya();

    public abstract void cetakNota();

    public String getKodeTransaksi() { return kodeTransaksi; }
    public String getNamaPelanggan() { return namaPelanggan; }
    public Gitar getGitar() { return gitar; }
    public String getTanggalTransaksi() { return tanggalTransaksi; }
    public double getTotalBiaya() { return totalBiaya; }
}
