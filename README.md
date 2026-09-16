# Tugas Individu: Pemrograman Berorientasi Objek (Java)
## Sistem Manajemen Rental & Penjualan Toko Gitar Custom Shop (CLI)

Program aplikasi berbasis konsol (*Command Line Interface* / CLI) menggunakan bahasa pemrograman Java dengan menerapkan prinsip-prinsip **Pemrograman Berorientasi Objek (PBO)**, khususnya **Inheritance (Pewarisan)**, **Polymorphism (Polimorfisme)**, **Encapsulation (Enkapsulasi)**, dan **Abstraction (Abstraksi)**.

Aplikasi ini dibangun menggunakan struktur standar **Maven Project** yang kompatibel langsung dengan **Apache NetBeans**.

---

## 👤 Identitas Mahasiswa
* **Nama** : Muhammad Fahriel
* **NIM** : 2509116050
* **Kelas / Program Studi** : B'2025 Sistem Informasi
* **Mata Kuliah** : Pemrograman Berorientasi Objek

---

## 📖 Penjelasan Studi Kasus
Studi kasus yang diangkat adalah **Sistem Manajemen Rental & Penjualan pada Toko Gitar Custom Shop**. Toko ini mengelola instrumen musik *high-end* (Gitar Elektrik, Gitar Akustik, dan Bass Elektrik) dengan dua model bisnis utama:

1. **Layanan Penjualan Unit Gitar (Sales)**:
   * Pelanggan dapat membeli gitar custom shop.
   * Mendukung perhitungan diskon member dan opsi penambahan aksesoris *Deluxe Hardcase*.
   * Menyertakan sertifikasi luthier resmi dan masa garansi.
2. **Layanan Penyewaan / Rental Gitar**:
   * Pelanggan dapat menyewa gitar harian untuk keperluan rekaman studio, konser, atau sesi latihan.
   * Menerapkan kalkulasi uang jaminan / deposit pengaman (*refundable*).
   * Status ketersediaan instrumen diperbarui secara otomatis (`TERSEDIA` atau `DISEWA`).
3. **Layanan Pengembalian & Denda**:
   * Menangani pengembalian instrumen yang telah disewa.
   * Menghitung denda keterlambatan secara otomatis berdasarkan hari telat dan memotongnya dari uang deposit.
4. **Pencetakan Nota / Invoice Resmi**:
   * Mencetak bukti transaksi dengan format kuitansi kasir profesional di konsol.

---

## 🏗️ Diagram Kelas & Hierarki Class

Program ini mengimplementasikan **dua rantai pewarisan (Inheritance)**:

### 1. Diagram Hubungan Class (Mermaid)
```mermaid
classDiagram
    class Gitar {
        <<abstract>>
        #String idGitar
        #String merk
        #String model
        #String jenisKayuBody
        #double hargaBeli
        #double tarifSewaPerHari
        #boolean isTersedia
        +displaySpesifikasi()*
        +hitungBiayaSewa(int durasiHari) double
        +getInfoSingkat() String
        +isTersedia() boolean
        +setTersedia(boolean tersedia)
    }

    class GitarElektrik {
        -String tipePickup
        -String bridgeType
        -boolean hasCoilSplit
        +displaySpesifikasi()
    }

    class GitarAkustik {
        -String topWood
        -String preampBrand
        -boolean hasCutaway
        +displaySpesifikasi()
    }

    class GitarBass {
        -int jumlahSenar
        -boolean isPreampAktif
        -String jenisPickup
        +displaySpesifikasi()
    }

    class Transaksi {
        <<abstract>>
        #String kodeTransaksi
        #String namaPelanggan
        #Gitar gitar
        #String tanggalTransaksi
        #double totalBiaya
        +hitungTotalBiaya()* double
        +cetakNota()*
    }

    class TransaksiBeli {
        -double diskonPersen
        -boolean includeHardcase
        -int masaGaransiBulan
        +hitungTotalBiaya() double
        +cetakNota()
    }

    class TransaksiSewa {
        -int durasiHari
        -double uangDeposit
        -int hariTerlambat
        -double dendaPerHari
        -boolean isSelesai
        +hitungTotalBiaya() double
        +prosesPengembalian(int terlambat)
        +cetakNota()
    }

    Gitar <|-- GitarElektrik : extends
    Gitar <|-- GitarAkustik : extends
    Gitar <|-- GitarBass : extends

    Transaksi <|-- TransaksiBeli : extends
    Transaksi <|-- TransaksiSewa : extends
```

