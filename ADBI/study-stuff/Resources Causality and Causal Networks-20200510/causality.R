
# File: causality.R

# incomment and install the packages 
#install.packages("pcalg", dependencies = TRUE)
#source("http://bioconductor.org/biocLite.R")
#biocLite(c("graph", "RBGL", "Rgraphviz"))
#install.packages("gRain", dependencies=TRUE)

library(pcalg)

#---------------------------
# Conditional independence
# v-structure 
#---------------------------
X_1 <- rnorm(1000)
Y_1 <- rnorm(1000)
Z_1 <- 0.2*X_1 + 0.4*Y_1 + rnorm(1000)
dat_1 <- cbind(X_1, Y_1, Z_1)

# Test conditional independence of variables from
# Gaussian distributions via Fisher's Z-statistic
gaussCItest(1, 2, S=NULL, 
            suffStat = list(C=cor(dat_1), 
                            n=nrow(dat_1)))

#--------------------------
# violation of faithfulness
#--------------------------
X_2 <- rnorm(100)
Y_2 <- 2*X_2 + rnorm(100)
Z_2 <- 5*Y_2 - 10*X_2 + rnorm(100)
dat_2 <- cbind(X_2, Y_2, Z_2)
gaussCItest(1, 3, S=NULL, 
            suffStat = list(C=cor(dat_2), 
                            n=nrow(dat_2)))
gaussCItest(1, 2, S=NULL, 
            suffStat = list(C=cor(dat_2), 
                            n=nrow(dat_2)))
gaussCItest(3, 2, S=NULL, 
            suffStat = list(C=cor(dat_2), 
                            n=nrow(dat_2)))

#-------------------------------
# PC algorithm example
# Causal structure: I <- W -> C
#-------------------------------
W <- rnorm(1000)
I <- 4*W + rnorm(1000)
C <- 2*W + rnorm(1000)
dat_3 <- cbind(W, I, C)
pc_fit <- pc(suffStat=list(C=cor(dat_3), 
                           n=nrow(dat_3)), 
             indepTest=gaussCItest, 
             alpha=0.05, 
             labels=colnames(dat_3), 
             verbose=TRUE)

plot(pc_fit, main="Estimated PDAG")

#--------------------------------------
# PC algorithm example
# Initialize the number of variables
#--------------------------------------
rDAG <- randomDAG(10, prob=0.3, lB=0.1, uB=1)

# Visualize the generated DAG
plot(rDAG, main="A sample random DAG")
# Generate a multivariate data according to rDAG
dat_4 <- rmvDAG(1000, rDAG, errDist="normal")

# Now let's run the PC algorithm using the data
suffStat=list(C=cor(dat_4), n=nrow(dat_4))
pc_fit <- pc(suffStat, indepTest=gaussCItest, alpha=0.05, labels=colnames(dat_4), verbose=TRUE)
plot(pc_fit, main="PC Output")

# Evaluate the estimated graph with the true graph
shd(rDAG , pc_fit)
compareGraphs(pc_fit@graph, rDAG)


#----------------------------------------------------
# Comparing PC and LiNGAM on linear non-Gaussian data
# Initialize the number of variables 
#---------------------------------------------------
x1 <- runif(1000)
x2 <- 0.8*x1 + runif(1000)
dat_5 <- cbind(x1, x2)

# Visualize the distribution
plot(dat_5[, 1], dat_5[, 2])

# Now let's run the PC and LiNGAM algorithms on the data
lingam_fit <- LINGAM(dat_5, verbose=TRUE)
show(lingam_fit)

pc_fit <- pc(suffStat=list(C=cor(dat_5), n=nrow(dat_5)), indepTest=gaussCItest, alpha=0.05, p=ncol(dat_5))
plot(pc_fit)