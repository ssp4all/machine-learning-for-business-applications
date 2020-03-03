"""
Author: Suraj Sunil Pawar
Student ID: 200315997
"""
import sys
import pandas as pd
import numpy as np
from collections import defaultdict
from copy import deepcopy
import random


class Adwords:
    """
    AdWords Placement Problem via 
    Online Bipartite Graph Matching
    """

    def __init__(self):
        # Read dataset
        random.seed(0)
        self.df = pd.read_csv("bidder_dataset.csv")
        self.queries = pd.read_csv(
            "queries.txt", header=None, names=["queries"])
        # print(self.queries.values)
        adv_bidder = self.df.loc[(self.df.Budget > 0), [
            'Budget', 'Advertiser']]
        # print(adv_bidder)

        self.map = defaultdict(int)  # map of adv and budget
        self.total_budget = 0
        for i in adv_bidder.values:
            b, adv = i
            self.map[int(adv)] += b
            self.total_budget += b          #optimal revenue
        # print(map, total_budget)

        # Now map queries to bid
        self.query_map = defaultdict(list)
        for item in self.queries.values:
            # print(item)
            query = item[0]
            if query in self.query_map:
                continue
            self.query_map[query] = self.df.loc[(self.df.Keyword == query)].\
                sort_values(by="Bid Value", ascending=0).values
        # print(self.query_map)

    def greedy(self):
        """ Calculate greedy revenue """
        greedy_revenue = 0
        # temp_budget = deepcopy(self.map)
        i = 0
        while i < 100:
            random.shuffle(self.queries.values)
            temp_budget = deepcopy(self.map)
            for _, j in enumerate(self.queries.values):
                flag = 0
                for _, item in enumerate(self.query_map[j[0]]):
                    # print(item)
                    adv, _, bid, _ = item
                    if temp_budget[adv] - bid > 0:
                        temp_budget[adv] -= bid
                        greedy_revenue += bid
                        if flag + 1:
                            break
                # print(j)
            i += 1
        rev = greedy_revenue / 100
        return rev

    def msvv(self):
        """MVCC algo implementation"""
        msvv_rev = 0
        i = 0

        def helper(bid, adv):
            """Calculate MSVV value"""
            return bid * (1 - np.exp(100 / temp_budget[adv]))

        while i < 100:
            random.shuffle(self.queries.values)
            temp_budget = deepcopy(self.map)
            for _, j in (enumerate(self.queries.values)):
                bid_org, adv_org, msvv = 0, 0, 0
                for _, item in enumerate(self.query_map[j[0]]):
                    adv, _, bid, _ = item
                    new_val = helper(bid, adv)
                    if new_val < msvv:
                        if 100 + bid <= temp_budget[adv]:
                            bid_org, adv_org, msvv = bid, adv, new_val
                msvv_rev += bid
            i += 1
        return msvv_rev / 100

    def balance(self):
        """BALANCE ALGO """
        i = 0
        balance_rev = 0
        while i < 100:
            random.shuffle(self.queries.values)
            temp_budget = deepcopy(self.map)
            for _, j in enumerate(self.queries.values):
                bal, org_bid, advert = 0, 0, 0
                for _, j in self.query_map[j[0]]:
                    adv, _, bid, _ = j
                    if bal < temp_budget[adv]:
                        if temp_budget[adv] >= bid:
                            bal, org_bid = temp_budget[adv], bid
                    temp_budget[adv] -= org_bid
                    balance_rev += org_bid
            i += 1
        return balance_rev / 100


if __name__ == "__main__":
    print("\nAdWords Placement Problem via " + "\n \
    " + "Online Bipartite Graph Matching\n")

    arguments = sys.argv
    if len(arguments) > 1:
        algo_type = arguments[1]
        ad = Adwords()
    else:
        print("\nPlease enter the type of algorithm!\n")
        sys.exit(0)

    # print(algo_type)
    rev = 0
    if algo_type == "greedy":
        print("Greedy")
        rev = ad.greedy()

    elif algo_type == "msvv":
        print("MSVV")
        rev = ad.msvv()

    elif algo_type == "balance":
        print("Balance")
        rev = ad.msvv()
    else:
        print("Invalid Input!\nEnter one of below options")
        print("1. Greedy")
        print("2. MSVV")
        print("3. Balance")
        sys.exit(0)
    print(f"{algo_type}'s revenue' : {round(rev, 3)}")
    print(f"Competitive ratio:{round(rev / ad.total_budget, 3)}")
