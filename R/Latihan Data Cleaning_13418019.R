#LATIHAN DATA CLEANING & PRE PROCESSING
#Panduan, tanda "__" harap dimasukkan dengan input yang sesuai untuk memperoleh output yang diinginkan

#Import data Boston Crime ke dalam RStudio
bostonCrime <- read.csv(file.choose())

#Melakukan pemahaman terhadap data
dim(bostonCrime)

#Menampilkan statistical summary dari dataset
summary(bostonCrime)

#Menampilkan tipe data dari tiap kolom pada dataset
str(bostonCrime)

#Melakukan data cleaning

#DUPLICATES
#Lakukan identifikasi terhadap duplikasi yang terjadi pada data
duplicated(bostonCrime)
sum(duplicated(bostonCrime))

#Hilangkan duplikasi yang terdapat pada data

#install package "dplyr" jika belum terinstall pada RStudio Anda
install.library(dplyr)

#Panggil package "dplyr" untuk digunakan
library(dplyr)

#Hilangkan data yang terduplikasi dengan function "distinct"
bostonCrime <- distinct(bostonCrime, .keep_all=FALSE)

#IRRELEVANT DATA
#Pada case ini REPORTING_AREA tidak digunakan, hapus REPORTING_AREA
bostonCrime$REPORTING_AREA <- NULL

#MISSING DATA
#BLANKS AS MISSING DATA

#Lakukan identifikasi terhadap blanks(" ") untuk semua kolom pada dataset
colSums(bostonCrime=="")

#Lakukan konversi terhadap data blanks yang diidentifikasi sebagai suatu missing data menjadi NA
bostonCrime$DISTRICT[bostonCrime$DISTRICT == ""] <- NA
bostonCrime$UCR_PART[bostonCrime$UCR_PART == ""] <- NA
bostonCrime$STREET[bostonCrime$STREET == ""] <- NA

#MISSING DATA: NA
#Lakukan identifikasi terhadap missing data
colSums(is.na(bostonCrime))

#Lakukan plot terhadap missing data
#Install package "DataExplorer" jika belum terdapat pada RStudio Anda
install.package("DataExplorer")

#Panggil package "DataExplorer" untuk digunakan pada RStudio Anda
library("DataExplorer")

#Gunakan function plot_missing untuk mem-plot missing data
plot_missing(bostonCrime)

#Hapus missing data karena missing data berjumlah kurang dari 5% total data pada dataset
bostonCrime <- na.omit(bostonCrime)

#Periksa kembali apakah masih terdapat missing data pada dataset Anda
colSums(is.na(bostonCrime))
plot_missing(bostonCrime)

#Lakukan export terhadap data yang telah siap digunakan
write.csv(bostonCrime, "D:\\Kuliah\\Semester 7\\Workshop R\\Day 2\\Boston Crime_cleaned.csv")
