#LATIHAN 2: DATA FRAME
#Panduan, tanda "__" harap diganti dengan input yang sesuai sehingga dapat memberikan hasil yang diminta comment dan soal latihan yang diberikan

#Lakukan import file csv bernama "Latihan 2 Dataset"
data <- read.csv(file.choose(), sep=";")
data
#Periksa struktur data dari dataset "Latihan 2 Dataset" yang telah diimport
str(data)

#Lakukan pemisahan dataset berdasarkan tahun
#Dataset berisi informasi tahun 1960
data1960 <- data[data$Year==1960,]

#Dataset berisi informasi tahun 2013
data2013 <- data[data$Year==2013,]

#Lakukan pembuatan vektor-vektor penyusun data "Life Expectancy"
Country_Code <- c("ABW", "AFG", "AGO", "ALB","ARE","ARG","ARM","ATG","AUS","AUT")
Life_Expectancy_At_Birth_1960 <- c(65.662,32.446,37.524,62.283,51.537,65.055,65.972,61.968,70.817,68.585)
Life_Expectancy_At_Birth_2013 <- c(75.441,62.525,58.054,77.554,76.903,75.756,74.056,76.218,82.149,81.136)

#Lakukan pembuatan dataset life expectancy tahun 1960 dan 2013 yang terdiri dari country code dan nilai life expectancy-nya
#Data frame berisi informasi life expectancy pada tahun 1960
life1960 <- data.frame(Country_Code, Life_Expectancy_At_Birth_1960)

#Data frame berisi informasi life expectancy pada tahun 2013
life2013 <- data.frame(Country_Code, Life_Expectancy_At_Birth_2013)

#Lakukan merging data untuk data fertility dan life expectancy tahun 1960 melalui kesamaan "Country_Code" dengan fungsi merge
#merge(data 1, data 2, kolom yang digunakan untuk merging)
merge1960 <- merge(data1960,life1960,by.x = "Country_Code", by.y = "Country_Code")
View(merge1960)
#Lakukan merging data untuk data fertility dan life expectancy tahun 2013
merge2013 <- merge(data2013,life2013,by.x = "Country_Code", by.y = "Country_Code")

#Lakukan perhitungan nilai rata-rata fertility di tahun 1960 untuk tiap region secara berurutan yaitu Asia, Europe, Africa, Australia, America
meanFertility1960 <- c(mean(merge1960$Fertility.Rate[merge1960$Region=="Asia"]),
        mean(merge1960$Fertility.Rate[merge1960$Region=="Europe"]),
        mean(merge1960$Fertility.Rate[merge1960$Region=="Africa"]),
        mean(merge1960$Fertility.Rate[merge1960$Region=="Australia"]),
        mean(merge1960$Fertility.Rate[merge1960$Region=="America"]))

#Lakukan perhitungan nilai rata-rata life expectancy di tahun 1960 untuk tiap region secara berurutan yaitu Asia, Europe, Africa, Australia, America
lifeExpectance1960 <- c(mean(merge1960$Life_Expectancy_At_Birth_1960[merge1960$Region=="Asia"]),
                        mean(merge1960$Life_Expectancy_At_Birth_1960[merge1960$Region=="Europe"]),
                        mean(merge1960$Life_Expectancy_At_Birth_1960[merge1960$Region=="Africa"]),
                        mean(merge1960$Life_Expectancy_At_Birth_1960[merge1960$Region=="Australia"]),
                        mean(merge1960$Life_Expectancy_At_Birth_1960[merge1960$Region=="America"]))

#Lakukan perhitungan nilai rata-rata fertility di tahun 2013 untuk tiap region secara berurutan yaitu Asia, Europe, Africa, Australia, America
meanFertility2013 <- c(mean(merge2013$Fertility.Rate[merge2013$Region=="Asia"]),
                       mean(merge2013$Fertility.Rate[merge2013$Region=="Europe"]),
                       mean(merge2013$Fertility.Rate[merge2013$Region=="Africa"]),
                       mean(merge2013$Fertility.Rate[merge2013$Region=="Australia"]),
                       mean(merge2013$Fertility.Rate[merge2013$Region=="America"]))

#Lakukan perhitungan nilai rata-rata life expectancy di tahun 2013 untuk tiap region secara berurutan yaitu Asia, Europe, Africa, Australia, America
lifeExpectancy2013 <- c(mean(merge2013$Life_Expectancy_At_Birth_2013[merge2013$Region=="Asia"]),
                       mean(merge2013$Life_Expectancy_At_Birth_2013[merge2013$Region=="Europe"]),
                       mean(merge2013$Life_Expectancy_At_Birth_2013[merge2013$Region=="Africa"]),
                       mean(merge2013$Life_Expectancy_At_Birth_2013[merge2013$Region=="Australia"]),
                       mean(merge2013$Life_Expectancy_At_Birth_2013[merge2013$Region=="America"]))


#Buat dataframe yang berisi region, rata-rata fertility tahun 1960, rata-rata fertility tahun 2013, rata-rata life expectancy tahun 1960, dan rata-rata life expectancy tahun 2013
#Lakukan pembuatan vektor "Region" untuk keperluan soal
Region <- c("Asia","Europe","Africa","Australia","America")

#Buat dataframe
summary <- data.frame(Region, meanFertility1960, meanFertility2013, lifeExpectance1960, lifeExpectancy2013)

#Tampilkan dataframe tersebut
summary
