#LATIHAN 1
#Petunjuk, tanda "__" menunjukkan blank yang harus diganti dengan input agar soal-soal yang diberikan dapat terjawab dengan baik

##############################################################################
#VARIABEL

#1. Buat variabel bernama "integer" yang berisi bilangan bulat bernilai 10 dan buktikan bahwa variabel tersebut merupakan variabel bertipe integer!
#Buat variabel "integer" berisi bilangan bulat bernilai 10
integer <- 10L

#Lakukan pengecekan tipe variabel
typeof(integer)

#2. Buat variabel bernama "Azka" yang berisi nilai pelajaran matematikanya yaitu 85.5 dan cek tipe variabel tersebut!
#Buat variabel "Azka" berisi nilai 85.5
Azka <- 85.5

#Lakukan pengecekan tipe variabel
typeof(Azka)

#3. Buat variabel bernama "automatic_message" yang berisi pesan berbunyi "Halo, saya sedang sibuk" dan cek tipe variabel tersebut!
#Buat variabel "automatic_message" berisi pesan "Halo, saya sedang sibuk"
automatic_message <- "Halo, saya sedang sibuk"

#Lakukan pengecekan tipe variabel
typeof(automatic_message)

#4. Buat variabel bernama "bumi_itu_bulat" yang berisi logika "TRUE"(dikarenakan pernyataan tersebut benar atau TRUE" dan cek tipe variabel tersebut!
#Buat variabel "bumi_itu_bulat" berisi logika "TRUE"
bumi_itu_bulat <- TRUE

#Lakukan pengecekan tipe variabel
typeof(bumi_itu_bulat)

##############################################################################
#OPERATOR
#1.Kayla dan Keshya baru saja membeli apel secara berturut-turut 5 dan 8 buah dari pasar. Ibunya ingin mengetahui total jumlah apel yang dibeli kedua anaknya.
#Buat variabel bernama "Kayla" berisi nilai 5
Kayla <- 5

#Buat variabel bernama "Keshya" berisi nilai 8
Keshya <- 8

#Buat variabel bernama "Total_Apel" yang berisi penjumlahan antara variabel "Kayla" dan "Keshya"
Total_Apel <- Kayla + Keshya

#2.Lakukan integer division dari pembagian 432 dengan 31
432 %/% 31

#3.Apakah 2 pangkat 100 lebih besar dibandingkan 10 pangkat 31? (TRUE/FALSE)
2^100 > 10^31

##############################################################################
#FUNCTIONS AND VECTORS 

#1. Buatlah vektor untuk dua nilai ujian dari tiga mahasiswa (Rey, Rara, Rafiq). 
# Saat ujian pertama, nilai mereka secara berurut adalah 90, 70, dan 100.
#Saat ujian kedua, nilai mereka secara berurut adalah 80, 60, 75.
#Simpanlah ke dalam vektor bernama Nilai_ujian1 dan Nilai_ujian2.
Nilai_ujian1 <- c(90,70,100)
  Nilai_ujian2 <- c(80,70,75)
  
  #2. Berapakah total nilai ketiga mahasiswa pada Ujian 1 dan Ujian 2?
  sum(Nilai_ujian1)
sum(Nilai_ujian2)

#3. Jumlahkan nilai dari kedua ujian untuk masing-masing mahasiswa, simpan ke dalam
#vektor yang bernama Total.
Total <- Nilai_ujian1 + Nilai_ujian2

#4. Tetapkan nama mahasiswa untuk setiap nilai pada vektor Total.
names(Total) <- c("Rey", "Rara", "Rafiq")

#5. Tampilkan total nilai dari mahasiswa bernama Rara.
Total["Rara"]

#6. Urutkan nama mahasiswa dari total nilai terbesar hingga terkecil.
names(sort(Total, decreasing=TRUE))

#7. Tampilkan nama mahasiswa yang memiliki total nilai di atas rata-rata.
Rata <- mean(Total)
names(Total[Total>Rata])

##############################################################################
#READING AND WRITING
#Berikut merupakan vektor karakter yang berisi kode-kode jurusan dalam FTI ITB.
#1. Lakukan execute pada baris berikut.
Jurusan <- c("ti", "mri", "Ft", "pg", "tB", "TI")
Jurusan
#2. Kode jurusan wajib memiliki hanya 2 karakter. Berapa jumlah huruf dari setiap kode jurusan?
nchar(Jurusan)
#Lakukan perbaikan agar setiap kode hanya memiliki 2 karakter.
Jurusan <- substr(Jurusan,start=1,stop=2)

#3. Kode jurusan wajib menggunakan huruf kapital. Ubahlah vektor karakter agar memenuhi persyaratan tsb.
Jurusan <- toupper(Jurusan)

#4. Kode jurusan harusnya bersifat unik. Ubahlah agar setiap kode jurusan
#hanya disimpan sekali dalam vektor karakter.
Jurusan <- unique(Jurusan)

#5. Berapa jumlah kode jurusan yang dicatat pada vektor Jurusan?
nlevels(Jurusan)

#6. Ubahlah vektor karakter menjadi sebuah factor.
factor(Jurusan)

#7. Setelah diperiksa, ternyata ada satu jurusan yang belum masuk ke daftar, yaitu TK (Teknik Kimia).
#Tambahkan level TK ke dalam factor Jurusan.
factor(Jurusan, levels=c("FT","MR","PG","TB","TI","TK"))

