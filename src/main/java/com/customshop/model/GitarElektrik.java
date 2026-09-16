package com.customshop.model;


public class GitarElektrik extends Gitar {
    private String tipePickup;
    private String bridgeType;
    private boolean hasCoilSplit;

    public GitarElektrik(String idGitar, String merk, String model, String jenisKayuBody, 
                         double hargaBeli, double tarifSewaPerHari, 
                         String tipePickup, String bridgeType, boolean hasCoilSplit) {
        super(idGitar, merk, model, jenisKayuBody, hargaBeli, tarifSewaPerHari);
        this.tipePickup = tipePickup;
        this.bridgeType = bridgeType;
        this.hasCoilSplit = hasCoilSplit;
    }

    @Override
    public void displaySpesifikasi() {
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(" Kategori     : GITAR ELEKTRIK CUSTOM SHOP%n");
        System.out.printf(" ID Instrumen : %s%n", idGitar);
        System.out.printf(" Seri & Merk  : %s %s%n", merk, model);
        System.out.printf(" Kayu Body    : %s%n", jenisKayuBody);
        System.out.printf(" Tipe Pickup  : %s%n", tipePickup);
        System.out.printf(" Bridge/Trem  : %s%n", bridgeType);
        System.out.printf(" Fitur Khusus : %s%n", (hasCoilSplit ? "Coil-Split Switch (Humbucker to Single-Coil)" : "Standard Tone Circuit"));
        System.out.printf(" Harga Beli   : Rp %,.0f%n", hargaBeli);
        System.out.printf(" Tarif Sewa   : Rp %,.0f / hari%n", tarifSewaPerHari);
        System.out.printf(" Status       : %s%n", (isTersedia ? "READY FOR SALE / RENT" : "CURRENTLY RENTED OUT"));
        System.out.println("-----------------------------------------------------------------");
    }

    public String getTipePickup() { return tipePickup; }
    public String getBridgeType() { return bridgeType; }
    public boolean isHasCoilSplit() { return hasCoilSplit; }
}
