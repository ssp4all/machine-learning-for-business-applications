"""
Author: Suraj Sunil Pawar
Unity ID: spawar2
S-ID: 200315997
"""

import statsmodels.formula.api as smf
import sklearn.metrics as sm
import pandas as pd
import numpy as np
import math
import sys


# The path to the data folder should be given as input
if len(sys.argv) != 2:
    print('bitcoin.py <path to data folder>')
    sys.exit(1)
data_path = sys.argv[1]


# Reading the vectors from the given csv files
train1_90 = pd.read_csv(data_path+'/train1_90.csv')
train1_180 = pd.read_csv(data_path+'/train1_180.csv')
train1_360 = pd.read_csv(data_path+'/train1_360.csv')

train2_90 = pd.read_csv(data_path+'/train2_90.csv')
train2_180 = pd.read_csv(data_path+'/train2_180.csv')
train2_360 = pd.read_csv(data_path+'/train2_360.csv')

test_90 = pd.read_csv(data_path+'/test_90.csv')
test_180 = pd.read_csv(data_path+'/test_180.csv')
test_360 = pd.read_csv(data_path+'/test_360.csv')


def computeDelta(wt, X, Xi):
    """
    This function computes equation 6 of the paper, but with the euclidean distance 
    replaced by the similarity function given in Equation 9.

    Parameters
    ----------
    wt : int
        This is the constant c at the top of the right column on page 4.
    X : A row of Panda Dataframe
        Corresponds to (x, y) in Equation 6.
    Xi : Panda Dataframe
        Corresponds to a dataframe of (xi, yi) in Equation 6.

    Returns
    -------
    float
        The output of equation 6, a prediction of the average price change.
    """
    # YOUR CODE GOES HERE
    weight = wt
    n = len(X) - 1
    mean_along_x = np.mean(X[:-1])
    std_x = np.std(X[:-1])
    # ratio = 0
    temp1 = 0
    temp2 = 0
    
    
    for _, item in Xi.iterrows() :
        yi = item[-1]
        new_std = np.std(item[:-1])
        new_mean = np.mean(item[:-1])
        temp = 0

        for i in range(n):
            a =  (X[i] - mean_along_x) 
            b =  (item[i] - new_mean)
            temp += a * b
        sim = temp // (n * std_x * new_std)
        temp2 += math.exp(weight * sim)
        # temp2 += fac
        temp1 += yi * temp2
        
    return temp1 / temp2



# Perform the Bayesian Regression to predict the average price change for each dataset of train2 using train1 as input. 
# These will be used to estimate the coefficients (w0, w1, w2, and w3) in equation 8.
weight = 2  # This constant was not specified in the paper, but we will use 2.
trainDeltaP90 = np.empty(0)
trainDeltaP180 = np.empty(0)
trainDeltaP360 = np.empty(0)
for i in range(0,len(train1_90.index)) :
  trainDeltaP90 = np.append(trainDeltaP90, computeDelta(weight,train2_90.iloc[i],train1_90))
for i in range(0,len(train1_180.index)) :
  trainDeltaP180 = np.append(trainDeltaP180, computeDelta(weight,train2_180.iloc[i],train1_180))
for i in range(0,len(train1_360.index)) :
  trainDeltaP360 = np.append(trainDeltaP360, computeDelta(weight,train2_360.iloc[i],train1_360))


# Actual deltaP values for the train2 data.
trainDeltaP = np.asarray(train2_360[['Yi']])
trainDeltaP = np.reshape(trainDeltaP, -1)


# Combine all the training data
d = {
        'deltaP': trainDeltaP,
        'deltaP90': trainDeltaP90,
        'deltaP180': trainDeltaP180,
        'deltaP360': trainDeltaP360
    }
trainData = pd.DataFrame(d)


# Feed the data: [deltaP, deltaP90, deltaP180, deltaP360] to train the linear model. 
# Use the statsmodels ols function.
# Use the variable name model for your fitted model
# YOUR CODE HERE
model_temp = smf.ols(formula='deltaP ~ deltaP90 + deltaP360 + deltaP180', data=trainData)
model = model_temp.fit()

# Print the weights from the model
print(model.params)

# Perform the Bayesian Regression to predict the average price change for each dataset of test using train1 as input.
# This should be similar to above where it was computed for train2.
# YOUR CODE HERE

weigh = 2  # This constant was not specified in the paper, but we will use 2.

test_delta_90 = np.empty(0)
test_delta_180 = np.empty(0)
test_delta_360 = np.empty(0)

def helper():
    for _, i in enumerate(train1_90):
        test_delta_90 = np.append(test_delta_90, computeDelta(weigh,test_90.iloc[i],train1_90))
    for _, i in enumerate(train1_180):
        test_delta_180 = np.append(test_delta_180, computeDelta(weigh,test_180.iloc[i],train1_180))
    for _, i in enumerate(train1_360):
        test_delta_360 = np.append(test_delta_360, computeDelta(weigh,test_360.iloc[i],train1_360))

helper()
# Actual deltaP values for test data.
# YOUR CODE HERE (use the right variable names so the below code works)

testDeltaP = np.asarray(test_360[['Yi']])
testDeltaP = np.reshape(testDeltaP, -1)


# Combine all the test data
d = {
        'deltaP': testDeltaP,
        'deltaP90': test_delta_90,
        'deltaP180': test_delta_180,
        'deltaP360': test_delta_360
    }

testData = pd.DataFrame(d)


# Predict price variation on the test data set.
result = model.predict(testData)
compare = { 'Actual': testDeltaP,
            'Predicted': result }
compareDF = pd.DataFrame(compare)


# Compute the MSE and print the result
# HINT: consider using the sm.mean_squared_error function
MSE = 0.0
# YOUR CODE HERE
MSE = sm.mean_squared_error(compareDF.Actual, compareDF.Predicted)
print("The MSE is %f" % (MSE))
