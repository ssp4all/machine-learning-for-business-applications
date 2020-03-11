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
        self.phase1()
    
    def phase1(self):
        """Phase 1 of SAC 1"""
        def get_dq_newman(vertex=None, community=None):
            old = self.g.modularity(total_comm, weights="weight")
            temp = total_comm[vertex]
            total_comm[vertex] = community
            new = self.g.modularity(total_comm, weights="weight")
            total_comm[vertex] = temp
            res = new - old
            return res
        
        def dq_header(vertex=c1, community=c2, similarity_mat=simi):
            sum_, count = 0, 0
            i = 0
            n = len(self.g.vs)
            while i < n:
                total_comm[i] = community
                sum_ += similarity_mat[vertex][i]
                count += 1
                i += 1
            temp = count // n
            return sum_ // temp


        print("Phase - 1")

        n = len(self.g.vs)  #n = 324
        total_comm = list(range(n))

        for _ in range(15):
        # og_mod = self.g.as_undirected().modularity(total_comm)
        # print(og_mod)
            for c1 in total_comm:
                comm_vertex = total_comm[c1]
                maxi = float('inf')
                maxi_community = None
                for c2 in range(n):
                    if c1 == c2:    continue
                    dq = self.alpha * get_dq_newman(c1, c2) *\
                        (1 - self.alpha) * dq_header(c1, c2, simi)
                if maxi > 0:
                    total_comm[c1] = dq
                    maxi_community = c2
                else:
                    break
        return total_comm


if __name__ == "__main__":
    print("Attributed Graph Community \n\
            Detection/Market Segmentation")
    ip = sys.argv
    if len(ip) < 2:
        print("Enter alpha value!")
        sys.exit(0)
    alpha = float(ip[1])
    s = Solution(alpha)
