#CASE 2
#CLASSIFICATION WITH DECISION TREE

#Lakukan import dataset "Case 2 Classification" ke dalam dataset "Telecom_Data"
Telecom_Data <- read.csv(file.choose())

#Periksa data-data dari dataset "Telecom_Data" (nama kolom, dll)
summary(Telecom_Data)
str(Telecom_Data)

#Lakukan import package untuk cleaning data
#1. Import package "dplyr"
install.packages("dplyr") #Dilakukan ketika package belum ter-install
library(dplyr)

#2. Import package "plyr"
install.packages("plyr") #Dilakukan ketika package belum ter-install
library(plyr)

#3. Import package "DataExplorer"
install.packages("DataExplorer") #Dilakukan ketika package belum ter-install
library(DataExplorer)

#DATA CLEANING
#Cleaning data-data yang terduplikasi
Telecom_Data <- distinct(Telecom_Data, .keep_all = FALSE)

#Cleaning data-data dari missing data
#Identifikasi missing data melalui visualisasi
plot_missing(Telecom_Data)
# kurang dari 0.16% yang missing

#Menghapus missing data
Telecom_Data <- na.omit(Telecom_Data)
# atau
Telecom_Data <- Telecom_Data[!is.na(Telecom_Data$TotalCharges)]
#Menghapus kolom yang tidak diperlukan
#Penghapusan kolom "customerID" karena dalam membuat model, pelanggan tidak perlu dilihat secara individual
Telecom_Data$customerID <- NULL

#Lakukan pembagian (split) data menjadi train dan test data
#Train data akan digunakan untuk membangun model sedangkan test data akan digunakan untuk melakukan evaluasi model
#Komposisi train data adalah 70% dan komposisi test data adalah 30%

#set seed sebesar 56 untuk men-generate random number yang sama setiap melakukan running model
set.seed(56)

#Buat pembagian/partisi data pada kolom churn sesuai komposisi yang telah ditentukan diatas
#Import package "caret" untuk menggunakan fungsi createDataPartition
install.packages("caret")
library("caret")

#Buat pembagian data dengan fungsi createDataPartition
#createDataPartition(kolom yang menentukan partisi, persentase data yang masuk ke partisi, logika pembentukan tipe data list)
split_train_set <- createDataPartition(Telecom_Data$Churn, p=0.7, list=FALSE)

#Buat train data dan test data
#test/train data <- Telecom_Data[partisi data untuk test/train,]
dtrain2 <- Telecom_Data[split_train_set,]
dtest2 <- Telecom_Data[-split_train_set,]

#Lakukan import package untuk decision tree
#1. Import package "rpart"
install.packages("rpart")
library(rpart)

#2. Import package "rpart.plot"
install.packages("rpart.plot")
library(rpart.plot)

#Buat decision tree prediksi churn dengan prediktor semua kolom pada dataset Telecom_Churn
decision_tree <- rpart(Churn ~ ., data=dtrain2, method="class")

#Plot decision tree
rpart.plot(decision_tree)

#Lakukan evaluasi terhadap model decision tree yang telah dibuat sebelumnya dengan dataset dtest

#Evaluasi dengan confusion matrix
#Mencari true positive (TP), true negative (TN), false positive(FP), dan false negative (FN)

#Gunakan fungsi predict untuk memasukkan dtest ke model dan buat dataset baru berisi hasil fungsi predict
#predict(model, test data)
dtree_prob2 <- predict(decision_tree, dtest2)

#Lihat hasil fungsi predict
dtree_prob2

#Tentukan peluang keputusan seseorang dikatakan churn dan tidak berdasarkan hasil predict
#Jika peluang churn lebih besar dari 50% maka orang tersebut dikatakan churn (yes)

#Buat dataset baru yang berisi hasil keputusan churn berdasarkan dataset hasil fungsi predict
#Gunakan logika ifelse memudahkan pengisian
#ifelse(condition, value if true, value if false)
dtree_pred2 <- ifelse(dtree_prob2[,2]>0.5,"Yes","No")
dtree_pred2

#Gunakan fungsi tabel untuk melihat nilai TP, TN, FP, dan FN dengan melihat frekuensi "yes" dan "no" untuk churn di dtree_pred1 dibandingkan dengan data aktual churn dari dtest
#table(nama dataset 1, nama dataset 2)
confusion_matrix <- table(Predicted=dtree_pred2, Actual=dtest2$Churn)

#Menghitung akurasi
#Total keputusan yang benar/total keputusan yang salah
accuracy <- sum(diag(confusion_matrix))/sum(confusion_matrix)
accuracy


