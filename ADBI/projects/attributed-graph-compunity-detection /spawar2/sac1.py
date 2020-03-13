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
        self.g = self.g.as_undirected()
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
        self.sac1()

    def sac1(self):
        """Phase 1 of SAC 1"""
        n = len(self.g.vs)

        similarity_mat = [[0 for _ in range(n)] for _ in range(n)]

        def computer_similarity():
            for i in range(n):
                for j in range(n):
                    if similarity_mat[i][j] != None:
                        continue

                    def similarity(a, b):
                        ip1, ip2 = list(a.attributes().values),\
                            list(b.attributes().values)
                        x, y, z = 0, 0, 0
                        for i, j in enumerate(ip1):
                            x += math.pow(ip1[i], 2)
                            y += math.pow(ip2[i], 2)
                            z += (x + y)

                        return z / (math.sqrt(x * y))
                    similarity_mat[i][j] = similarity(self.g.vs[i],
                                                      self.g.vs[j])
                    similarity_mat[j][i] = similarity_mat[i][j]

        computer_similarity()

        def cluster(vertex=None, community=None):
            old = self.g.modularity(total_comm, weights="weight")
            temp = total_comm[vertex]
            total_comm[vertex] = community
            new = self.g.modularity(total_comm, weights="weight")
            total_comm[vertex] = temp
            res = new - old
            """Second part """
            sum_, count = 0, 0
            i = 0

            while i < n:
                total_comm[i] = community
                sum_ += similarity_mat[vertex][i]
                count += 1
                i += 1
            temp = count // n
            ans = sum_ // temp
            return [res, ans]

        print("Writing in the file...")

        n = len(self.g.vs)  # n = 324
        total_comm = list(range(n))

        for _ in range(15):
            # og_mod = self.g.as_undirected().modularity(total_comm)
            # print(og_mod)
            for c1 in total_comm:
                comm_vertex = total_comm[c1]
                maxi = float('inf')
                maxi_community = None

                for c2 in range(n):
                    if c1 == c2:
                        continue
                    op1, op2 = cluster(c1, c2)
                    dq = self.alpha * op1 *\
                        (1 - self.alpha) * op2
                if maxi > 0:
                    total_comm[c1] = dq
                    maxi_community = c2
                else:
                    break
        return total_comm

        self.write_to_file()

    def write_to_file(self):
        op = self.helper()
        file = open(f"Communities_{self.alpha}.txt", mode="w+")
        for f in op:
            file.write(str(f))
        file.close()


if __name__ == "__main__":
    print("Attributed Graph Community \n\
            Detection/Market Segmentation")
    ip = sys.argv
    if len(ip) < 2:
        print("Enter alpha value!")
        sys.exit(0)
    alpha = float(ip[1])
    s = Solution(alpha)
