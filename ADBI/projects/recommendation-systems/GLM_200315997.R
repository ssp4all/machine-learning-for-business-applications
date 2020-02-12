ss <- as.data.frame( state.x77[,c("Murder","Population", "Illiteracy", "Income", "Frost")]) 
dim(ss) 
t(ss[1,]) 
dtrain <- ss[1:25,] 
dtest <- ss[26:50,] 
Model <- lm (Murder ~ Population + Illiteracy + Income + Frost, data=dtrain) 
summary (Model) 
library(car) 

# Testing Linearity Assumption: 
crPlots(Model) 
 
# Test for Normality Assumption: 
qqPlot(Model) 

install.packages("gvlma")
library(gvlma)
evaluation <- gvlma(Model)
summary(evaluation)

# Test for Error/Noise Assumption: 
durbinWatsonTest(Model)

# Test for Homoscedasticity Assumption: 
ncvTest(Model) 
plot(Model)

# Test for Multicollinearity Assumption: 
vif_output <- vif(Model) 
vif_output 

# Test for Sensitivity to outliers Assumption: 
outlierTest(Model)

#Test for Model_Complexity
install.packages("leaps")
library(leaps)
sub = regsubsets(Murder ~ Population + Illiteracy + Income + Frost, data=dtrain,nbest = 4,nvmax = 10)
#print(subsets)
sub_summary = summary(sub)
sub_summary$adjr2
sub_summary$bic
plot(sub, scale = "adjr2", main = "Adjusted R^2")