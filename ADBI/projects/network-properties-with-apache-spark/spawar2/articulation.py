import sys
import time
import networkx as nx
from pyspark import SparkContext
from pyspark.sql import SQLContext
from pyspark.sql import functions
from graphframes import *
from copy import deepcopy

sc=SparkContext("local", "degree.py")
sqlContext = SQLContext(sc)

def articulations(g, usegraphframe=False):
	# Get the starting count of connected components
	# YOUR CODE HERE
	
	base_count = g.connectedComponents().select('component').distinct().count()
    
	# Default version sparkifies the connected components process 
	# and serializes node iteration.
	if usegraphframe:
		# Get vertex list for serial iteration
		# YOUR CODE HERE
		vertex_list = g.vertices(lambda x:x.id).collect()
		res = []
		# For each vertex, generate a new graphframe missing that vertex
		# and calculate connected component count. Then append count to
		# the output
		# YOUR CODE HERE
		for v in vertex_list:
			g2 = GraphFrame(g.vertices.filter('id!="'+v+'"'), g.edges.filter('src !="'+v+'"').filter('dst!="'+v+'"'))
			count = g2.connectedComponents().select('component').distinct().count()
			res.append((v,1 if count>baseCount else 0))
		return sqlContext.createDataFrame(sc.parallelize(res),['id','articulation'])
        
		# For each vertex, generate a new graphframe missing that vertex
		# and calculate connected component count. Then append count to
		# the output
		# YOUR CODE HERE
		
	# Non-default version sparkifies node iteration and uses networkx 
	# for connected components count.
	else:
        # YOUR CODE HERE
		graph_x = nx.Graph()
        graph_x.add_nodes_from(g.vertices.map(lambda x:x.id).collect())
        graph_x.add_edges_from(g.edges.map(lambda x:(x.src,x.dst)).collect())
        
        def components(n):
            graph_copy = deepcopy(graph_copyraph_x)
            graph_copy.remove_node(n)
            return nx.number_connected_components(graph_copy)
        
        return sqlContext.createDataFrame(graph_copy.vertices.map(lambda x:(x.id,1 if components(x.id) > baseCount else 0)),['id','articulation'])
		

filename = sys.argv[1]
lines = sc.textFile(filename)

pairs = lines.map(lambda s: s.split(","))
e = sqlContext.createDataFrame(pairs,['src','dst'])
e = e.unionAll(e.selectExpr('src as dst','dst as src')).distinct() # Ensure undirectedness 	

# Extract all endpoints from input file and make a single column frame.
v = e.selectExpr('src as id').unionAll(e.selectExpr('dst as id')).distinct()	

# Create graphframe from the vertices and edges.
g = GraphFrame(v,e)

#Runtime approximately 5 minutes
print("---------------------------")
print("Processing graph using Spark iteration over nodes and serial (networkx) connectedness calculations")
init = time.time()
df = articulations(g, False)
print("Execution time: %s seconds" % (time.time() - init))
print("Articulation points:")
df.filter('articulation = 1').show(truncate=False)
print("---------------------------")

#Runtime for below is more than 2 hours
print("Processing graph using serial iteration over nodes and GraphFrame connectedness calculations")
init = time.time()
df = articulations(g, True)
print("Execution time: %s seconds" % (time.time() - init))
print("Articulation points:")
df.filter('articulation = 1').show(truncate=False)
