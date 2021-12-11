#Instalasi Package untuk Kepentingan Clustering
install.packages("tidyverse")
install.packages("cluster")
install.packages("factoextra")
install.packages("dplyr")
library(dplyr) #Manipulasi data
library(tidyverse) #Manipulasi data
library(cluster) #Algoritma clustering
library(factoextra) #Algoritma clustering dan visualisasi

#Memanggil Data untuk Proses Clustering
# *Download dataset: https://www.kaggle.com/arawind/retail-marketing

retailMarketingDI <- read.csv(file.choose()) #import dataset
str(retailMarketingDI) #melihat struktur data clustering 
head(retailMarketingDI) #untuk melihat data-data pada urutan terdepan/awal
view(retailMarketingDI)

#Keterangan:
#Pada kolom Age, 0='Young', 1='Middle', 2='Old'
#Pada kolom Gender, 0='Female', 1='Male'
#Pada kolom OwnHome, 0='Own', 1='Rent'
#Pada kolom Married, 0='Married', 1='Single'
#Pada kolom Location, 0='Close', 1='Far'

sum(is.na(retailMarketingDI$History))

#Pre-Processing Data
retailMarketingDI2 <- retailMarketingDI[-8] #menghapus kolom history karena sangat banyak missing data
datafix <- na.omit(retailMarketingDI2) #melakukan penghapusan missing data

str(datafix)

#Penentuan Jumlah Cluster

fviz_nbclust(datafix, kmeans, method = "wss") #metode elbow

fviz_nbclust(datafix, kmeans, method = "silhouette") #metode silhouette

set.seed(322) #membangkitkan angka secara random untuk proses simulasi Monte Carlo
gap_stat <- clusGap(datafix, FUN = kmeans, nstart = 25,
                    K.max = 10, B = 50) #melakukan proses perhitungan gap statistic menggunakan referensi data yang dibuat dari simulasi Monte Carlo
fviz_gap_stat(gap_stat) #menampilkan visualisasi hasil metode gap statistic

#metode gap statistic


#Mengelompokkan Data-Data ke Dalam Cluster
clusterFinal <- kmeans(datafix, 4, nstart = 25) #mengelompokkan data-data yang ada ke dalam cluster
?kmeans
print(clusterFinal) #menampilkan data-data yang sudah masuk ke dalam cluster

#Visualisasi Hasil Clustering
fviz_cluster(clusterFinal, data = datafix) #visualisasi clustering

#Pembentukan Profil Hasil Clustering
datafix %>%
  mutate(Cluster = clusterFinal$cluster) %>%
  group_by(Cluster) %>%
  summarise_all("mean") #membentuk profil untuk setiap cluster yang terbentuk


