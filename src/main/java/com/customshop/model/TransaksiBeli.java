package com.customshop.model;


public class TransaksiBeli extends Transaksi {
    private double diskonPersen;
    private boolean includeHardcase;
    private int masaGaransiBulan;

    public TransaksiBeli(String kodeTransaksi, String namaPelanggan, Gitar gitar, String tanggalTransaksi, 
                         double diskonPersen, boolean includeHardcase, int masaGaransiBulan) {
        super(kodeTransaksi, namaPelanggan, gitar, tanggalTransaksi);
        this.diskonPersen = diskonPersen;
        this.includeHardcase = includeHardcase;
        this.masaGaransiBulan = masaGaransiBulan;
        this.totalBiaya = hitungTotalBiaya();
    }

    @Override
    public double hitungTotalBiaya() {
        double hargaDasar = gitar.getHargaBeli();
        double potongan = hargaDasar * (diskonPersen / 100.0);
        double biayaTambahan = includeHardcase ? 750000 : 0;
        return (hargaDasar - potongan) + biayaTambahan;
    }

    @Override
    public void cetakNota() {
        System.out.println("=================================================================");
        System.out.println("              INVOICE PENJUALAN GITAR CUSTOM SHOP               ");
        System.out.println("=================================================================");
        System.out.printf(" No. Invoice      : %s%n", kodeTransaksi);
        System.out.printf(" Tanggal Transaksi: %s%n", tanggalTransaksi);
        System.out.printf(" Nama Pelanggan   : %s%n", namaPelanggan);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(" Unit Gitar       : [%s] %s %s%n", gitar.getIdGitar(), gitar.getMerk(), gitar.getModel());
        System.out.printf(" Harga Pokok Unit : Rp %,14.0f%n", gitar.getHargaBeli());
        System.out.printf(" Diskon Promo (%.0f%%): -Rp %,13.0f%n", diskonPersen, (gitar.getHargaBeli() * (diskonPersen / 100.0)));
        System.out.printf(" Deluxe Hardcase  : Rp %,14.0f (%s)%n", (includeHardcase ? 750000.0 : 0.0), (includeHardcase ? "Termasuk" : "Tidak"));
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(" TOTAL PEMBAYARAN : Rp %,14.0f%n", totalBiaya);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(" Status Garansi   : Sertifikat Luthier Resmi (%d Bulan)%n", masaGaransiBulan);
        System.out.println(" Catatan          : Gratis 2x Setup & Ganti Senar Pertama.");
        System.out.println("=================================================================");
    }

    public double getDiskonPersen() { return diskonPersen; }
    public boolean isIncludeHardcase() { return includeHardcase; }
    public int getMasaGaransiBulan() { return masaGaransiBulan; }
}