### 2. Penjelasan Hierarki Class:
1. **Super-Class `Gitar`**:
   * Merupakan *abstract class* yang menjadi cetak biru bagi seluruh instrumen di toko. Menyimpan atribut bersama seperti `idGitar`, `merk`, `model`, `jenisKayuBody`, `hargaBeli`, dan `tarifSewaPerHari`.
2. **Sub-Class `GitarElektrik`, `GitarAkustik`, `GitarBass`**:
   * Mewarisi sifat-sifat dasar dari `Gitar`.
   * Menambahkan atribut spesifik (misal: `tipePickup` pada elektrik, `topWood` pada akustik, dan `jumlahSenar` pada bass).
   * Masing-masing mengimplementasikan method abstrak `displaySpesifikasi()` sesuai karakteristik fisiknya.
3. **Super-Class `Transaksi`**:
   * Merupakan induk bagi setiap transaksi bisnis yang terjadi di toko (`kodeTransaksi`, `namaPelanggan`, objek `gitar`, `tanggalTransaksi`).
4. **Sub-Class `TransaksiBeli` & `TransaksiSewa`**:
   * `TransaksiBeli` mengkhususkan diri pada perhitungan diskon dan garansi.
   * `TransaksiSewa` mengkhususkan diri pada durasi pinjam, uang deposit, serta kalkulasi denda keterlambatan.

---

## 🔍 Penjelasan Bagian Kode Penerapan Inheritance

Penerapan konsep pewarisan (*Inheritance*) dalam kode program dapat ditunjukkan pada bagian-bagian berikut:

### 1. Penggunaan Sintaks `extends`
Pewarisan kelas dilakukan dengan kata kunci `extends`:
* **`GitarElektrik` mewarisi `Gitar`**:
  ```java
  public class GitarElektrik extends Gitar { ... }
  ```
  *(File: `src/main/java/com/customshop/model/GitarElektrik.java`)*
* **`TransaksiBeli` mewarisi `Transaksi`**:
  ```java
  public class TransaksiBeli extends Transaksi { ... }
  ```
  *(File: `src/main/java/com/customshop/model/TransaksiBeli.java`)*

### 2. Penggunaan Kata Kunci `super()`
Konstruktor sub-class memanggil konstruktor milik super-class untuk menginisialisasi atribut turunan:
```java
public GitarElektrik(String idGitar, String merk, String model, String jenisKayuBody, 
                     double hargaBeli, double tarifSewaPerHari, 
                     String tipePickup, String bridgeType, boolean hasCoilSplit) {
    // Memanggil konstruktor super-class Gitar
    super(idGitar, merk, model, jenisKayuBody, hargaBeli, tarifSewaPerHari);
    this.tipePickup = tipePickup;
    this.bridgeType = bridgeType;
    this.hasCoilSplit = hasCoilSplit;
}
```

### 3. Penerapan Polimorfisme (@Override)
Method abstrak pada super-class dioverride secara dinamis oleh sub-class untuk menyesuaikan perilakunya:
```java
@Override
public void displaySpesifikasi() {
    System.out.println(" Kategori     : GITAR ELEKTRIK CUSTOM SHOP");
    System.out.printf(" Tipe Pickup  : %s%n", tipePickup);
    System.out.printf(" Bridge/Trem  : %s%n", bridgeType);
    // ...
}
```

---

## 🚀 Cara Membuka & Menjalankan di Apache NetBeans

1. Buka aplikasi **Apache NetBeans**.
2. Klik menu **File** > **Open Project...** (atau tekan shortcut `Ctrl + Shift + O`).
3. Buka folder:
   ```text
   CustomShopGuitar
   ```
   *(Yang berada di folder default NetBeans Anda: `Documents\NetBeansProjects\CustomShopGuitar`)*.
4. Klik tombol **Open Project**.
5. Jalankan aplikasi dengan menekan tombol **F6** atau tombol panah hijau (**Run Project**) di toolbar.
6. Program CLI akan langsung berjalan di panel **Output** NetBeans.

---

## 🖥️ Tangkapan Layar (Screenshot Running Program)


### 1. Menu Utama & Katalog Instrumen
<img width="863" height="503" alt="image" src="https://github.com/user-attachments/assets/8008c828-b167-4169-b14f-d92331a93959" />


### 2. Transaksi Penyewaan (Rental Contract)
<img width="840" height="594" alt="image" src="https://github.com/user-attachments/assets/83c24533-761d-44eb-88dd-84333f48fb88" />


### 3. Transaksi Pembelian Unit (Sales Invoice)
<img width="1056" height="659" alt="image" src="https://github.com/user-attachments/assets/a1555255-06b1-4e2e-a9f4-c115e6583064" />

