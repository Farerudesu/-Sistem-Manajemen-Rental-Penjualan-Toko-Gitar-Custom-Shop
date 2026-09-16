package com.customshop.model;


public class GitarBass extends Gitar {
    private int jumlahSenar;
    private boolean isPreampAktif;
    private String jenisPickup;

    public GitarBass(String idGitar, String merk, String model, String jenisKayuBody, 
                     double hargaBeli, double tarifSewaPerHari, 
                     int jumlahSenar, boolean isPreampAktif, String jenisPickup) {
        super(idGitar, merk, model, jenisKayuBody, hargaBeli, tarifSewaPerHari);
        this.jumlahSenar = jumlahSenar;
        this.isPreampAktif = isPreampAktif;
        this.jenisPickup = jenisPickup;
    }

    @Override
    public void displaySpesifikasi() {
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(" Kategori     : BASS ELEKTRIK CUSTOM SHOP%n");
        System.out.printf(" ID Instrumen : %s%n", idGitar);
        System.out.printf(" Seri & Merk  : %s %s%n", merk, model);
        System.out.printf(" Body Wood    : %s%n", jenisKayuBody);
        System.out.printf(" Jumlah Senar : %d Senar (Extended Range Bass)%n", jumlahSenar);
        System.out.printf(" Konfigurasi  : %s%n", jenisPickup);
        System.out.printf(" Elektronik   : %s%n", (isPreampAktif ? "Active 3-Band EQ Preamp (Bass/Mid/Treble Boost)" : "Passive Vintage Tone Control"));
        System.out.printf(" Harga Beli   : Rp %,.0f%n", hargaBeli);
        System.out.printf(" Tarif Sewa   : Rp %,.0f / hari%n", tarifSewaPerHari);
        System.out.printf(" Status       : %s%n", (isTersedia ? "READY FOR SALE / RENT" : "CURRENTLY RENTED OUT"));
        System.out.println("-----------------------------------------------------------------");
    }

    public int getJumlahSenar() { return jumlahSenar; }
    public boolean isPreampAktif() { return isPreampAktif; }
    public String getJenisPickup() { return jenisPickup; }
}
