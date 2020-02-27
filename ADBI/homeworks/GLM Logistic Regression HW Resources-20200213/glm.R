# 	@author Suraj Pawar
# 	Unity ID: spawar2
# 	Student ID: 200315997

# Import Libraries
library(reshape)
library(qcc)
library(fastDummies)


#Find similar features and reduce features
combiner <- function(pivot, col){
	thres = 0.03
	dictt = list()
	for(i in 1:(length(pivot)-1)){
		for(j in i + 1:length(pivot)){
				if(abs(pivot[i,2]-pivot[j,2]) < thres){
					dictt[toString(pivot[j,1])] = pivot[i,1]
			}
		}
  	}

	for(x in names(dictt)){
		train[train[col]==x,col] <<- dictt[[x]]
		test[test[col]==x,col] <<- dictt[[x]]
	}
}


#Load and store the data
df = readxl::read_xls("eBayAuctions.xls")
colnames(df)[8] = "Competitive"
df$Duration = as.factor(df$Duration)

#Generate train and test
train_size = 0.6
size <- floor(train_size * nrow(df))
set.seed(7)
train_ind <- sample(seq_len(nrow(df)), size = size)
train <- df[train_ind, ]
test <- df[-train_ind, ]
train_copy <- train

#Pivot the data
train.melt = melt(train, id.vars = c("Category", "currency", "Duration", "endDay"), measure.vars = "Competitive")
train.melt
train.pivotCat = cast(train.melt, Category ~ variable, mean)
train.pivotCur = cast(train.melt, currency ~ variable, mean)
train.pivotDur = cast(train.melt, Duration ~ variable, mean)
train.pivotEnd = cast(train.melt, endDay ~ variable, mean)

##Combine Categories that are similar
combiner(train.pivotCat,"Category")
combiner(train.pivotCur, "currency")
combiner(train.pivotDur, "Duration")
combiner(train.pivotEnd, "endDay")


##Create Dummy Variables (optional since glm does it automatically)
train = dummy_cols(train,remove_first_dummy = TRUE)
train = within(train, rm("Category", "currency","Duration","endDay"))

test = dummy_cols(test,remove_first_dummy = TRUE)
test = within(test, rm("Category", "currency","Duration","endDay"))


##Fitting with all predictors
fit.all = glm(Competitive ~ ., family=binomial(link="logit"),data=train)
summary(fit.all)
max = -.Machine$integer.max
max_feature = NA
for(i in 1:length(fit.all$coefficients)){
	if(abs(fit.all$coefficients[i])>max){
		max = fit.all$coefficients[i]
		max_feature = names(fit.all$coefficients[i])
	}
}
message("Feature with highest estimate is ",max_feature," with an estimate of ",max)


##Find statistically significant predictors and fit reduced model
significance = summary(fit.all)$coef[,4]
formula = "Competitive ~ "
count=0
for(i in 1:length(significance)){
	if(significance[i]<0.05){
		if(count==0){
			formula = paste(formula,names(significance[i]),"")
			count=count+1
		}
		else{
			formula = paste(formula,"+",names(significance[i]))
		}
	}
}
fit.reduced = glm(formula, family=binomial(link="logit"),data=train)
summary(fit.reduced)

##Check if both are equivalent
anova(fit.reduced, fit.all, test = "Chisq")

##Check overdispersion
message("Dispersion of model is ",(fit.reduced$deviance/fit.reduced$df.residual))
sample_size=rep(100, length(train$Competitive))
qcc.overdispersion.test(train$Competitive, size=sample_size, type="binomial")