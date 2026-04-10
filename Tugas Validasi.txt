Validasi menggunakan validation dan manual di service

Long id
String nis (tidak boleh kosong)
String nama (tidak boleh kosong)
String kelas (tidak boleh kosong)
String jenisKelamin tidak boleh kosong
String alamat 
String hobi
String warnaKesukaan 
String noTelepon (tidak boleh kosong, maximal 15 digit)
String tempatLahir (tidak boleh kosong)
String golonganDarah (tidak boleh kosong, maximal 5 digit)

- Buat file exception custom "ValidasiException" untuk menangani validasi di sisi service
- untuk response pada controller nya ubah jadi : 
{
  "status": "00",
  "pesan": "Ini adalah pesan",
  "data": {
	// berisi data produk jika ada, jika tidak ada null
   }
}

create : 
- nis hanya boleh angka
- noTelepon hanya boleh angka
- jenisKelamin hanya boleh berisi "pria" atau "Wanita"

update : 
- nis hanya boleh angka
- noTelepon hanya boleh angka
- jenisKelamin hanya boleh berisi "pria" atau "Wanita"
- jika data tidak di temukan lempar exception ValidasiException dan buat agar 
tidak tampil error tapi pesan "Aplikasi sedang di update"

get data by id : 
- jika data tidak di temukan lempar exception ValidasiException dan tampil pesan "Data tidak ditemukan"


get data by nis 
- jika data tidak di temukan lempar exception ValidasiException dan tampil pesan "Data tidak ditemukan"


delete 
- jika data tidak di temukan lempar exception ValidasiException dan tampil pesan "Data tidak ditemukan"
