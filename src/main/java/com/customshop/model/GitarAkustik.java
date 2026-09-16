package com.customshop.model;


public class GitarAkustik extends Gitar {
    private String topWood;
    private String preampBrand;
    private boolean hasCutaway;

    public GitarAkustik(String idGitar, String merk, String model, String jenisKayuBody, 
                        double hargaBeli, double tarifSewaPerHari, 
                        String topWood, String preampBrand, boolean hasCutaway) {
        super(idGitar, merk, model, jenisKayuBody, hargaBeli, tarifSewaPerHari);
        this.topWood = topWood;
        this.preampBrand = preampBrand;
        this.hasCutaway = hasCutaway;
    }

    @Override
    public void displaySpesifikasi() {
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(" Kategori     : GITAR AKUSTIK / ELEKTRO-AKUSTIK%n");
        System.out.printf(" ID Instrumen : %s%n", idGitar);
        System.out.printf(" Seri & Merk  : %s %s%n", merk, model);
        System.out.printf(" Body Wood    : %s (Back & Sides)%n", jenisKayuBody);
        System.out.printf(" Top Wood     : %s (Solid Tone Wood)%n", topWood);
        System.out.printf(" Preamp Pickup: %s%n", preampBrand);
        System.out.printf(" Desain Body  : %s%n", (hasCutaway ? "Venetian Cutaway (Easy High Fret Access)" : "Traditional Dreadnought"));
        System.out.printf(" Harga Beli   : Rp %,.0f%n", hargaBeli);
        System.out.printf(" Tarif Sewa   : Rp %,.0f / hari%n", tarifSewaPerHari);
        System.out.printf(" Status       : %s%n", (isTersedia ? "READY FOR SALE / RENT" : "CURRENTLY RENTED OUT"));
        System.out.println("-----------------------------------------------------------------");
    }

    public String getTopWood() { return topWood; }
    public String getPreampBrand() { return preampBrand; }
    public boolean isHasCutaway() { return hasCutaway; }
}
