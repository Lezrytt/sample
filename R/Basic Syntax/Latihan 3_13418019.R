
dataSet1 <- read.csv(file.choose())
dataSet2 <- read.csv(file.choose())
test<- dataSet2
library("ggplot2")
library(dplyr)



ggplot(dataSet1, aes(x = sugarpercent, y = pricepercent)) +
  geom_point() +
  stat_smooth() 

cor(dataSet1$sugarpercent, dataSet1$pricepercent)
# Semakin naik sugar semakin naik karena positif
colnames(dataSet2)
dataSet2$DATE <- as.Date(dataSet2$DATE, "%y-%m-%d")
ggplot(dataSet2, aes(x = DATE, y =IPG3113N)) + geom_line() + stat_smooth()
