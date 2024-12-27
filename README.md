Artify - Galeri Seni Digital
Deskripsi
Artify adalah aplikasi desktop berbasis Java yang memungkinkan pengguna untuk mengelola galeri seni digital. Pengguna dapat menambahkan karya seni baru dengan mengisi judul, kategori, deskripsi, dan mengunggah gambar. Karya seni yang sudah ditambahkan akan ditampilkan dalam tabel yang menampilkan informasi seperti judul, kategori, deskripsi, dan gambar pratinjau.

Aplikasi ini dibangun menggunakan Swing untuk tampilan antarmuka grafis dan JUnit untuk pengujian unit.

Fitur Utama
Tambah Karya Baru
Pengguna dapat menambahkan karya seni baru dengan mengisi form yang terdiri dari:

Judul karya seni
Kategori karya seni
Deskripsi karya seni
Gambar pratinjau karya seni yang diunggah dari file lokal.
Lihat Karya Seni
Karya seni yang sudah ditambahkan akan tampil dalam tabel yang terdiri dari:

Judul karya
Kategori karya
Deskripsi karya
Gambar karya seni dalam ukuran kecil
Hapus Karya Seni
Pengguna dapat memilih karya seni dari tabel dan menghapusnya. Sebuah konfirmasi akan ditampilkan sebelum menghapus karya seni dari galeri.

Unggah Gambar
Pengguna dapat mengunggah gambar untuk karya seni menggunakan file explorer yang mendukung format gambar seperti .jpg, .png, dan .jpeg.

Clear Fields
Setelah menambahkan karya seni, aplikasi akan membersihkan form dan gambar yang ditampilkan, siap untuk input karya seni selanjutnya.

Teknologi yang Digunakan
Java Swing: Untuk pembuatan antarmuka grafis aplikasi desktop.
JUnit: Untuk pengujian unit aplikasi, memastikan fungsionalitas aplikasi berjalan dengan benar.
File I/O: Untuk mengunggah gambar dari file lokal dan menampilkan gambar dalam aplikasi.
Struktur Kode
1. ArtifyAppSwing.java
   Merupakan file utama yang mendefinisikan tampilan dan fungsionalitas aplikasi. Beberapa komponen utama dari aplikasi ini meliputi:

Form input karya seni: Menampilkan komponen untuk mengisi judul, kategori, deskripsi, dan mengunggah gambar.
Tabel galeri seni: Menampilkan karya seni yang telah ditambahkan dalam aplikasi.
Metode untuk menambahkan dan menghapus karya seni.
2. ArtifyAppSwingTest.java
   File ini berisi unit test yang menggunakan JUnit untuk menguji fungsionalitas aplikasi seperti menambah karya, menghapus karya, dan membersihkan form input.

3. JFileChooser dan ImageIcon
   Aplikasi ini menggunakan JFileChooser untuk memilih gambar yang akan diunggah dan ImageIcon untuk menampilkan gambar pratinjau dalam aplikasi.

Penggunaan
Menambahkan Karya Seni

Isi form dengan judul, kategori, deskripsi, dan pilih gambar untuk karya seni menggunakan tombol "Unggah Gambar".
Setelah semua field terisi, klik tombol "Tambah Karya" untuk menambahkan karya seni ke dalam galeri.
Menghapus Karya Seni

Pilih karya seni yang ingin dihapus dari tabel galeri.
Klik tombol "Hapus Karya" dan konfirmasi penghapusan.
Mengunggah Gambar

Klik tombol "Unggah Gambar" untuk memilih gambar yang akan ditampilkan sebagai pratinjau karya seni.
Membersihkan Form

Klik tombol "Clear Fields" untuk mengosongkan form setelah menambahkan karya seni.
Pengujian
Pengujian Unit (JUnit)
Aplikasi ini telah dilengkapi dengan pengujian unit menggunakan JUnit untuk memverifikasi fungsionalitas berikut:

Menambahkan karya seni dengan input yang valid
Menambahkan karya seni dengan input yang tidak lengkap
Menghapus karya seni yang dipilih
Menangani situasi saat tidak ada karya seni yang dipilih untuk dihapus
Memastikan form dibersihkan setelah penambahan karya
Instalasi
Pastikan Anda memiliki Java Development Kit (JDK) yang terinstal di komputer Anda (minimal versi 8).
Clone repository ini ke komputer lokal Anda atau unduh file .zip.
Kompilasi dan jalankan aplikasi menggunakan IDE Java pilihan Anda (misalnya IntelliJ IDEA, Eclipse) atau menggunakan terminal/command prompt.
Kontribusi
Jika Anda ingin berkontribusi pada proyek ini, silakan buka issue atau buat pull request dengan perbaikan atau fitur baru. Pastikan untuk mengikuti standar kode yang sudah ada dan menambahkan pengujian unit untuk fitur baru yang ditambahkan.
