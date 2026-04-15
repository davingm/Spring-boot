
# buat jadi masing masing path / fungsi pada controller product

- menampilakan semua data product yang meiliki kategori yang di input dan harganya lebih besar sama dengan daya yang di input 
- menampilakan data product dengan tags yang di input data kategori yang di input
- menampilakan data dengan  harga antara dua harga lebih kecil sama dengan dan lebih besar sama dengan misal : harga inputan 20.000 dan 100.000 maka cari data antara
dua harga itu 


Filter produk berdasarkan kategori dan harga minimum: GET /api/products/filter/category/Makanan/min-price/10000

Filter produk berdasarkan tag dan kategori: GET /api/products/filter/tag/1/category/Makanan

Filter produk berdasarkan rentang harga: GET /api/products/filter/price-range/20000/100000