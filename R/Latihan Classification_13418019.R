#LATIHAN CASE
#CLASSIFICATION WITH DECISION TREE: TELEMARKETING
#Isi "__" dengan code yang sesuai sambil ikuti arahan pada comment

#IMPORT DATASET
#Menampilkan working directory saat ini
setwd("D:\\Kuliah\\Semester 7\\Workshop R\\Day 2")
getwd()
#Setup working directory sesuai alamat tempat file dataset disimpan (jika perlu diganti)
#Hapus lambang #, ubah alamat sesuai yang dibutuhkan, dan run code ini :
#setwd("C:\\Users\\Kayla Maudisa Lastyo\\Documents")
#Import dataset "Latihan Classification" dari directory ke dalam variabel bernama "Marketing Data"
Marketing_Data <- read.csv("Case 2 Data - Bank Marketing.csv", sep=";")

#Periksa ringkasan dan struktur data dari dataset "Marketing_Data"
summary(Marketing_Data)
str(Marketing_Data)

#Lakukan import package untuk cleaning data
#1. Import package "dplyr"
install.packages("dplyr") #Dilakukan ketika package belum ter-install
library("dplyr")

#2. Import package "plyr"
install.packages("plyr") #Dilakukan ketika package belum ter-install
library("plyr")

#3. Import package "DataExplorer"
install.packages("DataExplorer") #Dilakukan ketika package belum ter-install
library("DataExplorer")

#DATA CLEANING
#Cleaning data-data yang terduplikasi
Marketing_Data <- distinct(Marketing_Data, .keep_all = TRUE)
plot_missing(Marketing_Data)
#Tidak terdapat missing data

#mengubah nama kolom "y" yang menyatakan apakah konsumen membeli produk atau tidak
colnames(Marketing_Data)[17] <- "success"

#Lakukan pembagian (split) data menjadi train dan test data
#Import package "caret"
install.packages("caret")
library(caret)

set.seed(70) #Menentukan nilai random untuk iterasi
split_train_set <- createDataPartition(Marketing_Data$success, p=0.7, list=FALSE)
dtrain <- Marketing_Data[split_train_set,]
dtest <- Marketing_Data[-split_train_set,]

#Lakukan import package untuk decision tree
#1. Import package "rpart"
install.packages("rpart")
library("rpart")

#2. Import package "rpart.plot"
install.packages("rpart.plot")
library("rpart.plot")

#Buat decision tree prediksi success dengan prediktor semua kolom pada training dataset dari Marketing_Data
decision_tree <- rpart(success ~ age + job + marital + education + default 
                       + balance + housing + loan + contact + day + month
                       + duration + campaign + pdays + previous + poutcome
                       , data = dtrain, method = "class")

#Panggil decision tree
decision_tree
#Plotting decision tree
rpart.plot(decision_tree)

#Lakukan evaluasi terhadap model decision tree yang telah dibuat sebelumnya dengan dataset dtest

#Evaluasi dengan confusion matrix
#Mencari true positive (TP), true negative (TN), false positive(FP), dan false negative (FN)

#Gunakan fungsi predict untuk memasukkan dtest ke model
#predict(model, new data)
dtree_prob1 <- predict(decision_tree, dtest)

#Tentukan peluang keputusan seseorang akan success (membeli produk) dan tidak
#Jika peluang success lebih besar dari 50% maka orang tersebut dikatakan success (yes)

#Gunakan logika ifelse
#ifelse(condition, value if true, value if false)
dtree_pred1 <- ifelse(dtree_prob1[,2]>0.5,"Yes","No")
dtree_pred1

#Gunakan fungsi tabel untuk melihat nilai TP, TN, FP, dan FN dengan melihat frekuensi "yes" dan "no" untuk churn di dtree_pred1 dibandingkan dengan data aktual churn dari dtest
#table(nama dataset 1, nama dataset 2)
matrix <- table(Predicted=dtree_pred1, Actual=dtest$success)

#Menghitung akurasi
#Total keputusan yang benar/total keputusan yang salah
accuracy <- sum(diag(matrix))/sum(matrix)
accuracy
