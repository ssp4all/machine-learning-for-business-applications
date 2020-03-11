"""
Author: Suraj Sunil Pawar
Student ID: 200315997
Unity ID: spawar2
"""

import math
import sys

import numpy as np
import pandas as pd
from igraph import Graph, VertexClustering


class Solution:
    def __init__(self, a):
        """Initialize the data """
        self.alpha = a * 10
        self.read_data()
    
    def read_data(self):
        """Get the data"""
        self.g = Graph.Read_Edgelist("data/fb_caltech_small_edgelist.txt")
        self.df = pd.read_csv("data/fb_caltech_small_attrlist.csv")
        self.preprocessing()

    def preprocessing(self):
        """Build the data """
        self.g.es['weight'] = 2
        headers = list(self.df)
        # print(headers)
        # print(list(self.df), x)
        for i1 in self.df.iterrows():
            for ind2, i2 in enumerate(i1):
                self.g.vs[i1[0]][headers[ind2]] = i1[1][ind2]
            # # print(ind, item)
            # break
        # print(list(self.g.vs))

        



if __name__ == "__main__":
    print("Attributed Graph Community \n\
            Detection/Market Segmentation")
    ip = sys.argv
    if len(ip) < 2:
        print("Enter alpha value!")
        sys.exit(0)
    alpha = float(ip[1])
    s = Solution(alpha)
