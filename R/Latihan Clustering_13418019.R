#LATIHAN CASE
#CLUSTERING DENGAN ALGORITMA K-MEANS
#Isi "__" dengan code yang sesuai sambil ikuti arahan pada comment

#Instalasi Package untuk Kepentingan Clustering
install.packages("tidyverse")
install.packages("cluster")
install.packages("factoextra")
library(tidyverse) #Manipulasi data
library(cluster) #Algoritma clustering
library(factoextra) #Algoritma clustering dan visualisasi

#Pemanggilan Data untuk Proses Clustering
dataclus <- iris #menggunakan data built-in yang sudah ada di dalam R terkait data ukuran iris milik Fisher atau Anderson sebagai dataset untuk clustering
str(dataclus) #melihat struktur data clustering

#Penghapusan Kolom Spesies pada Dataset
dataclus2 <- iris[-5] #hapus kolom spesies, clustering akan digunakan untuk mengelompokkan data-data yang ada
str(dataclus2) #melihat struktur data clustering setelah penghapusan kolom spesies

head(dataclus) #untuk melihat data-data bunga iris pada urutan terdepan/awal

#Pre-Processing Data
dataclus3 <- na.omit(dataclus2) #untuk menghilangkan missing data yang mungkin ada pada dataset, merupakan salah satu proses pre-processing data
summary(dataclus3) #untuk mengetahui statistika deskriptif dari dataset
datafix <- scale(dataclus3) #scaling dataclus3

#Penentuan Jumlah Cluster
fviz_nbclust(datafix, kmeans, method = "wss") #metode elbow
fviz_nbclust(datafix, kmeans, method = "silhouette") #metode silhouette

set.seed(123) #membangkitkan angka secara random untuk proses simulasi Monte Carlo
gap_stat <- clusGap(datafix, FUN = kmeans, nstart = 25,
                K.max = 10, B = 50) #melakukan proses perhitungan gap statistic menggunakan referensi data yang dibuat dari simulasi Monte Carlo
fviz_gap_stat(gap_stat) #menampilkan visualisasi hasil metode gap statistic

#Mengelompokkan Data-Data ke Dalam Cluster
final <- kmeans(datafix, 3, nstart = 25) #mengelompokkan data-data yang ada ke dalam cluster
print(final) #menampilkan data-data yang sudah masuk ke dalam cluster

#Visualisasi Hasil Clustering
fviz_cluster(final, data = datafix) #visualisasi clustering

#Pembentukan Profil Hasil Clustering
dataclus3 %>%
  mutate(Cluster = final$cluster) %>%
  group_by(Cluster) %>%
  summarise_all("mean") #membentuk profil untuk setiap cluster yang terbentuk
